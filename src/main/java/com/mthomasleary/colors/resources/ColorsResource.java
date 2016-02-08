package com.mthomasleary.colors.resources;

import com.mthomasleary.colors.Color;
import com.mthomasleary.colors.managers.CharacterManager;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/colors")
public class ColorsResource {
    @Path("/")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response receiveGET(){
        return Response.ok().entity("Please send a POST request").build();
    }

    /**
     * For a given input: <br/>
     * 1 - record usage <br/>
     * 2 - get colors <br/>
     * 3 - convert to JSON and return
     *
     * @param input
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getColorsForCharacters(String input) throws IOException, SQLException, ClassNotFoundException {
        if(input == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List<Color> colors = new ArrayList<Color>();
        for(char ch : input.toCharArray()){
            try{
                CharacterManager.writeChar(ch);
            } catch (SQLException | ClassNotFoundException e) {
                return Response.serverError().build();
            }

            colors.add(generateColorFromChar(ch));
        }

        String data = listToData(colors);
        return Response.ok().entity(data).build();
    }

    /**
     * For given character ch, generate a Color object.<br/>
     * I just picked some values,
     * so play around with this and see what colors you get!
     * @param ch
     * @return
     */
    protected Color generateColorFromChar(final char ch){
        Color ret = new Color();
        ret.setRed(((int) ch * 7) % 255);
        ret.setGreen(((int) ch * 3) % 255);
        ret.setBlue(((int) ch * 42) % 255);
        ret.setCharacter(ch);
        return ret;
    }

    protected String listToData(final List list) throws IOException {
        final OutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out, list);

        final String data = out.toString();
        return data;
    }
}

package com.mthomasleary.colors.resources;

import com.mthomasleary.colors.managers.CharacterManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("characters")
public class CharactersResource {
    /**
     * Read the usage count for ch
     * @param ch
     * @return
     */
    @Path("/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUsageCount(String ch){
        System.out.println("Getting count for: " + ch);
        int ret = 0;
        try {
            ret = CharacterManager.readCharCount(ch);
        } catch (SQLException | ClassNotFoundException e) {
            return Response.serverError().build();
        }
        return Response.ok().entity(ret).build();
    }
}

package com.mthomasleary.colors;

import com.mthomasleary.colors.managers.CharacterManager;
import com.mthomasleary.colors.resources.CharactersResource;
import com.mthomasleary.colors.resources.ColorsResource;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Path("/")
public class CharactersColorsApplication extends Application {
    public CharactersColorsApplication() throws SQLException, ClassNotFoundException {
        CharacterManager.lazyInitTables();
    }

    /**
     * defines our API endpoints
     * @return
     */
    @Override
    public Set<Class<?>> getClasses(){
        final Set ret = new HashSet();
        ret.add(ColorsResource.class);
        ret.add(CharactersResource.class);
        return ret;
    }
}

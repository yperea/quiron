package co.net.quiron.service;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.domain.location.Country;
import co.net.quiron.domain.location.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/states")
public class States {

    @GET
    @Produces("application/json")
    @Path("/")
    public Response getStates() throws JsonProcessingException {

        //List<Country> countries = ManagerFactory.getManager(Country.class).getList();
        List<State> states = ManagerFactory.getManager(State.class).getList();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(states);

        return Response.status(200)
                .entity(json)
                .build();
    }
}

package co.net.quiron.service;

import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.domain.location.Country;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/states")
public class State {

    @GET
    @Produces("application/json")
    @Path("/{code}")
    public Response getStates(@PathParam("code") String stateCode) {

        String output = "hello";

        //String id = uriInfo

        List<Country> countries = ManagerFactory.getManager(Country.class).getList();

        GenericEntity<List<Country>> list = new GenericEntity<List<Country>>(countries) {};

        return Response.status(200)
                .entity(list)
                .build();

    }
}

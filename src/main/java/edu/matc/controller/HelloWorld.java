package edu.matc.controller;

import edu.matc.entity.UsertableEntity;
import edu.matc.persistance.UserHibernateDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.*;


@Path("/user")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Path("")
    public Response getMessage() {

        List<UsertableEntity> output = null;

            UserHibernateDao user = new UserHibernateDao();

            output = user.getAllUser();

        return Response.status(200).entity(output).build();
    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Path("{param}")
    public Response getMessage(@PathParam("param") int id) {

        List<UsertableEntity> output = null;

        UserHibernateDao user = new UserHibernateDao();

        output = user.getUserById(id);

        return Response.status(200).entity(output).build();
    }
}
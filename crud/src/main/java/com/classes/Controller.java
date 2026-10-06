package com.classes;

import java.util.List;
import com.classes.model.Poster;
import com.classes.model.User;
import com.classes.Service;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
@jakarta.ws.rs.Produces (MediaType.APPLICATION_JSON)
public class Controller {

    @Inject
    private Service service;

    @GET
    public List<User> getUser() {
        return service.getAll();
    }

    //-------------- REGISTER ---------------
    @POST
    public String user(User user) {
        service.registerUser(user);
        return "Account Created!";
    }

    @Path("/{id}")
    @GET
    public User getUser(@PathParam("id") int id) {
        return service.getUser(id);
    }

    //------------------------ POSTER --------------------------
    @Path("/poster")
    @POST
    public String Poster(Poster poster) {
        service.createPoster(poster);
        return "Poster Added!";
    }

    @Path("/poster/{id}")
    @GET
    public Poster getPoster(@PathParam("id") int id) {
        return service.getPoster(id);
    }

    @Path("/poster")
    @GET
    public List<Poster> getPoster() {
        return service.getAllPosters();
    }

    
    @Path("/{id}")
    @DELETE
    public String deleteUser(@PathParam("id") int id) {
        service.deleteUser(id);
        return "User Deleted!";
    }

    @Path("/{id}")
    @PUT
    public String changeUser(@PathParam("id") int id, User user) {
        service.changeUser(id, user);
        return "User Updated!";
    } 

}



  

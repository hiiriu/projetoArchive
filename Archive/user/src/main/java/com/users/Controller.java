package com.users;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path ("/user")
public class Controller {

    @Inject 
    private Service service;

    @POST 
    public String user(user user) {
        service.registerUser(user);
        return "Account Created!";
    }

    @GET
    public List<user> getUser() {
        return service.getAll();
    }

    @Path("/{id}")
    @GET
    public user getUser(@PathParam("id") int id){
        return service.getUser(id);
    }

    @Path("/{id}")
    @DELETE
    public String deleteUser(@PathParam("id") int id){
        service.deleteUser(id);
        return "User Deleted!";
    }

    @Path("/{id}")
    @PUT
    public String changeUser(@PathParam("id") int id, user user){
        service.changeUser(id, user);
        return "User Updated!";
    }


}

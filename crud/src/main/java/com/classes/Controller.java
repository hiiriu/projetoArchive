package com.classes;

import java.util.List;
import java.util.Map;

import com.classes.model.Poster;
import com.classes.model.User;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Controller {

    @Inject
    private Service service;

    // -------------- REGISTER ---------------
    @POST
    public Response user(User user) {
        boolean valid = service.registerUser(user);

        if (!valid) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Username already exists")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        return Response.status(Response.Status.CREATED)
                .entity("Account Created!")
                .type(MediaType.TEXT_PLAIN)
                .build();

    }

    @GET
    public List<User> getUser() {
        return service.getAll();
    }

    // --------- LOGIN ----------
    @POST
    @Path("/login")
    public Response login(
            Map<String, String> credentials,
            @Context HttpServletRequest request) {

        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = service.login(username, password);

        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "Invalid username or password"))
                    .build();
        }

        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("userId", user.getId());

        return Response.ok(
                Map.of("message", "Login successful")).build();
    }

    // verificar sessao e guardar informação do user
    @GET
    @Path("/me")
    public Response getCurrentUser(
            @Context HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "Not logged in"))
                    .build();
        }

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "Not logged in"))
                    .build();
        }

        User user = service.getUser(userId);

        if (user == null) {
            session.invalidate();

            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "User not found"))
                    .build();
        }

        return Response.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "displayname", user.getDisplayname())).build();
    }

    // terminar sessao
    @POST
    @Path("/logout")
    public Response logout(
            @Context HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return Response.noContent().build();
    }
}
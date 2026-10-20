package com.classes;

import com.classes.model.Poster;
import com.classes.model.User;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@WebServlet("/upload-poster")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 5 * 1024 * 1024,
    maxRequestSize = 6 * 1024 * 1024
)
public class PosterServlet extends HttpServlet {

    @Inject
    private Service service;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendError(401, "Please log in first");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        User user = service.getUser(userId);

        if (user == null) {
            session.invalidate();
            response.sendError(401, "User not found");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String location = request.getParameter("location");
        String link = request.getParameter("link");

        Part file = request.getPart("posterImg");

        if (file == null || file.getSize() == 0) {
            response.sendError(400, "Please select an image");
            return;
        }

        String submittedName = file.getSubmittedFileName();

        if (submittedName == null) {
            response.sendError(400, "Invalid filename");
            return;
        }

        String lower = submittedName.toLowerCase(Locale.ROOT);
        String extension;

        if (lower.endsWith(".jpg")) {
            extension = ".jpg";
        } else if (lower.endsWith(".jpeg")) {
            extension = ".jpeg";
        } else if (lower.endsWith(".png")) {
            extension = ".png";
        } else {
            response.sendError(400, "Only JPG, JPEG and PNG are accepted");
            return;
        }

        Poster poster = new Poster();
        poster.title = title;
        poster.description = description;
        poster.location = location;
        poster.link = link;
        poster.setUser(user);

        try (InputStream input = file.getInputStream()) {
            service.createPosterWithImage(
                poster, input, extension, user.getUsername()
            );
        } catch (Exception e) {
            throw new ServletException("Could not save poster", e);
        }

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write("Poster created successfully");
    }
}
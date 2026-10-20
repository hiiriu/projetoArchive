package com.classes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/poster-image/*")
public class PosterImageServlet extends HttpServlet {

    private Path getImageDirectory() {
        String configured = System.getProperty(
            "posters.upload.dir", "posters"
        );

        return Path.of(configured).toAbsolutePath().normalize();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String filename = request.getPathInfo();

        if (filename == null || filename.length() < 2) {
            response.sendError(400, "Missing filename");
            return;
        }

        filename = filename.substring(1);

        if (!filename.matches("[a-zA-Z0-9_-]+\\.(jpg|jpeg|png)")) {
            response.sendError(400, "Invalid filename");
            return;
        }

        Path image = getImageDirectory().resolve(filename).normalize();

        if (!image.startsWith(getImageDirectory())
                || !Files.isRegularFile(image)) {
            response.sendError(404, "Image not found");
            return;
        }

        String lower = filename.toLowerCase(java.util.Locale.ROOT);

        if (lower.endsWith(".png")) {
            response.setContentType("image/png");
        } else if (lower.endsWith(".jpg")
                || lower.endsWith(".jpeg")) {
            response.setContentType("image/jpeg");
        }

        response.setContentLengthLong(Files.size(image));
        Files.copy(image, response.getOutputStream());
    }
}

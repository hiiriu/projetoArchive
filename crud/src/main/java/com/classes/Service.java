package com.classes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.classes.model.Poster;
import com.classes.model.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.nio.file.Path;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class Service {

    @Inject
    Repository repository; // inicialização

    public List<User> getAll() {
        return repository.listarTodos();

    }

    // ---------------- REGISTER ----------------

    public boolean registerUser(User user) {
        if (repository.findByUsername(user.username) != null) {
            System.out.println("username already exists");
            return false;

        } else {
            repository.save(user);
            return true;
        }
    }

    public User getUser(int id) {
        return repository.findById(id);
    }

    public User getUsername(String username) {
        return repository.findByUsername(username);
    }

    // ---------------- LOGIN -----------------

    public User login(String username, String password) {

        User user = repository.findByUsername(username);
        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }

    // ------------------------------ POSTER ------------------------------
    // verificar tipo de ficheiro

    public void createPoster(Poster poster) {
        repository.savePoster(poster);
    }

    public Poster getPoster(int id) {
        return repository.findPosterById(id);
    }

    public List<Poster> getAllPosters() {
        return repository.listarTodosPosters();
    }

    @Transactional
    public void createPosterWithImage(
            Poster poster,
            InputStream input,
            String extension,
            String username) throws IOException {

        poster.img = "pending";
        repository.savePoster(poster);

        String safeUsername = username.replaceAll("[^a-zA-Z0-9_-]", "_");

        String filename = safeUsername + "_" + poster.getId() + extension;

        Path directory = getImageDirectory();
        Files.createDirectories(directory);

        Path destination = directory.resolve(filename).normalize();

        if (!destination.getParent().equals(directory)) {
            throw new IOException("Invalid image path");
        }

        try {
            try (OutputStream output = Files.newOutputStream(
                    destination,
                    StandardOpenOption.CREATE_NEW,
                    StandardOpenOption.WRITE)) {

                byte[] buffer = new byte[8192];
                int count;
                long total = 0;

                while ((count = input.read(buffer)) != -1) {
                    total += count;

                    if (total > 5 * 1024 * 1024) {
                        throw new IOException("Image exceeds 5 MB");
                    }

                    output.write(buffer, 0, count);
                }

                if (total == 0) {
                    throw new IOException("Empty image");
                }
            }

            poster.img = filename;
            repository.savePosterUpdate(poster);

        } catch (IOException | RuntimeException e) {
            Files.deleteIfExists(destination);
            throw e;
        }
    }

    private Path getImageDirectory() {
    String configured = System.getProperty(
            "posters.upload.dir",
            "posters"
    );

    return Path.of(configured).toAbsolutePath().normalize();
}
}

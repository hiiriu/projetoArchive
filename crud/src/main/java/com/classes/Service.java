package com.classes;

import java.util.List;

import com.classes.model.Poster;
import com.classes.model.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Service {

    @Inject
    Repository repository; // inicialização

    public List<User> getAll() {
        return repository.listarTodos();
    }

    // ---------------- REGISTER ----------------

    public void registerUser(User user) {

        repository.save(user);
        // verificar que passwords correspondem
    }

    public User getUser(int id) {
        return repository.findById(id);
    }

    public User getUsername(String username) {
        return repository.findByUsername(username);
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

    // ---------------- LOGIN -----------------

    public User login(String username, String password) {

        User user = repository.findByUsername(username);

        if (!user.getPassword().equals(password) || user == null) {
            return null;

        }

        return user;
    }

    public void deleteUser(int id) {
        repository.delete(id);
    }

    public void changeUser(int id, User user) {
        repository.change(id, user);
    }
}

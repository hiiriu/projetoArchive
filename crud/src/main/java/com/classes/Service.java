package com.classes;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Service {

    @Inject
    Repository repository; // inicialização

    public List<User> getAll() {
        return repository.listarTodos();
    }

    // ---------------- REGISTER -----------------

    // email valido - user
    // blanks preenchidos - feito para o username

    public void registerUser(User user) {

        repository.save(user);

        // verificar que username n tem espaços
        // verificar que palavrapasse n tem espaços
        // verificar que passwords correspondem
    
    }

    public void deleteUser(int id) {
        repository.delete(id);
    }

    public void changeUser(int id, User user) {
        repository.change(id, user);
    }

    public User getUser(int id) {
        return repository.findById(id);
    }

    // ------------------------------ POSTER ------------------------------
    // verificar tipo de ficheiro
    // tamanho da descrição

    public void createPoster(Poster poster) {
        repository.savePoster(poster);

        // verificar que username n tem espaços
        // verificar que palavrapasse n tem espaços
        // verificar que passwords correspondem
    
    }

    public Poster getPoster(int id) {
        return repository.findPosterById(id);
    }

     public List<Poster> getAllPosters() {
        return repository.listarTodosPosters();
    }
}

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
    // verificar que o user ainda nao existe
    // email valido
    // blanks preenchidos
    // confirmar password
    public void registerUser(User user) {

        repository.save(user);
    }

    public void deleteUser(int id) {
        repository.delete(id);
    }

    public void changeUser(int id, User user) {
        repository.change(id, user);
    }


    public User getUser(int id) {
        return repository.buscar(id);

    }

    // verificar tipo de ficheiro
    // tamanho da descrição

}


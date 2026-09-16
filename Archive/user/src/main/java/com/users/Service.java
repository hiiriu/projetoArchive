package com.users;

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
    // amail valido
    // blanks preenchidos
    // confirmar password
    public void registerUser(User user) {

     /*   for (int i = 0; i < getAllUsers().length; i++) {
            if (getAllUsers(i).getUsername() == user.getUsername()) {

            } else {

                User user = user.getUser(i);
                User currUser = new User();
                currUser.setUsername(user.getUsername());
                currUser.setPassword(user.getPassword());

                getAllUsers().add(currUser);
            }

        } */


        repository.save(user);
    }

    
    public void deleteUser(int id) {
        repository.apagar(id);
    }

    public void changeUser(int id, User user) {
        repository.alterar(id, user);
    }



    public User getUser(int id) {
        return repository.buscar(id);

    }

    // verificar tipo de ficheiro
    // tamanho da descrição

}

package com.users;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Repository {
    @PersistenceContext
    private EntityManager em;

    @Transactional // try catch
    public User save(User user) {
        em.persist(user); // guarda na base de dados a variavel da class user
        return user;
    }

        //apgar personagem
    @Transactional
    public void apagar(int id) {
        //procurar personagem pelo id
        User u = em.find(User.class, id);
        em.remove(u);
    }

    @Transactional
    public void alterar(int id, User user) {
        User u = em.find(User.class, id);
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
    }


    @Transactional
    public User buscar(int id) {
        return em.find(User.class, id);

    }

    public List<User> listarTodos() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}

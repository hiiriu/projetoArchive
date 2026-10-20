package com.classes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.*;
import com.classes.model.Poster;
import com.classes.model.User;

@ApplicationScoped
public class Repository {
    @PersistenceContext(unitName = "myPU")
    private EntityManager em;

    @Transactional // try catch
    public User save(User user) {
        em.persist(user); // guarda na base de dados a variavel da class user
        return user;
    }

    public List<User> listarTodos() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    // Pesquisar User por if --------------------------

    @Transactional
    public User findById(int id) {
        return em.find(User.class, id);
    }

    public User findByUsername(String username) {
        try {
            return em.createQuery(
                    "select u from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // ---------------------- POSTER ------------------------
    @Transactional
    public Poster savePoster(Poster poster) {
        em.persist(poster);
        em.flush();
        return poster;
    }

    @Transactional
    public Poster findPosterById(int id) {
        return em.find(Poster.class, id);
    }

    public List<Poster> listarTodosPosters() {
        return em.createQuery("SELECT p FROM Poster p", Poster.class).getResultList();
    }

    @Transactional
    public Poster savePosterUpdate(Poster poster) {
        return em.merge(poster);
    }
}

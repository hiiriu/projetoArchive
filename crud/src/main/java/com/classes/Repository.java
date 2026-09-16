package com.classes;

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

    // Pesquisar User por if --------------------------

    @Transactional
    public User findById(int id) {
        return em.find(User.class, id);
    }

    public List<User> listarTodos() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    // ------------------------------------ POSTER -----------------------------------------
    @Transactional
    public Poster savePoster(Poster poster) {
        em.persist(poster);
        return poster;
    }

    @Transactional
    public Poster findPosterById(int id) {
        return em.find(Poster.class, id);
    }

    public List<Poster> listarTodosPosters() {
        return em.createQuery("SELECT p FROM Poster p", Poster.class).getResultList();
    }

  
}







/*     // Alteraoes User ----------------------------------
    @Transactional
    public void delete(int id) {
        User u = em.find(User.class, id);
        em.remove(u);
    }
    @Transactional
    public void change(int id, User user) {
        User u = em.find(User.class, id);
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
    }
 */
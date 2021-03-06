package org.example.crud.dao;

import org.example.crud.models.Role;
import org.example.crud.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<User> index() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }


    public User show(int id) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.id = :id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }


    public void save(User user) {
        em.persist(user);
    }


    public void update(int id, User updatedUser) {
        em.merge(updatedUser);
    }


    public void delete(int id) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.id = :id", User.class);
        q.setParameter("id", id);
        em.remove(q.getResultList().stream().findAny().orElse(null));
    }

    @Override
    public User findUserByName(String name) {
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + name + " not found.");
    }


}











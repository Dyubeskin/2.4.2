package org.example.crud.dao;

import org.example.crud.models.Role;
import org.example.crud.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("SELECT u FROM Role u", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        List<Role> roles = em.createQuery("SELECT u FROM Role u", Role.class).getResultList();
        for (Role role : roles) {
            if (role.getRole().equals(name)) {
                return role;
            }
        }
        throw new UsernameNotFoundException("User " + name + " not found.");
    }

}

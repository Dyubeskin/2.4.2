package org.example.crud.service;

import org.example.crud.dao.RoleDAO;
import org.example.crud.models.Role;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements  RoleService {


    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}

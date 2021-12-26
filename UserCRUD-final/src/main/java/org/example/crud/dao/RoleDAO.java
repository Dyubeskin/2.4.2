package org.example.crud.dao;

import org.example.crud.models.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();
    Role getRoleByName(String name);
}

package org.example.crud.service;

import org.example.crud.models.Role;

import java.util.List;


public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String name);
}


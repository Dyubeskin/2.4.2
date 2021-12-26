package org.example.crud.service;

import org.example.crud.models.User;

import java.util.List;

public interface UserService {
    List<User> index();
    User show(int id);
    void save(User user);
    void delete(int id);
    void update(int id, User updatedUser);
    public User findUserByName(String name);
}

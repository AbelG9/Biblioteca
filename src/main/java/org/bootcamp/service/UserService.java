package org.bootcamp.service;

import org.bootcamp.model.User;

import java.util.List;

public interface UserService {
    public void addUser(User almacenUser);
    public List<User> showAllUsers();
    public void editUser(int id);
    public void deleteUser(int id);
}

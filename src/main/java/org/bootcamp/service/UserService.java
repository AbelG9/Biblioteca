package org.bootcamp.service;

import org.bootcamp.model.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public List<User> showAllUsers();
    public void editUser(User user);
    public void deleteUser(int id);
    public User findUserById(int userID);
}

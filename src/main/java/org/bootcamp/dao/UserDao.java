package org.bootcamp.dao;

import org.bootcamp.model.User;

import java.util.List;

public interface UserDao {
    public void addUser(User user);
    public List<User> showAllUsers();
    public void editUser(User user);
    public void deleteUser(int id);
    public User findUserById(int userID);
}

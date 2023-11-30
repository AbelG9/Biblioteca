package org.bootcamp.dao;

import org.bootcamp.model.User;

import java.util.List;

public interface UserDao {
    public void addUser(User almacenUser);
    public List<User> showAllUsers();
    public void editUser(int id);
    public void deleteUser(int id);
}

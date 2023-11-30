package org.bootcamp.service.impl;

import org.bootcamp.dao.UserDao;
import org.bootcamp.model.User;
import org.bootcamp.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> showAllUsers() {
        return userDao.showAllUsers();
    }

    @Override
    public void editUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public User findUserById(int userID) {
        return userDao.findUserById(userID);
    }
}

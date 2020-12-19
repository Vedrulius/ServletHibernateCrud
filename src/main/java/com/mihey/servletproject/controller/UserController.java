package com.mihey.servletproject.controller;

import com.mihey.servletproject.model.User;
import com.mihey.servletproject.repository.UserRepository;
import com.mihey.servletproject.repository.hibernate.UserRepositoryImpl;

import java.util.List;

public class UserController {

    private UserRepository userRepository = new UserRepositoryImpl();

    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public User editUser(User user) {
        return userRepository.update(user);
    }


    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}

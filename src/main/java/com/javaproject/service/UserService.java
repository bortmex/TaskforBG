package com.javaproject.service;

import com.javaproject.model.User;
import com.javaproject.util.exception.NotFoundException;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    User save(User user) throws NoSuchAlgorithmException;

    void delete(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();
}

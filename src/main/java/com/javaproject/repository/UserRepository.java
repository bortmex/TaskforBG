package com.javaproject.repository;

import com.javaproject.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserRepository {
    User save(User user) throws NoSuchAlgorithmException;

    // false if not found
    boolean delete(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}

package com.javaproject.web;

import com.javaproject.model.User;
import com.javaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.javaproject.util.ValidationUtil.checkNew;

@Controller
public class UserRestController {

    @Autowired
    private UserService service;

    public List<User> getAll() {
        return service.getAll();
    }

    public User create(User user) throws NoSuchAlgorithmException {
        checkNew(user);
        return service.save(user);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public User getByMail(String email) {
        return service.getByEmail(email);
    }
}

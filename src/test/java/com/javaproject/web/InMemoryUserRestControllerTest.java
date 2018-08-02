package com.javaproject.web;

import com.javaproject.UserTestData;
import com.javaproject.model.User;
import com.javaproject.repository.UserRepository;
import com.javaproject.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static com.javaproject.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class InMemoryUserRestControllerTest {

    @Autowired
    private UserRestController controller;

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.getAll().forEach(u -> repository.delete(u.getId()));
        repository.save(USER);
        repository.save(USER1);
    }


    @Test
    public void getAll() throws Exception {
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 2);
        Assert.assertEquals(users, USERS);
    }

    @Test
    public void delete() throws Exception {
        controller.delete(UserTestData.USER1_ID);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), USER);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }

    @Test
    public void create() throws Exception {
        controller.create(USER_NEW);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 3);
        Assert.assertEquals(users, USERS_WITH_NEW_USER);
    }

    @Test
    public void getByMail() throws Exception {
        User user = controller.getByMail("test@gmail.com");
        Assert.assertEquals(user, USER);
    }

}
package com.javaproject.service;

import com.javaproject.model.User;
import com.javaproject.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.javaproject.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Before
    public void setUp() throws Exception {
        service.getAll().forEach(u -> service.delete(u.getId()));
        service.save(USER);
        service.save(USER1);
    }

    @Test
    public void testSave() throws Exception {
        service.save(USER_NEW);
        MATCHER.assertCollectionEquals(USERS_WITH_NEW_USER, service.getAll());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(USER1), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("test@gmail.com");
        MATCHER.assertEquals(USER, user);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<User> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(USER, USER1), all);
    }
}
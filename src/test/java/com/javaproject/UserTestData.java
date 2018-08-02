package com.javaproject;

import com.javaproject.matcher.ModelMatcher;
import com.javaproject.model.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.javaproject.model.User.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;
    public static final int USER_NEW_ID = START_SEQ + 2;

    public static final User USER = new User(USER_ID, "User", "UserFam", LocalDate.now(), "test@gmail.com", "test");
    public static final User USER1 = new User(USER1_ID, "User12", "UserFam12", LocalDate.now(), "test12@gmail.com", "test12");
    public static final User USER_NEW = new User(null, "User_new", "User_Fam_new", LocalDate.now(), "test13@gmail.com", "test13");

    public static final List<User> USERS = Arrays.asList(USER, USER1);
    public static final List<User> USERS_WITH_NEW_USER = Arrays.asList(USER, USER1, USER_NEW);

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getBirthday(), actual.getBirthday())
                            && Objects.equals(expected.getSurname(), actual.getSurname())
                    )
    );
}
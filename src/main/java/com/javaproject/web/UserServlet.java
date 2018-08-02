package com.javaproject.web;

import com.javaproject.model.User;
import com.javaproject.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class UserServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;

    @Autowired
    private UserRestController userController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        userController = springContext.getBean(UserRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            LocalDate localDate = null;
            if(name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()){
                request.getRequestDispatcher("user_bad_input_parametr.jsp").forward(request, response);
                return;
            }
            try {
                localDate = LocalDate.parse(request.getParameter("dateBirthday"));
            }catch (DateTimeParseException e){
                request.getRequestDispatcher("user_bad_input_parametr.jsp").forward(request, response);
                return;
            }
            final User user = new User(  name,surname,localDate,email,password);
            try {
                userController.create(user);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("user_create_successfully.jsp").forward(request, response);
        }
        if ("search".equals(action)) {
            String emailsearch = request.getParameter("email_search");

            try {
                User user = userController.getByMail(emailsearch);

                request.setAttribute("result", true);
                request.setAttribute("user_name", user.getName());
                request.setAttribute("user_surname", user.getSurname());
                request.setAttribute("user_birthday", user.getBirthday());
                request.setAttribute("user_email", user.getEmail());
            }catch (NotFoundException e){
                request.setAttribute("result", false);
            }
            request.getRequestDispatcher("user_search_result.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("users", userController.getAll());
            request.getRequestDispatcher("/users.jsp").forward(request, response);

        }
        if ("delete".equals(action)) {
            int id = getId(request);
            userController.delete(id);
            response.sendRedirect("users");
        }
        if ("create".equals(action)) {
            final User user = new User();
            request.setAttribute("user", user);
            request.getRequestDispatcher("user_add.jsp").forward(request, response);
        }
        if ("search".equals(action)) {
            request.getRequestDispatcher("user_search.jsp").forward(request, response);
        }
    }

        private int getId(HttpServletRequest request) {
            String paramId = Objects.requireNonNull(request.getParameter("id"));
            return Integer.valueOf(paramId);
        }
}

package com.ss13.servlets;

import com.ss13.dao.*;
import com.ss13.pojo.User;
import com.ss13.utils.Cookies;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/send_register_form")
public class RegistrationServlet extends HttpServlet{
    private static Logger log = Logger.getLogger(LoginServlet.class);
    private static UserDAO userDao = DAOFactory.getDAOFactory(1).getUserDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        log.info("Starting registering user");

        User newUser = new User();
        String email = request.getParameter("reg_email");
        String password = request.getParameter("reg_password");
        String password_check = request.getParameter("reg_password_check");
        String username = request.getParameter("reg_username");
        boolean remember = "on".equals(request.getParameter("remember_me"));
        HttpSession session = request.getSession();

        if (!email.matches("^[A-Za-z0-9]+@[a-z0-9]+(?:.[a-z]+[a-z]*)+$")) {
            request.setAttribute("error", "Email must be valid.");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        if (!username.matches("^[A-Za-z0-9_]{4,16}$")) {
            request.setAttribute("error", "Username must be valid.");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(password_check)) {
            request.setAttribute("error", "Typed passwords does not match.");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        newUser.setEmail(email);
        newUser.setUsername(username);
        try {
            newUser.setPassword(password, false);
        } catch (NoSuchAlgorithmException e) {
            log.info("Failed to create password: No such algorithm");
            e.printStackTrace();
        }

        log.info("Saving user " + username + " to database");
        try {
            userDao.create(newUser);
            newUser = userDao.read(username);
            session.setAttribute("user", newUser);
            request.setAttribute("error", null);
            log.info("User " + username + " saved");
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                request.setAttribute("error", "Login or email already taken");
            }
            log.info("Saving failed");
            e.printStackTrace();
            session.setAttribute("user", null);
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }

        try {
            if (remember) {
                String randomUUID = UUID.randomUUID().toString();
                DAOFactory.getDAOFactory(1).getUserDAO().setUUID(newUser.getUsername(), randomUUID);
                log.info("For user " + username + " uuid is stored to db.");
                Cookies.addCookie(request, response, Cookies.COOKIE_NAME, randomUUID, Cookies.COOKIE_AGE);
                log.info("For user " + username + " uuid cookie is added to the forwarded response.");
            } else {
                DAOFactory.getDAOFactory(1).getUserDAO().deleteUUID(newUser.getUsername());
                log.info("For user " + username + " uuid is cleared from the db");
                Cookies.removeCookie(request, response, Cookies.COOKIE_NAME);
                log.info("For user " + username + " uuid cookie is removed i.e. set to 0 age in forwarded response");
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            log.error(e.getSQLState());
            log.error(e.getLocalizedMessage());
            request.setAttribute("error", "Remember me function is failed. Please try again.");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
    }
}

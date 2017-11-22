package com.ss13.servlets;

import com.ss13.dao.*;
import com.ss13.pojo.User;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
        String email = request.getParameter("reg_username");
        String passwrod = request.getParameter("reg_password");
        String username = request.getParameter("reg_username");
        HttpSession session = request.getSession();

        newUser.setEmail(email);
        newUser.setUsername(username);
        try {
            newUser.setPassword(passwrod, false);
        } catch (NoSuchAlgorithmException e) {
            log.info("Failed to create password: No such algorithm");
            e.printStackTrace();
        }

        log.info("Saving user " + username + " to database");
        try {
            userDao.create(newUser);
            newUser = userDao.read(username);
            request.setAttribute("user", newUser);
            session.setAttribute("user", newUser);
        } catch (Exception e) {
            log.info("Saving failed");
            e.printStackTrace();
            request.setAttribute("user", null);
            session.setAttribute("user", null);
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
    }
}

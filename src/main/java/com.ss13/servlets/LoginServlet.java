package com.ss13.servlets;

import com.ss13.dao.DAOFactory;
import com.ss13.dao.UserDAO;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private static Logger log = Logger.getLogger(LoginServlet.class);
    private static UserDAO userDao = DAOFactory.getDAOFactory(1).getUserDAO();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        User newUser = new User();
        String passwrod = request.getParameter("log_password");
        String username = request.getParameter("log_username");
        HttpSession session = request.getSession();

        if (!username.matches("^[A-Za-z0-9_-]{4,16}$")) {
            request.setAttribute("error", "Username must be valid.");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        log.info("User " + username + " sent the password.");
        boolean remember = "on".equals(request.getParameter("remember_me"));
        log.info("Remember me mode is " + remember + ".");

        try {
            try {
                newUser.setPassword(passwrod, true);
                newUser.setUsername(username);
                request.login(newUser.getUsername(), newUser.getPasswordHash());

                log.info("If user succeed with login for username " + username);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            String randomUUID = UUID.randomUUID().toString();

            try {
                if (remember) {
                    DAOFactory.getDAOFactory(1).getUserDAO().setUUID(newUser.getUsername(), randomUUID);
                    log.info("For user  " + username + " uuid is stored to db.");
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
            }

        } catch (ServletException e) {
            String errorMessage = "Username or password is incorrect, please try again.";
            request.setAttribute("error", errorMessage);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        try {
            newUser = DAOFactory.getDAOFactory(1).getUserDAO().read(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("user", newUser);

        response.sendRedirect(request.getContextPath() + "/main.jsp");
    }
}
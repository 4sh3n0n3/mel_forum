package com.ss13.servlets;

import com.ss13.dao.DAOFactory;
import com.ss13.utils.Cookies;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/prelogin")
public class LoginUUIDServlet extends HttpServlet{
    private static final Logger log = Logger.getLogger(LoginUUIDServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        doProcess(request, response);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uuid = Cookies.getCookieValue(request, Cookies.COOKIE_NAME, log);
        log.info(uuid);
        Object user = request.getSession().getAttribute("user");
        String username;

        if (uuid == null) {
            if (user != null) {
                log.info("The user is defined from session as: " + user.toString());
                uuid = UUID.randomUUID().toString();
                Cookies.addCookie(request, response, Cookies.COOKIE_NAME, uuid, Cookies.COOKIE_AGE);
                log.info("Cookie was added and time prolonged ");
            } else {
                log.info("The user is not defined from session as authorized.");
                Cookies.removeCookie(request, response, Cookies.COOKIE_NAME);
                log.info("Cookie is removed and request is dispatched to the login.jsp");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            try {
                username = DAOFactory.getDAOFactory(1).getUserDAO().readByUUID(uuid).getUsername();
                log.info("The cookie for the online market accepted; the corresponding user found: " + username);
                request.login(username, uuid);
                log.info("The user " + username
                        + " was requested to log in using username and uuid received from cookie");
                Cookies.addCookie(request, response, Cookies.COOKIE_NAME, uuid, Cookies.COOKIE_AGE); // Extends age.
                log.info("Cookie was added and time prolonged ");
                PrintWriter pw = response.getWriter();
                pw.print("<body onLoad=\"window.location.reload()\"/>");
            } catch (SQLException e) {
                log.error(e.getSQLState());
                log.error(e.getErrorCode());
                log.error(e.getLocalizedMessage());
                log.error(e.getMessage());
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
                log.error(e.getMessage());
                Cookies.removeCookie(request, response, Cookies.COOKIE_NAME);
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }

    }
}
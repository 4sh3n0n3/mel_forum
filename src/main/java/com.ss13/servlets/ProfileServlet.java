package com.ss13.servlets;

import com.ss13.dao.DAOFactory;
import com.ss13.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String targeted_username = request.getParameter("targeted_username");
        User user = new User();

        if (targeted_username == null) {
            user = (User)session.getAttribute("user");
            if (user == null) {
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            try {
                user = DAOFactory.getDAOFactory(1).getUserDAO().read(targeted_username);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("user", user);

        getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}

package com.ss13.servlets;

import com.ss13.dao.DAOFactory;
import com.ss13.pojo.Profile;
import com.ss13.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/upd_profile")
public class UpdateProfileServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        HttpSession session = request.getSession();

        String newFirstName = request.getParameter("firstname");
        String newLastName = request.getParameter("lastname");
        User user = (User) session.getAttribute("user");

        Profile userProfile = user.getProfile();
        userProfile.setFirstName(newFirstName);
        userProfile.setLastName(newLastName);

        user.setProfile(userProfile);
        DAOFactory.getDAOFactory(1).getProfileDAO().update(userProfile);

        session.setAttribute("user", user);

        String json_response = "{\"firstname\": \""+ newFirstName +"\" ,\"lastname\": \""+ newLastName +"\"}";
        response.getWriter().print(json_response);
    }
}

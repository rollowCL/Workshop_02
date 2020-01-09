package org.school.user;

import org.school.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deluser")
public class DelUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDao userDao = new UserDao();
        userDao.delete(userId);
        response.getWriter().append("User deleted.<br/>");
        response.getWriter().append("<a href=\"/admin/users\">All users</a>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDao userDao = new UserDao();

        if (!userDao.userExistsId(userId)) {
            response.getWriter().append("User does not exists<br/>");
            response.getWriter().append("<a href=\"/admin/users\">All users</a>");
        } else {

            String email = userDao.read(userId).getEmail();
            response.getWriter().append("<h2>Confirm deletion of user (along with his solutions) with email: " + email + "</h2>");
            response.getWriter().append("<form method=\"post\">\n" +
                    "    <input type=\"hidden\" name=\"userId\"/value=\"" + userId + "\">\n" +
                    "    <input type=\"submit\"/ value=\"Delete\">\n" +
                    "</form>");

        }

    }
}

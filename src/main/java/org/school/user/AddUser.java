package org.school.user;

import org.school.Group;
import org.school.User;
import org.school.model.GroupDao;
import org.school.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/adduser")
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Integer groupId = Integer.parseInt(request.getParameter("groupId"));

        UserDao userDao = new UserDao();

        if (userDao.userExists(email)) {

            response.getWriter().append("Email already exists<br/>");
            response.getWriter().append("<a href=\"/admin/users\">All users</a><br/>");
            doGet(request, response);

        } else {
            User user = new User(name, email, password, groupId);

            if ("OK".equals(user.validate())) {
                userDao.create(user);
                response.getWriter().append("User created<br/>");
                response.getWriter().append("<a href=\"/admin/users\">All users</a><br/>");
            } else {
                response.getWriter().append("Error creating user. Message: " + user.validate() + "<br/>");
                response.getWriter().append("<a href=\"/admin/users\">All users</a><br/>");
                doGet(request, response);

            }


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer groupId = null;

        try {
            groupId = Integer.parseInt(request.getParameter("groupId"));
        } catch (NumberFormatException ex) {

        }
        String email = request.getParameter("email");
        String name = request.getParameter("name");

        if (email == null) {
            email = "";
        }
        if (name == null) {
            name = "";
        }


        GroupDao groupDao = new GroupDao();
        Group[] groups = groupDao.findAll();

        if (groupId != null) {

            response.getWriter().append("<h2>Add user to group " + groupDao.read(groupId).getName() + "</h2>");
            response.getWriter().append("<form method=\"post\">\n");
            response.getWriter().append(printFormInput(name, "name"));
            response.getWriter().append(printFormInput(email, "email"));
            response.getWriter().append("<input type=\"password\" name=\"password\"/>Enter password<br/>\n" +
                    "    <input type=\"hidden\" name=\"groupId\" value=\"" + groupId + "\"/>\n" +
                    "    <input type=\"submit\" value=\"Save\"/></form>");

        } else {
            response.getWriter().append("<h2>Add user</h2>");
            response.getWriter().append("<form method=\"post\">\n");
            response.getWriter().append(printFormInput(name, "name"));
            response.getWriter().append(printFormInput(email, "email"));
            response.getWriter().append("<input type=\"password\" name=\"password\"/>Enter password<br/>\n" +
                    "    <select name=\"groupId\">");
            for (Group group : groups) {
                response.getWriter().append("<option value=\"" + group.getId() + "\">" + group.getName() + "</option>");
            }
            response.getWriter().append("</select><br/><input type=\"submit\" value=\"Save\"/></form>");


        }


    }
    private String printFormInput(String input, String field) {
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("<input type=\"text\" name=\"" + field + "\" ");

       if (input != null) {

           stringBuilder.append("value=\"" + input + "\"");

       }
        stringBuilder.append("/>Enter " + field + "<br/>");
        return stringBuilder.toString();
    }
}

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

@WebServlet("/admin/edituser")
public class EditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDao userDao = new UserDao();
        User user = new User(userId, name, email, password, groupId);
        userDao.update(user);
        response.getWriter().append("User updated<br/>");
        response.getWriter().append("<a href=\"/admin/users\">All users</a>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDao userDao = new UserDao();

        String oldName = userDao.read(userId).getUserName();
        String oldEmail = userDao.read(userId).getEmail();
        Integer oldGroupId = userDao.read(userId).getUserGroupId();

        response.getWriter().append("<h2>Edit user</h2>");
        response.getWriter().append("<form method=\"post\">\n" +
                "    <input type=\"text\" name=\"name\" value=\"" + oldName + "\">Enter new name<br/>\n" +
                "    <input type=\"text\" name=\"email\" value=\"" + oldEmail + "\">Enter new email<br/>\n");
        response.getWriter().append("<input type=\"password\" name=\"password\"/>Enter password<br/>\n" +
                "    <input hidden type=\"number\" name=\"userId\" value=\"" + userId + "\">\n");
        response.getWriter().append(printFormSelect(oldGroupId));
        response.getWriter().append("<input type=\"submit\" value=\"Save\">\n" +
                "</form>");
    }

    private String printFormSelect(int oldId) {

        GroupDao groupDao = new GroupDao();
        Group[] groups = groupDao.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<select name=\"groupId\">");

        for (Group group : groups) {


            if (group.getId() == oldId) {
                stringBuilder.append("<option value=\"" + group.getId() + "\" selected>" + group.getName() + "</option>");
            } else {
                stringBuilder.append("<option value=\"" + group.getId() + "\">" + group.getName() + "</option>");

            }

        }

        stringBuilder.append("</select>");
        return stringBuilder.toString();
    }

}

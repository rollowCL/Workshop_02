package org.school.group;

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

@WebServlet("/admin/delgroup")
public class DelGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        GroupDao groupDao = new GroupDao();
        groupDao.delete(groupId);
        response.getWriter().append("Group deleted.<br/>");
        response.getWriter().append("<a href=\"/admin/groups\">All groups</a>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        UserDao userDao = new UserDao();

        User[] users = userDao.findAllByGroupId(groupId);
        
        if (users.length != 0) {
            response.getWriter().append("Can't delete group. Users (" + users.length + ") assigned to the group. <a href=\"/admin/users?groupId=" + groupId + "\">Check</a> users of the group<br/>");
            response.getWriter().append("<a href=\"/admin/groups\">All groups</a>");
        } else {

            GroupDao groupDao = new GroupDao();
            Group group = groupDao.read(groupId);
            String name = group.getName();

            response.getWriter().append(
                    "<div class=\"container\">\n" +
                            "<div class=\"row\">\n" +
                            "<div class=\"col-sm-2\"></div>\n" +
                            "<div class=\"col-sm-8 shadow p-3 mb-5 bg-secondary rounded\">\n" +
                            "<p class=\"text-center text-white font-weight-bolder\">\n" +
                            "Confirm deletion of " + name +
                            "</p>\n" +
                            "</div>\n" +
                            "<div class=\"col-sm-2\"></div>\n" +
                            "</div>\n" +
                            "<div class=\"row\">\n" +
                            "<div class=\"col-sm-2\"></div>\n" +
                            "<div class=\"col-sm-8\">\n" +
                            "<form class=\"form-group\" method=\"post\">" +
                            "    <input type=\"hidden\" name=\"groupId\"/value=\"" + groupId + "\">\n" +
                            "<button type=\"submit\" class=\"btn btn-secondary\" value=\"Submit\">Delete</button>" +
                            "</form><br/><a href=\"javascript: window.history.go(-1)\">Back</a>" +
                            "</div>\n" +
                            "<div class=\"col-sm-2\"></div>\n" +
                            "</div>\n" +
                            "</div>");

        }

    }
}

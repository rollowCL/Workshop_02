package org.school.group;

import org.school.Group;
import org.school.model.GroupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/editgroup")
public class EditGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        GroupDao groupDao = new GroupDao();

        if (groupDao.groupExists(name)) {

            response.getWriter().append("Group already exists<br/>");
            response.getWriter().append("<a href=\"/admin/groups\">All groups</a><br/>");
            doGet(request, response);

        } else {
            Group group = new Group(groupId, name);
            groupDao.update(group);
            response.getWriter().append("Group updated<br/>");
            response.getWriter().append("<a href=\"/admin/groups\">All groups</a>");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        GroupDao groupDao = new GroupDao();
        String oldName = groupDao.read(groupId).getName();

        response.getWriter().append(
                "<div class=\"container\">\n" +
                        "<div class=\"row\">\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "<div class=\"col-sm-8 shadow p-3 mb-5 bg-secondary rounded\">\n" +
                        "<p class=\"text-center text-white font-weight-bolder\">\n" +
                        "Edit group" +
                        "</p>\n" +
                        "</div>\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "</div>\n" +
                        "<div class=\"row\">\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "<div class=\"col-sm-8\">\n" +
                        "<form class=\"form-group\" method=\"post\">" +
                        "    <input type=\"text\" name=\"name\"/ value=\"" + oldName + "\">Enter new name<br/>\n" +
                        "    <input hidden type=\"number\" name=\"groupId\"/ value=\"" + groupId + "\">\n" +
                        "<button type=\"submit\" class=\"btn btn-secondary\" value=\"Submit\">Save</button>" +
                        "</form><br/><a href=\"javascript: window.history.go(-1)\">Back</a>" +
                        "</div>\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "</div>\n" +
                        "</div>");
    }
}

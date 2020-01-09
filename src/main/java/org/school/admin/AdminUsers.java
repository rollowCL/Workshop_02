package org.school.admin;

import com.sun.java.accessibility.util.AccessibilityListenerList;
import org.school.AllData;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/users")
public class AdminUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer groupId = null;
        try {
            groupId = Integer.parseInt(request.getParameter("groupId"));
        } catch (NumberFormatException ex) {

        }
        UserDao userDao = new UserDao();
        GroupDao groupDao = new GroupDao();
        List<AllData> usersList = new ArrayList<>();
        User[] users;

        if (groupId == null) {
            users = userDao.findAll();
        } else {
            users = userDao.findAllByGroupId(groupId);
            request.setAttribute("group", groupDao.read(groupId));
        }

        for (User user: users) {
            Group group = groupDao.read(user.getUserGroupId());
            AllData allData = new AllData(null,user,null,group);
            usersList.add(allData);
        }

        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/adminUsers.jsp").forward(request,response);


    }
}

package org.school.group;

import org.school.model.GroupDao;
import org.school.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/groupusers")
public class GroupUsers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GroupDao groupDao = new GroupDao();
        request.setAttribute("group", groupDao.read(id));

        UserDao userDao = new UserDao();
        request.setAttribute("groupUsers", userDao.findAllByGroupId(id));

        getServletContext().getRequestDispatcher("/groupUsers.jsp")
                .forward(request, response);
    }
}

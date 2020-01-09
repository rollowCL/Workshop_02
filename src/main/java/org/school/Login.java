package org.school;

import org.school.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.login(email);

        if (user != null) {
            String storedPassword = user.getPassword();

            if (BCrypt.checkpw(password, storedPassword)) {
                session = req.getSession();

                if (user.getAdmin() == 0) {
                    session.setAttribute("loggedUser", user.getId());
                    resp.sendRedirect(req.getContextPath() + "/user/panel");
                } else if (user.getAdmin() == 1) {
                    session.setAttribute("loggedAdmin", user.getId());
                    resp.sendRedirect(req.getContextPath() + "/admin/panel");
                }


            } else {
                resp.getWriter().append("<div class=\"alert alert-danger text-center\" role=\"alert\">\n" +
                        " Invalid credentials.\n" +
                        "</div>");
                resp.getWriter().append("<br/>");
                resp.getWriter().append("<a href=\"/login\">Back to login</a><br/>");
            }

        } else {
            resp.getWriter().append("<div class=\"alert alert-danger text-center\" role=\"alert\">\n" +
                    " No such user.\n" +
                    "</div>");
            resp.getWriter().append("<br/>");
            resp.getWriter().append("<a href=\"/login\">Back to login</a><br/>");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }
}

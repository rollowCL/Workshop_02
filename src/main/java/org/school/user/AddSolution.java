package org.school.user;

import org.school.Solution;
import org.school.model.ExerciseDao;
import org.school.model.SolutionDao;
import org.school.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/user/addsolution")
public class AddSolution extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(req.getParameter("exerciseId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        String description = req.getParameter("description");


        SolutionDao solutionDao = new SolutionDao();
        Solution solution = new Solution(new Date(), null, description, userId, exerciseId);
        solutionDao.create(solution);
        resp.getWriter().append("<div class=\"alert alert-success text-center\" role=\"alert\">\n" +
                "  Solution created!\n" +
                "</div>");
        resp.getWriter().append("<a href=\"/user/panel\">Back to panel</a><br/>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();


        request.setAttribute("userDetails", userDao.read(userId));
        request.setAttribute("unsolvedExercises", exerciseDao.findUnsolvedForUser(userId));

        getServletContext().getRequestDispatcher("/addSolution.jsp")
                .forward(request, response);
    }
}

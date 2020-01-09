package org.school.admin;

import org.school.*;
import org.school.model.ExerciseDao;
import org.school.model.GroupDao;
import org.school.model.SolutionDao;
import org.school.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/solutions")
public class AdminSolutions extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));

        SolutionDao solDao = new SolutionDao();
        Solution[] solutions = solDao.findAllByExerciseId(exerciseId);
        List<AllData> solutionsList = new ArrayList<>();
        ExerciseDao exerciseDao = new ExerciseDao();
        UserDao userDao = new UserDao();
        GroupDao groupDao = new GroupDao();

        for (Solution solution: solutions) {
            User user = userDao.read(solution.getUserId());
            Exercise exercise = exerciseDao.read(solution.getExerciseId());
            Group group = groupDao.read(user.getUserGroupId());
            AllData allData = new AllData(solution, user, exercise, group);
            solutionsList.add(allData);
        }

        request.setAttribute("solutions", solutionsList);
        request.setAttribute("exerciseTitle", exerciseDao.read(exerciseId).getTitle());
        getServletContext().getRequestDispatcher("/adminSolutions.jsp")
                .forward(request, response);


    }
}

package org.school;

import org.school.model.ExerciseDao;
import org.school.model.SolutionDao;
import org.school.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/")
public class MainPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int limit = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));

        SolutionDao solDao = new SolutionDao();
        Solution[] solutions = solDao.findRecent(limit);
        List<AllData> recentSolutionsList = new ArrayList<>();
        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();

        for (Solution solution : solutions) {
            User user = userDao.read(solution.getUserId());
            Exercise exercise = exerciseDao.read(solution.getExerciseId());
            AllData allData = new AllData(solution, user, exercise, null);
            recentSolutionsList.add(allData);
        }

        request.setAttribute("solutions", recentSolutionsList);

        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(request, response);


    }
}

package org.school.user;

import org.school.AllData;
import org.school.Exercise;
import org.school.Solution;
import org.school.model.ExerciseDao;
import org.school.model.SolutionDao;
import org.school.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/panel")
public class UserPanel extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("loggedUser");

        UserDao userDao = new UserDao();
        request.setAttribute("userDetails", userDao.read(userId));

        SolutionDao solutionDao = new SolutionDao();
        Solution[] solutions = solutionDao.findAllByUserId(userId);
        ExerciseDao exerciseDao = new ExerciseDao();
        List<AllData> userSolutionList = new ArrayList<>();

        for (Solution solution: solutions) {
            Exercise exercise = exerciseDao.read(solution.getExerciseId());
            AllData allData = new AllData(solution, null, exercise, null);
            userSolutionList.add(allData);
        }

        request.setAttribute("solutions", userSolutionList);
        getServletContext().getRequestDispatcher("/userPanel.jsp")
                .forward(request, response);
    }
}

package org.school.admin;

import org.school.Exercise;
import org.school.User;
import org.school.model.ExerciseDao;
import org.school.model.GroupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/exercises")
public class AdminExercises extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ExerciseDao exerciseDao = new ExerciseDao();

        Exercise[] exercises = exerciseDao.findAll();
        request.setAttribute("exercises", exercises);
        getServletContext().getRequestDispatcher("/adminExercises.jsp")
                .forward(request, response);


    }

}

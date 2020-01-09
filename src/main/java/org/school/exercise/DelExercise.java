package org.school.exercise;

import org.school.Exercise;
import org.school.Solution;
import org.school.model.ExerciseDao;
import org.school.model.SolutionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delexercise")
public class DelExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));

        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.delete(exerciseId);
        response.getWriter().append("Exercise deleted.<br/>");
        response.getWriter().append("<a href=\"/admin/exercises\">All exercises</a>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));


        SolutionDao solutionDao = new SolutionDao();

        Solution[] solutions = solutionDao.findAllByExerciseId(exerciseId);
        
        if (solutions.length != 0) {
            response.getWriter().append("<div class=\"alert alert-danger text-center\" role=\"alert\">\n" +
                    "  Can't delete exercise. Solutions (" + solutions.length + ") subbmited to the solutions. <a href=\"/admin/solutions?exerciseId=" + exerciseId + "\">Check</a> solutions<br/>" +
                    "</div>");
            response.getWriter().append("<a href=\"/admin/exercises\">All exercises</a>");
        } else {

            ExerciseDao exerciseDao = new ExerciseDao();
            Exercise exercise = exerciseDao.read(exerciseId);
            String title = exercise.getTitle();
            response.getWriter().append(
                    "<div class=\"container\">\n" +
                            "<div class=\"row\">\n" +
                            "<div class=\"col-sm-2\"></div>\n" +
                            "<div class=\"col-sm-8 shadow p-3 mb-5 bg-secondary rounded\">\n" +
                            "<p class=\"text-center text-white font-weight-bolder\">\n" +
                            "Confirm deletion of \"" + title +
                            "</p>\n" +
                            "</div>\n" +
                            "<div class=\"col-sm-2\"></div>\n" +
                            "</div>\n" +
                            "<div class=\"row\">\n" +
                            "<div class=\"col-sm-2\"></div>\n" +
                            "<div class=\"col-sm-8\">\n" +
                            "<form class=\"form-group\" method=\"post\">" +
                            "<input type=\"hidden\" name=\"exerciseId\"/value=\"" + exerciseId + "\">\n" +
                            "<button type=\"submit\" class=\"btn btn-secondary\" value=\"Submit\">Delete</button>" +
                            "</form><br/><a href=\"javascript: window.history.go(-1)\">Back</a>" +
                            "</div>\n" +
                            "<div class=\"col-sm-2\"></div>\n" +
                            "</div>\n" +
                            "</div>");

        }

    }
}

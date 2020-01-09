package org.school.exercise;

import org.school.Exercise;
import org.school.model.ExerciseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/editexercise")
public class EditExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));

        ExerciseDao exerciseDao = new ExerciseDao();


        Exercise exercise = new Exercise(exerciseId, title, description);
        exerciseDao.update(exercise);
        response.getWriter().append("<div class=\"alert alert-success text-center\" role=\"alert\">\n" +
                "  Exercise updated!\n" +
                "</div>");
        response.getWriter().append("<a href=\"/admin/exercises\">All exercises</a><br/>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));

        ExerciseDao exerciseDao = new ExerciseDao();
        String oldTitle = exerciseDao.read(exerciseId).getTitle();
        String oldDescription = exerciseDao.read(exerciseId).getDescription();

        response.getWriter().append(
                "<div class=\"container\">\n" +
                        "<div class=\"row\">\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "<div class=\"col-sm-8 shadow p-3 mb-5 bg-secondary rounded\">\n" +
                        "<p class=\"text-center text-white font-weight-bolder\">\n" +
                        "Edit exercise\n" +
                        "</p>\n" +
                        "</div>\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "</div>\n" +
                        "<div class=\"row\">\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "<div class=\"col-sm-8\">\n" +
                        "<form class=\"form-group\" method=\"post\">" +
                        "Enter new title<input class=\"ml-5 mb-1\" type=\"text\" name=\"title\"/ value=\"" + oldTitle + "\"><br/>\n" +
                        "Enter new description<textarea name=\"description\"/ rows=\"10\" cols=\"50\" >" + oldDescription + "</textarea><br/>\n" +
                        "<input hidden type=\"number\" name=\"exerciseId\"/ value=\"" + exerciseId + "\">\n" +
                        "<button type=\"submit\" class=\"btn btn-secondary\" value=\"Submit\">Update</button>" +
                        "</form><br/><a href=\"javascript: window.history.go(-1)\">Back</a>" +
                        "</div>\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "</div>\n" +
                        "</div>");
    }
}

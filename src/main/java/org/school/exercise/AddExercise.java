package org.school.exercise;

import org.school.Exercise;
import org.school.model.ExerciseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/addexercise")
public class AddExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        ExerciseDao exerciseDao = new ExerciseDao();

        if (exerciseDao.exerciseExists(title)) {
            response.getWriter().append("<div class=\"alert alert-danger text-center\" role=\"alert\">\n" +
                    "  Exercise already exists!\n" +
                    "</div>");
            response.getWriter().append("<br/>");
            response.getWriter().append("<a href=\"/admin/exercises\">All exercises</a><br/>");
            doGet(request, response);

        } else {
            Exercise exercise = new Exercise(title, description);
            exerciseDao.create(exercise);
            response.getWriter().append("<div class=\"alert alert-success text-center\" role=\"alert\">\n" +
                    "  Exercise created!\n" +
                    "</div>");
            response.getWriter().append("<br/>");
            response.getWriter().append("<a href=\"/admin/exercises\">All exercises</a><br/>");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        if (title == null) {
            title = "";
        }
        if (description == null) {
            description = "";
        }
        response.getWriter().append(
                "<div class=\"container\">\n" +
                        "<div class=\"row\">\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "<div class=\"col-sm-8 shadow p-3 mb-5 bg-secondary rounded\">\n" +
                        "<p class=\"text-center text-white font-weight-bolder\">\n" +
                        "Add exercise\n" +
                        "</p>\n" +
                        "</div>\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "</div>\n" +
                        "<div class=\"row\">\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "<div class=\"col-sm-8\">\n" +
                        "<form class=\"form-group\" method=\"post\">" +
                        "Enter title <input class=\"ml-5 mb-1\" type=\"text\" name=\"title\" value=\"" + title + "\"/><br/>" +
                        "Enter description <textarea class=\"ml-1\" rows=\"10\" cols=\"50\" name=\"description\" value=\"" + description + "\"/></textarea><br/>" +
                        "<button type=\"submit\" class=\"btn btn-secondary\" value=\"Submit\">Submit</button>" +
                        "</form><br/><a href=\"javascript: window.history.go(-1)\">Back</a>" +
                        "</div>\n" +
                        "<div class=\"col-sm-2\"></div>\n" +
                        "</div>\n" +
                        "</div>");

    }
}

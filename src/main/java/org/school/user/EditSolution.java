package org.school.user;

import org.school.Solution;
import org.school.model.SolutionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/user/editsolution")
public class EditSolution extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int solutionId = Integer.parseInt(req.getParameter("solutionId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        String description = req.getParameter("description");


        SolutionDao solutionDao = new SolutionDao();
        Solution solution = new Solution(solutionId,new Date(), description);
        solutionDao.update(solution);
        resp.getWriter().append("<div class=\"alert alert-success text-center\" role=\"alert\">\n" +
                "  Solution updated!\n" +
                "</div>");
        resp.getWriter().append("<a href=\"/user/panel\">Back to panel</a><br/>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int solutionId = Integer.parseInt(request.getParameter("solutionId"));

        SolutionDao solutionDao = new SolutionDao();

        request.setAttribute("solutionDetails", solutionDao.read(solutionId));

        getServletContext().getRequestDispatcher("/editSolution.jsp")
                .forward(request, response);
    }
}

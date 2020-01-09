package org.school.user;

import org.school.model.SolutionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solutiondetails")
public class SolutionDetails extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int solutionId = Integer.parseInt(request.getParameter("solutionId"));

        SolutionDao solutionDao = new SolutionDao();

        request.setAttribute("solutionDetails", solutionDao.read(solutionId));

        getServletContext().getRequestDispatcher("/soldetails.jsp")
                .forward(request, response);
    }
}

package controller.mission;

import com.google.gson.Gson;
import dao.MentorDAO;
import dao.MissionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Mentor;
import model.Mission;

public class MissionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MissionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MissionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MissionDAO missionDAO = new MissionDAO();
        List<Mission> missions = missionDAO.getAllMissions();
        MentorDAO mentorDAO = new MentorDAO();
        List<Mentor> listOfMentor = mentorDAO.getAllMentors();
        missionDAO.updateMissionStatusContinuously();
        String jsonMissions = new Gson().toJson(missions);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonMissions);

        request.setAttribute("missions", missions);
        request.setAttribute("listOfMentor", listOfMentor);
        request.getRequestDispatcher("Mission.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

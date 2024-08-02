package controller.mission;

import dao.MissionDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.Mission;

public class ReSubmitMission extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int missionId = Integer.parseInt(request.getParameter("misId"));
        MissionDAO missionDAO = new MissionDAO();
        Mission mission = missionDAO.getMissionById(missionId);
        Timestamp deadline = mission.getDeadline();
        LocalDateTime currentTime = LocalDateTime.now();

        if ("COMPLETED".equals(mission.getMisStatus().name())) {
            request.setAttribute("errorMessage", "The mission has been accepted by the mentor!");
            request.getRequestDispatcher("mission").forward(request, response);
        } else if ("REJECTED".equals(mission.getMisStatus().name())) {
            request.setAttribute("errorMessage", "The mission has been rejected by the mentor!");
            request.getRequestDispatcher("mission").forward(request, response);
        } else if (currentTime.isAfter(deadline.toLocalDateTime())) {
            missionDAO.reSubmitMission(missionId);
            response.sendRedirect("mission");
        } else {
            request.setAttribute("errorMessage", "Please rate after the task expires!");
            request.getRequestDispatcher("mission").forward(request, response);
        }
    }
}
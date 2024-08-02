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

public class AcceptMission extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int missionId = Integer.parseInt(request.getParameter("misId"));
        MissionDAO missionDAO = new MissionDAO();
        Mission mission = missionDAO.getMissionById(missionId);
        Timestamp deadline = mission.getDeadline();
        LocalDateTime currentTime = LocalDateTime.now();

        System.out.println("Trạng thái nhiệm vụ: " + mission.getMisStatus());

//        String status = mission.getMisStatus();

        if ("REJECTED".equals(mission.getMisStatus().name())) {
            request.setAttribute("errorMessage", "The mission has been rejected by the mentor!");
            request.getRequestDispatcher("mission").forward(request, response);  // Ensure the path is correct
        } else if (currentTime.isBefore(deadline.toLocalDateTime())) {
            request.setAttribute("errorMessage", "Please accept after the task expires!");
            request.getRequestDispatcher("mission").forward(request, response);  // Ensure the path is correct
        } else if (mission.getFile_path() == null || mission.getFile_path().isEmpty()) {
            request.setAttribute("errorMessage", "The mission cannot be accepted as completed if there is no submission!");
            request.getRequestDispatcher("mission").forward(request, response);  // Ensure the path is correct
        } else {
            missionDAO.acceptMission(missionId);
            response.sendRedirect("mission");  // Ensure the path is correct
        }
    }
}
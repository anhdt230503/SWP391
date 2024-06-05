package controller;

import dao.MissionDAO;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Mission;

public class AddMissionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MissionDAO missionDAO = new MissionDAO();
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String link = request.getParameter("link");
            Timestamp startDate = Timestamp.valueOf(request.getParameter("startDate") + " 00:00:00");
            Timestamp deadline = Timestamp.valueOf(request.getParameter("deadline") + " 00:00:00");

            // Get the current timestamp
            Timestamp currentTimestamp = new Timestamp(new Date().getTime());

            // Determine the status based on current time
            Mission.MissionStatus status;
            if (currentTimestamp.before(startDate)) {
                status = Mission.MissionStatus.NOT_START;
            } else if (currentTimestamp.after(deadline)) {
                status = Mission.MissionStatus.FINISHED;
            } else {
                status = Mission.MissionStatus.ON_GOING;
            }

            // Create Mission object
            Mission mission = new Mission();
            mission.setMisName(name);
            mission.setMisStatus(status);
            mission.setMisDescription(description);
            mission.setLink(link);
            mission.setStartDate(startDate);
            mission.setDeadline(deadline);

            // Add mission to the database
            missionDAO.addMission(mission);

            // Retrieve the list of missions after adding
            List<Mission> missions = missionDAO.getAllMissions();
            request.setAttribute("missions", missions);

            // Forward to Mission.jsp
            request.getRequestDispatcher("Mission.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi thêm nhiệm vụ: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

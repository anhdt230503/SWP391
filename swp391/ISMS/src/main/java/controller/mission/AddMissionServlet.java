package controller.mission;

import dao.MissionDAO;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
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

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedStartDate = dateFormat.parse(request.getParameter("startDate"));
            Date parsedDeadline = dateFormat.parse(request.getParameter("deadline"));

            Timestamp startDate = new Timestamp(parsedStartDate.getTime());
            Timestamp deadline = new Timestamp(parsedDeadline.getTime());

            if (deadline.before(startDate)) {
                request.setAttribute("errorMessage", "Deadline phải sau Start Date");

                request.setAttribute("name", name);
                request.setAttribute("description", description);
                request.setAttribute("link", link);
                request.setAttribute("startDate", request.getParameter("startDate"));
                request.setAttribute("deadline", request.getParameter("deadline"));

                request.getRequestDispatcher("AddMission.jsp").forward(request, response);
                return;
            }

            Timestamp currentTimestamp = new Timestamp(new Date().getTime());

            Mission.MissionStatus status;
            if (currentTimestamp.before(startDate)) {
                status = Mission.MissionStatus.NOT_START;
            } else if (currentTimestamp.after(deadline)) {
                status = Mission.MissionStatus.FINISHED;
            } else {
                status = Mission.MissionStatus.ON_GOING;
            }

            Mission mission = new Mission();
            mission.setMisName(name);
            mission.setMisStatus(status);
            mission.setMisDescription(description);
            mission.setLink(link);
            mission.setStartDate(startDate);
            mission.setDeadline(deadline);

            
            missionDAO.addMission(mission);

            if (currentTimestamp.after(deadline)) {
                missionDAO.updateMissionStatus(mission.getMisId(), Mission.MissionStatus.FINISHED);
            }

            List<Mission> missions = missionDAO.getAllMissions();
            request.setAttribute("missions", missions);
            request.getRequestDispatcher("Mission.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi thêm nhiệm vụ: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

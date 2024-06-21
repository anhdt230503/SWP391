package controller.mission;

import dao.AccountDAO;
import dao.MissionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Mission;
import model.Mission.MissionStatus;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Intern;

public class UpdateMissionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AccountDAO accountDAO = new AccountDAO();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            Account account = accountDAO.getAccountByEmail(email);
            int mentorId = account.getMentorId();

            // Get parameters and check for empty values
            String missionIdStr = request.getParameter("misId");
            String misName = request.getParameter("misName");
            String misDescription = request.getParameter("misDescription");
            String link = request.getParameter("link");
            String startDateStr = request.getParameter("startDate");
            String deadlineStr = request.getParameter("deadline");
            String internIdStr = request.getParameter("internId");

            if (missionIdStr == null || missionIdStr.isEmpty() ||
                misName == null || misName.isEmpty() ||
                misDescription == null || misDescription.isEmpty() ||
                startDateStr == null || startDateStr.isEmpty() ||
                deadlineStr == null || deadlineStr.isEmpty() ||
                internIdStr == null || internIdStr.isEmpty()) {
                throw new IllegalArgumentException("One or more required parameters are missing or empty.");
            }

            int missionId = Integer.parseInt(missionIdStr);
            int internId = Integer.parseInt(internIdStr);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedStartDate = dateFormat.parse(startDateStr);
            Date parsedDeadline = dateFormat.parse(deadlineStr);

            Timestamp startDate = new Timestamp(parsedStartDate.getTime());
            Timestamp deadline = new Timestamp(parsedDeadline.getTime());

            if (deadline.before(startDate)) {
                request.setAttribute("errorMessage", "Deadline must be after Start Date");
                request.setAttribute("name", misName);
                request.setAttribute("description", misDescription);
                request.setAttribute("link", link);
                request.setAttribute("startDate", startDateStr);
                request.setAttribute("deadline", deadlineStr);
                request.getRequestDispatcher("updateMission.jsp").forward(request, response);
                return;
            }

            // Determine status based on the current time
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
            mission.setMisId(missionId);  // Assuming there is a setter for mission ID
            mission.setMisName(misName);
            mission.setMisStatus(status);
            mission.setMisDescription(misDescription);
            mission.setLink(link);
            mission.setStartDate(startDate);
            mission.setDeadline(deadline);
            mission.setMentorId(mentorId);
            mission.setInternId(internId);
              
            MissionDAO missionDAO = new MissionDAO();
            missionDAO.updateMission(mission);

            response.sendRedirect(request.getContextPath() + "/mission");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating mission: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        Account account = accountDAO.getAccountByEmail(email);
        int mentorId = account.getMentorId();

        MissionDAO missionDAO = new MissionDAO();
        List<Intern> internList = missionDAO.getInternsByMentorId(mentorId);

        request.setAttribute("internList", internList);
        String id = request.getParameter("misId");
        request.setAttribute("misId", id);

        request.getRequestDispatcher("updateMission.jsp").forward(request, response);
    }
}

package controller.mission;

import dao.AccountDAO;
import dao.MissionDAO;
import model.Account;
import model.Intern;
import model.Mission;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@MultipartConfig
public class AddMissionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        try {
            // get mentorId from Account
            Account account = accountDAO.getAccountByEmail(email);
            int mentorId = account.getMentorId();
            MissionDAO missionDAO = new MissionDAO();
            List<Intern> internList = missionDAO.getInternsByMentorId(mentorId);

            request.setAttribute("internList", internList);
            request.getRequestDispatcher("addMission.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving intern list: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AccountDAO accountDAO = new AccountDAO();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            int internId = Integer.parseInt(request.getParameter("internId"));
            // get mentorId from Account
            Account account = accountDAO.getAccountByEmail(email);
            int mentorId = account.getMentorId();
            MissionDAO missionDAO = new MissionDAO();
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String link = null;
            Part filePart = request.getPart("link");
            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                Path uploadDirectory = Paths.get("C:\\swp391\\ISMS\\src\\file_upload");
                if (!Files.exists(uploadDirectory)) {
                    Files.createDirectories(uploadDirectory);
                }
                Path filePath = uploadDirectory.resolve(originalFileName);
                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                link = originalFileName;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedStartDate = dateFormat.parse(request.getParameter("startDate"));
            Date parsedDeadline = dateFormat.parse(request.getParameter("deadline"));

            Timestamp startDate = new Timestamp(parsedStartDate.getTime());
            Timestamp deadline = new Timestamp(parsedDeadline.getTime());
            if (deadline.before(startDate)) {
                request.setAttribute("errorMessage", "Deadline must be after Start Date");
                request.setAttribute("name", name);
                request.setAttribute("description", description);
                request.setAttribute("link", link);
                request.setAttribute("startDate", request.getParameter("startDate"));
                request.setAttribute("deadline", request.getParameter("deadline"));

                request.getRequestDispatcher("addMission.jsp").forward(request, response);
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
            mission.setMentorId(mentorId);
            mission.setInternId(internId);
            mission.setCreated_at(currentTimestamp);

            missionDAO.addMission(mission);

            // Save creation time into session
            if (currentTimestamp.after(deadline)) {
                missionDAO.updateMissionStatus(mission.getMisId(), Mission.MissionStatus.FINISHED);
            }
            List<Mission> missions = missionDAO.getAllMissions();
            request.setAttribute("missions", missions);
            response.sendRedirect("mission");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding mission: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

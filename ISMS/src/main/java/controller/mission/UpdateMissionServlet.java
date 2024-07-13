package controller.mission;

import dao.AccountDAO;
import dao.MissionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Mission;
import model.Mission.MissionStatus;
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
import model.Account;
import model.Intern;

@MultipartConfig

public class UpdateMissionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AccountDAO accountDAO = new AccountDAO();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            Account account = accountDAO.getAccountByEmail(email);
            int role = account.getRoleId();
            int mentorId = account.getMentorId();
            String missionIdStr = request.getParameter("misId");
            String misName = request.getParameter("misName");
            String misDescription = request.getParameter("misDescription");
            String startDateStr = request.getParameter("startDate");
            String deadlineStr = request.getParameter("deadline");
            String internIdStr = request.getParameter("internId");
            String link = null;
            Part filePart = request.getPart("link");
            // Check if filePart exists and its size is greater than 0
            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
                // Check if the file extension is allowed (.doc or .pdf)
                if (!fileExtension.equals(".doc") && !fileExtension.equals(".pdf")) {
                    request.setAttribute("errorMessage", "Only .doc or .pdf files are allowed.");
                    request.getRequestDispatcher("addMission.jsp").forward(request, response);
                    return;
                }
                Path uploadDirectory = Paths.get("\\swp391\\ISMS\\src\\file_upload");
                if (!Files.exists(uploadDirectory)) {
                    Files.createDirectories(uploadDirectory);
                }
                Path filePath = uploadDirectory.resolve(originalFileName);
                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                link = originalFileName; // Set link to the uploaded file name
            }
            System.out.println("missionIdStr: " + missionIdStr);
            System.out.println("misName: " + misName);
            System.out.println("misDescription: " + misDescription);
            System.out.println("startDateStr: " + startDateStr);
            System.out.println("deadlineStr: " + deadlineStr);
            System.out.println("internIdStr: " + internIdStr);

            if (missionIdStr == null || missionIdStr.isEmpty()
                    || misName == null || misName.isEmpty()
                    || misDescription == null || misDescription.isEmpty()
                    || startDateStr == null || startDateStr.isEmpty()
                    || deadlineStr == null || deadlineStr.isEmpty()
                    || internIdStr == null || internIdStr.isEmpty()) {
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
            mission.setMisId(missionId);
            mission.setMisName(misName);
            mission.setMisStatus(status);
            mission.setMisDescription(misDescription);
            mission.setLink(link);
            mission.setStartDate(startDate);
            mission.setDeadline(deadline);
            mission.setMentorId(mentorId);
            mission.setInternId(internId);
            mission.setUpdateTime(currentTimestamp);
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
        MissionDAO missionDAO = new MissionDAO();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // Lấy thông tin tài khoản của người dùng từ email
        Account account = accountDAO.getAccountByEmail(email);
        int mentorId = account.getMentorId();

        // Lấy danh sách thực tập sinh dựa trên mentorId
        List<Intern> internList = missionDAO.getInternsByMentorId(mentorId);
        request.setAttribute("internList", internList);

        // Lấy misId từ parameter
        String id = request.getParameter("misId");
        int misId = Integer.parseInt(id);

        // Lấy thông tin chi tiết của nhiệm vụ dựa trên misId
        Mission mission = missionDAO.getMissionById(misId);
        request.setAttribute("mission", mission);

        // Chuyển hướng request đến updateMission.jsp
        request.getRequestDispatcher("updateMission.jsp").forward(request, response);
    }

}

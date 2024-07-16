package controller.mission;

import dao.AccountDAO;
import dao.MissionDAO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.sql.Timestamp;
import java.util.Date;
import model.Account;
import model.Mission;

@MultipartConfig
public class SubmitMission extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        MissionDAO missionDAO = new MissionDAO();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // Lấy thông tin tài khoản của người dùng từ email
        Account account = accountDAO.getAccountByEmail(email);
        int mentorId = account.getMentorId();

        // Lấy misId từ parameter
        String id = request.getParameter("misId");
        int misId = Integer.parseInt(id);

        // Lấy thông tin chi tiết của nhiệm vụ dựa trên misId
        Mission mission = missionDAO.getMissionById(misId);
        request.setAttribute("mission", mission);

        // Chuyển hướng request đến SubmitFile.jsp
        request.getRequestDispatcher("SubmitFile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MissionDAO missionDAO = new MissionDAO();
        String id = request.getParameter("misId");
        int misId = Integer.parseInt(id);
        Mission mission = missionDAO.getMissionById(misId);

        // Check the status of the mission
        if (mission == null) {
            request.setAttribute("errorMessage", "Mission không tồn tại.");
            request.setAttribute("mission", mission); // Send back the mission information
            request.getRequestDispatcher("DetailMission.jsp").forward(request, response);
            return;
        }

        if ("FINISHED".equals(mission.getMisStatus().name())) {
            request.setAttribute("errorMessage", "Mission đã hết hạn.");
            request.setAttribute("mission", mission); // Send back the mission information
            request.getRequestDispatcher("DetailMission.jsp").forward(request, response);
            return;
        } else if ("NOT_START".equals(mission.getMisStatus().name())) {
            request.setAttribute("errorMessage", "Mission chưa bắt đầu.");
            request.setAttribute("mission", mission); // Send back the mission information
            request.getRequestDispatcher("DetailMission.jsp").forward(request, response);
            return;
        } else if ("MISSING".equals(mission.getMisStatus().name())) {
            request.setAttribute("errorMessage", "Mission đã hết hạn.");
            request.setAttribute("mission", mission); // Send back the mission information
            request.getRequestDispatcher("DetailMission.jsp").forward(request, response);
            return;
        }

        String file_path = null;
        Part filePart = request.getPart("file_path");
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
            file_path = originalFileName;
        }

        // Update file_path and submission time in the database
        missionDAO.upMissionFilePath(misId, file_path);

        // Redirect to the mission list or detail page after successful submission
        response.sendRedirect(request.getContextPath() + "/mission");
    }

    @Override
    public String getServletInfo() {
        return "Servlet to handle mission submission";
    }

}

package controller;

import dao.AccountDAO;
import dao.MentorReportDAO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.StandardCopyOption;
import model.Account;

@MultipartConfig
public class MentorReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reportTitle = request.getParameter("reportTitle");
        Part filePart = request.getPart("reportFile");
        AccountDAO accountDAO = new AccountDAO();

        if (filePart != null) {
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Update the path to reflect a valid upload directory in your system
            Path uploadDirectory = Paths.get("\\swp391\\ISMS\\src\\file_upload");

            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }

            // Create the path for the new file
            Path filePath = uploadDirectory.resolve(originalFileName);

            // Try-with-resources to ensure InputStream is closed properly
            try (InputStream fileContent = filePart.getInputStream()) {
                // Save the file
                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            MentorReportDAO reportDAO = new MentorReportDAO();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");

            Account account = accountDAO.getAccountByEmail(email);
            int mentorId = account.getMentorId();

            // Save report details to the database
            reportDAO.UploadReport(reportTitle, mentorId, originalFileName);

            request.setAttribute("message", "File uploaded successfully to the 'file_upload' directory.");
            request.getRequestDispatcher("MentorReport.jsp").forward(request, response);

        } else {
            request.setAttribute("message", "File upload failed. Please try again.");
            request.getRequestDispatcher("MentorReport.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

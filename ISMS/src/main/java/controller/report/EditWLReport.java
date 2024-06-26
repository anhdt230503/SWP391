package controller.report;

import dao.AccountDAO;
import dao.WeeklyReportDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import model.Account;

/**
 * Servlet to handle editing of weekly reports.
 */
@MultipartConfig
public class EditWLReport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        String reportTitle = request.getParameter("reportTitle");
        String reportweek = request.getParameter("reportweek");
        String reportDescription = request.getParameter("reportDescription");
        Part filePart = request.getPart("reportFile");
        AccountDAO accountDAO = new AccountDAO();

        if (filePart != null) {
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            Path uploadDirectory = Paths.get("\\swp391\\ISMS\\src\\file_upload");
            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }
            Path filePath = uploadDirectory.resolve(originalFileName);
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("message", "File upload failed. Please try again.");
                request.getRequestDispatcher("WeeklyReport.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            Account account = accountDAO.getAccountByEmail(email);
            if (account == null) {
                request.setAttribute("message", "Session expired. Please log in again.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            }
            int studentId = account.getInternId();

            WeeklyReportDAO WLD = new WeeklyReportDAO();
            try {
                WLD.updateWeeklyReport(reportTitle, reportweek, reportDescription, originalFileName, reportId);
                request.setAttribute("message", "File uploaded successfully to the 'file_update' directory.");
                response.sendRedirect(request.getContextPath() + "/WeeklyReportList");
            } catch (Exception e) {
                // Handle database update exception
                e.printStackTrace();
                request.setAttribute("message", "Database update failed. Please try again.");
                request.getRequestDispatcher("MidtermReport.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "No file uploaded. Please choose a file.");
            request.getRequestDispatcher("MidtermReport.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Edit Weekly Report Servlet";
    }
}

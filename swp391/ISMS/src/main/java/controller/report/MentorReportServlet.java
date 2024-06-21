package controller.report;

import dao.AccountDAO;
import dao.FinalReportDAO;
import dao.MidtermReportDAO;
import dao.WeeklyReportDAO;
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
            }

//            MentorReportDAO reportDAO = new MentorReportDAO();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");

            Account account = accountDAO.getAccountByEmail(email);
            int mentorId = account.getMentorId();

           switch (reportTitle){
               case "Weekly Report":                   
                    WeeklyReportDAO WLD = new WeeklyReportDAO();
                    WLD.UploadWLReport(email, email, reportTitle, mentorId, email);
                    break;
                case "Midterm Report":
                    MidtermReportDAO MTD = new MidtermReportDAO();
                    MTD.UploadMTReport(email, reportTitle, mentorId, email);
                    break;
                case "Final Report":
                    FinalReportDAO FND = new FinalReportDAO();
                    FND.UploadFNReport(email, reportTitle, mentorId, email);
                    break;
                default:
                    request.setAttribute("error", "Invalid report title.");
                    break;
           }

            request.setAttribute("message", "File uploaded successfully to the 'file_upload' directory.");
            request.getRequestDispatcher("mentorreportlist").forward(request, response);

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

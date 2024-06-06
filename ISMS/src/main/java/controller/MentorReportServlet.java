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
            InputStream fileContent = filePart.getInputStream();


           Path uploadDirectory = Paths.get("C:\\Users\\duong\\OneDrive\\Desktop\\SWP391\\swp391\\ISMS\\src\\file_upload");


            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }

            // Đặt tên tệp cụ thể trong thư mục đích
            Path filePath = uploadDirectory.resolve(originalFileName);

            // Lưu tệp vào thư mục đích
            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);

            MentorReportDAO reportDAO = new MentorReportDAO();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            System.out.println(email);

            Account account = accountDAO.getAccountByEmail(email);
            int mentorId = account.getMentorId();
            System.out.println(mentorId);
            reportDAO.UploadReport(reportTitle, mentorId, fileContent); 

            request.setAttribute("message", "File uploaded successfully to the 'fileupload' directory.");
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

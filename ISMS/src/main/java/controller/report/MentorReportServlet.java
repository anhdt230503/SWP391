//package controller.report;
//
//import dao.AccountDAO;
//import dao.FinalReportDAO;
//import dao.MidtermReportDAO;
//import dao.WeeklyReportDAO;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.Part;
//import java.nio.file.StandardCopyOption;
//import model.Account;
//
//@MultipartConfig
//public class MentorReportServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String reportTitle = request.getParameter("reportTitle");
//        String reportDescription = request.getParameter("reportDescription");
//        Part filePart = request.getPart("reportFile");
//        AccountDAO accountDAO = new AccountDAO();
//
//        if (filePart != null) {
//            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//            Path uploadDirectory = Paths.get("\\swp391\\ISMS\\src\\file_upload");
//            if (!Files.exists(uploadDirectory)) {
//                Files.createDirectories(uploadDirectory);
//            }
//            Path filePath = uploadDirectory.resolve(originalFileName);
//            try (InputStream fileContent = filePart.getInputStream()) {
//                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
//            }
//
//            HttpSession session = request.getSession();
//            String email = (String) session.getAttribute("email");
//
//            Account account = accountDAO.getAccountByEmail(email);
//            int mentorId = account.getMentorId();
//            int internId = account.getInternId();
//
//            if ("Weekly Report".equals(reportTitle)) {
//                WeeklyReportDAO WLD = new WeeklyReportDAO();
//               WLD.UploadWLReport(reportTitle, email, reportDescription, internId, email);
//            } else if ("Midterm Report".equals(reportTitle)) {
//                MidtermReportDAO MTD = new MidtermReportDAO();
//                MTD.UploadMTReport(reportTitle, reportDescription, mentorId, originalFileName);
//            } else if ("Final Report".equals(reportTitle)) {
//                FinalReportDAO FND = new FinalReportDAO();
//                FND.UploadFNReport(reportTitle, reportDescription, mentorId, originalFileName);
//            } else {
//                request.setAttribute("error", "Invalid report title.");
//            }
//
//            request.setAttribute("message", "File uploaded successfully to the 'file_upload' directory.");
//            response.sendRedirect(request.getContextPath() + "/MidtermReportList");
//
//        } else {
//            request.setAttribute("message", "File upload failed. Please try again.");
//            request.getRequestDispatcher("Midtter,.jsp").forward(request, response);
//        }
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }
//}

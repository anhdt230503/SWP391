/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.report;

import dao.AccountDAO;
import dao.WeeklyReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
 *
 * @author duong
 */
@MultipartConfig
public class UploadWeekly extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadWeekly</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadWeekly at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            }
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            Account account = accountDAO.getAccountByEmail(email);
            int studenId = account.getInternId();
            WeeklyReportDAO WLD = new WeeklyReportDAO();
            WLD.UploadWLReport(reportTitle, reportweek, reportDescription, studenId, originalFileName);
            request.setAttribute("message", "File uploaded successfully.");
            response.sendRedirect(request.getContextPath() + "/WeeklyReportList");

        } else {
            request.setAttribute("message", "File upload failed. Please try again.");
            request.getRequestDispatcher("MidtermReport.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

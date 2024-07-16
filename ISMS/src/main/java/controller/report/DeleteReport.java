/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.report;

import dao.FinalReportDAO;
import dao.MidtermReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author duong
 */
public class DeleteReport extends HttpServlet {

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
            out.println("<title>Servlet DeleteReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteReport at " + request.getContextPath() + "</h1>");
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
        int internId = Integer.parseInt(request.getParameter("internId"));
        MidtermReportDAO midtermReportDAO = new MidtermReportDAO();
        boolean deleted = midtermReportDAO.deleteReport(internId);
        HttpSession session = request.getSession();
        if (deleted) {
            session.setAttribute("deleteMessage", "Report deleted successfully.");
        } else {
            session.setAttribute("error", "Failed to delete report.");
        }
        response.sendRedirect(request.getContextPath() + "/MidtermReportList");
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
        int internId = Integer.parseInt(request.getParameter("internId"));

        // Delete from database (example using DAO pattern)
        FinalReportDAO finalReportDAO = new FinalReportDAO();
        boolean deleted = finalReportDAO.deleteReport(internId);

        // Set message in session
        HttpSession session = request.getSession();
        if (deleted) {
            session.setAttribute("deleteMessage", "Report deleted successfully.");
        } else {
            session.setAttribute("deleteMessage", "Failed to delete report.");
        }
        response.sendRedirect(request.getContextPath() + "/FinalReportList");
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

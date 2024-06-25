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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.WeeklyReport;

/**
 *
 * @author duong
 */
public class WeeklyReportList extends HttpServlet {

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
            out.println("<title>Servlet WeeklyReportList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WeeklyReportList at " + request.getContextPath() + "</h1>");
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
        List<WeeklyReport> listOfWeeklyReport;
        WeeklyReportDAO weeklyReportDAO = new WeeklyReportDAO();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByEmail(email);
        int role = account.getRoleId();

        if (role == 2) {
            listOfWeeklyReport = weeklyReportDAO.getallweeklyreport();
            request.setAttribute("listOfWeeklyReport", listOfWeeklyReport);
        } else if (role == 3) {
            listOfWeeklyReport = weeklyReportDAO.getallweeklyreport();
            request.setAttribute("listOfWeeklyReport", listOfWeeklyReport);
        } else if (role == 4) {
            int internId =account.getInternId();
            listOfWeeklyReport = weeklyReportDAO.getallweeklyreportbyInternId(internId);
            request.setAttribute("listOfWeeklyReport", listOfWeeklyReport);
        }

        listOfWeeklyReport = weeklyReportDAO.getallweeklyreport();
        request.setAttribute("listOfWeeklyReport", listOfWeeklyReport);
        request.getRequestDispatcher("/WeeklyReport.jsp").forward(request, response);
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
        processRequest(request, response);
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

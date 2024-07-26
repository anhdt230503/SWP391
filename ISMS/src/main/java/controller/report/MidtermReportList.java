/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.report;

import dao.AccountDAO;
import dao.FinalReportDAO;
import dao.MidtermReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Intern;

/**
 *
 * @author duong
 */
public class MidtermReportList extends HttpServlet {

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
            out.println("<title>Servlet MidtermReportList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MidtermReportList at " + request.getContextPath() + "</h1>");
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
        List<Intern> listOfMidtermReport;
        List<Integer> totalmission;
        List<Integer> totalfinished;
        MidtermReportDAO mtd = new MidtermReportDAO();
        FinalReportDAO finalReportDAO = new FinalReportDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByEmail(email);
        totalmission = finalReportDAO.getTotalMissions();
        totalfinished = finalReportDAO.getTotalFinished();

        int role = account.getRoleId();
        if (role == 4) {
            int internId = account.getInternId();
            listOfMidtermReport = finalReportDAO.getStudentById1(internId);
            totalmission = finalReportDAO.getTotalMissions();
            totalfinished = finalReportDAO.getTotalFinished();
            request.setAttribute("listOfMidtermReport", listOfMidtermReport);
            request.setAttribute("totalmission", totalmission);
            request.setAttribute("totalfinished", totalfinished);
        } else if (role == 3) {
            int mentorId = account.getMentorId();
            listOfMidtermReport = finalReportDAO.getStudent(mentorId);
            totalmission = finalReportDAO.getTotalMissions();
            totalfinished = finalReportDAO.getTotalFinished();
            request.setAttribute("listOfMidtermReport", listOfMidtermReport);
            request.setAttribute("totalmission", totalmission);
            request.setAttribute("totalfinished", totalfinished);
        } else if (role == 2) {
            listOfMidtermReport = finalReportDAO.getStudentAll();
            totalmission = finalReportDAO.getTotalMissions();
            totalfinished = finalReportDAO.getTotalFinished();
            request.setAttribute("listOfMidtermReport", listOfMidtermReport);
            request.setAttribute("totalmission", totalmission);
            request.setAttribute("totalfinished", totalfinished);
        }
        request.setAttribute("totalmission", totalmission);
        request.setAttribute("totalfinished", totalfinished);
        request.getRequestDispatcher("/MidtermReport.jsp").forward(request, response);
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

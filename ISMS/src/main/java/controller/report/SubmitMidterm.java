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
import model.Account;
import model.Intern;

/**
 *
 * @author duong
 */
public class SubmitMidterm extends HttpServlet {

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
            out.println("<title>Servlet SubmitMidterm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitMidterm at " + request.getContextPath() + "</h1>");
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
        String internId = request.getParameter("internId");
        FinalReportDAO fn = new FinalReportDAO();
        Intern student = fn.getStudentById(Integer.parseInt(internId));
        request.setAttribute("student", student);
        request.getRequestDispatcher("CheckMidterm.jsp").forward(request, response);
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
        String comment = request.getParameter("comment");
        boolean excellent = request.getParameter("Excellent") != null;
        boolean veryGood = request.getParameter("VeryGood") != null;
        boolean good = request.getParameter("Good") != null;
        boolean average = request.getParameter("Average") != null;
        boolean poor = request.getParameter("Poor") != null;
        MidtermReportDAO midtermReportDAO = new MidtermReportDAO();
        if (midtermReportDAO.isInternIdExists(internId)) {
            request.setAttribute("errorMessage", "InternId already exists. Please update instead.");
           request.getRequestDispatcher("/FinalReport.jsp").forward(request, response);
        }else {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByEmail(email);
        int mentorId = account.getMentorId();
        
        midtermReportDAO.insertMidtermReport(mentorId, internId, excellent, veryGood, good, average, poor,comment);
        response.sendRedirect(request.getContextPath() + "/MidtermReportList");
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

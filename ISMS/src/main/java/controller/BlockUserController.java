/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.ManagerDAO;
import dao.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author ACER
 */
public class BlockUserController extends HttpServlet {

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
            out.println("<title>Servlet BlockUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlockUserController at " + request.getContextPath() + "</h1>");
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
        String mentorIdParam = request.getParameter("mentorId");
        String managerIdParam = request.getParameter("managerId");
        String statusParam = request.getParameter("status");

        AccountDAO accountDAO = new AccountDAO();

        if (mentorIdParam != null && !mentorIdParam.isEmpty()) {
            Integer mentorId = Integer.parseInt(mentorIdParam);
            int status = Integer.parseInt(statusParam);

            if (status == 1) {
                accountDAO.updateAccountMentorStatus(mentorId, 0);
            } else {
                accountDAO.updateAccountMentorStatus(mentorId, 1);
            }
            request.getRequestDispatcher("MentorManageController").forward(request, response);
        } else if (managerIdParam != null && !managerIdParam.isEmpty()) {
            Integer managerId = Integer.parseInt(managerIdParam);
            int status = Integer.parseInt(statusParam);

            if (status == 1) {
                accountDAO.updateAccountManagerStatus(managerId, 0);
            } else {
                accountDAO.updateAccountManagerStatus(managerId, 1);
            }
            request.getRequestDispatcher("ManagerManageController").forward(request, response);
        } 
   
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

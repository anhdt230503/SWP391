/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.InternAssignDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.InternAssign;

/**
 *
 * @author haidu
 */
public class SelectInternServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final int MENTOR_LIMIT = 3;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] selectedInterns = request.getParameterValues("selectedInterns");
        AccountDAO accountDAO = new AccountDAO();
        InternAssignDAO internAssignDAO = new InternAssignDAO();
        InternAssign internAssign = new InternAssign();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        System.out.println(email);

        // get mentorId from Account
        Account account = accountDAO.getAccountByEmail(email);
        int mentorId = account.getMentorId();
        System.out.println(mentorId);

        if (selectedInterns != null && mentorId != 0) {
            int currentInternCount = internAssignDAO.getInternCountByMentor(mentorId);
            if (currentInternCount + selectedInterns.length > MENTOR_LIMIT) {
                request.setAttribute("errorMessage", "You can only select up to " + MENTOR_LIMIT + " interns.");
            } else {
                for (String internId : selectedInterns) {
                    System.out.println(internId);
                    internAssign.setInternId(Integer.parseInt(internId));
                    internAssign.setMentorId(mentorId);
                    internAssignDAO.insertInternAssign(internAssign);
                }
                request.setAttribute("successMessage", "Interns selected successfully.");
            }
        } else {
            request.setAttribute("errorMessage", "No interns selected or invalid mentor.");
        }

        int finalInternCount = internAssignDAO.getInternCountByMentor(mentorId);
        request.setAttribute("currentInternCount", finalInternCount);
        request.setAttribute("mentorLimit", MENTOR_LIMIT);
        request.getRequestDispatcher("internList").forward(request, response);
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

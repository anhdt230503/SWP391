/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Account;
import model.Mentor;

/**
 *
 * @author ACER
 */
public class EditMentorProfileByHR extends HttpServlet {

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
            out.println("<title>Servlet EditProfileByHR</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileByHR at " + request.getContextPath() + "</h1>");
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
        int mentorId = Integer.parseInt(request.getParameter("mentorId"));
        MentorDAO mentorDAO = new MentorDAO();
        
        Mentor mentor = mentorDAO.getMentorById(mentorId);
        request.setAttribute("mentor", mentor);
        
        request.getRequestDispatcher("EditMentorProfileByHR.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");

        if (account != null) {
            int mentorId = Integer.parseInt(request.getParameter("mentorId"));
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String birthDateString = request.getParameter("birthDate");

            java.sql.Date sqlBirthDate = null;
            if (birthDateString != null && !birthDateString.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date utilBirthDate = sdf.parse(birthDateString);
                    sqlBirthDate = new java.sql.Date(utilBirthDate.getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            AccountDAO accountDAO = new AccountDAO();
            MentorDAO mentorDAO = new MentorDAO();
            
            Mentor mentor = mentorDAO.getMentorById(mentorId);
            
            if(mentor != null && !mentor.getEmail().equals(email)&& accountDAO.isEmailExists(email)){
                request.setAttribute("errorMessage", "Email đã tồn tại");
                request.setAttribute("mentor", mentor);
                request.getRequestDispatcher("EditMentorProfileByHR.jsp").forward(request, response);
            }else{
            
            
            mentorDAO.updateProfileMentorByHR(mentorId, fullname,email, sqlBirthDate, phoneNumber);
            
            accountDAO.updateEmailAccountMentor(email, mentorId);
            request.getRequestDispatcher("MentorManageController").forward(request, response);
            }
            
            
        } else {
            response.sendRedirect("login");
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

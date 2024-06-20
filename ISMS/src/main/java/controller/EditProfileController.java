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
import java.util.Date;
import java.text.SimpleDateFormat;
import model.Account;
import model.Manager;
import model.Mentor;

/**
 *
 * @author ACER
 */
public class EditProfileController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet EditProfileController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       HttpSession session = request.getSession();
       Account acc  = (Account) session.getAttribute("acc");
       
       MentorDAO mentorDAO = new MentorDAO();
       ManagerDAO managerDAO = new ManagerDAO();
       
       Integer mentorId = acc.getMentorId();
       Integer managerId = acc.getManagerId();
       
       
           String fullName = request.getParameter("fullname");
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

        if (mentorId != null) {
            mentorDAO.updateProfileMentor(mentorId, fullName, sqlBirthDate, phoneNumber);
//            Mentor mentor = mentorDAO.getMentorById(mentorId);
//            request.setAttribute("a",mentor);
            request.setAttribute("successMessage", "Thay đổi thông tin thành công.");
        } 
        
        if (managerId != null) {
            managerDAO.updateProfileManager(managerId, fullName, sqlBirthDate, phoneNumber);
//            Manager manager = managerDAO.getManagerById(managerId);
//            request.setAttribute("a", manager);
            request.setAttribute("successMessage", "Thay đổi thông tin thành công.");
        }
     request.getRequestDispatcher("UserProfileController").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

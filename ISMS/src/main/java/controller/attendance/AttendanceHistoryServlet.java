/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import dao.AccountDAO;
import dao.AttendanceDAO;
import dao.InternDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Attendance;
import model.Intern;

/**
 *
 * @author haidu
 */
public class AttendanceHistoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        AccountDAO accountDAO = new AccountDAO();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);

        int roleId = account.getRoleId();

        // nếu là HR thì thấy tất cả các intern trong hệ thống
        if (roleId == 1) {
            InternDAO internDAO = new InternDAO();
            List<Intern> list = internDAO.getAllIntern();
            request.setAttribute("internList", list);
            request.getRequestDispatcher("AllInternAttendance.jsp").forward(request, response);

            // nếu là Mentor thì thấy list intern mà họ quản lý 
        } else if (roleId == 3) {
            int mentorId = account.getMentorId();
            InternDAO internDAO = new InternDAO();
            List<Intern> list = internDAO.getAllInternForMentor(mentorId);
            request.setAttribute("internList", list);
            request.getRequestDispatcher("AllInternAttendance.jsp").forward(request, response);

            // nếu là intern thì xem history của họ
        } else if (roleId == 4) {
            int internId = account.getInternId();
            InternDAO internDAO = new InternDAO();
            Intern intern = internDAO.getInternByInternId(internId);
            String midtermWorkTime = String.format("%.2f", intern.getMidtermWorkTime());
            String finalWorkTime = String.format("%.2f", intern.getFinalWorkTime());

            List<Attendance> attendance = attendanceDAO.getAllAttendance(internId);

            request.setAttribute("midtermWorkTime", midtermWorkTime);
            request.setAttribute("finalWorkTime", finalWorkTime);
            request.setAttribute("listOfAttendance", attendance);
            request.getRequestDispatcher("AttendanceReport.jsp").forward(request, response);
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

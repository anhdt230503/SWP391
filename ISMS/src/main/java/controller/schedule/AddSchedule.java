/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.schedule;

import dao.AccountDAO;
import dao.ScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Schedule;

/**
 *
 * @author duong
 */
public class AddSchedule extends HttpServlet {

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

        List<Schedule> schedulelist;
        ScheduleDAO sclDao = new ScheduleDAO();
        schedulelist = sclDao.getlistSchedule();
        request.setAttribute("schedulelist", schedulelist);
        request.getRequestDispatcher("AddSchedule.jsp").forward(request, response);
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
        String missionName = request.getParameter("missionName");
        String dateSchedule = request.getParameter("date-schedule");
        int hoursSchedule = Integer.parseInt(request.getParameter("hours-schedule"));
        String description = request.getParameter("description");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedStartDate = dateFormat.parse(dateSchedule);
            Timestamp date_time = new Timestamp(parsedStartDate.getTime());

            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccountByEmail(email);
            int internId = account.getInternId();

            ScheduleDAO scl = new ScheduleDAO();
            scl.Addschedule(internId, missionName, 0, date_time, hoursSchedule, description);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/ScheduleList");
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

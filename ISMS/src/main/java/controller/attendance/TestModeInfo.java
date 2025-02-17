/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import dao.AccountDAO;
import dao.AttendanceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.Account;
import model.Attendance;

/**
 *
 * @author haidu
 */
public class TestModeInfo extends HttpServlet {

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
            out.println("<title>Servlet TestModeInfo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestModeInfo at " + request.getContextPath() + "</h1>");
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
        // lấy internId thông qua Session
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);
        AttendanceDAO attendanceDao = new AttendanceDAO();

        int internSessionId = account.getInternId();
        int roleId = account.getRoleId();

        if (roleId == 4) {

            // lấy id từ định dạng date trong AttendanceReport.jsp
            String attendanceId = request.getParameter("date");
            Attendance attendance = attendanceDao.getAttendanceToTest(attendanceId, internSessionId);

            Date attendDate = attendance.getAttendDate();
//        System.out.println(attendDate);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(attendDate);
//        System.out.println("Date String: " + formattedDate);

            String checkoutTimeString = request.getParameter("checkoutTime");
//        System.out.println(checkoutTime);

            String timeStampString = formattedDate + " " + checkoutTimeString + ":00";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                java.util.Date parsedDate = dateFormat.parse(timeStampString);
                java.sql.Timestamp checkoutTime = new java.sql.Timestamp(parsedDate.getTime());
                session.setAttribute("checkoutTime", checkoutTime);
            } catch (ParseException e) {
                // Xử lý lỗi nếu chuỗi không đúng định dạng
                System.err.println("Lỗi khi chuyển đổi chuỗi sang timestamp: " + e.getMessage());
            }
            session.setAttribute("attendDate", attendDate);
            request.getRequestDispatcher("attendanceHistory").forward(request, response);
        } else if (roleId == 1) {
            String dateToCheckString = request.getParameter("dateToCheck");
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng của input type="date"
                LocalDate localDate = LocalDate.parse(dateToCheckString, formatter);
                session.setAttribute("localDate", localDate);
            } catch (DateTimeParseException e) {
                // Xử lý lỗi nếu chuỗi không hợp lệ
                System.err.println("Lỗi khi chuyển đổi ngày tháng: " + e.getMessage());
            }
            request.getRequestDispatcher("attendanceHistory").forward(request, response);
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

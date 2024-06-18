/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import dao.AccountDAO;
import dao.AttendanceDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import java.sql.Date;
import java.sql.Timestamp;
import model.Account;
import model.Attendance;
import org.apache.commons.net.util.SubnetUtils;
import service.AttendanceService;

/**
 *
 * @author haidu
 */
public class CheckInTime extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AccountDAO accountDAO = new AccountDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        AttendanceService attendanceService = new AttendanceService();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);

        int internId = account.getInternId();
        int roleId = account.getRoleId();

        if (roleId == 4) {
//            String ipAddress = request.getRemoteAddr();
            String ipAddress = "10.33.37.211";
            // Sử dụng dải IP của trường
            String ipRange = "10.33.0.0/17"; // Dải IP từ 10.33.0.0 đến 10.33.127.255
            SubnetUtils utils = new SubnetUtils(ipRange);
            boolean isAllowed = utils.getInfo().isInRange(ipAddress);

            if (isAllowed) {
                // lấy ngày giờ hiện tại
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                // lấy ngày tháng năm từ đối tượng timestamp
                Date date = new Date(currentTimestamp.getTime());
                // dùng date của ngày hôm đó và internId để kiểm tra xem hôm đó đã có bản ghi nào trong DB hay chưa
                Attendance attendance = attendanceDAO.getAttendanceByDate(date, internId);
                // nếu chưa có bản ghi trong ngày hôm đó thì 
                if (attendance == null) {
                    // bắt đầu thời gian tồn tại của session
                    attendanceService.sessionCreated(new HttpSessionEvent(session));
                    // và insert vào DB 1 bản ghi mới
                    Attendance attendance1 = new Attendance();
                    attendance1.setInternId(internId);
                    attendance1.setCheckInTime(currentTimestamp);
                    attendance1.setStatus(Attendance.AttendanceStatus.PRESENT);
                    attendanceDAO.insertAttendance(attendance1);
                    request.setAttribute("message", "Attendance Successfull!");
                } else {
                    request.setAttribute("message", "You have attandance already for today !!!");
                }
            }
        }
        request.getRequestDispatcher("attendanceHistory").forward(request, response);
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

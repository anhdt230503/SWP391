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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                Timestamp uploadDate = new Timestamp(System.currentTimeMillis());
                // lấy ngày tháng năm từ đối tượng timestamp
                LocalDate importDate = uploadDate.toLocalDateTime().toLocalDate();
//                LocalDate attendDate = importDate.plusDays(2);
                LocalDate attendDate = importDate;
//                System.out.println(java.sql.Date.valueOf(attendDate));
                // dùng date của ngày hôm đó và internId để kiểm tra xem hôm đó đã có bản ghi nào trong DB hay chưa
                Attendance attendance = attendanceDAO.getAttendanceByDate(java.sql.Date.valueOf(attendDate), 1);

                // cần đổi sang localdatetime cho thuộc tính "checkInTime" để xử lý cộng thêm 2 ngày giống với attendDate
                LocalDateTime checkInTime = uploadDate.toLocalDateTime();
//                LocalDateTime checkInTimeAfter = checkInTime.plusDays(2);
                LocalDateTime checkInTimeAfter = checkInTime;
                LocalTime checkInLocalTime = checkInTimeAfter.toLocalTime();

                LocalTime startTime = LocalTime.of(7, 30);
                LocalTime endTime = LocalTime.of(8, 30);

//                System.out.println(java.sql.Timestamp.valueOf(checkInTimeAfter));
                if (attendance != null && attendance.getCheckInTime() == null) {
                    // bắt đầu thời gian tồn tại của session
                    attendanceService.sessionCreated(new HttpSessionEvent(session));
                    // và insert vào DB 1 bản ghi mới
                    Attendance attendance1 = new Attendance();
//                    attendance1.setStatus(Attendance.AttendanceStatus.PRESENT);
                    attendance1.setCheckInTime(java.sql.Timestamp.valueOf(checkInTimeAfter));
                    attendance1.setAttendDate(java.sql.Date.valueOf(attendDate));
                    attendance1.setInternId(internId);
                    attendanceDAO.updateCheckInTime(attendance1);
                    if (checkInLocalTime.isAfter(startTime) && checkInLocalTime.isBefore(endTime)) {
                        attendance1.setStatus(Attendance.AttendanceStatus.PRESENT);
                        attendance1.setAttendDate(java.sql.Date.valueOf(attendDate));
                        attendance1.setInternId(internId);
                        attendanceDAO.updateStatus(attendance);
                    } else {
                        attendance1.setStatus(Attendance.AttendanceStatus.ABSENT);
                        attendance1.setAttendDate(java.sql.Date.valueOf(attendDate));
                        attendance1.setInternId(internId);
                        attendanceDAO.updateStatus(attendance);
                    }

                } else {
                    request.setAttribute("message", "You have attandance already for today !");
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

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
import java.text.SimpleDateFormat;
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

            LocalDate attendDate = null;
            Timestamp currentTimestamp = null;
            LocalDateTime checkInTimeAfter = null;

            if (isAllowed) {
                // xử lý nếu test mode là "on" thì sẽ lấy date từ cái mà người dùng chọn để test
                String testMode = (String) session.getAttribute("testMode");
//                System.out.println("Test Mode in check in time: " + testMode);
                if (testMode.equals("on")) {
                    Date date = (Date) session.getAttribute("attendDate");
                    attendDate = date.toLocalDate();
                    currentTimestamp = new Timestamp(System.currentTimeMillis());
                    LocalTime localTime = currentTimestamp.toLocalDateTime().toLocalTime();
                    LocalDateTime checkInTime = LocalDateTime.of(attendDate, localTime);
//                    System.out.println("Check in time 'ON': " + checkInTime);
                    checkInTimeAfter = checkInTime;
                    session.setAttribute("checkInTime", checkInTimeAfter);
                // xử lý bth nếu k ở chế độ test - lấy ngày giờ hiện tại
                } else {
                    // lấy ngày giờ hiện tại
                    currentTimestamp = new Timestamp(System.currentTimeMillis());
                    // lấy ngày tháng năm từ đối tượng timestamp
                    LocalDate importDate = currentTimestamp.toLocalDateTime().toLocalDate();
//                    attendDate = importDate.plusDays(2);
                    attendDate = importDate;
                    // dùng date của ngày hôm đó và internId để kiểm tra xem hôm đó đã có bản ghi nào trong DB hay chưa
                    // cần đổi sang localdatetime cho thuộc tính "checkInTime" để xử lý cộng thêm 2 ngày giống với attendDate
                    LocalDateTime checkInTime = currentTimestamp.toLocalDateTime();
//                    checkInTimeAfter = checkInTime.plusDays(2);
                    System.out.println("Check in time 'OFF': " + checkInTime);
                    checkInTimeAfter = checkInTime;
                }
                Attendance attendance = attendanceDAO.getAttendanceByDate(java.sql.Date.valueOf(attendDate), internId);

                if (attendance != null && attendance.getCheckInTime() == null && attendance.getStatus() == Attendance.AttendanceStatus.NOT_YET) {
                    // bắt đầu thời gian tồn tại của session
                    attendanceService.sessionCreated(new HttpSessionEvent(session));
                    // và update check in time và status cho bản ghi điểm danh cho ngày hôm đó
                    Attendance attendance1 = new Attendance();
                    attendance1.setCheckInTime(java.sql.Timestamp.valueOf(checkInTimeAfter));
                    attendance1.setStatus(Attendance.AttendanceStatus.PRESENT);
                    attendance1.setAttendDate(java.sql.Date.valueOf(attendDate));
                    attendance1.setInternId(internId);
                    attendanceDAO.updateCheckInTime(attendance1);
//                    System.out.println(attendance1);
                } else if (attendance.getStatus() == Attendance.AttendanceStatus.ABSENT) {
                    request.setAttribute("message", "You have been recorded as absent for today !");
                } else {
                    request.setAttribute("message", "You have attandance already for today !");
                }
            }
        }
        request.getRequestDispatcher("attendanceHistory").forward(request, response);
    }

}

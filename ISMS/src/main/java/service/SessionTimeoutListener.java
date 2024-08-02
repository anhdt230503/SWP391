/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AccountDAO;
import dao.AttendanceDAO;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import model.Account;
import model.Attendance;

public class SessionTimeoutListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Session được tạo
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        AccountDAO accountDAO = new AccountDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);
        int internId = account.getInternId();

        Timestamp checkoutTime = null;
        LocalDate attendDate = null;
        // Lấy thời gian tồn tại của session
        long startTime = 0;
        // Tính toán thời gian tồn tại của session
        long endTime = 0;

        String testMode = (String) session.getAttribute("testMode");
//        System.out.println("Test Mode in check out time: " + testMode);
        if (testMode.equals("on")) {
            checkoutTime = (Timestamp) session.getAttribute("checkoutTime");
//            System.out.println("Mode 'ON': " + checkoutTime);
            Date date = (Date) session.getAttribute("attendDate");
            Attendance attendance = attendanceDAO.getAttendanceByDate(java.sql.Date.valueOf(date.toLocalDate()), internId);
            Timestamp checkInTime = attendance.getCheckInTime();
            startTime = checkInTime.getTime();
            endTime = checkoutTime.getTime();
//            System.out.println("Mode 'ON': " + endTime);
        } else {
            checkoutTime = new Timestamp(System.currentTimeMillis());
//            System.out.println("Mode 'OFF': " + checkoutTime);
            startTime = (long) session.getAttribute("startTime");
            endTime = System.currentTimeMillis();
//            System.out.println("Mode 'OFF': " + endTime);
        }
        attendDate = checkoutTime.toLocalDateTime().toLocalDate();
        Attendance attendance = attendanceDAO.getAttendanceByDate(java.sql.Date.valueOf(attendDate), internId);

        long duration = attendance.getDuration();
        long newDuration = duration + (endTime - startTime) / 1000;
        long hours = TimeUnit.SECONDS.toHours(newDuration);
        long minutes = TimeUnit.SECONDS.toMinutes(newDuration) - TimeUnit.HOURS.toMinutes(hours);
        long seconds = newDuration % 60;

        String totalWorkTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        // nếu đã có bản ghi trong DB thì sẽ update checkout time, total work time, duration
        if (attendance != null && attendance.getStatus() == Attendance.AttendanceStatus.PRESENT) {
            System.out.println("AHAHAHAHAH");
            Attendance attendance1 = new Attendance();
            attendance1.setCheckOutTime(checkoutTime);
            attendance1.setTotalWorkTime(totalWorkTime);
            attendance1.setDuration(newDuration);
            attendance1.setAttendDate(java.sql.Date.valueOf(attendDate));
            attendance1.setInternId(internId);
            attendance1.setStatus(Attendance.AttendanceStatus.PRESENT);
            attendanceDAO.updateCheckOutTime(attendance1);
        }

    }

}

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

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(currentTimestamp);

        Date date = new Date(currentTimestamp.getTime());
        Attendance attendance = attendanceDAO.getAttendanceByDate(date, internId);
        Timestamp checkInTime = attendance.getCheckInTime();
        System.out.println(checkInTime);

        // Lấy thời gian tồn tại của session
        long startTime = (long) session.getAttribute("startTime");

        // Tính toán thời gian tồn tại của session
        long endTime = System.currentTimeMillis();

        long duration = attendance.getDuration();
        long newDuration = duration + (endTime - startTime) / 1000;
        long hours = TimeUnit.SECONDS.toHours(newDuration);
        long minutes = TimeUnit.SECONDS.toMinutes(newDuration);
        long seconds = newDuration % 60;

        String totalWorkTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
//        System.out.println(totalWorkTime);

        // nếu đã có bản ghi trong DB thì sẽ update checkout time, total work time, duration
        if (attendance != null) {
            Attendance attendance1 = new Attendance();
            attendance1.setCheckOutTime(currentTimestamp);
            attendance1.setTotalWorkTime(totalWorkTime);
            attendance1.setDuration(newDuration);
            attendance1.setCheckInTime(checkInTime);
            attendance1.setInternId(internId);
            attendanceDAO.updateAllAttendance(attendance1);
        }

    }

}

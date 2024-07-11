/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import dao.AttendanceDAO;
import dao.InternDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import model.Attendance;
import model.Intern;

/**
 *
 * @author haidu
 */
public class AttendanceEndOfDay extends HttpServlet {

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
        LocalDate todayDate = null;
        AttendanceDAO attendanceDao = new AttendanceDAO();
        InternDAO internDao = new InternDAO();
        HttpSession session = request.getSession();

        String testMode = (String) session.getAttribute("testMode");
//        System.out.println("Test Mode in check in time: " + testMode);
        if (testMode.equals("on")) {
            todayDate = (LocalDate) session.getAttribute("localDate");
        } else {
            todayDate = LocalDate.now();
        }

        // thực hiện kiểm tra các bản ghi có trạng thái "Not yet" trong ngày hôm đó 
        List<Attendance> notYetList = attendanceDao.getNotYetAttendance(java.sql.Date.valueOf(todayDate));
        // HR sẽ update attendance status vào mỗi cuối ngày 
        for (Attendance i : notYetList) {
            // nếu hôm đó intern chưa điểm danh thì sẽ đổi status thành "Absent"
            if (i.getCheckInTime() == null) {
                int internId = i.getInternId();
                Attendance attendance = new Attendance();
                attendance.setStatus(Attendance.AttendanceStatus.ABSENT);
                attendance.setAttendDate(java.sql.Date.valueOf(todayDate));
                attendance.setInternId(internId);
                attendanceDao.updateEndOfDay(attendance);
            }
        }

        List<Intern> internList = internDao.getAllIntern();
        int stopIndex1 = 34;
        int currentIndex1 = 0;
        for (Intern intern : internList) {
            List<Attendance> internAttendances = attendanceDao.getAllAttendance(intern.getInternId());

            // nếu ngày thứ 36 có check in time bằng null thì thực hiện tính toán tổng thời gian làm việc của Midterm (từ 1 đến 35)
            Attendance internAttendanceAt35 = internAttendances.get(35); // ngày thứ 36
            long midtermDuration = 0;

            // xử lý cộng dồn cả duration của midterm vào final work time
            double getMidtermWorkTime = intern.getMidtermWorkTime();
//            System.out.println(getMidtermWorkTime);
            int getHours = (int) getMidtermWorkTime;
//            System.out.println(getHours);
            int getMinutes = (int) Math.round((getMidtermWorkTime - getHours) * 100);
//            System.out.println(getMinutes);
            Duration duration = Duration.ofHours(getHours).plusMinutes(getMinutes);
            long getMidtermDuration = duration.getSeconds();
            long finalDuration = 0 + getMidtermDuration;

            if (internAttendanceAt35.getCheckInTime() == null) {
                for (Attendance a : internAttendances) {
                    if (a.getStatus() == Attendance.AttendanceStatus.PRESENT) {
                        long duration1 = a.getDuration();
                        midtermDuration += duration1;
                        long hours = TimeUnit.SECONDS.toHours(midtermDuration);
                        long minutes = TimeUnit.SECONDS.toMinutes(midtermDuration) - TimeUnit.HOURS.toMinutes(hours);
                        String timeString = hours + "." + minutes;
                        double midtermWorkTime = Double.parseDouble(timeString);
                        intern.setMidtermWorkTime(midtermWorkTime);
                        intern.setInternId(intern.getInternId());
                        internDao.updateMidtermWorkTime(intern);
                        if (currentIndex1 == stopIndex1) {
                            break;
                        }
                        currentIndex1++;
                    }
                }
                currentIndex1 = 0;
            } else {
                for (int k = 35; k < internAttendances.size(); k++) {
                    Attendance internAttendanceFinal = internAttendances.get(k);
                    if (internAttendanceFinal.getStatus() == Attendance.AttendanceStatus.PRESENT) {
                        long duration2 = internAttendanceFinal.getDuration();
                        finalDuration += duration2;
                        long hours = TimeUnit.SECONDS.toHours(finalDuration);
                        long minutes = TimeUnit.SECONDS.toMinutes(finalDuration) - TimeUnit.HOURS.toMinutes(hours);
                        String timeString = hours + "." + minutes;
                        double finalWorkTime = Double.parseDouble(timeString);
                        intern.setFinalWorkTime(finalWorkTime);
                        intern.setInternId(intern.getInternId());
                        internDao.updateFinalWorkTime(intern);
                    }
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

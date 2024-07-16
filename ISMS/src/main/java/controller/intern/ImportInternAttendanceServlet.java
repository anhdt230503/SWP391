/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.intern;

import dao.AttendanceDAO;
import dao.InternDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Attendance;
import model.Intern;
import service.InternService;

/**
 *
 * @author haidu
 */
public class ImportInternAttendanceServlet extends HttpServlet {

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
            out.println("<title>Servlet ImportInternAttendanceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImportInternAttendanceServlet at " + request.getContextPath() + "</h1>");
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
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        InternDAO internDAO = new InternDAO();

        String dateToImportString = request.getParameter("dateToImport");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng của input type="date"
        LocalDate attendDate = LocalDate.parse(dateToImportString, formatter);

        String uploadDateString = request.getParameter("date");
        Timestamp uploadDate = Timestamp.valueOf(uploadDateString);
        LocalDateTime uploadLocalDateTime = uploadDate.toLocalDateTime();
        LocalDate uploadLocalDate = uploadLocalDateTime.toLocalDate();

        if (attendDate.isAfter(uploadLocalDate)) {

            List<Intern> listOfInterns = internDAO.getAllInternByUploadDate(uploadDate);
            int workingDays = 0;

//        List<Attendance> statusList = attendanceDAO.getAllStatus();
            // insert 14 tuần = 98 ngày làm việc trừ thứ 7 và chủ nhật
            while (workingDays < 70) {
                DayOfWeek dayOfWeek = attendDate.getDayOfWeek();
                if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                    for (Intern intern : listOfInterns) {

                        List<Attendance> attendancesEachIntern = attendanceDAO.getAllAttendance(intern.getInternId());
                        if (attendancesEachIntern == null || attendancesEachIntern.isEmpty() || attendancesEachIntern.size() < 70) {
                            Attendance attendance = new Attendance();
                            attendance.setInternId(intern.getInternId());
                            attendance.setAttendDate(java.sql.Date.valueOf(attendDate));
                            attendanceDAO.insertAttendance(attendance);
                        } else if (attendancesEachIntern.size() == 70) {
                            int id = intern.getInternId();
                            int notYetCount = attendanceDAO.getNotYetCountForIntern(id);
                            for (Attendance a : attendancesEachIntern) {
                                if (a.getStatus() == Attendance.AttendanceStatus.ABSENT || a.getStatus() == Attendance.AttendanceStatus.PRESENT) {
                                    request.setAttribute("message1", "You can no longer import or update any attendance records. Because the system already has the data");
                                } else if (notYetCount == 70) {
                                    attendanceDAO.deleteAttendance(intern.getInternId());
                                    Attendance attendance = new Attendance();
                                    attendance.setInternId(intern.getInternId());
                                    attendance.setAttendDate(java.sql.Date.valueOf(attendDate));
                                    attendanceDAO.insertAttendance(attendance);
                                }
                            }
                        }
                    }
                    workingDays++;
                }
                attendDate = attendDate.plusDays(1);
            }
        } else {
            request.setAttribute("message1", "Please choose date again! Because your choice not valid.");
        }
        request.getRequestDispatcher("internList").forward(request, response);
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

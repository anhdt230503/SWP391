///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller.attendance;
//
//import dao.AttendanceDAO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.util.List;
//import model.Attendance;
//
///**
// *
// * @author haidu
// */
//public class UpdateAttendanceStatus extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UpdateAttendanceStatus</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UpdateAttendanceStatus at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//        System.out.println("Đây là Update Attendance Status Servlet");
//        AttendanceDAO attendanceDAO = new AttendanceDAO();
//
//        LocalDate today = LocalDate.now();
//
//        List<Attendance> notYetAttendances = attendanceDAO.getNotYetAttendance(java.sql.Date.valueOf(today));
//
//        for (Attendance attendance : notYetAttendances) {
//            Timestamp checkInTime = attendance.getCheckInTime();
//            if (checkInTime == null) {
//                attendance.setStatus(Attendance.AttendanceStatus.ABSENT);
//                attendance.setAttendDate(java.sql.Date.valueOf(today));
//                attendanceDAO.updateAllNotyetStatus(attendance);
//            }
//        }
//        response.sendRedirect("attendanceHistory");
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}

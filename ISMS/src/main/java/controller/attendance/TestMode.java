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
import model.Account;
import model.Attendance;
import service.AttendanceService;

/**
 *
 * @author haidu
 */
public class TestMode extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountDAO accountDAO = new AccountDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);

        int roleId = account.getRoleId();

        // xử lý trạng thái testMode
        String testMode = request.getParameter("testMode");
//        System.out.println("Test Mode: " + testMode);
        if (roleId == 4) {
            request.getRequestDispatcher("attendanceHistory").forward(request, response);
        } else if (roleId == 1) {
            request.getRequestDispatcher("attendanceHistory").forward(request, response);
        }
        session.setAttribute("testMode", testMode);
    }
}

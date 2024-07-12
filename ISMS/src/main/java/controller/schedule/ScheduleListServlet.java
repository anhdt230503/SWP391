/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.schedule;

import dao.AccountDAO;
import dao.MissionDAO;
import dao.ScheduleMissionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Mission;
import model.ScheduleMission;

/**
 *
 * @author haidu
 */
public class ScheduleListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        ScheduleMissionDAO scheduleMission = new ScheduleMissionDAO();
        MissionDAO missionDao = new MissionDAO();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);

        int internId = account.getInternId();

        List<ScheduleMission> scheduleMissionsList = scheduleMission.getAllScheduleMissions(internId);
        List<Mission> missionList = missionDao.getMissionByInternId(internId);

        request.setAttribute("list", scheduleMissionsList);
        request.setAttribute("missionList", missionList);

        request.getRequestDispatcher("Schedule.jsp").forward(request, response);
    }

}

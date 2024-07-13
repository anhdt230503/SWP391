/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mission;

import com.google.gson.Gson;
import dao.AccountDAO;
import dao.MissionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.List;
import model.Account;
import model.Intern;
import model.Mission;
import model.Account;

@WebServlet(name = "DetailMission", urlPatterns = {"/DetailMission"})
public class DetailMission extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountDAO accountDAO = new AccountDAO();
        MissionDAO missionDAO = new MissionDAO();
        Account account = accountDAO.getAccountByEmail(email);
        int mentorId = account.getMentorId();
        String id = request.getParameter("misId");
        int misId = Integer.parseInt(id);
        Mission mission = missionDAO.getMissionById(misId);
        request.setAttribute("mission", mission);
        request.getRequestDispatcher("DetailMission.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}

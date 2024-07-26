package controller.mission;

import com.google.gson.Gson;
import dao.AccountDAO;
import dao.MentorDAO;
import dao.MissionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Intern;
import model.Mentor;
import model.Mission;

public class MissionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MissionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MissionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MissionDAO missionDAO = new MissionDAO();
        String statusParam = request.getParameter("status");
        List<Mission> missions = null;
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);
        int roleId = account.getRoleId();
        if (roleId == 3) { // Mentor
             int mentorId = account.getMentorId();
        if (statusParam != null && !statusParam.isEmpty()) {
           
            try {
              
                Mission.MissionStatus status = Mission.MissionStatus.valueOf(statusParam);
                missions = missionDAO.getMissionsByMentorId(mentorId); // Add filtering by status if needed
            } catch (IllegalArgumentException e) {
                missions = missionDAO.getMissionsByMentorId(mentorId);
            }
        } else {
            missions = missionDAO.getMissionsByMentorId(mentorId);
        }
    } else if (roleId == 4) { // Intern
        int internId = account.getInternId();
        if (statusParam != null && !statusParam.isEmpty()) {
            try {
               
                Mission.MissionStatus status = Mission.MissionStatus.valueOf(statusParam);
                missions = missionDAO.getMissionsByInternId(internId); // Add filtering by status if needed
            } catch (IllegalArgumentException e) {
                missions = missionDAO.getMissionsByInternId(internId);
            }
        } else {
            missions = missionDAO.getMissionsByInternId(internId);
        }
    }
        MentorDAO mentorDAO = new MentorDAO();
        List<Mentor> listOfMentor = mentorDAO.getAllMentors();
        missionDAO.updateMissionStatusContinuously();
        String jsonMissions = new Gson().toJson(missions);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonMissions);
        request.setAttribute("missions", missions);
        request.setAttribute("listOfMentor", listOfMentor);
        request.getRequestDispatcher("Mission.jsp").forward(request, response);
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

package controller.report;

import dao.AccountDAO;
import dao.FinalReportDAO;
import dao.MidtermReportDAO;
import dao.WeeklyReportDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Account;
import model.FinalReport;
import model.MidtermReport;
import model.WeeklyReport;

/**
 * Servlet implementation class MentorReportList
 */
public class MentorReportList extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        // Forward to JSP for report selection
        request.getRequestDispatcher("MentorReport.jsp").forward(request, response);
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
        String reportTitle = request.getParameter("reportTitle");
        List<WeeklyReport> listOfWeeklyReport;
        List<MidtermReport> listOfMidtermReport;
        List<FinalReport> listOfFinalReport;
        WeeklyReportDAO weeklyReportDAO = new WeeklyReportDAO();
        MidtermReportDAO midtermReportDAO = new MidtermReportDAO();
        FinalReportDAO finalReportDAO = new FinalReportDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByEmail(email);
        int role = account.getRoleId();
        if (role == 3) {
            int mentorId = account.getMentorId();
            switch (reportTitle) {
                case "Weekly Report":
                    listOfWeeklyReport = weeklyReportDAO.getReportsByMentorId(mentorId);
                    request.setAttribute("listOfWeeklyReport", listOfWeeklyReport);
                    break;
                case "Midterm Report":
                    listOfMidtermReport = midtermReportDAO.getAllMidtermReportbyID(mentorId);
                    request.setAttribute("listOfMidtermReport", listOfMidtermReport);
                    break;
                case "Final Report":
                    listOfFinalReport = finalReportDAO.getAllFinalReportbyID(mentorId);
                    request.setAttribute("listOfFinalReport", listOfFinalReport);
                    break;
                default:
                    request.setAttribute("error", "Invalid report title.");
                    break;
            }
        } else if (role == 2) {
            switch (reportTitle) {
                case "Weekly Report":
                    listOfWeeklyReport = weeklyReportDAO.getallweeklyreport();
                    request.setAttribute("listOfWeeklyReport", listOfWeeklyReport);
                    break;
                case "Midterm Report":
                    listOfMidtermReport = midtermReportDAO.getAllMidtermReport();
                    request.setAttribute("listOfMidtermReport", listOfMidtermReport);
                    break;
                case "Final Report":
                    listOfFinalReport = finalReportDAO.getAllFinalReport();
                    request.setAttribute("listOfFinalReport", listOfFinalReport);
                    break;
                default:
                    request.setAttribute("error", "Invalid report title.");
                    break;
            }
        } else {            
            request.setAttribute("error", "Invalid role.");
        }
        
        
        request.getRequestDispatcher("/MentorReport.jsp").forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "MentorReportList Servlet";
    }
}

package controller.feedback;

import dao.AccountDAO;
import dao.FeedbackDAO;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Feedback;
import model.LabRoom;
import model.Mentor;

public class EditFeedbackServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Your existing processRequest method code here
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating feedback: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);
        int internId = account.getInternId();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        Mentor mentor = feedbackDAO.getMentorByInternId(internId);
        if (mentor == null) {
            throw new ServletException("Mentor not found for internId: " + internId);
        }
        request.setAttribute("mentor", mentor);
        LabRoom labRoom = feedbackDAO.getLabRoomByInternId(internId);
        if (labRoom == null) {
            throw new ServletException("LabRoom not found for internId: " + internId);
        }
        String id = request.getParameter("feedbackId");
        int feedbackId = Integer.parseInt(id);
        Feedback feedback = feedbackDAO.getFeedbackById(feedbackId);
        request.setAttribute("feedback", feedback);
        request.setAttribute("labRoom", labRoom);
        request.getRequestDispatcher("EditFeedback.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountDAO accountDAO = new AccountDAO();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            Account account = accountDAO.getAccountByEmail(email);
            int internId = account.getInternId();
            String mentorIdStr = request.getParameter("mentorId");
            String labroomIdStr = request.getParameter("labroomId");
            String feedbackIdStr = request.getParameter("feedbackId");
            if (mentorIdStr == null || mentorIdStr.isEmpty()) {
                throw new ServletException("mentorId is null or empty");
            }
            if (labroomIdStr == null || labroomIdStr.isEmpty()) {
                throw new ServletException("labroomId is null or empty");
            }
            int feedbackId = Integer.parseInt(feedbackIdStr);
            int mentorId = Integer.parseInt(mentorIdStr);
            int labroomId = Integer.parseInt(labroomIdStr);
            String punctuality = request.getParameter("punctuality");
            String coverage = request.getParameter("coverage");
            String response1 = request.getParameter("response");
            String teachingSkills = request.getParameter("teachingSkills");
            String support = request.getParameter("support");
            String feedbackText = request.getParameter("feedbackText");
            Timestamp currentTimestamp = new Timestamp(new Date().getTime());
            Feedback feedback = new Feedback();
            feedback.setFeedbackId(feedbackId);
            feedback.setMentorId(mentorId);
            feedback.setPunctuality(punctuality);
            feedback.setCoverage(coverage);
            feedback.setResponse(response1);
            feedback.setTeachingSkills(teachingSkills);
            feedback.setSupport(support);
            feedback.setFeedbackText(feedbackText);
            feedback.setSubmissionDate(currentTimestamp);
            feedback.setInternId(internId);
            feedback.setRoomId(labroomId);
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            feedbackDAO.editFeedback(feedback);
            response.sendRedirect(request.getContextPath() + "/Feedback");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating feedback: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

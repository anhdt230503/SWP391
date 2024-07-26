package controller.feedback;

import dao.AccountDAO;
import dao.FeedbackDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Feedback;
import model.LabRoom;
import model.Mentor;

public class SubmitFeedback extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);
        int internId = account.getInternId();
        System.out.println(internId);
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        Mentor mentor = feedbackDAO.getMentorByInternId(internId);
        System.out.println(mentor);
        if (mentor == null) {
            throw new ServletException("Mentor not found for internId: " + internId);
        }
        request.setAttribute("mentor", mentor);
        LabRoom labRoom = feedbackDAO.getLabRoomByInternId(internId);
        if (labRoom == null) {
            throw new ServletException("LabRoom not found for internId: " + internId);
        }
        request.setAttribute("labRoom", labRoom);
        request.getRequestDispatcher("FormFeedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Account account = accountDAO.getAccountByEmail(email);
        int internId = account.getInternId();
        if (feedbackDAO.hasSubmittedFeedback(internId)) {
            request.setAttribute("feedbackSubmittedError", "You have already submitted feedback. You can edit your feedback but you can submit again.");
            doGet(request, response);

        } else {
            String mentorIdStr = request.getParameter("mentorId");
            String labroomIdStr = request.getParameter("labroomId");
            if (mentorIdStr == null || mentorIdStr.isEmpty()) {
                throw new ServletException("mentorId is null or empty");
            }
            if (labroomIdStr == null || labroomIdStr.isEmpty()) {
                throw new ServletException("labroomId is null or empty");
            }
            int mentorId = Integer.parseInt(mentorIdStr);
            int labroomId = Integer.parseInt(labroomIdStr);
            String punctuality = request.getParameter("punctuality");
            String coverage = request.getParameter("coverage");
            String response1 = request.getParameter("response");
            String teaching_skills = request.getParameter("teaching_skills");
            String support = request.getParameter("support");
            String feedbackText = request.getParameter("feedbackText");
            Timestamp currentTimestamp = new Timestamp(new Date().getTime());
            Feedback feedback = new Feedback();
            feedback.setPunctuality(punctuality);
            feedback.setCoverage(coverage);
            feedback.setResponse(response1);
            feedback.setTeachingSkills(teaching_skills);
            feedback.setSupport(support);
            feedback.setFeedbackText(feedbackText);
            feedback.setSubmissionDate(currentTimestamp);
            feedback.setMentorId(mentorId);
            feedback.setInternId(internId);
            feedback.setRoomId(labroomId);
            feedbackDAO.submitFeedback(feedback);
            List<Feedback> feedbacks = feedbackDAO.getAllFeedBack();
            request.setAttribute("feedbacks", feedbacks);
            response.sendRedirect("Feedback");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

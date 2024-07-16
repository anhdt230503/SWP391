package controller.feedback;
import dao.AccountDAO;
import dao.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Feedback;

public class FeedbackServlet extends HttpServlet {
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
        int roleId = account.getRoleId();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        if(roleId==2){
            List<Feedback> feedbackList = feedbackDAO.getAllFeedBack();
            request.setAttribute("feedbacks", feedbackList);
        }else if(roleId==4){
            Feedback feedback = feedbackDAO.getFeedbackByInternId(internId);
            request.setAttribute("feedback", feedback);
        }
        request.getRequestDispatcher("Feedback.jsp").forward(request, response);
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

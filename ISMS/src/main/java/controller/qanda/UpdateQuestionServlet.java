package controller.qanda;

import dao.AccountDAO;
import dao.QandADAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import model.Account;
import model.Mentor;
import model.QandA;

/**
 *
 * @author admin
 */
public class UpdateQuestionServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateQuestionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateQuestionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        try {
            Account account = accountDAO.getAccountByEmail(email);
            int internID = account.getInternId();
            QandADAO qandaDAO = new QandADAO();
            Mentor mentor = qandaDAO.getMentorByInternId(internID);
            if (mentor == null) {
                throw new ServletException("Mentor not found for internId: " + internID);
            }
            String id = request.getParameter("qandaId");
            int qandaId = Integer.parseInt(id);
            QandA qanda = qandaDAO.getQuestionByQuestionId(qandaId);

            // Check the question status
            if (qanda.getQuestionStatus() == QandA.QandAStatus.ANSWERED) {
                // Set an error message and redirect to the QandAServlet
                request.getSession().setAttribute("errorMessage", "Không thể thay đổi câu hỏi vì mentor đã trả lời câu hỏi này.");
                response.sendRedirect(request.getContextPath() + "/QandAServlet");
                return; // Stop further processing
            }

            request.setAttribute("qanda", qanda);
            request.setAttribute("mentor", mentor);
            request.getRequestDispatcher("updateQuestion.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving question: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
        try {
            AccountDAO accountDAO = new AccountDAO();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            Account account = accountDAO.getAccountByEmail(email);
            int internId = account.getInternId();
            QandADAO qandadao = new QandADAO();
            int mentorId = qandadao.getMentorByInternId(internId).getMentorId();
            String qandaIdstr = request.getParameter("qandaId");
            if (qandaIdstr == null) {
                throw new ServletException("qandaId parameter is missing");
            }
            String question_title = request.getParameter("question_title");
            String question_text = request.getParameter("question_text");
            Timestamp currentTimestamp = new Timestamp(new Date().getTime());
            int qandaId = Integer.parseInt(qandaIdstr);
            QandA qanda = new QandA();
            qanda.setQandaId(qandaId);
            qanda.setQuestionTitle(question_title);
            qanda.setQuestionStatus(QandA.QandAStatus.PENDING);
            qanda.setQuestionText(question_text);
            qanda.setMentorId(mentorId);
            qanda.setInternId(internId);
            qanda.setUpdatedAt(currentTimestamp);
            qandadao.updateQuestion(qanda);
            response.sendRedirect(request.getContextPath() + "/QandAServlet");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding question: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.qanda;

import dao.AccountDAO;
import dao.MissionDAO;
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
import java.util.List;
import model.Account;
import model.Mentor;
import model.QandA;

/**
 *
 * @author admin
 */
public class AddQuestionServlet extends HttpServlet {

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
            out.println("<title>Servlet AddQuestionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddQuestionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        try {
            // get mentorId from Account
            Account account = accountDAO.getAccountByEmail(email);
            int internID = account.getInternId();
            QandADAO qandaDAO = new QandADAO();
            Mentor mentor = qandaDAO.getMentorByInternId(internID);
            if (mentor == null) {
                throw new ServletException("Mentor not found for internId: " + internID);
            }
            request.setAttribute("mentor", mentor);
            request.getRequestDispatcher("AddQuestion.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving intern list: " + e.getMessage());
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
            String question_title = request.getParameter("question_title");
            String question_text = request.getParameter("question_text");
            Timestamp currentTimestamp = new Timestamp(new Date().getTime());
            QandA qanda = new QandA();
            qanda.setQuestionTitle(question_title);
            qanda.setQuestionStatus(QandA.QandAStatus.PENDING);
            qanda.setQuestionText(question_text);
            qanda.setMentorId(mentorId);
            qanda.setInternId(internId);
            qanda.setCreatedAt(currentTimestamp);
            qandadao.addQandA(qanda);
            List<QandA> qanas = qandadao.getQandA();
//            request.setAttribute("qandas", qanas);
            response.sendRedirect("QandAServlet");
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

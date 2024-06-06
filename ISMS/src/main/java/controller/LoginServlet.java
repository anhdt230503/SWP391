/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.GoogleProfile;
import service.GoogleHandlerService;

/**
 *
 * @author haidu
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        GoogleHandlerService ggHandlerService = new GoogleHandlerService();
        String code = request.getParameter("code");
        String accesssToken = ggHandlerService.getToken(code);
        GoogleProfile ggProfile = ggHandlerService.getUserInfo(accesssToken);
        System.out.println(ggProfile);

        String email = ggProfile.getEmail();
        String username = ggProfile.getName();
        String avatarUrl = ggProfile.getPicture();
        AccountDAO accDAO = new AccountDAO();
        Account account = accDAO.login(email);

        if (account == null) {
            request.setAttribute("message", "Your account is not allowed to log into the system");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            
            HttpSession session = request.getSession();
            session.setAttribute("name", username);
            session.setAttribute("email", email);
            session.setAttribute("avatar", avatarUrl);
            session.setAttribute("acc", account);
            response.sendRedirect("Home.jsp");
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
        processRequest(request, response);
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

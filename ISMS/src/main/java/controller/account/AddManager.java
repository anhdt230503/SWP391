/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import dao.AccountDAO;
import dao.ManagerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Manager;
import service.ManagerService;

/**
 *
 * @author ACER
 */
public class AddManager extends HttpServlet {

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
        processRequest(request, response);
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

        ManagerDAO managerDAO = new ManagerDAO();
        AccountDAO accountDAO = new AccountDAO();
        ManagerService managerservice = new ManagerService();

        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");

    if (accountDAO.isEmailExists(email)) {
            request.setAttribute("errorMessage", "Email đã tồn tại.");
        }
    else{
        int managerId = managerservice.generateManagerIdKey() + 1;
        Manager newManager = new Manager();
        newManager.setManagerId(managerId);
        newManager.setFullname(fullName);
        newManager.setEmail(email);
        managerDAO.addManager(newManager);

        Account newAccount = new Account();
        newAccount.setEmail(email);
        newAccount.setManagerId(managerId);

        accountDAO.insertManagerAccount(newAccount);
        
    }
        request.getRequestDispatcher("ManagerManageController").forward(request, response);
           
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

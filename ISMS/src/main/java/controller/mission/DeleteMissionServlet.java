/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.mission;

import dao.MissionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Mission;

/**
 *
 * @author admin
 */
public class DeleteMissionServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet DeleteMissionServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteMissionServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
    int missionId = Integer.parseInt(request.getParameter("misId"));
    MissionDAO missionDAO = new MissionDAO();
    
    // Lấy thông tin nhiệm vụ để kiểm tra trạng thái
    Mission mission = missionDAO.getMissionById(missionId);
    
    if (mission != null) {
        // Kiểm tra trạng thái của nhiệm vụ
        if (mission.getMisStatus() == Mission.MissionStatus.MISSING || 
            mission.getMisStatus() == Mission.MissionStatus.FINISHED) {
            // Nếu trạng thái là MISSING hoặc FINISHED, không cho phép xóa
            request.setAttribute("errorMessage", "Cannot delete the task that has expired");
            request.getRequestDispatcher("mission").forward(request, response);
            return;
        }
    }

    // Nếu không có vấn đề, thực hiện xóa
    missionDAO.deleteMission(missionId);
    response.sendRedirect("mission");
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

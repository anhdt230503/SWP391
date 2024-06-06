/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.LabRoomDAO;
import dao.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.LabRoom;
import model.Mentor;

/**
 *
 * @author Huynguyen
 */
public class ListLabRoomsServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ListLabRoomsServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListLabRoomsServlet at " + request.getContextPath () + "</h1>");
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
            LabRoomDAO labRoomDAO = new LabRoomDAO();
        List<LabRoom> listOfLabRooms = labRoomDAO.getAllLabRooms();
        MentorDAO mentorDAO = new MentorDAO();
        List<Mentor> listOfMentors = mentorDAO.getAllMentors();
        
        request.setAttribute("listOfLabRooms", listOfLabRooms);
        request.setAttribute("listOfMentors", listOfMentors);
        request.getRequestDispatcher("Createlabroom.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
  String roomName = request.getParameter("roomName");

        boolean isAssigned = false;

        String mentorIdStr = request.getParameter("mentorId");

        if (mentorIdStr != null && !mentorIdStr.isEmpty()) {
            isAssigned = true;
        }

        LabRoom labRoom = new LabRoom();
        labRoom.setRoomName(roomName);
        labRoom.setAssigned(isAssigned);
        if (!isAssigned) {
            labRoom.setMentorId(0); 
        } else {
            labRoom.setMentorId(Integer.parseInt(mentorIdStr));
        }

        LabRoomDAO labRoomDAO = new LabRoomDAO();
        labRoomDAO.insertLabRoom(labRoom);

        response.sendRedirect(request.getContextPath() + "/ListLabRoomsServlet");
    }

    @Override
    public String getServletInfo() {
        return "Servlet to list and create lab rooms";
    }
}

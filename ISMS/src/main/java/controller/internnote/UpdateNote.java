/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.internnote;

import dao.AccountDAO;
import dao.NoteDAO;
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
import model.Note;

/**
 *
 * @author admin
 */
public class UpdateNote extends HttpServlet {

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
            out.println("<title>Servlet UpdateNote</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateNote at " + request.getContextPath() + "</h1>");
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
        Account account = accountDAO.getAccountByEmail(email);
        int internId = account.getInternId();
        String id = request.getParameter("noteId");
        int noteId = Integer.parseInt(id);
        NoteDAO noteDAO = new NoteDAO();
        Note note = noteDAO.getNoteByNoteId(noteId);
        request.setAttribute("note", note);
        request.getRequestDispatcher("updateNote.jsp").forward(request, response);

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
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        int interId = accountDAO.getAccountByEmail(email).getInternId();
        NoteDAO noteDAO = new NoteDAO();

        String note_title = request.getParameter("note_title");
        String note_content = request.getParameter("note_content");
        int noteId = Integer.parseInt(request.getParameter("noteId")); // Thêm dòng này

        Note note = new Note();
        note.setNoteId(noteId); // Thêm dòng này
        note.setNoteTitle(note_title);
        note.setNoteContent(note_content);
        note.setInternId(interId);

        noteDAO.updateNote(note);

        List<Note> notes = noteDAO.getAllnote();
        request.setAttribute("notes", notes);

        // Forward the request to the JSP page
        response.sendRedirect("note");
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

package controller.internnote;

import dao.AccountDAO;
import dao.NoteDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Note;

public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        int interId = accountDAO.getAccountByEmail(email).getInternId();
        
        NoteDAO noteDAO = new NoteDAO();
        List<Note> notes = noteDAO.getNotesByInternId(interId);
        
        // Add notes to the request attributes
        request.setAttribute("notes", notes);
        
        // Forward the request to the JSP page
        request.getRequestDispatcher("Notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }
}

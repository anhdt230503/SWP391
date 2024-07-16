package controller.internnote;

import dao.NoteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteNote extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy noteId từ yêu cầu

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String noteIdStr = request.getParameter("noteId");

        // Kiểm tra xem noteId có hợp lệ không
        if (noteIdStr != null && !noteIdStr.isEmpty()) {
            try {
                int noteId = Integer.parseInt(noteIdStr);
                NoteDAO noteDAO = new NoteDAO();

                // Xóa ghi chú
                noteDAO.deleteNote(noteId);

                // Chuyển hướng về trang danh sách ghi chú
                response.sendRedirect("note");
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Note ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Note ID is required");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for deleting notes";
    }
}

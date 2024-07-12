/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.schedule;

import dao.AccountDAO;
import dao.ScheduleMissionDAO;
import dao.TaskDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.ScheduleMission;
import model.Task;

/**
 *
 * @author haidu
 */
public class UpdateTaskServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateTaskServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateTaskServlet at " + request.getContextPath() + "</h1>");
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
        TaskDAO taskDAO = new TaskDAO();
        String[] selectedTaskIds = request.getParameterValues("selectedTasks");
        if (selectedTaskIds == null) {
            request.setAttribute("message", "Nothing to change here");
            request.getRequestDispatcher("loadTask").forward(request, response);

        }
        int[] taskIds = new int[selectedTaskIds.length];
        HttpSession session = request.getSession();
        int scheduleId = (int) session.getAttribute("scheduleId");
        List<Task> taskList = taskDAO.getAllTaskByScheduleId(scheduleId);

        List<Integer> allTaskIds = new ArrayList<>();
        for (Task task : taskList) {
            allTaskIds.add(task.getTaskId());
        }

        // Update trạng thái isDone của các task được chọn
        for (int i = 0; i < selectedTaskIds.length; i++) {
            taskIds[i] = Integer.parseInt(selectedTaskIds[i]);
            Task task = new Task();
            task.setTaskId(taskIds[i]);
            taskDAO.updateSelectedTask(task);
        }

        // Tìm các taskId có trong allTaskIds nhưng không có trong selectedTaskIds
        List<Integer> unselectedTaskIds = new ArrayList<>();
        for (int taskId : allTaskIds) {
            boolean isSelected = false;
            for (int selectedTaskId : taskIds) {
                if (taskId == selectedTaskId) {
                    isSelected = true;
                    break;
                }
            }
            if (!isSelected) {
                unselectedTaskIds.add(taskId);
            }
        }

        // Update trạng thái isDone của các task không được chọn
        for (int unselectedTaskId : unselectedTaskIds) {
            Task task1 = new Task();
            task1.setTaskId(unselectedTaskId);
            taskDAO.updateUnselectedTask(task1); // Đặt isDone = false cho các task không được chọn
        }
        response.sendRedirect("getMissionName");
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

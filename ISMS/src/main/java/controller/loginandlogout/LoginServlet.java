/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.loginandlogout;

import dao.AccountDAO;
import dao.AttendanceDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import java.sql.Date;
import java.sql.Timestamp;
import model.Account;
import model.Attendance;
import model.GoogleProfile;
import org.apache.commons.net.util.SubnetUtils;
import service.AttendanceService;
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
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        AttendanceService attendanceService = new AttendanceService();

        String email = ggProfile.getEmail();
        String username = ggProfile.getName();
        String avatarUrl = ggProfile.getPicture();
        AccountDAO accDAO = new AccountDAO();
        Account account = accDAO.login(email);
        Account acc = new Account();
        
        if (account == null) {
            request.setAttribute("message", "Your account is not allowed to log into the system");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {

            if(account.getStatus() == 1){
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("name", username);
            session.setAttribute("avatar", avatarUrl);
            session.setAttribute("acc", account);

            account = accDAO.getAccountByEmail(email);

            int internId = account.getInternId();
            int roleId = account.getRoleId();

            if (roleId == 4) {
//            String ipAddress = request.getRemoteAddr();
                String ipAddress = "10.33.37.211";
                // Sử dụng dải IP của trường
                String ipRange = "10.33.0.0/17"; // Dải IP từ 10.33.0.0 đến 10.33.127.255
                SubnetUtils utils = new SubnetUtils(ipRange);
                boolean isAllowed = utils.getInfo().isInRange(ipAddress);

                if (isAllowed) {
                    // lấy ngày giờ hiện tại
                    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                    // lấy ngày tháng năm từ đối tượng timestamp
                    Date date = new Date(currentTimestamp.getTime());
                    Attendance attendance = attendanceDAO.getAttendanceByDate(date, internId);
                    // nếu đã có bản ghi trong DB thì
                    if (attendance != null) {
                        // bắt đầu đếm thời gian tồn tại của session
                        attendanceService.sessionCreated(new HttpSessionEvent(session));
                    }
                }
            }
            // set thời gian timeout cho session
            int timeoutInSeconds = 30 * 60; // 30 phút (30 * 60 giây)
            session.setMaxInactiveInterval(timeoutInSeconds);
            response.sendRedirect("Home.jsp");
        }else{
                request.setAttribute("message2", "Account is blocked.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
    }

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

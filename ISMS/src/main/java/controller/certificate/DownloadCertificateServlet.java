package controller.certificate;

import dao.AccountDAO;
import dao.CertificateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.OutputStream;
import model.Account;

/**
 *
 * @author haidu
 */
public class DownloadCertificateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet DownloadCertificateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownloadCertificateServlet at " + request.getContextPath() + "</h1>");
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
        // Khởi tạo đối tượng AccountDAO và CertificateDAO để truy xuất dữ liệu từ cơ sở dữ liệu
        AccountDAO accountDAO = new AccountDAO();
        CertificateDAO certificateDAO = new CertificateDAO();

        // Lấy session từ request
        HttpSession session = request.getSession();
        // Lấy email của người dùng từ session
        String email = (String) session.getAttribute("email");
        // Lấy thông tin tài khoản của người dùng từ cơ sở dữ liệu dựa trên email
        Account account = accountDAO.getAccountByEmail(email);

        // Lấy roleId của người dùng từ tài khoản
        int roleId = account.getRoleId();
        byte[] pdfData = null;

        // Kiểm tra vai trò của người dùng để xác định cách lấy chứng chỉ
        if (roleId == 4) { // Nếu người dùng có roleId là 4 (thực tập sinh)
            int internId = account.getInternId();
            // Lấy dữ liệu chứng chỉ của thực tập sinh dựa trên internId
            pdfData = certificateDAO.getCertificateDataByIntern(internId);
        } else { // Nếu không phải thực tập sinh
            // Lấy id của chứng chỉ từ request parameter
            int certificateId = Integer.parseInt(request.getParameter("id"));
            // Lấy dữ liệu chứng chỉ dựa trên certificateId
            pdfData = certificateDAO.getCertificateData(certificateId);
        }

        // Kiểm tra xem dữ liệu chứng chỉ có tồn tại hay không
        if (pdfData != null) {
            // Thiết lập type của response là PDF
            response.setContentType("application/pdf");
            // Thiết lập header để trình duyệt nhận diện đây là file đính kèm và đặt tên cho file
            response.setHeader("Content-Disposition", "attachment; filename=\"certificate.pdf\"");
            // Ghi dữ liệu PDF vào response
            try (OutputStream os = response.getOutputStream()) {
                os.write(pdfData);
                os.flush();
            }
        } else {
            // Xử lý trường hợp không tìm thấy chứng chỉ
            response.setContentType("text/html");
            response.getWriter().write("Certificate not found.");
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
        // Phương thức POST không được sử dụng trong servlet này, nên không cần triển khai
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

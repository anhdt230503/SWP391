package controller.mission;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author admin
 */
public class DownFileMisson extends HttpServlet {

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
            out.println("<title>Servlet DownFileMisson</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownFileMisson at " + request.getContextPath () + "</h1>");
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
        System.out.println("doGet");
        // Lấy tham số 'link' từ request
        String link = request.getParameter("link");
        
        // In ra tên file từ tham số 'link'
        System.out.println("File Name: " + link);

        // Định nghĩa thư mục chứa các file tải lên
        Path uploadDirectory = Paths.get("\\swp391\\ISMS\\src\\file_upload");

        // In ra đường dẫn thư mục tải lên
        System.out.println("Upload Directory: " + uploadDirectory);

        // Xác định đường dẫn đầy đủ của file cần tải xuống
        Path filePath = uploadDirectory.resolve(link);
        
        // In ra đường dẫn đầy đủ của file
        System.out.println("File Path: " + filePath);

        // Chuyển đường dẫn file thành đối tượng File
        File downloadFile = filePath.toFile();
        // In ra đối tượng file
        System.out.println("Download File: " + downloadFile);
        // Kiểm tra xem file có tồn tại không
        if (!downloadFile.exists()) {
            // Nếu không tồn tại, trả về thông báo lỗi
            response.getWriter().write("File not found");
            response.getWriter().flush();
            return;
        }

        // Thiết lập type của response là binary stream cho file tải xuống
        response.setContentType("application/octet-stream");
        // Thiết lập header để trình duyệt nhận diện file tải xuống và đặt tên cho file
        response.setHeader("Content-Disposition", "attachment;filename=" + link);

        // Đọc file từ hệ thống và ghi vào response output stream
        try (FileInputStream fileInputStream = new FileInputStream(downloadFile); 
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            // Đọc file theo khối (buffer) và ghi vào response output stream
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu có lỗi khi đọc file hoặc ghi vào output stream
            e.printStackTrace();
        }
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

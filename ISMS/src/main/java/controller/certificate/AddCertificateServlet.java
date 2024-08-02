/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.certificate;

import com.ibm.icu.text.Transliterator;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import dao.CertificateDAO;
import dao.FinalReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Certificate;
import model.FinalReport;

/**
 *
 * @author haidu
 */
public class AddCertificateServlet extends HttpServlet {

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
            out.println("<title>Servlet AddCertificateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCertificateServlet at " + request.getContextPath() + "</h1>");
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

        FinalReportDAO finalReportDAO = new FinalReportDAO();
        List<FinalReport> list = finalReportDAO.getAllInternCompletionOfInternship();

        request.setAttribute("list", list);
        request.getRequestDispatcher("AddCertificate.jsp").forward(request, response);
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
        // Khởi tạo đối tượng FinalReportDAO và CertificateDAO để truy xuất dữ liệu từ cơ sở dữ liệu
        FinalReportDAO finalReportDAO = new FinalReportDAO();
        CertificateDAO certificateDAO = new CertificateDAO();

        // Lấy internId từ request, internId là id của thực tập sinh cần thêm chứng chỉ
        int internId = Integer.parseInt(request.getParameter("internName"));

        // Kiểm tra xem chứng chỉ cho thực tập sinh có tồn tại hay không
        Certificate certificate = certificateDAO.getCertificateByIntern(internId);
        if (certificate == null) { // Nếu chưa có chứng chỉ cho thực tập sinh này

            // Lấy tên của thực tập sinh từ cơ sở dữ liệu
            String internName = finalReportDAO.getNameByInternId(internId);

            // Chuyển đổi tên thực tập sinh sang dạng ASCII bằng Transliterator
            Transliterator transliterator = Transliterator.getInstance("Any-Latin; Latin-ASCII");
            String transliteratedName = transliterator.transliterate(internName);

            // Đường dẫn tới file mẫu chứng chỉ PDF trong thư mục web
            String templatePath = getServletContext().getRealPath("/file_example/Certificate.pdf");

            // Đọc file PDF mẫu
            PdfReader reader = new PdfReader(templatePath);

            try {
                // Tạo một ByteArrayOutputStream để lưu trữ dữ liệu PDF đã thay đổi
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfStamper stamper = new PdfStamper(reader, baos);
                PdfContentByte canvas = stamper.getOverContent(1); // Lấy content của trang đầu tiên của PDF

                // Tạo BaseFont để sử dụng font mặc định
                BaseFont baseFont = BaseFont.createFont();
                // Tính độ rộng của text dựa trên tên đã chuyển đổi
                float textWidth = baseFont.getWidthPoint(transliteratedName, 48); // 48 là kích cỡ font

                // Tìm vị trí ngang giữa của trang PDF
                Rectangle pageSize = reader.getPageSize(1); // Lấy kích thước của trang 1
                float centerX = pageSize.getWidth() / 2;

                // Điều chỉnh vị trí bắt đầu để căn giữa văn bản
                float startX = centerX - (textWidth / 2);
                canvas.beginText();
                canvas.setFontAndSize(baseFont, 48);
                canvas.setTextMatrix(startX, 280); // 280 là tọa độ y (điều chỉnh nếu cần)
                canvas.showText(transliteratedName);
                canvas.endText();

                stamper.close();
                reader.close();

                // Lưu dữ liệu PDF vào một mảng byte
                byte[] pdfData = baos.toByteArray();

                // Tạo đối tượng Certificate mới và lưu vào cơ sở dữ liệu
                Certificate certificate1 = new Certificate();
                certificate1.setInternId(internId);
                certificate1.setPdfData(pdfData);
                certificateDAO.saveCertificate(certificate1);

                // Chuyển hướng đến trang danh sách chứng chỉ
                response.sendRedirect("certificateList");

            } catch (DocumentException ex) {
                // Xử lý ngoại lệ nếu có lỗi khi thêm chữ ký vào PDF
                Logger.getLogger(AddCertificateServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { // Nếu đã có chứng chỉ cho thực tập sinh này
            // Chuyển hướng đến trang danh sách chứng chỉ
            response.sendRedirect("certificateList");
        }
    }

}

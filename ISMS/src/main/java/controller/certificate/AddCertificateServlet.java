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
        FinalReportDAO finalReportDAO = new FinalReportDAO();
        CertificateDAO certificateDAO = new CertificateDAO();
        int internId = Integer.parseInt(request.getParameter("internName"));

        Certificate certificate = certificateDAO.getCertificateByIntern(internId);
        if (certificate == null) {

            String internName = finalReportDAO.getNameByInternId(internId);
            Transliterator transliterator = Transliterator.getInstance("Any-Latin; Latin-ASCII");
            String transliteratedName = transliterator.transliterate(internName);
            String templatePath = getServletContext().getRealPath("/file_example/Certificate.pdf");
            PdfReader reader = new PdfReader(templatePath);

            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfStamper stamper = new PdfStamper(reader, baos);
                PdfContentByte canvas = stamper.getOverContent(1); // Trang số 1
                // 1. Calculate Text Width
                BaseFont baseFont = BaseFont.createFont();
//            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                float textWidth = baseFont.getWidthPoint(transliteratedName, 48); // 48 is the font size

                // 2. Find Horizontal Center
                Rectangle pageSize = reader.getPageSize(1); // Get dimensions of page 1
                float centerX = pageSize.getWidth() / 2;

                // 3. Adjust for Text Width and Set Position
                float startX = centerX - (textWidth / 2);
                canvas.beginText();
                canvas.setFontAndSize(baseFont, 48);
                canvas.setTextMatrix(startX, 280); // 280 is the y-coordinate (adjust if needed)
                canvas.showText(transliteratedName);
                canvas.endText();

                stamper.close();
                reader.close();

                // 4. Lưu vào Cơ sở Dữ liệu
                byte[] pdfData = baos.toByteArray();

                Certificate certificate1 = new Certificate();
                certificate1.setInternId(internId);
                certificate1.setPdfData(pdfData);
                certificateDAO.saveCertificate(certificate1);

                response.sendRedirect("certificateList");
            } catch (DocumentException ex) {
                Logger.getLogger(AddCertificateServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("certificateList");
        }

    }

}

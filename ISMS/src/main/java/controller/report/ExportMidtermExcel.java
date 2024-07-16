/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.report;

import dao.FinalReportDAO;
import dao.MidtermReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import model.MidtermReport;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author duong
 */
public class ExportMidtermExcel extends HttpServlet {
   
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
            out.println("<title>Servlet ExportMidtermExcel</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExportMidtermExcel at " + request.getContextPath () + "</h1>");
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
       String filePath = "C:\\Users\\duong\\OneDrive\\Desktop\\SWP391\\swp391-iter3\\swp391-iter3\\ISMS\\src\\main\\webapp\\file_example\\Example_Midterm.xlsx";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        MidtermReportDAO midtermreportDao = new MidtermReportDAO();
        List<MidtermReport> reports = midtermreportDao.getAllMidtermReports();
        int rowNum = 7; 
        for (MidtermReport report : reports) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);}
            row.createCell(1).setCellValue(report.getIntern_id());
            row.createCell(2).setCellValue(report.getStaff_id());
            row.createCell(3).setCellValue(report.getIntern_name());
            
            row.createCell(6).setCellValue(report.isExcellent() ? "X" : "");
            row.createCell(7).setCellValue(report.isVeryGood() ? "X" : "");
            row.createCell(8).setCellValue(report.isGood() ? "X" : "");
            row.createCell(9).setCellValue(report.isAverage() ? "X" : "");
            row.createCell(10).setCellValue(report.isPoor() ? "X" : "");
            
            rowNum++;}
        fileInputStream.close();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=final_reports.xlsx");
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        workbook.close();
        out.close();
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

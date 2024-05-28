/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import model.Intern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import service.InternService;

/**
 *
 * @author haidu
 */
public class ImportInternServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");

        // Nhận file từ yêu cầu HTTP
        Part filePart = request.getPart("file");

        // Lấy tên file gốc
        String fileName = filePart.getSubmittedFileName();

        // Xác định đường dẫn để lưu file tạm thời trên server
        String filePath = getServletContext().getRealPath("/") + fileName;

        // Lưu file tạm thời trên server
        filePart.write(filePath);

        // Đọc và xử lý file Excel (được thực hiện trong khối try-catch ở dưới)
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            InternService internService = new InternService();

            int numOfRows = sheet.getPhysicalNumberOfRows();
            response.getWriter().println("Number of rows: " + numOfRows);

            for (Row row : sheet) {

                // Skip header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                Intern intern = new Intern();
                intern.setStudentId(row.getCell(0).getStringCellValue());
                intern.setEmail(row.getCell(1).getStringCellValue());
                intern.setFullName(row.getCell(2).getStringCellValue());
                // Đọc phone number dưới dạng BigDecimal để tránh mất dữ liệu
                Cell phoneNumberCell = row.getCell(3);
                if (phoneNumberCell != null) {
                    BigDecimal phoneNumber = new BigDecimal(phoneNumberCell.toString());
                    intern.setPhoneNumber(phoneNumber.toPlainString());
                }
                intern.setMajor(row.getCell(4).getStringCellValue());
                intern.setCompany(row.getCell(5).getStringCellValue());
                intern.setJobTitle(row.getCell(6).getStringCellValue());
                intern.setLinkCv(row.getCell(7).getStringCellValue());

                internService.importIntern(intern);
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Xóa file tạm thời sau khi xử lý xong
            new File(filePath).delete();
        }

        // Trả về thông báo thành công cho client
        response.getWriter().println("File uploaded and data imported successfully.");

    }

}

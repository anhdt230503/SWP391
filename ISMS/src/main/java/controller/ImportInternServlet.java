/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import model.Account;
import model.Intern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
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
        System.out.println(fileName);

        // Xác định đường dẫn để lưu file tạm thời trên server
        String filePath = getServletContext().getRealPath("/") + fileName;
        System.out.println(filePath);
        // Lưu file tạm thời trên server
        filePart.write(filePath);

        // Đọc và xử lý file Excel (được thực hiện trong khối try-catch ở dưới)
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {

            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            AccountDAO accountDao = new AccountDAO();
            InternService internService = new InternService();

            for (Row row : sheet) {
                // Skip header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                int internId = internService.generateInternIdKey() + 1;
                String studentId = row.getCell(0).getStringCellValue();
                String email = row.getCell(1).getStringCellValue();
                String fullName = row.getCell(2).getStringCellValue();
                // Đọc phone number dưới dạng BigDecimal để tránh mất dữ liệu
                Cell phoneNumberCell = row.getCell(3);
                String phoneNumber = null;
                if (phoneNumberCell != null) {
                    phoneNumber = formatter.formatCellValue(phoneNumberCell);
                }
                String major = row.getCell(4).getStringCellValue();
                String company = row.getCell(5).getStringCellValue();
                String jobTitle = row.getCell(6).getStringCellValue();
                String linkCv = row.getCell(7).getStringCellValue();

                String staffId = internService.genarateStaffId();

                Intern existingIntern = internService.getInternByStudentId(studentId);
                if (existingIntern != null) {

                    existingIntern.setInternId(internId);
                    existingIntern.setStudentId(studentId);
                    existingIntern.setEmail(email);
                    existingIntern.setFullName(fullName);
                    existingIntern.setPhoneNumber(phoneNumber);
                    existingIntern.setMajor(major);
                    existingIntern.setCompany(company);
                    existingIntern.setJobTitle(jobTitle);
                    existingIntern.setLinkCv(linkCv);
                    existingIntern.setStaffId(staffId);
                    existingIntern.setStatus(Intern.InternStatus.INTERN);
                    internService.updateIntern(existingIntern);
                } else {
                    Intern intern = new Intern();
                    Account account = new Account();

                    intern.setInternId(internId);
                    intern.setStudentId(studentId);
                    intern.setEmail(email);
                    intern.setFullName(fullName);
                    intern.setPhoneNumber(phoneNumber);
                    intern.setMajor(major);
                    intern.setCompany(company);
                    intern.setJobTitle(jobTitle);
                    intern.setLinkCv(linkCv);
                    intern.setStaffId(staffId);
                    intern.setStatus(Intern.InternStatus.INTERN);
                    internService.updateIntern(existingIntern);
                    internService.importIntern(intern);

                    // insert Account for Intern
                    account.setEmail(email);
                    account.setInternId(internId);
                    accountDao.insertInternAccount(account);
                }
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Xóa file tạm thời sau khi xử lý xong
            File tempFile = new File(filePath);
            if (tempFile.exists()) {
                if (tempFile.delete()) {
                    System.out.println("Temporary file deleted successfully.");
                } else {
                    System.out.println("Failed to delete temporary file.");
                }
            }
        }

        request.setAttribute("successMessage", "Import Successfully");
        request.getRequestDispatcher("internList").forward(request, response);
    }

}

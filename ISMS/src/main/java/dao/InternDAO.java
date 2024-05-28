/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;
import model.Intern;

/**
 *
 * @author haidu
 */
public class InternDAO extends MyDAO {

    public void insertIntern(Intern intern) {

        String xSql = "INSERT INTO Intern (student_id, email, full_name, phone_number, major, company, job_title, link_cv) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, intern.getStudentId());
            ps.setString(2, intern.getEmail());
            ps.setString(3, intern.getFullName());
            ps.setString(4, intern.getPhoneNumber());
            ps.setString(5, intern.getMajor());
            ps.setString(6, intern.getCompany());
            ps.setString(7, intern.getJobTitle());
            ps.setString(8, intern.getLinkCv());
            ps.executeUpdate();
//            con.commit(); // Thêm commit transaction
        } catch (SQLException e) {
            System.err.println("Lỗi khi chèn dữ liệu: " + e.getMessage()); // In thông báo lỗi chi tiết
            try {
                con.rollback(); // Rollback transaction nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}

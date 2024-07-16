/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Intern;

/**
 *
 * @author haidu
 */
public class InternDAO extends MyDAO {

    public void insertIntern(Intern intern) {

        String xSql = "INSERT INTO Intern (intern_id, student_id, email, full_name, phone_number, major, company, job_title, link_cv, staff_id, status, upload_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, intern.getInternId());
            ps.setString(2, intern.getStudentId());
            ps.setString(3, intern.getEmail());
            ps.setString(4, intern.getFullName());
            ps.setString(5, intern.getPhoneNumber());
            ps.setString(6, intern.getMajor());
            ps.setString(7, intern.getCompany());
            ps.setString(8, intern.getJobTitle());
            ps.setString(9, intern.getLinkCv());
            ps.setString(10, intern.getStaffId());
            ps.setString(11, intern.getStatus().toString());
            ps.setTimestamp(12, intern.getUploadDate());
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

    public List<Intern> getAllIntern() {
        List<Intern> list = new ArrayList<>();

        xSql = "select * from Intern";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(11);
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                list.add(new Intern(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        status,
                        rs.getInt(12),
                        rs.getTimestamp(13),
                        rs.getDouble(14),
                        rs.getDouble(15)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getLastInternId() {
        xSql = "SELECT MAX(intern_id) FROM Intern";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public Intern getInternByStudentId(String studentId) {
        xSql = "select * from Intern \n"
                + "where student_id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(11);
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                return new Intern(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        status,
                        rs.getInt(12),
                        rs.getTimestamp(13),
                        rs.getDouble(14),
                        rs.getDouble(15));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateIntern(Intern intern) {
        xSql = "UPDATE Intern\n"
                + "SET email = ?,\n"
                + "    full_name = ?,\n"
                + "    phone_number = ?,\n"
                + "    major = ?,\n"
                + "    company = ?,\n"
                + "    job_title = ?,\n"
                + "    link_cv = ?\n"
                + "    upload_date = ?\n"
                + "WHERE student_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, intern.getEmail());
            ps.setString(2, intern.getFullName());
            ps.setString(3, intern.getPhoneNumber());
            ps.setString(4, intern.getMajor());
            ps.setString(5, intern.getCompany());
            ps.setString(6, intern.getJobTitle());
            ps.setString(7, intern.getLinkCv());
            ps.setTimestamp(8, intern.getUploadDate());
            ps.setString(9, intern.getStudentId());
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    // hàm dành cho chức năng xem lịch sử điểm danh cho Mentor
    public List<Intern> getAllInternForMentor(int mentorId) {
        List<Intern> list = new ArrayList<>();

        xSql = "select * FROM Intern i\n"
                + "join InternAssign ia\n"
                + "on i.intern_id = ia.intern_id \n"
                + "where ia.mentor_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, mentorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(11);
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                list.add(new Intern(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        status,
                        rs.getInt(12),
                        rs.getTimestamp(13),
                        rs.getDouble(14),
                        rs.getDouble(15)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public void updateMidtermWorkTime(Intern intern) {
        xSql = "UPDATE Intern\n"
                + "SET midterm_work_time = ?,\n"
                + "final_work_time = ?\n"
                + "WHERE intern_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setDouble(1, intern.getMidtermWorkTime());
            ps.setDouble(2, intern.getFinalWorkTime());
            ps.setInt(3, intern.getInternId());
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void updateFinalWorkTime(Intern intern) {
        xSql = "UPDATE Intern\n"
                + "SET final_work_time = ?\n"
                + "WHERE intern_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setDouble(1, intern.getFinalWorkTime());
            ps.setInt(2, intern.getInternId());
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public Intern getInternByInternId(int internId) {
        xSql = "select * from Intern \n"
                + "where intern_id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(11);
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                return new Intern(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        status,
                        rs.getInt(12),
                        rs.getTimestamp(13),
                        rs.getDouble(14),
                        rs.getDouble(15));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Intern> getAllUploadDate() {
        List<Intern> list = new ArrayList<>();
        xSql = "SELECT DISTINCT upload_date \n"
                + "FROM Intern;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Intern intern = new Intern();
                intern.setUploadDate(rs.getTimestamp(1));
                list.add(intern);
            }
        } catch (Exception ex) {
        }
        return list;
    }

    public List<Intern> getAllInternByUploadDate(Timestamp uploadDate) {
        List<Intern> list = new ArrayList<>();

        xSql = "select * from Intern\n"
                + "where upload_date = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setTimestamp(1, uploadDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(11);
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                list.add(new Intern(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        status,
                        rs.getInt(12),
                        rs.getTimestamp(13),
                        rs.getDouble(14),
                        rs.getDouble(15)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public void editIntern(Intern intern) {
        xSql = "UPDATE Intern\n"
                + "SET email = ?,\n"
                + "full_name = ?,\n"
                + "phone_number = ?,\n"
                + "major = ?,\n"
                + "company = ?,\n"
                + "job_title = ?,\n"
                + "link_cv = ?\n"
                + "WHERE intern_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, intern.getEmail());
            ps.setString(2, intern.getFullName());
            ps.setString(3, intern.getPhoneNumber());
            ps.setString(4, intern.getMajor());
            ps.setString(5, intern.getCompany());
            ps.setString(6, intern.getJobTitle());
            ps.setString(7, intern.getLinkCv());
            ps.setInt(8, intern.getInternId());
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

        InternDAO internDAO = new InternDAO();
        System.out.println(internDAO.getInternByStudentId("HE170364"));
        System.out.println(internDAO.getLastInternId());

    }

}

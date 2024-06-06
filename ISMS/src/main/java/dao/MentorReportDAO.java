/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MentorReport;

/**
 *
 * @author haidu
 */
public class MentorReportDAO extends MyDAO {

    public void UploadReport(String title, int mentorId, InputStream content) {
        String INSERT_REPORT_SQL = "INSERT INTO MentorReport (report_name, report_file, mentor_id) VALUES (?, ?, ?)";

        try (
                PreparedStatement ps = con.prepareStatement(INSERT_REPORT_SQL)) {
            ps.setString(1, title);
            ps.setBlob(2, content);
            ps.setInt(3, mentorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MentorReport> getAllReports() {

        String GET_ALL_REPORTS_SQL = "SELECT * FROM MentorReport";
        List<MentorReport> report = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(GET_ALL_REPORTS_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                report.add(new MentorReport(rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3),
                        rs.getBinaryStream(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return report;
    }

//    public static void main(String[] args) {
//        MentorReportDAO mentorreport = new MentorReportDAO();
//                mentorreport.getAllReports();
//                
//                System.out.print(mentorreport.getAllReports());
//    }

}

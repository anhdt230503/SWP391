/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MidtermReport;

/**
 *
 * @author duong
 */
public class MidtermReportDAO extends MyDAO {

    public void UploadMTReport(String title, String description, int mentorId, String filename) {
        String INSERT_REPORT_SQL = "INSERT INTO MidTermReport (report_name,report_description,report_file, mentor_id) VALUES (?,?,?, ?)";

        try (
                PreparedStatement ps = con.prepareStatement(INSERT_REPORT_SQL)) {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, filename);
            ps.setInt(4, mentorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public List<MidtermReport> getAllMidtermReportbyID(int mentorId) {

        String GET_ALL_REPORTS_SQL = "SELECT * FROM MidtermReport where mentor_id=? ";
        List<MidtermReport> report = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(GET_ALL_REPORTS_SQL);
            ps.setInt(1, mentorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                report.add(new MidtermReport(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getInt(6)));
            }
        } catch (Exception e) {
        }
        return report;
    }
   
      public List<MidtermReport> getAllMidtermReport() {

        String GET_ALL_REPORTS_SQL = "SELECT MidtermReport.*, Mentor.full_name "
                   + "FROM MidtermReport "
                   + "JOIN Mentor ON MidtermReport.mentor_id = Mentor.mentor_id "
                   + "WHERE MidtermReport.mentor_id = ?";
        List<MidtermReport> report = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(GET_ALL_REPORTS_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                report.add(new MidtermReport(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getInt(6)));
            }
        } catch (Exception e) {
        }
        return report;
    }

    public void updateMidtermReport(MidtermReport report)  {
        String UPDATE_WEEKLY_REPORT_SQL = "UPDATE FinalReport SET report_name=?, report_description = ?,report_date = now(), report_file = ? WHERE report_id = ?";
        try (PreparedStatement rs = con.prepareStatement(UPDATE_WEEKLY_REPORT_SQL)) {
            rs.setString(1, report.getReportName());
            rs.setString(2, report.getReportDescription());
            rs.setString(4, report.getFiledata());
            rs.setInt(5, report.getReportId());
            rs.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteMidtermReport(int reportId)  {
        String DELETE_WEEKLY_REPORT_SQL = "DELETE FROM MidtermReport WHERE report_id = ?";
        try (PreparedStatement rs = con.prepareStatement(DELETE_WEEKLY_REPORT_SQL)) {
            rs.setInt(1, reportId);
            rs.executeUpdate();
        }catch (Exception e) {
        }
    }
}

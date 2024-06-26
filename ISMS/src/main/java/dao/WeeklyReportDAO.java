/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.WeeklyReport;

/**
 *
 * @author duong
 */
public class WeeklyReportDAO extends MyDAO {

    public void UploadWLReport(String name, String week, String description, int internid, String filename) {
        String INSERT_REPORT_SQL = "INSERT INTO WeeklyReport (report_name,week_report,report_description,report_file, intern_id) VALUES (?,?,?,?,?)";

        try (
                PreparedStatement ps = con.prepareStatement(INSERT_REPORT_SQL)) {
            ps.setString(1, name);
            ps.setString(2, week);
            ps.setString(3, description);
            ps.setString(4, filename);
            ps.setInt(5, internid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   

    public List<WeeklyReport> getReportsByMentorId(int mentorId) {
        String GET_ALL_REPORTS_SQL = "SELECT wr.* "
                + "FROM WeeklyReport wr "
                + "JOIN InternAssign ia ON wr.intern_id = ia.intern_id "
                + "WHERE ia.mentor_id = ?";

        List<WeeklyReport> report = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(GET_ALL_REPORTS_SQL);
            ps.setInt(1, mentorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                report.add(new WeeklyReport(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5),
                        rs.getString(6),
                        rs.getInt(7)));
            }
        } catch (Exception e) {
        }
        return report;

    }

    public List<WeeklyReport> getallweeklyreport() {
        String GET_ALL_REPORTS_SQL = "SELECT * from WeeklyReport";
        List<WeeklyReport> report = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(GET_ALL_REPORTS_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                report.add(new WeeklyReport(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5),
                        rs.getString(6),
                        rs.getInt(7)));
            }
        } catch (Exception e) {
        }
        return report;

    }

    public List<WeeklyReport> getallweeklyreportbyInternId(int internId) {
        String GET_ALL_REPORTS_SQL = "SELECT * from WeeklyReport where intern_id=?";
        List<WeeklyReport> report = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(GET_ALL_REPORTS_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                report.add(new WeeklyReport(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5),
                        rs.getString(6),
                        rs.getInt(7)));
            }
        } catch (Exception e) {
        }
        return report;

    }

    public void updateWeeklyReport( String name,String week,String description,String file,int reportid) {
        String UPDATE_WEEKLY_REPORT_SQL = "UPDATE WeeklyReport SET report_name=?,week_report = ?, report_description = ?,report_date = now(), report_file = ? WHERE report_id = ?";
        try (PreparedStatement rs = con.prepareStatement(UPDATE_WEEKLY_REPORT_SQL)) {
            rs.setString(1, name);
            rs.setString(2, week);
            rs.setString(3, description);
            rs.setString(4, file);
            ps.setInt(5, reportid);
            rs.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteWeeklyReport(int reportId) {
        String DELETE_WEEKLY_REPORT_SQL = "DELETE FROM WeeklyReport WHERE report_id = ?";
        try (PreparedStatement rs = con.prepareStatement(DELETE_WEEKLY_REPORT_SQL)) {
            rs.setInt(1, reportId);
            rs.executeUpdate();
        } catch (Exception e) {
        }
    }

}

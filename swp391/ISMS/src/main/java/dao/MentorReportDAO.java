///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dao;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import model.MentorReport;
//
///**
// *
// * @author haidu
// */
//public class MentorReportDAO extends MyDAO {
//
//    public void UploadReport(String title, String description, int mentorId, String filename) {
//        String INSERT_REPORT_SQL = "INSERT INTO MentorReport (report_name,report_description,report_file, mentor_id) VALUES (?,?,?, ?)";
//
//        try (
//                PreparedStatement ps = con.prepareStatement(INSERT_REPORT_SQL)) {
//            ps.setString(1, title);
//            ps.setString(2, description);
//            ps.setString(3, filename);
//            ps.setInt(4, mentorId);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<MentorReport> getAllMidTermReport() {
//
//        String GET_ALL_REPORTS_SQL = "SELECT * FROM MidTermReport";
//        List<MentorReport> report = new ArrayList<>();
//        try {
//            PreparedStatement ps = con.prepareStatement(GET_ALL_REPORTS_SQL);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                report.add(new MentorReport(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getTimestamp(4),
//                        rs.getString(5),
//                        rs.getInt(6)));
//            }
//        } catch (Exception e) {
//        }
//        return report;
//    }
//
//    public List<MentorReport> getAllFinalReport() {
//
//        String GET_ALL_REPORTS_SQL = "SELECT * FROM FinalReport";
//        List<MentorReport> report = new ArrayList<>();
//        try {
//            PreparedStatement ps = con.prepareStatement(GET_ALL_REPORTS_SQL);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                report.add(new MentorReport(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getTimestamp(4),
//                        rs.getString(5),
//                        rs.getInt(6)));
//            }
//        } catch (Exception e) {
//        }
//        return report;
//    }
//
//    
//    
//    
//    public void updateMidtermReport(Midterm report) throws SQLException {
//        String UPDATE_WEEKLY_REPORT_SQL = "UPDATE MidtermReport SET report_name=?,week_report = ?, report_description = ?,report_date = now(), report_file = ? WHERE report_id = ?";
//        try (PreparedStatement rs = con.prepareStatement(UPDATE_WEEKLY_REPORT_SQL)) {
//            rs.setString(1, report.getReportName());
//            rs.setString(2, report.getWeekreport());
//            rs.setString(3, report.getReportDescription());
//            rs.setString(4, report.getFiledata());
//            ps.setInt(5, report.getReportId());
//            rs.executeUpdate();
//        } catch (Exception e) {
//        }
//    }
//
//    public void deleteMidtermReport(int reportId) throws SQLException {
//        String DELETE_WEEKLY_REPORT_SQL = "DELETE FROM MidtermReport WHERE report_id = ?";
//        try (PreparedStatement rs = con.prepareStatement(DELETE_WEEKLY_REPORT_SQL)) {
//            rs.setInt(1, reportId);
//            rs.executeUpdate();
//        }
//    }
////    public static void main(String[] args) {
////        MentorReportDAO mentorreport = new MentorReportDAO();
////                mentorreport.getAllReports();
////                
////                System.out.print(mentorreport.getAllReports());
////    }
//}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.FinalReport;
import model.Intern;
import model.MidtermReport;

/**
 *
 * @author duong
 */
public class MidtermReportDAO extends MyDAO {

    public Intern getStudentById(int internId) {
        String GET_STUDENT_SQL = "SELECT * FROM Intern WHERE intern_id = ?";
        Intern student = null;

        try (PreparedStatement ps = con.prepareStatement(GET_STUDENT_SQL)) {
            ps.setInt(1, internId);
            rs = ps.executeQuery();

            while (rs.next()) {
                String statusString = rs.getString("status");
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                student = new Intern(
                        rs.getInt(1),
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public void insertMidtermReport(int mentorId, int internId, boolean excellent, boolean veryGood, boolean good, boolean average, boolean poor) {
        String INSERT_MIDTERM_REPORT_SQL = "INSERT INTO MidtermReport (mentor_id,intern_id, excellent, verygood, good, average, poor) VALUES (?, ?, ?, ?, ?,?, ?)";
        try (PreparedStatement ps = con.prepareStatement(INSERT_MIDTERM_REPORT_SQL)) {
            ps.setInt(1, mentorId);
            ps.setInt(2, internId);
            ps.setBoolean(3, excellent);
            ps.setBoolean(4, veryGood);
            ps.setBoolean(5, good);
            ps.setBoolean(6, average);
            ps.setBoolean(7, poor);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isInternIdExists(int internId) {
        String sql = "SELECT COUNT(*) AS count FROM MidtermReport WHERE intern_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<MidtermReport> getAllMidtermReportsbyId(int internId) {
        List<MidtermReport> reports = new ArrayList<>();
        String SELECT_ALL_REPORTS = "SELECT mission_rp_id, mentor_id, intern_id,\n"
                + "                 (SELECT full_name FROM Intern WHERE Intern.intern_id = midtermreport.intern_id) AS intern_name,\n"
                + "               (SELECT staff_id FROM Intern WHERE Intern.intern_id = midtermreport.intern_id) AS staff_id,excellent,verygood,good,average,poor,\n"
                + "    submission_date FROM midtermreport where intern_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_REPORTS);
            ps.setInt(1, internId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int missionRpId = rs.getInt(1);
                int mentorId = rs.getInt(2);
                internId = rs.getInt(3);
                String internName = rs.getString(4);
                String staffId = rs.getString(5);
                boolean excellent = rs.getBoolean(6);
                boolean veryGood = rs.getBoolean(7);
                boolean good = rs.getBoolean(8);
                boolean average = rs.getBoolean(9);
                boolean poor = rs.getBoolean(10);
                Timestamp submissionDate = rs.getTimestamp(11);
                reports.add(new MidtermReport(missionRpId, mentorId, internId, internName, staffId, excellent, veryGood, good, average, poor, submissionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public List<MidtermReport> getAllMidtermReports() {
        List<MidtermReport> reports = new ArrayList<>();
        String SELECT_ALL_REPORTS = "SELECT mission_rp_id, mentor_id, intern_id,\n"
                + "                 (SELECT full_name FROM Intern WHERE Intern.intern_id = midtermreport.intern_id) AS intern_name,\n"
                + "               (SELECT staff_id FROM Intern WHERE Intern.intern_id = midtermreport.intern_id) AS staff_id,excellent,verygood,good,average,poor,\n"
                + "    submission_date FROM midtermreport where intern_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_REPORTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                int missionRpId = rs.getInt(1);
                int mentorId = rs.getInt(2);
                int internId = rs.getInt(3);
                String internName = rs.getString(4);
                String staffId = rs.getString(5);
                boolean excellent = rs.getBoolean(6);
                boolean veryGood = rs.getBoolean(7);
                boolean good = rs.getBoolean(8);
                boolean average = rs.getBoolean(9);
                boolean poor = rs.getBoolean(10);
                Timestamp submissionDate = rs.getTimestamp(11);
                reports.add(new MidtermReport(missionRpId, mentorId, internId, internName, staffId, excellent, veryGood, good, average, poor, submissionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public void updatecomment(int internId, boolean excellent, boolean veryGood, boolean good, boolean average, boolean poor) {
        String UPDATE_SCORES_SQL = "UPDATE MidtermReport SET excellent=?, verygood=?, good=?, average=?, poor=? WHERE intern_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(UPDATE_SCORES_SQL);

            ps.setBoolean(1, excellent);
            ps.setBoolean(2, veryGood);
            ps.setBoolean(3, good);
            ps.setBoolean(4, average);
            ps.setBoolean(5, poor);
            ps.setInt(6, internId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteReport(int internId) {
        String DELETE_REPORT_SQL = "DELETE FROM MidtermReport WHERE intern_id=?";
        boolean deleted = false;

        try {
            PreparedStatement ps = con.prepareStatement(DELETE_REPORT_SQL);
            ps.setInt(1, internId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

//    public static void main(String[] args) {
//        List<MidtermReport> n = new ArrayList<>();
//        MidtermReportDAO m = new MidtermReportDAO();
//        n = m.getAllMidtermReports();
//        System.out.println(n);
//    }
}

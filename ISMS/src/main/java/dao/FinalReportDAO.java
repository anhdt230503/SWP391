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

/**
 *
 * @author duong
 */
public class FinalReportDAO extends MyDAO {

    public void SubmitReport(int mentorId, int internId, double softScore, double skillsScore, double attitudeScore, double finalScore) {
        String sql = "INSERT INTO FinalReport (mentor_id, intern_id, soft_score, skills_score, attitue_score, final_score) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mentorId);
            ps.setInt(2, internId);
            ps.setDouble(3, softScore);
            ps.setDouble(4, skillsScore);
            ps.setDouble(5, attitudeScore);
            ps.setDouble(6, finalScore);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isInternIdExists(int internId) {
        String sql = "SELECT COUNT(*) AS count FROM finalreport WHERE intern_id = ?";
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

    public List<Intern> getStudent(int mentorId) {
        String GET_ALL_REPORTS_SQL = "SELECT * FROM Intern i JOIN InternAssign ia ON i.intern_id = ia.intern_id  WHERE ia.mentor_id = ?";
        List<Intern> report = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(GET_ALL_REPORTS_SQL);
            ps.setInt(1, mentorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(11);
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                report.add(new Intern(rs.getInt(1),
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
        return report;

    }

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

    public List<Integer> getTotalMissions() {
        List<Integer> totalMissions = new ArrayList<>();
        String sql = "SELECT COUNT(mis_id) AS total_missions\n"
                + "FROM Mission\n"
                + "GROUP BY intern_id;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                totalMissions.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalMissions;
    }

    public List<Integer> getTotalFinished() {
        List<Integer> totalfinished = new ArrayList<>();
        String sql = "SELECT COUNT(mis_id) AS total_finishsed\n"
                + "FROM Mission\n"
                + "WHERE mis_status = 'FINISHED'\n"
                + "GROUP BY intern_id;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                totalfinished.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalfinished;
    }

    public List<FinalReport> getAllFinalReports() {
        List<FinalReport> reports = new ArrayList<>();
        String SELECT_ALL_REPORTS = "SELECT mission_rp_id, mentor_id, intern_id, \n"
                + "        (SELECT full_name FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS intern_name, \n"
                + "        (SELECT staff_id FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS staff_id, \n"
                + "        soft_score, skills_score, attitue_score, final_score, submission_date FROM FinalReport";

        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_REPORTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                int missionRpId = rs.getInt(1);
                int mentorId = rs.getInt(2);
                int internId = rs.getInt(3);
                String internName = rs.getString(4);
                String staffId = rs.getString(5);
                double softScore = rs.getDouble(6);
                double skillsScore = rs.getDouble(7);
                double attitueScore = rs.getDouble(8);
                double finalScore = rs.getDouble(9);
                Timestamp submissionDate = rs.getTimestamp(10);
                reports.add(new FinalReport(missionRpId, mentorId, internId, internName, staffId, softScore, skillsScore, attitueScore, finalScore, submissionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public List<FinalReport> getAllFinalReportsbyId(int internId) {
        List<FinalReport> reports = new ArrayList<>();
        String SELECT_ALL_REPORTS = "SELECT mission_rp_id, mentor_id, intern_id, \n"
                + "        (SELECT full_name FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS intern_name, \n"
                + "        (SELECT staff_id FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS staff_id, \n"
                + "        ROUND(soft_score, 2), ROUND(skills_score, 2), ROUND(attitue_score, 2) , ROUND(final_score, 3), submission_date FROM FinalReport where intern_id=?";

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
                double softScore = rs.getDouble(6);
                double skillsScore = rs.getDouble(7);
                double attitueScore = rs.getDouble(8);
                double finalScore = rs.getDouble(9);
                Timestamp submissionDate = rs.getTimestamp(10);
                reports.add(new FinalReport(missionRpId, mentorId, internId, internName, staffId, softScore, skillsScore, attitueScore, finalScore, submissionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public void updateScores(int internId, double skills, double softSkills, double attitude, double finalScore) {
        String UPDATE_SCORES_SQL = "UPDATE FinalReport SET soft_score=?, skills_score=?, attitue_score=?, final_score=? WHERE intern_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(UPDATE_SCORES_SQL);
            ps.setDouble(1, softSkills);
            ps.setDouble(2, skills);
            ps.setDouble(3, attitude);
            ps.setDouble(4, finalScore);
            ps.setInt(5, internId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean deleteReport(int internId) {
        String DELETE_REPORT_SQL = "DELETE FROM FinalReport WHERE intern_id=?";
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
}

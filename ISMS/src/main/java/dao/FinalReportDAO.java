/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.FinalReport;
import model.Intern;
import java.sql.Timestamp;
import model.Mission;

/**
 *
 * @author duong
 */
public class FinalReportDAO extends MyDAO {

    public void SubmitReport(int mentorId, int internId, double softScore, double skillsScore, double attitudeScore, double finalScore,String comment) {
        String sql = "INSERT INTO FinalReport (mentor_id, intern_id, soft_score, skills_score, attitue_score, final_score,comment) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mentorId);
            ps.setInt(2, internId);
            ps.setDouble(3, softScore);
            ps.setDouble(4, skillsScore);
            ps.setDouble(5, attitudeScore);
            ps.setDouble(6, finalScore);
            ps.setString(7,comment);
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

    public List<Intern> getStudentById1(int internId) {
        String GET_STUDENT_SQL = "SELECT * FROM Intern WHERE intern_id = ?";
        List<Intern> student = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(GET_STUDENT_SQL)) {
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString("status");
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                student.add(new Intern(
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
                        rs.getDouble(15)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Integer> getTotalMissions(int internid) {
        List<Integer> totalMissions = new ArrayList<>();
        String sql = "SELECT COUNT(mis_id) AS total_missions\n"
                + "FROM Mission\n"
                + "WHERE intern_id = ?\n"
                + "GROUP BY intern_id;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, internid);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalMissions.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalMissions;
    }

    public List<Integer> getTotalFinished(int internid) {
        List<Integer> totalfinished = new ArrayList<>();
        String sql = "SELECT COUNT(mis_id) AS total_finishsed\n"
                + "FROM Mission\n"
                + "WHERE mis_status = 'FINISHED'\n"
                + "WHERE intern_id = ?\n"
                + "GROUP BY intern_id;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, internid);
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
                + " (SELECT student_id FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS st_id,\n"
                + " (SELECT full_name FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS intern_name, \n"
                + " (SELECT full_name FROM Mentor WHERE mentor.mentor_id = FinalReport.mentor_id) AS full_name,\n"
                + "(SELECT staff_id FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS staff_id, \n"
                + " soft_score, skills_score, attitue_score, final_score,comment, submission_date FROM FinalReport";
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_REPORTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                int missionRpId = rs.getInt(1);
                int mentorId = rs.getInt(2);
                int internId = rs.getInt(3);
                String studentid = rs.getString(4);
                String internName = rs.getString(5);
                String mentorName = rs.getString(6);
                String staffId = rs.getString(7);
                double softScore = rs.getDouble(8);
                double skillsScore = rs.getDouble(9);
                double attitueScore = rs.getDouble(10);
                double finalScore = rs.getDouble(11);
                String comment = rs.getString(12);
                Timestamp submissionDate = rs.getTimestamp(13);
                reports.add(new FinalReport(missionRpId, mentorId, internId, studentid, internName, mentorName, staffId, softScore, skillsScore, attitueScore, finalScore,comment ,submissionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public List<FinalReport> getAllFinalReportsbyId(int internId) {
        List<FinalReport> reports = new ArrayList<>();
        String SELECT_ALL_REPORTS = "SELECT mission_rp_id, mentor_id, intern_id, \n"
                + "(SELECT student_id FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS st_id,\n"
                + "(SELECT full_name FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS intern_name, \n"
                + "(SELECT full_name FROM Mentor WHERE mentor.mentor_id = FinalReport.mentor_id) AS full_name,\n"
                + "(SELECT staff_id FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS staff_id, \n"
                + "soft_score, skills_score, attitue_score, final_score,comment, submission_date FROM FinalReport where intern_id=?;";
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_REPORTS);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int missionRpId = rs.getInt(1);
                int mentorId = rs.getInt(2);
                internId = rs.getInt(3);
                String studentid = rs.getString(4);
                String internName = rs.getString(5);
                String mentorName = rs.getString(6);
                String staffId = rs.getString(7);
                double softScore = rs.getDouble(8);
                double skillsScore = rs.getDouble(9);
                double attitueScore = rs.getDouble(10);
                double finalScore = rs.getDouble(11);
                String comment = rs.getString(12);
                Timestamp submissionDate = rs.getTimestamp(13);
                reports.add(new FinalReport(missionRpId, mentorId, internId, studentid, internName, mentorName, staffId, softScore, skillsScore, attitueScore, finalScore,comment ,submissionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public List<FinalReport> getAllFinalReportsbyMentorId(int mentor_Id) {
        List<FinalReport> reports = new ArrayList<>();
        String SELECT_ALL_REPORTS = "SELECT mission_rp_id, mentor_id, intern_id, \n"
                + " (SELECT student_id FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS st_id,\n"
                + " (SELECT full_name FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS intern_name, \n"
                + "  (SELECT full_name FROM Mentor WHERE mentor.mentor_id = FinalReport.mentor_id) AS full_name,\n"
                + "  (SELECT staff_id FROM Intern WHERE Intern.intern_id = FinalReport.intern_id) AS staff_id, \n"
                + "   soft_score, skills_score, attitue_score, final_score,comment ,submission_date FROM FinalReport where mentor_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_REPORTS);
            ps.setInt(1, mentor_Id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int missionRpId = rs.getInt(1);
                int mentorId = rs.getInt(2);
                int internId = rs.getInt(3);
                String studentid = rs.getString(4);
                String internName = rs.getString(5);
                String mentorName = rs.getString(6);
                String staffId = rs.getString(7);
                double softScore = rs.getDouble(8);
                double skillsScore = rs.getDouble(9);
                double attitueScore = rs.getDouble(10);
                double finalScore = rs.getDouble(11);
                String comment = rs.getString(12);
                Timestamp submissionDate = rs.getTimestamp(13);
                reports.add(new FinalReport(missionRpId, mentorId, internId, studentid, internName, mentorName, staffId, softScore, skillsScore, attitueScore, finalScore, comment,submissionDate));
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

    public List<FinalReport> getAllInternCompletionOfInternship() {
        List<FinalReport> list = new ArrayList();
        xSql = "SELECT fr.mission_rp_id, fr.mentor_id, i.intern_id, i.full_name, i.staff_id, fr.soft_score, fr.skills_score, fr.attitue_score, fr.final_score, fr.submission_date\n"
                + "FROM finalreport fr\n"
                + "JOIN intern i\n"
                + "ON i.intern_id = fr.intern_id \n"
                + "WHERE fr.final_score >= 6";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                FinalReport finalReport = new FinalReport();
                finalReport.setMission_rp_id(rs.getInt(1));
                finalReport.setMentor_id(rs.getInt(2));
                finalReport.setIntern_id(rs.getInt(3));
                finalReport.setIntern_name(rs.getString(4));
                finalReport.setStaff_id(rs.getString(5));
                finalReport.setSoft_score(rs.getDouble(6));
                finalReport.setSkills_score(rs.getDouble(7));
                finalReport.setAttitue_score(rs.getDouble(8));
                finalReport.setFinal_score(rs.getDouble(9));
                finalReport.setSubmission_date(rs.getTimestamp(10));
                list.add(finalReport);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public String getNameByInternId(int internId) {
        String internName = "";
        xSql = "SELECT full_name\n"
                + "FROM Intern \n"
                + "WHERE intern_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            if (rs.next()) {
                internName = rs.getString(1);
            }
        } catch (Exception e) {
        }
        return internName;
    }

    // Manager
    public List<Intern> getStudentAll() {
        String GET_STUDENT_SQL = "SELECT * FROM Intern";
        List<Intern> student = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(GET_STUDENT_SQL)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString("status");
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                student.add(new Intern(
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
                        rs.getDouble(15)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Intern getStudentAll2() {
        String GET_STUDENT_SQL = "SELECT * FROM Intern";
        Intern student = new Intern();

        try (PreparedStatement ps = con.prepareStatement(GET_STUDENT_SQL)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString("status");
                Intern.InternStatus status = Intern.InternStatus.valueOf(statusString.toUpperCase());
                student = (new Intern(
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
                        rs.getDouble(15)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

//     public List<Mission> getAllMissions() {
//        List<Mission> missions = new ArrayList<>();
//        String xSql = "Select * from mission";
//
//        try {
//            ps = con.prepareStatement(xSql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                int misId = rs.getInt("mis_id");
//                String misName = rs.getString("mis_name");
//                Mission.MissionStatus misStatus = Mission.MissionStatus.valueOf(rs.getString("mis_status"));
//                String misDescription = rs.getString("mis_description");
//                String link = rs.getString("link");
//                Timestamp startDate = rs.getTimestamp("start_date");
//                Timestamp deadline = rs.getTimestamp("deadline");
//                int mentorId = rs.getInt("mentor_id");
//                int internId = rs.getInt("intern_id");
//                String mentorFullName = rs.getString("mentorFullName");
//                String internFullName = rs.getString("internFullName");
//                String filePath = rs.getString("file_path");
//                Timestamp createdAt = rs.getTimestamp("created_at");
//                Timestamp updatedAt = rs.getTimestamp("updated_at");
//                Timestamp submittedAt = rs.getTimestamp("submitted_at");
//                Mission mission = new Mission(misId, misName, misStatus, misDescription, link, startDate, deadline, mentorId, internId, mentorFullName, internFullName, filePath, createdAt, updatedAt, submittedAt);
//                missions.add(mission);
//            }
//        } catch (SQLException e) {
//            System.err.println("Error retrieving mission list: " + e.getMessage());
//        }
//
//        return missions;
//    }
    public static void main(String[] args) {
        FinalReportDAO finalReportDAO = new FinalReportDAO();
//        List<FinalReport> list = finalReportDAO.getAllInternCompletionOfInternship();
//        for (FinalReport fr : list) {
//            System.out.println(fr);
//        }
//        String internName = finalReportDAO.getNameByInternId(1);
//        System.out.println("Name: " + internName);
//            List <Intern> list = finalReportDAO.getStudentAll();
//            for (Intern intern : list ){
//                int internid = intern.getInternId();
//                System.out.println(internid);
//
//            }

    }
}

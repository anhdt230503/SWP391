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
import model.MidtermReport;

/**
 *
 * @author duong
 */
public class FinalReportDAO extends MyDAO {

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
        xSql = "SELECT fr.mission_rp_id, fr.mentor_id, i.intern_id, i.full_name, fr.soft_score, fr.skills_score, fr.attitue_score, fr.final_score, fr.submission_date\n"
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
                finalReport.setSoft_score(rs.getDouble(5));
                finalReport.setSkills_score(rs.getDouble(6));
                finalReport.setAttitue_score(rs.getDouble(7));
                finalReport.setFinal_score(rs.getDouble(8));
                finalReport.setSubmission_date(rs.getTimestamp(9));
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
    
    

    public static void main(String[] args) {
        FinalReportDAO finalReportDAO = new FinalReportDAO();
        List<FinalReport> list = finalReportDAO.getAllInternCompletionOfInternship();
        for (FinalReport fr : list) {
            System.out.println(fr);

        }
        
        String internName = finalReportDAO.getNameByInternId(1);
        System.out.println("Name: " + internName);

    }

}

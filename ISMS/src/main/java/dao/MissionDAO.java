/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Mission;
import model.Mission.MissionStatus;

/**
 *
 * @author haidu
 */
public class MissionDAO extends MyDAO {

    public List<Mission> getAllMissions() {
        List<Mission> missions = new ArrayList<>();
        String xSql = "SELECT * FROM Mission";
        
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int misId = rs.getInt("mis_id");
                String misName = rs.getString("mis_name");
                MissionStatus misStatus = MissionStatus.valueOf(rs.getString("mis_status"));
                String misDescription = rs.getString("mis_description");
                String link = rs.getString("link");
                Timestamp startDate = rs.getTimestamp("start_date");
                Timestamp deadline = rs.getTimestamp("deadline");
                int mentorId = rs.getInt("mentor_id");
                int internId = rs.getInt("intern_id");

                Mission mission = new Mission(misId, misName, misStatus, misDescription, link, startDate, deadline, mentorId, internId);
                missions.add(mission);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách nhiệm vụ: " + e.getMessage());
        }
        
        return missions;
    }

    public void addMission(Mission mission) {
        String xSql = "INSERT INTO Mission (mis_name, mis_status, mis_description, link, start_date, deadline) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, mission.getMisName());
            ps.setString(2, mission.getMisStatus().name());
            ps.setString(3, mission.getMisDescription());
            ps.setString(4, mission.getLink());
            ps.setTimestamp(5, mission.getStartDate());
            ps.setTimestamp(6, mission.getDeadline());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm nhiệm vụ: " + e.getMessage());
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}

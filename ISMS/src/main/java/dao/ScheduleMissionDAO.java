/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.ScheduleMission;

/**
 *
 * @author haidu
 */
public class ScheduleMissionDAO extends MyDAO {

    public List<ScheduleMission> getAllScheduleMissions(int internId) {
        List<ScheduleMission> list = new ArrayList<>();
        xSql = "SELECT sm.schedule_id, sm.intern_id, sm.mis_id, m.mis_name, sm.rate, sm.created_date, sm.start_date, sm.hours, sm.note\n"
                + "From Mission m\n"
                + "Join ScheduleMission sm\n"
                + "On m.mis_id = sm.mis_id\n"
                + "Where sm.intern_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ScheduleMission scheduleMission = new ScheduleMission();
                scheduleMission.setScheduleId(rs.getInt(1));
                scheduleMission.setInternId(rs.getInt(2));
                scheduleMission.setMisId(rs.getInt(3));
                scheduleMission.setMisName(rs.getString(4));
                scheduleMission.setRate(rs.getDouble(5));
                scheduleMission.setCreatedDate(rs.getTimestamp(6));
                scheduleMission.setStartDate(rs.getDate(7));
                scheduleMission.setHour(rs.getInt(8));
                scheduleMission.setNote(rs.getString(9));
                list.add(scheduleMission);
            }
        } catch (Exception ex) {
        }
        return list;
    }

    public void addScheduleMission(ScheduleMission scheduleMission) {
        xSql = "INSERT INTO ScheduleMission (intern_id, mis_id, start_date, hours, note)\n"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, scheduleMission.getInternId());
            ps.setInt(2, scheduleMission.getMisId());
            ps.setDate(3, scheduleMission.getStartDate());
            ps.setInt(4, scheduleMission.getHour());
            ps.setString(5, scheduleMission.getNote());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public ScheduleMission getScheduleMissionById(int scheduleId) {
        xSql = "SELECT sm.schedule_id, sm.intern_id, sm.mis_id, m.mis_name, sm.rate, sm.created_date, sm.start_date, sm.hours, sm.note\n"
                + "From Mission m\n"
                + "Join ScheduleMission sm\n"
                + "On m.mis_id = sm.mis_id\n"
                + "Where sm.schedule_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, scheduleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ScheduleMission scheduleMission = new ScheduleMission();
                scheduleMission.setScheduleId(rs.getInt(1));
                scheduleMission.setInternId(rs.getInt(2));
                scheduleMission.setMisId(rs.getInt(3));
                scheduleMission.setMisName(rs.getString(4));
                scheduleMission.setRate(rs.getDouble(5));
                scheduleMission.setCreatedDate(rs.getTimestamp(6));
                scheduleMission.setStartDate(rs.getDate(7));
                scheduleMission.setHour(rs.getInt(8));
                scheduleMission.setNote(rs.getString(9));
                return scheduleMission;
            }
        } catch (Exception ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        ScheduleMissionDAO scheduleMissionDAO = new ScheduleMissionDAO();
        System.out.println(scheduleMissionDAO.getAllScheduleMissions(1));
//        System.out.println(scheduleMissionDAO.getScheduleMissionById(1));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Schedule;

/**
 *
 * @author haidu
 */
public class ScheduleDAO extends MyDAO {

    public List<Schedule> getlistSchedule() {
        String SQL = "SELECT sc.mis_id,m.mis_name,sc.intern_id,sc.rate,sc.start_date,sc.end_date,sc.hours,sc.status"
                + " from internmsdb.schedule_mis sc left join internmsdb.mission m on sc.mis_id = m.mis_id";
        List<Schedule> sc = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                sc.add(new Schedule(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        rs.getTimestamp(5),
                        rs.getTimestamp(6),
                        rs.getInt(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return sc;
    }

    public void Addschedule(int internId, String name, float rate, Timestamp date, int hours, String description) {
        String SQL = "INSERT INTO Schedule_Mis_Exten value (?,?,?,?,?,?)";
        try (
                PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, internId);
            ps.setString(2, name);
            ps.setFloat(3, rate);
            ps.setTimestamp(4, date);
            ps.setInt(5, hours);
            ps.setString(6, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        ScheduleDAO sc = new ScheduleDAO();
//        sc.getnamemission();
//        System.out.println(sc + "");
//    }
}

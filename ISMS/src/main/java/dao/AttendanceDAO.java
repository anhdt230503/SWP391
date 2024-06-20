/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Attendance;

/**
 *
 * @author haidu
 */
public class AttendanceDAO extends MyDAO {

    public Attendance getAttendanceByDate(Date currentDate, int internId) {
        xSql = "SELECT * FROM attendance\n"
                + "WHERE DATE(check_in_time) = ? AND intern_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setDate(1, currentDate);
            ps.setInt(2, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(7);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                return new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getTimestamp(4),
                        rs.getTimestamp(5),
                        rs.getString(6),
                        status,
                        rs.getLong(8));
            }
        } catch (Exception ex) {
        }
        return null;
    }

    public void insertAttendance(Attendance attendance) {
        xSql = "INSERT INTO Attendance (intern_id, check_in_time, status, duration)\n"
                + "VALUES (?, ?, ?, 0)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, attendance.getInternId());
            ps.setTimestamp(2, attendance.getCheckInTime());
            ps.setString(3, attendance.getStatus().toString());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateAllAttendance(Attendance attendance) {
        xSql = "UPDATE Attendance\n"
                + "SET check_out_time = ?,\n"
                + "    total_work_time = ?,\n"
                + "    duration = ?\n"
                + "WHERE check_in_time = ? AND intern_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setTimestamp(1, attendance.getCheckOutTime());
            ps.setString(2, attendance.getTotalWorkTime());
            ps.setLong(3, attendance.getDuration());
            ps.setTimestamp(4, attendance.getCheckInTime());
            ps.setInt(5, attendance.getInternId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Attendance> getAllAttendance(int internId) {
        List<Attendance> list = new ArrayList<>();
        xSql = "SELECT * FROM Attendance\n"
                + "WHERE intern_id = ?\n"
                + "ORDER BY check_in_time DESC";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(7);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                list.add(new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getTimestamp(4),
                        rs.getTimestamp(5),
                        rs.getString(6),
                        status,
                        rs.getLong(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void updateAttendanceStatus(Attendance attendance) {
        xSql = "UPDATE Attendance\n"
                + "SET status = ?\n"
                + "WHERE attendance_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, attendance.getStatus().toString());
            ps.setInt(2, attendance.getAttendanceId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Attendance getAttendanceById(String attendanceId) {
        xSql = "SELECT * FROM Attendance\n"
                + "WHERE attendance_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, attendanceId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(7);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                return new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getTimestamp(4),
                        rs.getTimestamp(5),
                        rs.getString(6),
                        status,
                        rs.getLong(8));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Attendance getAllAttendanceByMentor(String attendanceId) {
        xSql = "SELECT * FROM Attendance\n"
                + "WHERE attendance_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, attendanceId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(7);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                return new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getTimestamp(4),
                        rs.getTimestamp(5),
                        rs.getString(6),
                        status,
                        rs.getLong(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        AttendanceDAO attendanceDAO = new AttendanceDAO();

        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(timeStamp.getTime());
        System.out.println(date);

        Attendance attendance = attendanceDAO.getAttendanceById("2");
        System.out.println(attendance);
    }

}

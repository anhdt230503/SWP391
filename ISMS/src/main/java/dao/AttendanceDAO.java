/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Attendance;

/**
 *
 * @author haidu
 */
public class AttendanceDAO extends MyDAO {

    public Attendance getAttendanceByDate(Date currentDate, int internId) {
        xSql = "SELECT * FROM Attendance\n"
                + "WHERE attend_date = ? AND intern_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setDate(1, currentDate);
            ps.setInt(2, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(8);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                return new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getTimestamp(5),
                        rs.getTimestamp(6),
                        rs.getString(7),
                        status,
                        rs.getLong(9));
            }
        } catch (Exception ex) {
        }
        return null;
    }

    public void insertAttendance(Attendance attendance) {
        xSql = "INSERT INTO Attendance (intern_id, attend_date, duration)\n"
                + "VALUES (?, ?, 0)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, attendance.getInternId());
            ps.setDate(2, attendance.getAttendDate());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateCheckInTime(Attendance attendance) {
        xSql = "UPDATE Attendance\n "
//                + "SET status = ?,\n"
                + "SET check_in_time = ?"
                + "WHERE attend_date = ? AND intern_id =  ?;";
        try {
            ps = con.prepareStatement(xSql);
//            ps.setString(1, attendance.getStatus().toString());
            ps.setTimestamp(1, attendance.getCheckInTime());
            ps.setDate(2, attendance.getAttendDate());
            ps.setInt(3, attendance.getInternId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi");
        }
    }
    
    public void updateStatus(Attendance attendance) {
        xSql = "UPDATE Attendance\n "
                + "SET status = ?\n"
                + "WHERE attend_date = ? AND intern_id =  ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, attendance.getStatus().toString());
            ps.setDate(2, attendance.getAttendDate());
            ps.setInt(3, attendance.getInternId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi");
        }
    }
    
     public List<Attendance> getNotYetAttendance(Date attendDate) {
        List<Attendance> list = new ArrayList<>();
        xSql = "SELECT * FROM Attendance\n"
                + "WHERE status = 'NOT_YET' AND attend_date = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setDate(1, attendDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(8);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                list.add(new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getTimestamp(5),
                        rs.getTimestamp(6),
                        rs.getString(7),
                        status,
                        rs.getLong(9)));
            }
        } catch (Exception e) {
        }
        return list;
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
                + "WHERE intern_id = ?\n";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(8);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                list.add(new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getTimestamp(5),
                        rs.getTimestamp(6),
                        rs.getString(7),
                        status,
                        rs.getLong(9)));
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
                String statusString = rs.getString(8);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                return new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getTimestamp(5),
                        rs.getTimestamp(6),
                        rs.getString(7),
                        status,
                        rs.getLong(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        List<Attendance> list = new ArrayList<>();

//        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
//        Date date = new Date(timeStamp.getTime());
//        System.out.println(date);
        Timestamp uploadDate = new Timestamp(System.currentTimeMillis());
        LocalDate importDate = uploadDate.toLocalDateTime().toLocalDate();
//        LocalDate attendDate = importDate.plusDays(2);
        LocalDate attendDate = importDate;
        LocalDateTime dateTime = uploadDate.toLocalDateTime();
        LocalDateTime dateTime1 = dateTime;

        Attendance attendance = new Attendance();
        attendance.setStatus(Attendance.AttendanceStatus.PRESENT);
//        attendance.setCheckInTime(java.sql.Timestamp.valueOf(dateTime1));
        attendance.setAttendDate(java.sql.Date.valueOf(attendDate));
        attendance.setInternId(1);
//        attendanceDAO.testUpdate(attendance);

        attendanceDAO.updateStatus(attendance);
        System.out.println(attendance);
        
        list = attendanceDAO.getNotYetAttendance(java.sql.Date.valueOf(attendDate));
        System.out.println(list);
        for (Attendance a : list) {
            System.out.println(a);

        }
    }

}

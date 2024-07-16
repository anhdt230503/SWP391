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
import model.Intern;

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
                String statusString = rs.getString(7);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                return new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
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
                + "SET check_in_time = ?,\n"
                + "status = ?\n"
                + "WHERE attend_date = ? AND intern_id =  ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setTimestamp(1, attendance.getCheckInTime());
            ps.setString(2, attendance.getStatus().toString());
            ps.setDate(3, attendance.getAttendDate());
            ps.setInt(4, attendance.getInternId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi");
        }
    }

    public void updateEndOfDay(Attendance attendance) {
        xSql = "UPDATE Attendance\n "
                + "SET status = ?\n"
                + "WHERE attend_date <= ? AND intern_id =  ? AND status = 'NOT_YET';";
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
                + "WHERE status = 'NOT_YET' AND attend_date <= ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setDate(1, attendDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(7);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                list.add(new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
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

    public void updateCheckOutTime(Attendance attendance) {
        xSql = "UPDATE Attendance\n"
                + "SET check_out_time = ?,\n"
                + "    total_work_time = ?,\n"
                + "    duration = ?\n"
                + "WHERE attend_date = ? AND intern_id = ? AND status = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setTimestamp(1, attendance.getCheckOutTime());
            ps.setString(2, attendance.getTotalWorkTime());
            ps.setLong(3, attendance.getDuration());
            ps.setDate(4, attendance.getAttendDate());
            ps.setInt(5, attendance.getInternId());
            ps.setString(6, attendance.getStatus().toString());
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
                String statusString = rs.getString(7);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                list.add(new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
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
                        rs.getDate(3),
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

    public Attendance getAttendanceToTest(String attendanceId, int internId) {
        xSql = "SELECT * FROM Attendance\n"
                + "WHERE attendance_id = ? AND intern_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, attendanceId);
            ps.setInt(2, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(7);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                return new Attendance(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
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

    public void resetAttendanceData() {
        xSql = "UPDATE Attendance\n"
                + "SET check_in_time = null,\n"
                + "    check_out_time = null,\n"
                + "    total_work_time = null,\n"
                + "    status = 'NOT_YET',\n"
                + "    duration = 0;\n";
        try {
            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteAttendance(int internId) {
        xSql = "DELETE FROM Attendance\n"
                + "WHERE intern_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Attendance> getAllStatus() {
        List<Attendance> list = new ArrayList<>();
        xSql = "SELECT DISTINCT status \n"
                + "FROM Attendance;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String statusString = rs.getString(1);
                Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(statusString.toUpperCase());
                Attendance attendance = new Attendance();
                attendance.setStatus(status);
                list.add(attendance);
            }
        } catch (Exception ex) {
        }
        return list;
    }

    public int getNotYetCountForIntern(int internId) {
        int notYetCount = 0;
        xSql = "SELECT COUNT(*) AS not_yet_count\n"
                + "FROM Attendance\n"
                + "WHERE intern_id = ?\n"
                + "AND status = 'NOT_YET';";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            if (rs.next()) {
                notYetCount = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return notYetCount;
    }

    public static void main(String[] args) {
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        List<Attendance> list = attendanceDAO.getAllStatus();
        System.out.println(list);
        
        System.out.println(attendanceDAO.getNotYetCountForIntern(1));
    }
}

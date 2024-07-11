/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author haidu
 */
public class Attendance {

    public enum AttendanceStatus {
        NOT_YET,
        PRESENT,
        ABSENT
    }

    private int attendanceId;
    private int internId;
    private Date attendDate;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private String totalWorkTime;
    private AttendanceStatus status;
    private long duration;

    public Attendance() {
    }

    public Attendance(int attendanceId, int internId, Date attendDate, Timestamp checkInTime, Timestamp checkOutTime, String totalWorkTime, AttendanceStatus status, long duration) {
        this.attendanceId = attendanceId;
        this.internId = internId;
        this.attendDate = attendDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.totalWorkTime = totalWorkTime;
        this.status = status;
        this.duration = duration;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public Date getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(Date attendDate) {
        this.attendDate = attendDate;
    }

    public Timestamp getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Timestamp checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Timestamp checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getTotalWorkTime() {
        return totalWorkTime;
    }

    public void setTotalWorkTime(String totalWorkTime) {
        this.totalWorkTime = totalWorkTime;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Attendance{" + "attendanceId=" + attendanceId + ", internId=" + internId + ", attendDate=" + attendDate + ", checkInTime=" + checkInTime + ", checkOutTime=" + checkOutTime + ", totalWorkTime=" + totalWorkTime + ", status=" + status + ", duration=" + duration + '}';
    }

}

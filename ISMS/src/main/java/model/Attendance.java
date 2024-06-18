/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
    private int scheduleId;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private String totalWorkTime;
    private AttendanceStatus status;
    private long duration;

    public Attendance() {
    }

    public Attendance(int attendanceId, int internId, int scheduleId, Timestamp checkInTime, Timestamp checkOutTime, String totalWorkTime, AttendanceStatus status, long duration) {
        this.attendanceId = attendanceId;
        this.internId = internId;
        this.scheduleId = scheduleId;
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

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
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
        return "Attendance{" + "attendanceId=" + attendanceId + ", internId=" + internId + ", scheduleId=" + scheduleId + ", checkInTime=" + checkInTime + ", checkOutTime=" + checkOutTime + ", totalWorkTime=" + totalWorkTime + ", status=" + status + ", duration=" + duration + '}';
    }

}

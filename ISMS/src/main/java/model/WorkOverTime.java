/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author haidu
 */
public class WorkOverTime {
 
    private int overtimeId;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int scheduleId;
    private int internId;

    public WorkOverTime() {
    }

    public WorkOverTime(int overtimeId, Date date, Time startTime, Time endTime, int scheduleId, int internId) {
        this.overtimeId = overtimeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduleId = scheduleId;
        this.internId = internId;
    }

    public int getOvertimeId() {
        return overtimeId;
    }

    public void setOvertimeId(int overtimeId) {
        this.overtimeId = overtimeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    @Override
    public String toString() {
        return "WorkOverTime{" + "overtimeId=" + overtimeId + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", scheduleId=" + scheduleId + ", internId=" + internId + '}';
    }
    
}

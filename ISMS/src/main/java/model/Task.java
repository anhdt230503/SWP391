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
public class Task {
    
    private int taskId;
    private int scheduleId;
    private String taskName;
    private int hours;
    private String note;
    private boolean isDone;

    public Task() {
    }
    
    public Task(int taskId, int scheduleId, String taskName, int hours, String note, boolean isDone) {
        this.taskId = taskId;
        this.scheduleId = scheduleId;
        this.taskName = taskName;
        this.hours = hours;
        this.note = note;
        this.isDone = isDone;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getHours() {
        return hours;
    }

    public String getNote() {
        return note;
    }

    public boolean isIsDone() {
        return isDone;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "Task{" + "taskId=" + taskId + ", scheduleId=" + scheduleId + ", taskName=" + taskName + ", hours=" + hours + ", note=" + note + ", isDone=" + isDone + '}';
    }
}

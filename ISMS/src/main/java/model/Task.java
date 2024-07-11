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
    private Timestamp date;
    private int hours;
    private String description;
    private boolean isDone;

    public Task(int taskId, int scheduleId, String taskName, Timestamp date, int hours, String description, boolean isDone) {
        this.taskId = taskId;
        this.scheduleId = scheduleId;
        this.taskName = taskName;
        this.date = date;
        this.hours = hours;
        this.description = description;
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

    public Timestamp getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public String getDescription() {
        return description;
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

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "Task{" + "taskId=" + taskId + ", scheduleId=" + scheduleId + ", taskName=" + taskName + ", date=" + date + ", hours=" + hours + ", description=" + description + ", isDone=" + isDone + '}';
    }
    
}

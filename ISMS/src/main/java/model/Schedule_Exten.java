/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author duong
 */
public class Schedule_Exten {

    private int taskid;
    private int internid;
    private float rate;
    private Timestamp date;
    private int misid;
    private int hours;
    private String description;
    private boolean isdone;

    public Schedule_Exten() {
    }

    public Schedule_Exten(int taskid, int internid, float rate, Timestamp date, int misid, int hours, String description, boolean isdone) {
        this.taskid = taskid;
        this.internid = internid;
        this.rate = rate;
        this.date = date;
        this.misid = misid;
        this.hours = hours;
        this.description = description;
        this.isdone = isdone;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getInternid() {
        return internid;
    }

    public void setInternid(int internid) {
        this.internid = internid;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getMisid() {
        return misid;
    }

    public void setMisid(int misid) {
        this.misid = misid;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsdone() {
        return isdone;
    }

    public void setIsdone(boolean isdone) {
        this.isdone = isdone;
    }

    @Override
    public String toString() {
        return "Schedule_Exten{" + "taskid=" + taskid + ", internid=" + internid + ", rate=" + rate + ", date=" + date + ", misid=" + misid + ", hours=" + hours + ", description=" + description + ", isdone=" + isdone + '}';
    }

}

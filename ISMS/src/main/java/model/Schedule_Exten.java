/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author duong
 */
public class Schedule_Exten {

    private int taskid;
    private int internid;
    private int misid;
    private String misName;
    private float rate;
    private Timestamp date;
    private int hours;
    private String description;
    private boolean isdone;

    public Schedule_Exten() {
    }

    public Schedule_Exten(int taskid, int internid, int misid, String misName, float rate, Timestamp date, int hours, String description, boolean isdone) {
        this.taskid = taskid;
        this.internid = internid;
        this.misid = misid;
        this.misName = misName;
        this.rate = rate;
        this.date = date;
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

    public int getMisid() {
        return misid;
    }

    public void setMisid(int misid) {
        this.misid = misid;
    }

    public String getMisName() {
        return misName;
    }

    public void setMisName(String misName) {
        this.misName = misName;
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
        return "Schedule_Exten{" + "taskid=" + taskid + ", internid=" + internid + ", misid=" + misid + ", misName=" + misName + ", rate=" + rate + ", date=" + date + ", hours=" + hours + ", description=" + description + ", isdone=" + isdone + '}';
    }

    
}

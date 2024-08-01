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
public class MidtermReport {

    private int mission_rp_id;
    private int mentor_id;
    private int intern_id;
    private String student_id;
    private String intern_name;
    private String mentor_name;
    private String staff_id;
    private boolean excellent;
    private boolean veryGood;
    private boolean good;
    private boolean average;
    private boolean poor;
    private String comment;
    private Timestamp submissionDate;
    
    public MidtermReport() {
    }

    public MidtermReport(int mission_rp_id, int mentor_id, int intern_id, String student_id, String intern_name, String mentor_name, String staff_id, boolean excellent, boolean veryGood, boolean good, boolean average, boolean poor, String comment, Timestamp submissionDate) {
        this.mission_rp_id = mission_rp_id;
        this.mentor_id = mentor_id;
        this.intern_id = intern_id;
        this.student_id = student_id;
        this.intern_name = intern_name;
        this.mentor_name = mentor_name;
        this.staff_id = staff_id;
        this.excellent = excellent;
        this.veryGood = veryGood;
        this.good = good;
        this.average = average;
        this.poor = poor;
        this.comment = comment;
        this.submissionDate = submissionDate;
    }

    public int getMission_rp_id() {
        return mission_rp_id;
    }

    public void setMission_rp_id(int mission_rp_id) {
        this.mission_rp_id = mission_rp_id;
    }

    public int getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(int mentor_id) {
        this.mentor_id = mentor_id;
    }

    public int getIntern_id() {
        return intern_id;
    }

    public void setIntern_id(int intern_id) {
        this.intern_id = intern_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getIntern_name() {
        return intern_name;
    }

    public void setIntern_name(String intern_name) {
        this.intern_name = intern_name;
    }

    public String getMentor_name() {
        return mentor_name;
    }

    public void setMentor_name(String mentor_name) {
        this.mentor_name = mentor_name;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public boolean isExcellent() {
        return excellent;
    }

    public void setExcellent(boolean excellent) {
        this.excellent = excellent;
    }

    public boolean isVeryGood() {
        return veryGood;
    }

    public void setVeryGood(boolean veryGood) {
        this.veryGood = veryGood;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public boolean isAverage() {
        return average;
    }

    public void setAverage(boolean average) {
        this.average = average;
    }

    public boolean isPoor() {
        return poor;
    }

    public void setPoor(boolean poor) {
        this.poor = poor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public String toString() {
        return "MidtermReport{" + "mission_rp_id=" + mission_rp_id + ", mentor_id=" + mentor_id + ", intern_id=" + intern_id + ", student_id=" + student_id + ", intern_name=" + intern_name + ", mentor_name=" + mentor_name + ", staff_id=" + staff_id + ", excellent=" + excellent + ", veryGood=" + veryGood + ", good=" + good + ", average=" + average + ", poor=" + poor + ", comment=" + comment + ", submissionDate=" + submissionDate + '}';
    }

    

}

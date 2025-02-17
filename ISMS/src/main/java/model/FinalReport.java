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
public class FinalReport {

    private int mission_rp_id;
    private int mentor_id;
    private int intern_id;
    private String intern_name;
    private double soft_score;
    private double skills_score;
    private double attitue_score;
    private double final_score;
    private Timestamp submission_date;

    public FinalReport() {
    }

    public FinalReport(int mission_rp_id, int mentor_id, int intern_id, String intern_name, double soft_score, double skills_score, double attitue_score, double final_score, Timestamp submission_date) {
        this.mission_rp_id = mission_rp_id;
        this.mentor_id = mentor_id;
        this.intern_id = intern_id;
        this.intern_name = intern_name;
        this.soft_score = soft_score;
        this.skills_score = skills_score;
        this.attitue_score = attitue_score;
        this.final_score = final_score;
        this.submission_date = submission_date;
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

    public String getIntern_name() {
        return intern_name;
    }

    public void setIntern_name(String intern_name) {
        this.intern_name = intern_name;
    }

    public double getSoft_score() {
        return soft_score;
    }

    public void setSoft_score(double soft_score) {
        this.soft_score = soft_score;
    }

    public double getSkills_score() {
        return skills_score;
    }

    public void setSkills_score(double skills_score) {
        this.skills_score = skills_score;
    }

    public double getAttitue_score() {
        return attitue_score;
    }

    public void setAttitue_score(double attitue_score) {
        this.attitue_score = attitue_score;
    }

    public double getFinal_score() {
        return final_score;
    }

    public void setFinal_score(double final_score) {
        this.final_score = final_score;
    }

    public Timestamp getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(Timestamp submission_date) {
        this.submission_date = submission_date;
    }

    @Override
    public String toString() {
        return "FinalReport{" + "mission_rp_id=" + mission_rp_id + ", mentor_id=" + mentor_id + ", intern_id=" + intern_id + ", intern_name=" + intern_name + ", soft_score=" + soft_score + ", skills_score=" + skills_score + ", attitue_score=" + attitue_score + ", final_score=" + final_score + ", submission_date=" + submission_date + '}';
    }
    
    
}

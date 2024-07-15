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
public class Intern {

    public enum InternStatus {
        INTERN,
        ALUMNI
    }

    private int internId;
    private String studentId;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String major;
    private String company;
    private String jobTitle;
    private String linkCv;
    private String staffId;
    private InternStatus status;
    private int semester_id;
    private Timestamp uploadDate;
    private double midtermWorkTime;
    private double finalWorkTime;

    public Intern() {
    }

    public Intern(int internId, String studentId, String email, String fullName, String phoneNumber, String major, String company, String jobTitle, String linkCv, String staffId, InternStatus status, int semester_id, Timestamp uploadDate, double midtermWorkTime, double finalWorkTime) {
        this.internId = internId;
        this.studentId = studentId;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.major = major;
        this.company = company;
        this.jobTitle = jobTitle;
        this.linkCv = linkCv;
        this.staffId = staffId;
        this.status = status;
        this.semester_id = semester_id;
        this.uploadDate = uploadDate;
        this.midtermWorkTime = midtermWorkTime;
        this.finalWorkTime = finalWorkTime;
    }


    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLinkCv() {
        return linkCv;
    }

    public void setLinkCv(String linkCv) {
        this.linkCv = linkCv;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public InternStatus getStatus() {
        return status;
    }

    public void setStatus(InternStatus status) {
        this.status = status;
    }

    public int getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(int semester_id) {
        this.semester_id = semester_id;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    public double getMidtermWorkTime() {
        return midtermWorkTime;
    }

    public void setMidtermWorkTime(double midtermWorkTime) {
        this.midtermWorkTime = midtermWorkTime;
    }

    public double getFinalWorkTime() {
        return finalWorkTime;
    }

    public void setFinalWorkTime(double finalWorkTime) {
        this.finalWorkTime = finalWorkTime;
    }

    @Override
    public String toString() {
        return "Intern{" + "internId=" + internId + ", studentId=" + studentId + ", email=" + email + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", major=" + major + ", company=" + company + ", jobTitle=" + jobTitle + ", linkCv=" + linkCv + ", staffId=" + staffId + ", status=" + status + ", semester_id=" + semester_id + ", uploadDate=" + uploadDate + ", midtermWorkTime=" + midtermWorkTime + ", finalWorkTime=" + finalWorkTime + '}';
    }
    
    
}


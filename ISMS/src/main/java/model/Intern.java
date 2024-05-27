/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haidu
 */
public class Intern {
    
    private int internId;
    private String studentId;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String major;
    private String company;
    private String jobTitle;
    private String linkCv;

    public Intern() {
    }

    public Intern(int internId, String studentId, String email, String fullName, String phoneNumber, String major, String company, String jobTitle, String linkCv) {
        this.internId = internId;
        this.studentId = studentId;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.major = major;
        this.company = company;
        this.jobTitle = jobTitle;
        this.linkCv = linkCv;
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

    @Override
    public String toString() {
        return "Intern{" + "internId=" + internId + ", studentId=" + studentId + ", email=" + email + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", major=" + major + ", company=" + company + ", job_title=" + jobTitle + ", linkCv=" + linkCv + '}';
    }
 
    
}

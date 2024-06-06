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
public class MentorReport {
    
    private int reportId;
    private String reportName;
    private Timestamp reportDate;
    private String  filedata;
    private int mentorId;

    public MentorReport() {
    }

    public MentorReport(int reportId, String reportName, Timestamp reportDate, String filedata, int mentorId) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.reportDate = reportDate;
        this.filedata = filedata;
        this.mentorId = mentorId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }

    public String getFiledata() {
        return filedata;
    }

    public void setFiledata(String filedata) {
        this.filedata = filedata;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    @Override
    public String toString() {
        return "MentorReport{" + "reportId=" + reportId + ", reportName=" + reportName + ", reportDate=" + reportDate + ", filedata=" + filedata + ", mentorId=" + mentorId + '}';
    }
    
}

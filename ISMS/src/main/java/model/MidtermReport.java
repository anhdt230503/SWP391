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
     private int reportId;
    private String reportName;
    private String reportDescription;
    private Timestamp reportDate;
    private String filedata;
    private int mentorId;

    public MidtermReport() {
    }

    public MidtermReport(int reportId, String reportName, String reportDescription, Timestamp reportDate, String filedata, int mentorId) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.reportDescription = reportDescription;
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

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
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
        return "MidtermReport{" + "reportId=" + reportId + ", reportName=" + reportName + ", reportDescription=" + reportDescription + ", reportDate=" + reportDate + ", filedata=" + filedata + ", mentorId=" + mentorId + '}';
    }
    
    
}

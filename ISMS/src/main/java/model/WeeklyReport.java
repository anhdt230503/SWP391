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
public class WeeklyReport {
    private int reportId;
    private String reportName;
    private String weekreport;
    private String reportDescription;
    private Timestamp reportDate;
    private String filedata;
    private int internid;

    public WeeklyReport() {
    }

    public WeeklyReport(int reportId, String reportName, String weekreport, String reportDescription, Timestamp reportDate, String filedata, int internid) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.weekreport = weekreport;
        this.reportDescription = reportDescription;
        this.reportDate = reportDate;
        this.filedata = filedata;
        this.internid = internid;
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

    public String getWeekreport() {
        return weekreport;
    }

    public void setWeekreport(String weekreport) {
        this.weekreport = weekreport;
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

    public int getInternid() {
        return internid;
    }

    public void setInternid(int internid) {
        this.internid = internid;
    }

    @Override
    public String toString() {
        return "WeeklyReport{" + "reportId=" + reportId + ", reportName=" + reportName + ", weekreport=" + weekreport + ", reportDescription=" + reportDescription + ", reportDate=" + reportDate + ", filedata=" + filedata + ", internid=" + internid + '}';
    }

}

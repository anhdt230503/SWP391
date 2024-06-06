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
public class MissionReport {
    
    private int missionRpId;
    private int internId;
    private int misId;
    private String reportContent;
    private String link;
    private Timestamp submissionDate;

    public MissionReport() {
    }

    public MissionReport(int missionRpId, int internId, int misId, String reportContent, String link, Timestamp submissionDate) {
        this.missionRpId = missionRpId;
        this.internId = internId;
        this.misId = misId;
        this.reportContent = reportContent;
        this.link = link;
        this.submissionDate = submissionDate;
    }

    public int getMissionRpId() {
        return missionRpId;
    }

    public void setMissionRpId(int missionRpId) {
        this.missionRpId = missionRpId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public int getMisId() {
        return misId;
    }

    public void setMisId(int misId) {
        this.misId = misId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public String toString() {
        return "MissionReport{" + "missionRpId=" + missionRpId + ", internId=" + internId + ", misId=" + misId + ", reportContent=" + reportContent + ", link=" + link + ", submissionDate=" + submissionDate + '}';
    }
    
}

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
public class Mission {

    public enum MissionStatus {
        NOT_START,
        ON_GOING,
        FINISHED
    }
    private int misId;
    private String misName;
    private MissionStatus misStatus;
    private String misDescription;
    private String link;
    private Timestamp startDate;
    private Timestamp deadline;
    private int mentorId;
    private int internId;

    public Mission() {
    }

    public Mission(int misId, String misName, MissionStatus misStatus, String misDescription, String link, Timestamp startDate, Timestamp deadline, int mentorId, int internId) {
        this.misId = misId;
        this.misName = misName;
        this.misStatus = misStatus;
        this.misDescription = misDescription;
        this.link = link;
        this.startDate = startDate;
        this.deadline = deadline;
        this.mentorId = mentorId;
        this.internId = internId;
    }

    public int getMisId() {
        return misId;
    }

    public void setMisId(int misId) {
        this.misId = misId;
    }

    public String getMisName() {
        return misName;
    }

    public void setMisName(String misName) {
        this.misName = misName;
    }

    public MissionStatus getMisStatus() {
        return misStatus;
    }

    public void setMisStatus(MissionStatus misStatus) {
        this.misStatus = misStatus;
    }

    public String getMisDescription() {
        return misDescription;
    }

    public void setMisDescription(String misDescription) {
        this.misDescription = misDescription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    @Override
    public String toString() {
        return "Mission{" + "misId=" + misId + ", misName=" + misName + ", misStatus=" + misStatus + ", misDescription=" + misDescription + ", link=" + link + ", startDate=" + startDate + ", deadline=" + deadline + ", mentorId=" + mentorId + ", internId=" + internId + '}';
    }

    
}

package model;

import java.sql.Timestamp;

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
    private String mentorFullName;
    private String internFullName;

    public Mission() {
    }

    public Mission(int misId, String misName, MissionStatus misStatus, String misDescription, String link, Timestamp startDate, Timestamp deadline, int mentorId, int internId, String mentorFullName, String internFullName) {
        this.misId = misId;
        this.misName = misName;
        this.misStatus = misStatus;
        this.misDescription = misDescription;
        this.link = link;
        this.startDate = startDate;
        this.deadline = deadline;
        this.mentorId = mentorId;
        this.internId = internId;
        this.mentorFullName = mentorFullName;
        this.internFullName = internFullName;
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

    public String getMentorFullName() {
        return mentorFullName;
    }

    public void setMentorFullName(String mentorFullName) {
        this.mentorFullName = mentorFullName;
    }

    public String getInternFullName() {
        return internFullName;
    }

    public void setInternFullName(String internFullName) {
        this.internFullName = internFullName;
    }
    
}

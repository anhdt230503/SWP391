package model;

import java.sql.Timestamp;

public class Mission {

    public enum MissionStatus {
        NOT_START,
        ON_GOING,
        FINISHED,
        MISSING
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
    private String file_path;
    private Timestamp created_at;
    private Timestamp updateTime;  // New field
    private Timestamp submitTime;

    public Mission() {
    }

    public Mission(int misId, String misName, MissionStatus misStatus, String misDescription, String link, Timestamp startDate, Timestamp deadline, int mentorId, int internId, String mentorFullName, String internFullName, String file_path, Timestamp created_at, Timestamp updateTime, Timestamp submitTime) {
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
        this.file_path = file_path;
        this.created_at = created_at;
        this.updateTime = updateTime;
        this.submitTime = submitTime;
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

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "Mission{" + "misId=" + misId + ", misName=" + misName + ", misStatus=" + misStatus + ", misDescription=" + misDescription + ", link=" + link + ", startDate=" + startDate + ", deadline=" + deadline + ", mentorId=" + mentorId + ", internId=" + internId + ", mentorFullName=" + mentorFullName + ", internFullName=" + internFullName + ", file_path=" + file_path + ", created_at=" + created_at + ", updateTime=" + updateTime + ", submitTime=" + submitTime + '}';
    }
    
}

package model;
import java.sql.Timestamp;
public class Feedback {
    private int feedbackId;
    private String punctuality;
    private String coverage;
    private String response;
    private String teachingSkills;
    private String support;
    private String feedbackText;
    private Timestamp submissionDate;
    private int mentorId;
    private int internId;
    private int roomId;
    private String mentorFullName;
    private String internFullName;
    private String labroomName;
    public Feedback() {
    }
    public Feedback(int feedbackId, String punctuality, String coverage, String response, String teachingSkills, String support, String feedbackText, Timestamp submissionDate, int mentorId, int internId, int roomId, String mentorFullName, String internFullName, String labroomName) {
        this.feedbackId = feedbackId;
        this.punctuality = punctuality;
        this.coverage = coverage;
        this.response = response;
        this.teachingSkills = teachingSkills;
        this.support = support;
        this.feedbackText = feedbackText;
        this.submissionDate = submissionDate;
        this.mentorId = mentorId;
        this.internId = internId;
        this.roomId = roomId;
        this.mentorFullName = mentorFullName;
        this.internFullName = internFullName;
        this.labroomName = labroomName;
    }
    public int getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }
    public String getPunctuality() {
        return punctuality;
    }
    public void setPunctuality(String punctuality) {
        this.punctuality = punctuality;
    }
    public String getCoverage() {
        return coverage;
    }
    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public String getTeachingSkills() {
        return teachingSkills;
    }
    public void setTeachingSkills(String teachingSkills) {
        this.teachingSkills = teachingSkills;
    }
    public String getSupport() {
        return support;
    }
    public void setSupport(String support) {
        this.support = support;
    }
    public String getFeedbackText() {
        return feedbackText;
    }
    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
    public Timestamp getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
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
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
    public String getLabroomName() {
        return labroomName;
    }
    public void setLabroomName(String labroomName) {
        this.labroomName = labroomName;
    }
    @Override
    public String toString() {
        return "Feedback{" + "feedbackId=" + feedbackId + ", punctuality=" + punctuality + ", coverage=" + coverage + ", response=" + response + ", teachingSkills=" + teachingSkills + ", support=" + support + ", feedbackText=" + feedbackText + ", submissionDate=" + submissionDate + ", mentorId=" + mentorId + ", internId=" + internId + ", roomId=" + roomId + ", mentorFullName=" + mentorFullName + ", internFullName=" + internFullName + ", labroomName=" + labroomName + '}';
    }
}

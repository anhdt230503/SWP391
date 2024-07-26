/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

public class QandA {

    public enum QandAStatus {
        PENDING,
        ANSWERED
    }
    private int qandaId;
    private String questionTitle;
    private QandAStatus questionStatus; // Use an Enum for better type safety if desired
    private String questionText;
    private int mentorId;
    private int internId;
    private String answerText;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp submittedAt;
    private String mentorFullName;
    private String internFullName;

    public QandA() {
    }

    public QandA(int qandaId, String questionTitle, QandAStatus questionStatus, String questionText, int mentorId, int internId, String answerText, Timestamp createdAt, Timestamp updatedAt, Timestamp submittedAt, String mentorFullName, String internFullName) {
        this.qandaId = qandaId;
        this.questionTitle = questionTitle;
        this.questionStatus = questionStatus;
        this.questionText = questionText;
        this.mentorId = mentorId;
        this.internId = internId;
        this.answerText = answerText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.submittedAt = submittedAt;
        this.mentorFullName = mentorFullName;
        this.internFullName = internFullName;
    }

    public int getQandaId() {
        return qandaId;
    }

    public void setQandaId(int qandaId) {
        this.qandaId = qandaId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public QandAStatus getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(QandAStatus questionStatus) {
        this.questionStatus = questionStatus;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
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

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Timestamp submittedAt) {
        this.submittedAt = submittedAt;
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

    @Override
    public String toString() {
        return "QandA{" + "qandaId=" + qandaId + ", questionTitle=" + questionTitle + ", questionStatus=" + questionStatus + ", questionText=" + questionText + ", mentorId=" + mentorId + ", internId=" + internId + ", answerText=" + answerText + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", submittedAt=" + submittedAt + ", mentorFullName=" + mentorFullName + ", internFullName=" + internFullName + '}';
    }
    
}

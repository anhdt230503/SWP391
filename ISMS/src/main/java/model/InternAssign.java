/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haidu
 */
public class InternAssign {
    
    private int assignId;
    private int internId;
    private int mentorId;
    private boolean isSelected;

    public InternAssign() {
    }

    public InternAssign(int assignId, int internId, int mentorId, boolean isSelected) {
        this.assignId = assignId;
        this.internId = internId;
        this.mentorId = mentorId;
        this.isSelected = isSelected;
    }

    public int getAssignId() {
        return assignId;
    }

    public void setAssignId(int assignId) {
        this.assignId = assignId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "InternAssign{" + "assignId=" + assignId + ", internId=" + internId + ", mentorId=" + mentorId + ", isSelected=" + isSelected + '}';
    }
    
    
}

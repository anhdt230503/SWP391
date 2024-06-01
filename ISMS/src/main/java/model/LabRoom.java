/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haidu
 */
public class LabRoom {
    
    private int roomId;
    private String roomName;
    private boolean is_assigned;
    private int mentorId;

    public LabRoom() {
    }

    public LabRoom(int roomId, String roomName, boolean is_assigned, int mentorId) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.is_assigned = is_assigned;
        this.mentorId = mentorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isIs_assigned() {
        return is_assigned;
    }

    public void setIs_assigned(boolean is_assigned) {
        this.is_assigned = is_assigned;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    @Override
    public String toString() {
        return "LabRoom{" + "roomId=" + roomId + ", roomName=" + roomName + ", is_assigned=" + is_assigned + ", mentorId=" + mentorId + '}';
    }
    
    
}

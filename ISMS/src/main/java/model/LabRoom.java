package model;

/**
 * LabRoom model class representing a lab room in the database.
 * 
 */
public class LabRoom {

    private int roomId;
    private String roomName;
    private boolean isAssigned;
    private int mentorId;
    private String mentorFullName; // New field for mentor's full name

    // Default constructor
    public LabRoom() {
    }

    // Parameterized constructor
    public LabRoom(int roomId, String roomName, boolean isAssigned, int mentorId, String mentorFullName) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.isAssigned = isAssigned;
        this.mentorId = mentorId;
        this.mentorFullName = mentorFullName;
    }

    // Getter and Setter methods
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

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public String getMentorFullName() {
        return mentorFullName;
    }

    public void setMentorFullName(String mentorFullName) {
        this.mentorFullName = mentorFullName;
    }

    // Override toString method for debugging purposes
    @Override
    public String toString() {
        return "LabRoom{" + "roomId=" + roomId + ", roomName=" + roomName + ", isAssigned=" + isAssigned + ", mentorId=" + mentorId + ", mentorFullName=" + mentorFullName + '}';
    }
}

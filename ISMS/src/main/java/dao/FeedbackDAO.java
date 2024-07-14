package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Feedback;
import model.LabRoom;
import model.Mentor;

public class FeedbackDAO extends MyDAO {
    
    public List<Feedback> getAllFeedBack() {
        List<Feedback> feedbacks = new ArrayList<>();
        String xSql = "select f.feedback_id, f.punctuality, f.coverage, f.response, f.teaching_skills, f.support, f.feedback_text, f.submission_date, f.mentor_id, f.intern_id, f.room_id ,mt.full_name AS mentorFullName, i.full_name AS internFullName,l.room_name AS labroomFullName  "
                + "from Feedback f LEFT JOIN mentor mt ON f.mentor_id = mt.mentor_id "
                + "LEFT JOIN intern i ON f.intern_id = i.intern_id "
                + "LEFT JOIN LabRoom l ON f.room_id = l.room_id";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("feedback_id");
                String punctuality = rs.getString("punctuality");
                String coverage = rs.getString("coverage");
                String response = rs.getString("response");
                String teaching_skills = rs.getString("teaching_skills");
                String support = rs.getString("support");
                String feedback_text = rs.getString("feedback_text");
                Timestamp submission_date = rs.getTimestamp("submission_date");
                int mentorId = rs.getInt("mentor_id");
                int internId = rs.getInt("intern_id");
                int labroomId = rs.getInt("room_id");
                String mentorFullName = rs.getString("mentorFullName");
                String internFullName = rs.getString("internFullName");
                String labroomFullName = rs.getString("labroomFullName");
                Feedback Feedback = new Feedback(mentorId, punctuality, coverage, response, teaching_skills, support, feedback_text, submission_date, mentorId, internId, labroomId, mentorFullName, internFullName, labroomFullName);
                feedbacks.add(Feedback);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving mission list: " + e.getMessage());
        }
        return feedbacks;
    }
    
    public Mentor getMentorByInternId(int internId) {
        Mentor mentor = null;
        String sql = "SELECT m.mentor_id, m.full_name, m.email, m.birth_date, m.phone_number "
                + "FROM Mentor m "
                + "INNER JOIN InternAssign ia ON m.mentor_id = ia.mentor_id "
                + "WHERE ia.intern_id = ? AND ia.is_selected = 1";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            if (rs.next()) {
                mentor = new Mentor();
                mentor.setMentorId(rs.getInt("mentor_id"));
                mentor.setFullname(rs.getString("full_name"));
                mentor.setEmail(rs.getString("email"));
                mentor.setBirthDate(rs.getDate("birth_date"));
                mentor.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving mentor: " + e.getMessage());
            
        }
        return mentor;
    }
    
    public LabRoom getLabRoomByInternId(int internId) {
        LabRoom labRoom = null;
        String sql = "SELECT lr.* "
                + "FROM LabRoom lr "
                + "INNER JOIN Mentor m ON lr.mentor_id = m.mentor_id "
                + "INNER JOIN InternAssign ia ON m.mentor_id = ia.mentor_id "
                + "WHERE ia.intern_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            if (rs.next()) {
                labRoom = new LabRoom();
                labRoom.setRoomId(rs.getInt("room_id"));
                labRoom.setRoomName(rs.getString("room_name"));
                labRoom.setAssigned(rs.getBoolean("is_assigned"));
                labRoom.setMentorId(rs.getInt("mentor_id"));
                labRoom.setMentorFullName(getMentorByInternId(labRoom.getMentorId()).getFullname());
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving lab room: " + e.getMessage());
        }
        return labRoom;
    }
    
    public void submitFeedback(Feedback feedback) {
        String xsql = "INSERT INTO Feedback (punctuality, coverage, response, teaching_skills, support, feedback_text, submission_date, mentor_id, intern_id, room_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xsql);
            ps.setString(1, feedback.getPunctuality());
            ps.setString(2, feedback.getCoverage());
            ps.setString(3, feedback.getResponse());
            ps.setString(4, feedback.getTeachingSkills());
            ps.setString(5, feedback.getSupport());
            ps.setString(6, feedback.getFeedbackText());
            ps.setTimestamp(7, new Timestamp(new Date().getTime())); // Set created_at here
            ps.setInt(8, feedback.getMentorId());
            ps.setInt(9, feedback.getInternId());
            ps.setInt(10, feedback.getRoomId());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public boolean hasSubmittedFeedback(int internId) {
        String sql = "SELECT COUNT(*) AS count FROM Feedback WHERE intern_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking feedback submission: " + e.getMessage());
        }
        return false;
    }

    public void editFeedback(Feedback feedback) {
        String xsql = "UPDATE Feedback SET punctuality = ?, coverage = ?, response = ?, "
                + "teaching_skills = ?, support = ?, feedback_text = ?, "
                + "submission_date = ?, mentor_id = ?, intern_id = ?, room_id = ? WHERE feedback_id = ?";
        try {
            ps = con.prepareStatement(xsql);
            ps.setString(1, feedback.getPunctuality());
            ps.setString(2, feedback.getCoverage());
            ps.setString(3, feedback.getResponse());
            ps.setString(4, feedback.getTeachingSkills());
            ps.setString(5, feedback.getSupport());
            ps.setString(6, feedback.getFeedbackText());
            ps.setTimestamp(7, new Timestamp(new Date().getTime())); // Set updated_at here
            ps.setInt(8, feedback.getMentorId());
            ps.setInt(9, feedback.getInternId());
            ps.setInt(10, feedback.getRoomId());
            ps.setInt(11, feedback.getFeedbackId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error editing feedback: " + e.getMessage());
        }
    }

    public Feedback getFeedbackById(int feedbackId) {
        Feedback feedback = null;
        String sql = "SELECT f.feedback_id, f.punctuality, f.coverage, f.response, f.teaching_skills, f.support, f.feedback_text, f.submission_date, f.mentor_id, f.intern_id, f.room_id, mt.full_name AS mentorFullName, i.full_name AS internFullName, l.room_name AS labroomFullName "
                + "FROM Feedback f "
                + "LEFT JOIN mentor mt ON f.mentor_id = mt.mentor_id "
                + "LEFT JOIN intern i ON f.intern_id = i.intern_id "
                + "LEFT JOIN LabRoom l ON f.room_id = l.room_id "
                + "WHERE f.feedback_id =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            rs = ps.executeQuery();
            if (rs.next()) {
                feedback = new Feedback();
                feedback.setFeedbackId(rs.getInt("feedback_id"));
                feedback.setPunctuality(rs.getString("punctuality"));
                feedback.setCoverage(rs.getString("coverage"));
                feedback.setResponse(rs.getString("response"));
                feedback.setTeachingSkills(rs.getString("teaching_skills"));
                feedback.setSupport(rs.getString("support"));
                feedback.setFeedbackText(rs.getString("feedback_text"));
                feedback.setSubmissionDate(rs.getTimestamp("submission_date"));
                feedback.setMentorId(rs.getInt("mentor_id"));
                feedback.setInternId(rs.getInt("intern_id"));
                feedback.setRoomId(rs.getInt("room_id"));
                feedback.setMentorFullName(rs.getString("mentorFullName"));
                feedback.setInternFullName(rs.getString("internFullName"));
                feedback.setLabroomName(rs.getString("labroomFullName"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving feedback: " + e.getMessage());
        }
        return feedback;
    }

    public Feedback getFeedbackByInternId(int internId) {
        Feedback feedback = null;
        String sql = "SELECT f.feedback_id, f.punctuality, f.coverage, f.response, f.teaching_skills, f.support, f.feedback_text, f.submission_date, f.mentor_id, f.intern_id, f.room_id, mt.full_name AS mentorFullName, i.full_name AS internFullName, l.room_name AS labroomFullName "
                + "FROM Feedback f "
                + "LEFT JOIN mentor mt ON f.mentor_id = mt.mentor_id "
                + "LEFT JOIN intern i ON f.intern_id = i.intern_id "
                + "LEFT JOIN LabRoom l ON f.room_id = l.room_id "
                + "WHERE f.intern_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int feedbackId = rs.getInt("feedback_id");
                String punctuality = rs.getString("punctuality");
                String coverage = rs.getString("coverage");
                String response = rs.getString("response");
                String teachingSkills = rs.getString("teaching_skills");
                String support = rs.getString("support");
                String feedbackText = rs.getString("feedback_text");
                Timestamp submissionDate = rs.getTimestamp("submission_date");
                int mentorId = rs.getInt("mentor_id");
                int roomId = rs.getInt("room_id");
                String mentorFullName = rs.getString("mentorFullName");
                String internFullName = rs.getString("internFullName");
                String labroomFullName = rs.getString("labroomFullName");
                
                feedback = new Feedback(feedbackId, punctuality, coverage, response, teachingSkills, support, feedbackText, submissionDate, mentorId, internId, roomId, mentorFullName, internFullName, labroomFullName);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving feedback by intern ID: " + e.getMessage());
        }
        return feedback;
    }
    
    public static void main(String[] args) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        System.out.println(feedbackDAO.getFeedbackByInternId(1));
        
    }
}

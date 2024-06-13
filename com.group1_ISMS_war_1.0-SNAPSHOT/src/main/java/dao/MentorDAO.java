package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mentor;

public class MentorDAO extends MyDAO {

    public void insertMentor(Mentor mentor) {
        String sql = "INSERT INTO Mentor (mentor_id, full_name, email, birth_date, phone_number) VALUES (?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, mentor.getMentorId());
            ps.setString(2, mentor.getFullname());
            ps.setString(3, mentor.getEmail());
            ps.setDate(4, new java.sql.Date(mentor.getBirthDate().getTime()));
            ps.setString(5, mentor.getPhoneNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting mentor: " + e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                System.err.println("Error closing PreparedStatement: " + ex.getMessage());
            }
        }
    }

    public List<Mentor> getAllMentors() {
        List<Mentor> list = new ArrayList<>();
        String sql = "SELECT mentor_id, full_name, email, birth_date, phone_number FROM Mentor";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mentor mentor = new Mentor();
                mentor.setMentorId(rs.getInt("mentor_id"));
                mentor.setFullname(rs.getString("full_name"));
                mentor.setEmail(rs.getString("email"));
                mentor.setBirthDate(rs.getDate("birth_date"));
                mentor.setPhoneNumber(rs.getString("phone_number"));
                list.add(mentor);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving mentors: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.getMessage());
            }
        }
        return list;
    }
}

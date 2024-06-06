package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mentor;

/**
 *
 * @author haidu
 */
public class MentorDAO extends MyDAO {

    public void addMentor(Mentor mentor) {
        String query = "INSERT INTO Mentor (mentor_id, full_name, email) VALUES(?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, mentor.getMentorId());
            statement.setString(2, mentor.getFullname());
            statement.setString(3, mentor.getEmail());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalMentorCount() {
        int count = 0;
        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM Mentor"); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Mentor> getMentorsToManage(int page, int pageSize) {
        List<Mentor> mentors = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Mentor\n"
                        + "LIMIT ? OFFSET ?")) {
            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mentor mentor = new Mentor();
                    mentor.setMentorId(rs.getInt("mentor_id"));
                    mentor.setFullname(rs.getString("full_name"));
                    mentor.setEmail(rs.getString("email"));
                    mentor.setBirthDate(rs.getDate("birth_date"));
                    mentor.setPhoneNumber(rs.getString("phone_number"));
                    mentors.add(mentor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mentors;
    }

    public int getLastMentorId() {
        xSql = "SELECT MAX(mentor_id) FROM Mentor";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

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
                if (ps != null) {
                    ps.close();
                }
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
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.getMessage());
            }
        }
        return list;
    }
}

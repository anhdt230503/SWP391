/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public List<Mentor> getMentorsToManage() {
        List<Mentor> mentors = new ArrayList<>();

        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Mentor\n")) {
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

    public Mentor getMentorById(int mentorId) {
        Mentor mentor = null;
        String query = "SELECT * FROM Mentor WHERE mentor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, mentorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int mentorid = rs.getInt("mentor_id");
                String fullname = rs.getString("full_name");
                String email = rs.getString("email");
                Date bitrhdate = rs.getDate("birth_date");
                String phonenumber = rs.getString("phone_number");

                mentor = new Mentor(mentorid, fullname, email, bitrhdate, phonenumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mentor;
    }

    public void updateProfileMentor(int mentorId, String fullName, Date birthDate, String phoneNumber) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE Mentor SET full_name = ?, birth_date = ?, phone_number = ? WHERE mentor_id = ?")) {
            stmt.setString(1, fullName);
            stmt.setDate(2, birthDate);
            stmt.setString(3, phoneNumber);
            stmt.setInt(4, mentorId);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMentor(int mentorId) {
        try {
            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Account WHERE mentor_id = ?\n")) {
                stmt.setInt(1, mentorId);
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Mentor WHERE mentor_id = ?\n")) {
                stmt.setInt(1, mentorId);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProfileMentorByHR(int mentorId, String fullName,String email, Date birthDate, String phoneNumber) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE Mentor SET full_name = ?,email = ?, birth_date = ?, phone_number = ? WHERE mentor_id = ?")) {
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setDate(3, birthDate);
            stmt.setString(4, phoneNumber);
            stmt.setInt(5, mentorId);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

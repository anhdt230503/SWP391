/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        public void addMentor (Mentor mentor){
        String query = "INSERT INTO Mentor (mentor_id, full_name, email) VALUES(?,?,?)";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, mentor.getMentorId());
            statement.setString(2, mentor.getFullname());
            statement.setString(3, mentor.getEmail());
            
            statement.executeUpdate();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }        
    
    public List<Mentor> getMentorsToManage() throws SQLException{
        List<Mentor> mentors = new ArrayList<>();
        
        
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Mentor\n");
            try(ResultSet rs = stmt.executeQuery()){
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
         catch (Exception e) {
            e.printStackTrace();
        }
        return mentors;
    }
    
    public int getLastMentorId () {
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
}

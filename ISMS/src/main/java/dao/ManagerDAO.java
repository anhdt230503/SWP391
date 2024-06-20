/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Manager;

/**
 *
 * @author haidu
 */
public class ManagerDAO extends MyDAO {

         public void addManager (Manager manager){
        String query = "INSERT INTO Manager (manager_id, full_name, email) VALUES(?,?,?)";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, manager.getManagerId());
            statement.setString(2, manager.getFullName());
            statement.setString(3, manager.getEmail());
            
            statement.executeUpdate();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }

    public List<Manager> getManagersToManage() {
        List<Manager> managers = new ArrayList<>();

        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Manager\n")) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Manager manager = new Manager();
                    manager.setManagerId(rs.getInt("manager_id"));
                    manager.setFullName(rs.getString("full_name"));
                    manager.setEmail(rs.getString("email"));
                    manager.setBirthDate(rs.getDate("birth_date"));
                    manager.setPhoneNumber(rs.getString("phone_number"));
                    managers.add(manager);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return managers;
    }

    public int getLastManagerId() {
        xSql = "SELECT MAX(manager_id) FROM Manager";
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

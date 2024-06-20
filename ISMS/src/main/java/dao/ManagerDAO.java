/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
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

    public void addManager(Manager manager) {
        String query = "INSERT INTO Manager (manager_id, full_name, email) VALUES(?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, manager.getManagerId());
            statement.setString(2, manager.getFullname());
            statement.setString(3, manager.getEmail());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public int getTotalManagerCount() {
//        int count = 0;
//        try (
//                PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM Manager"); ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                count = rs.getInt("count");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
    public List<Manager> getManagersToManage() {
        List<Manager> managers = new ArrayList<>();

        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Manager\n")) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Manager manager = new Manager();
                    manager.setManagerId(rs.getInt("manager_id"));
                    manager.setFullname(rs.getString("full_name"));
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

    public Manager getManagerById(int managerId) {
        Manager manager = null;
        String query = "SELECT * FROM Manager WHERE manager_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, managerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int managerid = rs.getInt("manager_id");
                String fullname = rs.getString("full_name");
                String email = rs.getString("email");
                Date bitrhdate = rs.getDate("birth_date");
                String phonenumber = rs.getString("phone_number");

                manager = new Manager(managerid, fullname, email, bitrhdate, phonenumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manager;
    }

    public void updateProfileManager(int managerId, String fullName, Date birthDate, String phoneNumber) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE Manager SET full_name = ?, birth_date = ?, phone_number = ? WHERE manager_id = ?")) {
            stmt.setString(1, fullName);
            stmt.setDate(2, birthDate);
            stmt.setString(3, phoneNumber);
            stmt.setInt(4, managerId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteManager(int managerId) {
        try {
            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Account WHERE manager_id = ?\n")) {
                stmt.setInt(1, managerId);
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Manager WHERE manager_id = ?\n")) {
                stmt.setInt(1, managerId);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProfileManagerByHR(int managerId, String fullName, String email, Date birthDate, String phoneNumber) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE Manager SET full_name = ?,email = ?, birth_date = ?, phone_number = ? WHERE manager_id = ?")) {
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setDate(3, birthDate);
            stmt.setString(4, phoneNumber);
            stmt.setInt(5, managerId);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        ManagerDAO managerDAO = new ManagerDAO();
          int managerId = 2; // Thay đổi ID cho phù hợp
            String fullName = "John Doe";
            String email = "john.doe@example.com";
            Date birthDate = Date.valueOf("1980-01-01");
            String phoneNumber = "123456789";
        managerDAO.updateProfileManagerByHR(managerId, fullName, email, birthDate, phoneNumber);
    }
}

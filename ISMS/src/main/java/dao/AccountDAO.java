/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author haidu
 */
public class AccountDAO extends MyDAO {

    public Account login(String email) {
        String xSql = "select * from Account\n"
                + "where email = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void insertInternAccount(Account account) {
        xSql = "INSERT INTO Account (email, intern_id, role_id)\n"
                + "VALUES (?, ?, 4)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, account.getEmail());
            ps.setInt(2, account.getInternId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertMentorAccount(Account account) {
        xSql = "INSERT INTO Account (email, mentor_id, role_id)\n"
                + "VALUES (?, ?, 3)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, account.getEmail());
            ps.setInt(2, account.getMentorId());

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Account getAccountByEmail(String email) {
        xSql = "SELECT * FROM Account where email =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        
        Account account = new Account();

        AccountDAO accDAO = new AccountDAO();

        account.setEmail("anhdthe176337@fpt.edu.vn");
        account.setInternId(1);
        accDAO.insertInternAccount(account);
    }

    public void insertManagerAccount(Account account) {
        xSql = "INSERT INTO Account (email, manager_id, role_id)\n"
                + "VALUES (?, ?, 2)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, account.getEmail());
            ps.setInt(2, account.getManagerId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean isEmailExists(String email) {
        boolean exists = false;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT COUNT(*) FROM Account WHERE email=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    public void updateAccountMentorStatus(int mentorId) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE Account SET status = (CASE WHEN status = 1 THEN 0 ELSE 1 END) WHERE mentor_id = ?")) {
            stmt.setInt(1, mentorId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByID(int acccountId) {
        Account account = null;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Account WHERE account_id = ?")) {
            stmt.setInt(1, acccountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("email");
                    int status = rs.getInt("status");
                    int managerId = rs.getInt("manager_id");
                    int mentorId = rs.getInt("mentor_id");
                    int internId = rs.getInt("intern_id");
                    int role = rs.getInt("role_id");

                    account = new Account(acccountId, email, status, managerId, mentorId, internId, role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public Account getAccountMentor(int mentorId) {
        Account account = null;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Account WHERE mentor_id = ?")) {
            stmt.setInt(1, mentorId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("email");
                    int status = rs.getInt("status");
                    int managerId = rs.getInt("manager_id");
                    int mentorid = rs.getInt("mentor_id");
                    int internId = rs.getInt("intern_id");
                    int role = rs.getInt("role_id");
                    
                    account = new Account(internId, email, status, managerId, mentorid, internId, role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public void updateEmailAccountMentor(String email, int mentorId){
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE Account SET email = ? WHERE mentor_id = ?")){
            stmt.setString(1, email);
            stmt.setInt(2, mentorId);
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void updateEmailAccountManager(String email, int managerId){
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE Account SET email = ? WHERE manager_id = ?")){
            stmt.setString(1, email);
            stmt.setInt(2, managerId);
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public static void main(String[] args) {
//        AccountDAO accountDAO = new AccountDAO();
//        accountDAO.updateAccountMentorStatus(3);
//    }
}

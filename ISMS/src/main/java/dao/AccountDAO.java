/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
        
        AccountDAO accDAO = new AccountDAO();
        
        Account acc = accDAO.getAccountByEmail("daoa230503@gmail.com");
        System.out.println(acc);
    }
}

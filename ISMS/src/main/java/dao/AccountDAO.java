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
        String xSql = "select * from Account\n" +
                "where email = ?";
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
                        rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
//    public static void main(String[] args) {
//        
//        AccountDAO accDAO = new AccountDAO();
//        
//        Account acc = accDAO.login("daoa9596@gmail.com");
//        System.out.println(acc);
//    }
    
}

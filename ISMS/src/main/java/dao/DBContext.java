/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author haidu
 */
public class DBContext {

    protected Connection connection;
    public DBContext() {
        try {
            String url = "jdbc:mysql://localhost:3306/FurnitureDB";
            String username = "root"; // Thay bằng username MySQL của bạn
            String password = "password"; // Thay bằng password MySQL của bạn
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

}

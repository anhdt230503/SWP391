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

    public Connection connection;
    public DBContext() {
        try {
            String url = "jdbc:mysql://localhost:3306/InternMSDB";
            String username = "root"; 
            String password = "password"; 
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            connection.createStatement().execute("SET NAMES utf8mb4");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

}

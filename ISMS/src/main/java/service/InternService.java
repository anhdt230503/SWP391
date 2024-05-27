/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.InternDAO;
import java.sql.SQLException;
import model.Intern;

/**
 *
 * @author haidu
 */
public class InternService {
    
    private InternDAO internDao = new InternDAO();
    
    public void importIntern(Intern intern) throws SQLException {
        internDao.insertIntern(intern);
    }
    
    public static void main(String[] args) {
        
    }
}

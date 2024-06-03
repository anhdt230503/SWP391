/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.InternDAO;
import java.sql.SQLException;
import java.util.Random;
import model.Intern;

/**
 *
 * @author haidu
 */
public class InternService {
    
    private InternDAO internDao = new InternDAO();
    private int internIdKey = 0;
    
    
    public synchronized int generateInternIdKey() {
        internIdKey++;
        return internIdKey;
    }
    
    public void importIntern(Intern intern) throws SQLException {
        internDao.insertIntern(intern);
    }
    
    public Intern getInternByStudentId(String studentId) throws SQLException {
        return internDao.getInternByStudentId(studentId);
    }
    
    public void updateIntern(Intern intern) throws SQLException {
        internDao.updateIntern(intern);
    }
    
    public String genarateStaffId() {
        String PREFIX = "LAB";
        int ID_LENGTH = 4;
        
        Random random = new Random();
        StringBuilder staffId = new StringBuilder(PREFIX);
        
        for (int i = 0; i < ID_LENGTH; i++) {
            int digit = random.nextInt(10);
            staffId.append(digit);
        }
        
        return staffId.toString();
        
    }
    
//    public static void main(String[] args) {
//        InternService internService = new InternService();
//        String code = internService.genarateStaffId();
//        System.out.println(code);
//    }
}

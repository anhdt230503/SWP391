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
    
    public synchronized int generateInternIdKey() {
        return internDao.getLastInternId();
    }
    
    public void importIntern(Intern intern) throws SQLException {
        internDao.insertIntern(intern);
    }
    
<<<<<<< HEAD
=======
    public Intern getInternByStudentId(String studentId) throws SQLException {
        return internDao.getInternByStudentId(studentId);
    }
    
    public void updateIntern(Intern intern) throws SQLException {
        internDao.updateIntern(intern);
    }
    
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
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
    
<<<<<<< HEAD
//    public static void main(String[] args) {
//        InternService internService = new InternService();
//        String code = internService.genarateStaffId();
//        System.out.println(code);
//    }
=======
    public static void main(String[] args) {
//        InternService internService = new InternService();
//        String code = internService.genarateStaffId();
//        System.out.println(code);
    }
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
}

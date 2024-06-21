/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.MentorDAO;



/**
 *
 * @author haidu
 */
public class MentorService {
    
    private MentorDAO mentorDao = new MentorDAO();

    public synchronized int generateMentorIdKey() {
        return mentorDao.getLastMentorId();
    }
}

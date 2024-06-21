/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ManagerDAO;

/**
 *
 * @author haidu
 */
public class ManagerService {

    private ManagerDAO managerDao = new ManagerDAO();

    public synchronized int generateManagerIdKey() {
        return managerDao.getLastManagerId();
    }
}

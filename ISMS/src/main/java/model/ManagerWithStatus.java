/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Manager;
/**
 *
 * @author ACER
 */
public class ManagerWithStatus {
    
    private Manager manager;
    private int status;

    public ManagerWithStatus() {
    }

    public ManagerWithStatus(Manager manager, int status) {
        this.manager = manager;
        this.status = status;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}

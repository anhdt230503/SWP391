/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;

/**
 *
 * @author haidu
 */
public class TaskDAO extends MyDAO {
    
    public void addTask (Task task) {
        xSql = "INSERT INTO Task (schedule_id, task_name, hours, note)\n"
                + "VALUES(?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, task.getScheduleId());
            ps.setString(2, task.getTaskName());
            ps.setInt(3, task.getHours());
            ps.setString(4, task.getNote());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public List<Task> getAllTaskByScheduleId (int scheduleId) {
        List<Task> list = new ArrayList<>();
        xSql = "SELECT * FROM Task\n"
                + "WHERE schedule_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1,scheduleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Task(rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5),
                rs.getBoolean(6)));
            }
        } catch (Exception ex) {
        }
        return list;
    }
    
    public void updateSelectedTask(Task task) {
        xSql = "UPDATE Task\n "
                + "SET isDone = 1\n"
                + "WHERE task_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, task.getTaskId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi");
        }
    }
    
    public void updateUnselectedTask(Task task) {
        xSql = "UPDATE Task\n "
                + "SET isDone = 0\n"
                + "WHERE task_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, task.getTaskId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi");
        }
    }
    
}

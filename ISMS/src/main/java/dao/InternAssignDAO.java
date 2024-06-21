/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.InternAssign;

/**
 *
 * @author haidu
 */
public class InternAssignDAO extends MyDAO {

    public void insertInternAssign(InternAssign internAssign) {
        xSql = "INSERT INTO InternAssign (intern_id, mentor_id, is_selected)\n"
                + "VALUES (?, ?, 1)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internAssign.getInternId());
            ps.setInt(2, internAssign.getMentorId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<InternAssign> getAllInternAssign() {
        List<InternAssign> list = new ArrayList<>();
        xSql = "SELECT * FROM InternAssign WHERE is_selected = 1";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new InternAssign(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getBoolean(4)));
            }

        } catch (Exception e) {
        }
        return list;
    }
    
    public int getInternCountByMentor(int mentorId) {
        int count = 0;
        xSql = "SELECT COUNT(*) FROM InternAssign WHERE mentor_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, mentorId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        InternAssignDAO internAssignDAO = new InternAssignDAO();
        InternAssign internAssign = new InternAssign();

//        internAssign.setInternId(1);
//        internAssign.setMentorId(1);
//        internAssignDAO.insertInternAssign(internAssign);
        System.out.println(internAssignDAO.getInternCountByMentor(1));
        
    }
}

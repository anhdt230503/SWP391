/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
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

    public static void main(String[] args) {
        InternAssignDAO internAssignDAO = new InternAssignDAO();
        InternAssign internAssign = new InternAssign();

//        internAssign.setInternId(1);
//        internAssign.setMentorId(1);
//        internAssignDAO.insertInternAssign(internAssign);
        System.out.println(internAssignDAO.getAllInternAssign());
        
    }
}

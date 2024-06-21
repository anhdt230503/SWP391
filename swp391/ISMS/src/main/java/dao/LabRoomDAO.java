package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LabRoom;

public class LabRoomDAO extends MyDAO {
    public void insertLabRoom(LabRoom labRoom) {
        String sql = "INSERT INTO LabRoom (room_name, is_assigned, mentor_id) VALUES (?, ?, ?);";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, labRoom.getRoomName());
            ps.setBoolean(2, labRoom.isAssigned());
            ps.setInt(3, labRoom.getMentorId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                System.err.println("Error closing PreparedStatement: " + ex.getMessage());
            }
        }
    }

      public List<LabRoom> getAllLabRooms() {
        List<LabRoom> list = new ArrayList<>();
        String sql = "SELECT lr.room_id, lr.room_name, lr.is_assigned, lr.mentor_id, m.full_name " +
                     "FROM LabRoom lr LEFT JOIN Mentor m ON lr.mentor_id = m.mentor_id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                LabRoom labRoom = new LabRoom();
                labRoom.setRoomId(rs.getInt("room_id"));
                labRoom.setRoomName(rs.getString("room_name"));
                labRoom.setAssigned(rs.getBoolean("is_assigned"));
                labRoom.setMentorId(rs.getInt("mentor_id"));
                labRoom.setMentorFullName(rs.getString("full_name"));
                list.add(labRoom);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving lab rooms: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.getMessage());
            }
        }
        return list;
    }
}

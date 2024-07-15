package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Intern;
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
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing PreparedStatement: " + ex.getMessage());
            }
        }
    }

    public List<LabRoom> getAllLabRooms() {
        List<LabRoom> list = new ArrayList<>();
        String sql = "SELECT lr.room_id, lr.room_name, lr.is_assigned, lr.mentor_id, m.full_name "
                + "FROM LabRoom lr LEFT JOIN Mentor m ON lr.mentor_id = m.mentor_id";
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
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.getMessage());
            }
        }
        return list;
    }

    public LabRoom getLabRoomById(int roomId) {
        LabRoom labRoom = null;
        String sql = "SELECT lr.room_id, lr.room_name, lr.is_assigned, lr.mentor_id, m.full_name "
                + "FROM LabRoom lr LEFT JOIN Mentor m ON lr.mentor_id = m.mentor_id WHERE lr.room_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, roomId);
            rs = ps.executeQuery();
            if (rs.next()) {
                labRoom = new LabRoom();
                labRoom.setRoomId(rs.getInt("room_id"));
                labRoom.setRoomName(rs.getString("room_name"));
                labRoom.setAssigned(rs.getBoolean("is_assigned"));
                labRoom.setMentorId(rs.getInt("mentor_id"));
                labRoom.setMentorFullName(rs.getString("full_name"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving lab room: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.getMessage());
            }
        }
        return labRoom;
    }

    public void updateLabRoom(LabRoom labRoom) {
        String sql = "UPDATE LabRoom SET room_name = ?, is_assigned = ?, mentor_id = ? WHERE room_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, labRoom.getRoomName());
            ps.setBoolean(2, labRoom.isAssigned());
            ps.setInt(3, labRoom.getMentorId());
            ps.setInt(4, labRoom.getRoomId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating data: " + e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing PreparedStatement: " + ex.getMessage());
            }
        }
    }

    public void deleteLabRoom(int roomId) {
        String sql = "DELETE FROM LabRoom WHERE room_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, roomId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting data: " + e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing PreparedStatement: " + ex.getMessage());
            }
        }
    }

    public List<Intern> getInternsByMentorId(int mentorId) {
        List<Intern> internList = new ArrayList<>();
        String sql = "SELECT i.intern_id, i.student_id, i.email, i.full_name, i.phone_number, i.major, "
                + "i.company, i.job_title, i.link_cv, i.staff_id, i.status, i.semester_id "
                + "FROM Intern i "
                + "INNER JOIN InternAssign ia ON i.intern_id = ia.intern_id "
                + "WHERE ia.mentor_id = ? AND ia.is_selected = 1";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, mentorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Intern intern = new Intern();
                intern.setInternId(rs.getInt("intern_id"));
                intern.setStudentId(rs.getString("student_id"));
                intern.setEmail(rs.getString("email"));
                intern.setFullName(rs.getString("full_name"));
                intern.setPhoneNumber(rs.getString("phone_number"));
                intern.setStaffId(rs.getString("staff_id"));
                internList.add(intern);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving interns: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.getMessage());
            }
        }
        return internList;
    }

    public boolean isDuplicateRoomName(String roomName) {
        String sql = "SELECT COUNT(*) FROM LabRoom WHERE room_name = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, roomName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking duplicate room name: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.getMessage());
            }
        }
        return false;
    }

    public boolean isDuplicateRoomNameUpdate(String roomName, int roomId) {
        String sql = "SELECT COUNT(*) FROM LabRoom WHERE room_name = ? AND room_id != ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, roomName);
            ps.setInt(2, roomId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking duplicate room name: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.getMessage());
            }
        }
        return false;
    }

}

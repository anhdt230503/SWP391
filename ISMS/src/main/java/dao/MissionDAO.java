package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Intern;
import model.Mission;
import model.Mission.MissionStatus;

public class MissionDAO extends MyDAO {

    public List<Mission> getAllMissions() {
        List<Mission> missions = new ArrayList<>();
        String xSql = "SELECT m.mis_id, m.mis_name, m.mis_status, m.mis_description, m.link, m.start_date, m.deadline, m.mentor_id, m.intern_id, mt.full_name AS mentorFullName, i.full_name AS internFullName, m.file_path, m.created_at, m.updated_at, m.submitted_at "
                + "FROM mission m "
                + "LEFT JOIN mentor mt ON m.mentor_id = mt.mentor_id "
                + "LEFT JOIN intern i ON m.intern_id = i.intern_id";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int misId = rs.getInt("mis_id");
                String misName = rs.getString("mis_name");
                Mission.MissionStatus misStatus = Mission.MissionStatus.valueOf(rs.getString("mis_status"));
                String misDescription = rs.getString("mis_description");
                String link = rs.getString("link");
                Timestamp startDate = rs.getTimestamp("start_date");
                Timestamp deadline = rs.getTimestamp("deadline");
                int mentorId = rs.getInt("mentor_id");
                int internId = rs.getInt("intern_id");
                String mentorFullName = rs.getString("mentorFullName");
                String internFullName = rs.getString("internFullName");
                String filePath = rs.getString("file_path");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                Timestamp submittedAt = rs.getTimestamp("submitted_at");

                Mission mission = new Mission(misId, misName, misStatus, misDescription, link, startDate, deadline, mentorId, internId, mentorFullName, internFullName, filePath, createdAt, updatedAt, submittedAt);
                missions.add(mission);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving mission list: " + e.getMessage());
        }

        return missions;
    }

    public List<Mission> getMissionsByStatus(Mission.MissionStatus status) {
        List<Mission> missions = new ArrayList<>();
        String xSql = "SELECT m.mis_id, m.mis_name, m.mis_status, m.mis_description, m.link, m.start_date, m.deadline, m.mentor_id, m.intern_id, m.file_path, m.created_at, m.updated_at, m.submitted_at, mt.full_name AS mentorFullName, i.full_name AS internFullName "
                + "FROM mission m "
                + "LEFT JOIN mentor mt ON m.mentor_id = mt.mentor_id "
                + "LEFT JOIN intern i ON m.intern_id = i.intern_id "
                + "WHERE m.mis_status = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, status.name());
            rs = ps.executeQuery();

            while (rs.next()) {
                int misId = rs.getInt("mis_id");
                String misName = rs.getString("mis_name");
                Mission.MissionStatus misStatus = Mission.MissionStatus.valueOf(rs.getString("mis_status"));
                String misDescription = rs.getString("mis_description");
                String link = rs.getString("link");
                Timestamp startDate = rs.getTimestamp("start_date");
                Timestamp deadline = rs.getTimestamp("deadline");
                int mentorId = rs.getInt("mentor_id");
                int internId = rs.getInt("intern_id");
                String mentorFullName = rs.getString("mentorFullName");
                String internFullName = rs.getString("internFullName");
                String filePath = rs.getString("file_path");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                Timestamp submittedAt = rs.getTimestamp("submitted_at");

                Mission mission = new Mission(misId, misName, misStatus, misDescription, link, startDate, deadline, mentorId, internId, mentorFullName, internFullName, filePath, createdAt, updatedAt, submittedAt);
                missions.add(mission);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving mission list by status: " + e.getMessage());
        }

        return missions;
    }

    public List<Mission> getMissionsByInternFullName(String internFullName) {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT m.mis_id, m.mis_name, m.mis_status, m.mis_description, m.link, m.start_date, m.deadline, "
                + "m.mentor_id, m.intern_id, m.file_path, m.created_at, m.updated_at, m.submitted_at, "
                + "mt.full_name AS mentorFullName, i.full_name AS internFullName "
                + "FROM mission m "
                + "LEFT JOIN mentor mt ON m.mentor_id = mt.mentor_id "
                + "LEFT JOIN intern i ON m.intern_id = i.intern_id "
                + "WHERE i.full_name LIKE ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + internFullName + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                int misId = rs.getInt("mis_id");
                String misName = rs.getString("mis_name");
                Mission.MissionStatus misStatus = Mission.MissionStatus.valueOf(rs.getString("mis_status"));
                String misDescription = rs.getString("mis_description");
                String link = rs.getString("link");
                Timestamp startDate = rs.getTimestamp("start_date");
                Timestamp deadline = rs.getTimestamp("deadline");
                int mentorId = rs.getInt("mentor_id");
                int internId = rs.getInt("intern_id");
                String mentorFullName = rs.getString("mentorFullName");
                String internFullNameResult = rs.getString("internFullName");
                String filePath = rs.getString("file_path");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                Timestamp submittedAt = rs.getTimestamp("submitted_at");

                Mission mission = new Mission(misId, misName, misStatus, misDescription, link, startDate, deadline,
                        mentorId, internId, mentorFullName, internFullNameResult, filePath, createdAt, updatedAt, submittedAt);
                missions.add(mission);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving missions by intern full name: " + e.getMessage());
        } finally {
            // Close resources (ps, rs, etc.)
        }

        return missions;
    }

    public void addMission(Mission mission) {
        String xSql = "INSERT INTO Mission (mis_name, mis_status, mis_description, link, start_date, deadline, mentor_id, intern_id, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, mission.getMisName());
            ps.setString(2, mission.getMisStatus().name());
            ps.setString(3, mission.getMisDescription());
            ps.setString(4, mission.getLink());
            ps.setTimestamp(5, mission.getStartDate());
            ps.setTimestamp(6, mission.getDeadline());
            ps.setInt(7, mission.getMentorId());
            ps.setInt(8, mission.getInternId());
            ps.setTimestamp(9, new Timestamp(new Date().getTime())); // Set created_at here
            ps.executeUpdate();
        } catch (SQLException e) {
            // Handle exception
        }
    }

    public void updateMissionStatus(int missionId, MissionStatus status) {
        String sql = "UPDATE Mission SET mis_status = ? WHERE mis_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, status.name());
            ps.setInt(2, missionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái của nhiệm vụ: " + e.getMessage());
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateMission(Mission mission) {
        String sql = "UPDATE Mission SET mis_name = ?, mis_status = ?, mis_description = ?, link = ?, start_date = ?, deadline = ?, mentor_id = ?, intern_id = ?, updated_at = ? WHERE mis_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, mission.getMisName());
            ps.setString(2, mission.getMisStatus().name());
            ps.setString(3, mission.getMisDescription());
            ps.setString(4, mission.getLink());
            ps.setTimestamp(5, mission.getStartDate());
            ps.setTimestamp(6, mission.getDeadline());
            ps.setInt(7, mission.getMentorId());
            ps.setInt(8, mission.getInternId());
            ps.setTimestamp(9, new Timestamp(new Date().getTime())); // Set updated_at here
            ps.setInt(10, mission.getMisId());
            ps.executeUpdate();
        } catch (SQLException e) {
            // Handle exception
        }
    }

    public void deleteMission(int missionId) {
        String sql = "DELETE FROM Mission WHERE mis_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, missionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa nhiệm vụ: " + e.getMessage());
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Mission getMissionById(int missionId) {
        Mission mission = null;
        String xSql = "SELECT m.mis_id, m.mis_name, m.mis_status, m.mis_description, m.link, m.start_date, m.deadline, "
                + "m.mentor_id, m.intern_id, m.file_path, m.created_at, m.updated_at, m.submitted_at, "
                + "mt.full_name AS mentorFullName, i.full_name AS internFullName "
                + "FROM mission m "
                + "LEFT JOIN mentor mt ON m.mentor_id = mt.mentor_id "
                + "LEFT JOIN intern i ON m.intern_id = i.intern_id "
                + "WHERE m.mis_id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, missionId); // Set the missionId parameter in the query
            rs = ps.executeQuery();
            if (rs.next()) {
                int misId = rs.getInt("mis_id");
                String misName = rs.getString("mis_name");
                Mission.MissionStatus misStatus = Mission.MissionStatus.valueOf(rs.getString("mis_status"));
                String misDescription = rs.getString("mis_description");
                String link = rs.getString("link");
                Timestamp startDate = rs.getTimestamp("start_date");
                Timestamp deadline = rs.getTimestamp("deadline");
                int mentorId = rs.getInt("mentor_id");
                int internId = rs.getInt("intern_id");
                String mentorFullName = rs.getString("mentorFullName");
                String internFullName = rs.getString("internFullName");
                String filePath = rs.getString("file_path");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                Timestamp submittedAt = rs.getTimestamp("submitted_at");

                mission = new Mission(misId, misName, misStatus, misDescription, link, startDate, deadline, mentorId, internId,
                        mentorFullName, internFullName, filePath, createdAt, updatedAt, submittedAt);
            } else {
                System.out.println("Không tìm thấy nhiệm vụ với id: " + missionId);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy thông tin nhiệm vụ: " + e.getMessage());
        } finally {
            // Close resources (ps, rs, etc.)
        }

        return mission;
    }

    public void updateMissionStatusContinuously() {
        String sql = "SELECT mis_id, start_date, deadline, file_path FROM Mission";
        try (Connection con = connection; PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int misId = rs.getInt("mis_id");
                Timestamp startDate = rs.getTimestamp("start_date");
                Timestamp deadline = rs.getTimestamp("deadline");
                String filePath = rs.getString("file_path");
                MissionStatus newStatus = calculateMissionStatus(startDate, deadline, filePath);
                updateMissionStatus(misId, newStatus);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái của các nhiệm vụ: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private MissionStatus calculateMissionStatus(Timestamp startDate, Timestamp deadline, String filePath) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (now.before(startDate)) {
            return MissionStatus.NOT_START;
        } else if (now.after(deadline)) {
            if (filePath != null && !filePath.isEmpty()) {
                return MissionStatus.FINISHED;
            } else {
                return MissionStatus.MISSING;
            }
        } else {
            return MissionStatus.ON_GOING;
        }
    }

    public static void main(String[] args) {
        MissionDAO cc = new MissionDAO();
        System.out.println(cc.getAllMissions());
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

    public void upMissionFilePath(int misId, String file_path) {
        String sql = "UPDATE Mission SET file_path = ?, submitted_at = ? WHERE mis_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, file_path);
            ps.setTimestamp(2, new Timestamp(new Date().getTime()));
            ps.setInt(3, misId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật file_path: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<Mission> getMissionByInternId(int internId) {
        List<Mission> list = new ArrayList<>();
        xSql = "SELECT mis_id, mis_name FROM Mission \n"
                + "WHERE intern_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mission mission1 = new Mission();
                mission1.setMisId(rs.getInt(1));
                mission1.setMisName(rs.getString(2));
                list.add(mission1);
            }
        } catch (Exception ex) {
        }
        return list;
    }

}

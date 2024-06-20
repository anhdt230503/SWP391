package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Intern;
import model.Mission;
import model.Mission.MissionStatus;

public class MissionDAO extends MyDAO {

    public List<Mission> getAllMissions() {
        List<Mission> missions = new ArrayList<>();
        String xSql = "select m.mis_id,m.mis_name,m.mis_status,m.mis_description,m.link,m.start_date,m.deadline,m.mentor_id,m.intern_id,mt.full_name as mentorFullName,i.full_name as internFullName from mission m left join mentor mt on m.mentor_id = mt.mentor_id\n" +
"                left join intern i on m.intern_id = i.intern_id";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int misId = rs.getInt("mis_id");
                String misName = rs.getString("mis_name");
                MissionStatus misStatus = MissionStatus.valueOf(rs.getString("mis_status"));
                String misDescription = rs.getString("mis_description");
                String link = rs.getString("link");
                Timestamp startDate = rs.getTimestamp("start_date");
                Timestamp deadline = rs.getTimestamp("deadline");
                int mentorId = rs.getInt("mentor_id");
                int internId = rs.getInt("intern_id");
                String mentorFullName = rs.getString("mentorFullName"); // Corrected column name
                String internFullName = rs.getString("internFullName");

                // Create Mission object and add to missions list
                Mission mission = new Mission(misId, misName, misStatus, misDescription, link, startDate, deadline, mentorId, internId, mentorFullName,internFullName);
                missions.add(mission);

            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách nhiệm vụ: " + e.getMessage());
        }

        return missions;
    }

    public void addMission(Mission mission) {
        String xSql = "INSERT INTO Mission (mis_name, mis_status, mis_description, link, start_date, deadline,mentor_id,intern_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm nhiệm vụ: " + e.getMessage());
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
        String sql = "UPDATE Mission SET mis_name = ?, mis_status = ?, mis_description = ?, link = ?, start_date = ?, deadline = ?,mentor_id = ?,intern_id = ? WHERE mis_id = ?";
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
            ps.setInt(9, mission.getMisId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật nhiệm vụ: " + e.getMessage());
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
        String sql = "SELECT * FROM Mission WHERE mis_id = ?";
        Mission mission = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, missionId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int misId = rs.getInt("mis_id");
                String misName = rs.getString("mis_name");
                MissionStatus misStatus = MissionStatus.valueOf(rs.getString("mis_status"));
                String misDescription = rs.getString("mis_description");
                String link = rs.getString("link");
                Timestamp startDate = rs.getTimestamp("start_date");
                Timestamp deadline = rs.getTimestamp("deadline");
                int mentorId = rs.getInt("mentor_id");
                int internId = rs.getInt("intern_id");
                String full_name = rs.getString("fullname"); // Lấy thông tin mentorName
                String fullName = rs.getString("fullName");
                mission = new Mission(misId, misName, misStatus, misDescription, link, startDate, deadline, mentorId, internId, full_name,fullName);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy thông tin nhiệm vụ: " + e.getMessage());
            e.printStackTrace();
        }

        return mission;
    }

    public void updateMissionStatusContinuously() {
        String sql = "SELECT mis_id, start_date, deadline FROM Mission";
        try (Connection con = connection; PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int misId = rs.getInt("mis_id");
                Timestamp startDate = rs.getTimestamp("start_date");
                Timestamp deadline = rs.getTimestamp("deadline");
                MissionStatus newStatus = calculateMissionStatus(startDate, deadline);

                // Cập nhật trạng thái nếu khác với trạng thái hiện tại trong CSDL
                updateMissionStatus(misId, newStatus);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái của các nhiệm vụ: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private MissionStatus calculateMissionStatus(Timestamp startDate, Timestamp deadline) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (now.before(startDate)) {
            return MissionStatus.NOT_START;
        } else if (now.after(deadline)) {
            return MissionStatus.FINISHED;
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
        String sql = "SELECT i.intern_id, i.student_id, i.email, i.full_name, i.phone_number, i.major, " +
                     "i.company, i.job_title, i.link_cv, i.staff_id, i.status, i.semester_id " +
                     "FROM Intern i " +
                     "INNER JOIN InternAssign ia ON i.intern_id = ia.intern_id " +
                     "WHERE ia.mentor_id = ? AND ia.is_selected = 1";
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
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.getMessage());
            }
        }
        return internList;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Intern;
import model.Mentor;
import model.QandA;

/**
 *
 * @author admin
 */
public class QandADAO extends MyDAO {

    public List<QandA> getQandA() {
        List<QandA> qanas = new ArrayList<>();
        String xSQL = "SELECT q.qanda_id, q.question_title, q.question_status, q.question_text, q.mentor_id, q.intern_id, q.answer_text, q.created_at, q.updated_at, q.submitted_at, "
                + "m.full_name AS mentorFullName, i.full_name AS internFullName "
                + "FROM qanda q "
                + "LEFT JOIN mentor m ON q.mentor_id = m.mentor_id "
                + "LEFT JOIN intern i ON q.intern_id = i.intern_id";
        try {
            ps = con.prepareStatement(xSQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int qanda_id = rs.getInt("qanda_id");
                String question_title = rs.getString("question_title");
                QandA.QandAStatus question_status = QandA.QandAStatus.valueOf(rs.getString("question_status"));
                String question_text = rs.getString("question_text");
                int mentor_id = rs.getInt("mentor_id");
                int intern_id = rs.getInt("intern_id");
                String answer_text = rs.getString("answer_text");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                Timestamp submittedAt = rs.getTimestamp("submitted_at");
                String mentorFullName = rs.getString("mentorFullName");
                String internFullName = rs.getString("internFullName");
                QandA qana = new QandA(qanda_id, question_title, question_status, question_text, mentor_id, intern_id, answer_text, createdAt, updatedAt, submittedAt, mentorFullName, internFullName);
                qanas.add(qana);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving qanda list: " + e.getMessage());
        }
        return qanas;
    }

    public void addQandA(QandA qanda) {
        String xSql = "INSERT INTO QandA (question_title, question_status, question_text, mentor_id, intern_id ,created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, qanda.getQuestionTitle());
            ps.setString(2, qanda.getQuestionStatus().name());
            ps.setString(3, qanda.getQuestionText());
            ps.setInt(4, qanda.getMentorId());
            ps.setInt(5, qanda.getInternId());
            ps.setTimestamp(6, new Timestamp(new Date().getTime())); // Set created_at here
            ps.executeUpdate();

        } catch (SQLException e) {

        }

    }

    public Mentor getMentorByInternId(int internId) {
        Mentor mentor = null;
        String sql = "SELECT m.mentor_id, m.full_name, m.email, m.birth_date, m.phone_number "
                + "FROM Mentor m "
                + "INNER JOIN InternAssign ia ON m.mentor_id = ia.mentor_id "
                + "WHERE ia.intern_id = ? AND ia.is_selected = 1";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(+
                    1, internId);
            rs = ps.executeQuery();
            if (rs.next()) {
                mentor = new Mentor();
                mentor.setMentorId(rs.getInt("mentor_id"));
                mentor.setFullname(rs.getString("full_name"));
                mentor.setEmail(rs.getString("email"));
                mentor.setBirthDate(rs.getDate("birth_date"));
                mentor.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving mentor: " + e.getMessage());

        }
        return mentor;
    }

    public void updateQuestion(QandA qanda) {
        String sql = "UPDATE QandA SET question_title = ?, question_status = ? , question_text = ? , mentor_id = ?, intern_id = ? , updated_at = ? WHERE qanda_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, qanda.getQuestionTitle());
            ps.setString(2, qanda.getQuestionStatus().name());
            ps.setString(3, qanda.getQuestionText());
            ps.setInt(4, qanda.getMentorId());
            ps.setInt(5, qanda.getInternId());
            ps.setTimestamp(6, new Timestamp(new Date().getTime()));
            ps.setInt(7, qanda.getQandaId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating question: " + e.getMessage());
        }

    }

    public QandA getQuestionByQuestionId(int questionId) {
        QandA qanda = null;
        String xSQL = "SELECT q.qanda_id, q.question_title, q.question_status, q.question_text, q.mentor_id, q.intern_id, q.answer_text, q.created_at, q.updated_at, q.submitted_at, "
                + "m.full_name AS mentorFullName, i.full_name AS internFullName "
                + "FROM qanda q "
                + "LEFT JOIN mentor m ON q.mentor_id = m.mentor_id "
                + "LEFT JOIN intern i ON q.intern_id = i.intern_id "
                + "WHERE q.qanda_id = ?";
        try {
            ps = con.prepareStatement(xSQL);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int qanda_id = rs.getInt("qanda_id");
                String question_title = rs.getString("question_title");
                QandA.QandAStatus question_status = QandA.QandAStatus.valueOf(rs.getString("question_status"));
                String question_text = rs.getString("question_text");
                int mentor_id = rs.getInt("mentor_id");
                int intern_id = rs.getInt("intern_id");
                String answer_text = rs.getString("answer_text");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                Timestamp submittedAt = rs.getTimestamp("submitted_at");
                String mentorFullName = rs.getString("mentorFullName");
                String internFullName = rs.getString("internFullName");
                qanda = new QandA(qanda_id, question_title, question_status, question_text, mentor_id, intern_id, answer_text, createdAt, updatedAt, submittedAt, mentorFullName, internFullName);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving question by question_id: " + e.getMessage());
        }
        return qanda;
    }

    public void deleteQuestion(int qandaId) {
        String sql = "DELETE FROM QandA WHERE qanda_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, qandaId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting question: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
    }

    public void sendAnswer(int qandaId, String answerText) {
        String sql = "UPDATE qanda SET answer_text = ?, submitted_at = ?, question_status = ? WHERE qanda_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, answerText);
            ps.setTimestamp(2, new Timestamp(new Date().getTime()));
            ps.setString(3, QandA.QandAStatus.ANSWERED.name());
            ps.setInt(4, qandaId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating answer: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
    }

    public Intern getInternByQuestionId(int questionId) {
        Intern intern = null; // Khởi tạo intern là null
        String sql = "SELECT i.intern_id, i.student_id, i.email, i.full_name, i.phone_number, i.major, "
                + "i.company, i.job_title, i.link_cv, i.staff_id, i.status, i.semester_id "
                + "FROM Intern i "
                + "INNER JOIN qanda q ON i.intern_id = q.intern_id "
                + "WHERE q.qanda_id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();

            if (rs.next()) {
                intern = new Intern();
                intern.setInternId(rs.getInt("intern_id"));
                intern.setStudentId(rs.getString("student_id"));
                intern.setEmail(rs.getString("email"));
                intern.setFullName(rs.getString("full_name"));
                intern.setPhoneNumber(rs.getString("phone_number"));
                intern.setMajor(rs.getString("major"));
                intern.setCompany(rs.getString("company"));
                intern.setJobTitle(rs.getString("job_title"));
                intern.setLinkCv(rs.getString("link_cv"));
                intern.setStaffId(rs.getString("staff_id"));
                Intern.InternStatus status = Intern.InternStatus.valueOf(rs.getString("status"));
                intern.setSemester_id(rs.getInt("semester_id"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving intern by question ID: " + e.getMessage());
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

        return intern; // Trả về null nếu không tìm thấy
    }

    public static void main(String[] args) {
        QandADAO cc = new QandADAO();
    }
}

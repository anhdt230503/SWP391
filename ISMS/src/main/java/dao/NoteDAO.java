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
import model.Note;

/**
 *
 * @author admin
 */
public class NoteDAO extends MyDAO {

    public List<Note> getNotesByInternId(int internId) {
        List<Note> notes = new ArrayList<>();
        String xSql = "SELECT i.note_id, i.intern_id, i.note_content, i.created_at, i.note_title, it.full_name AS internFullName "
                + "FROM internnotes i LEFT JOIN intern it ON i.intern_id = it.intern_id "
                + "WHERE i.intern_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int noteId = rs.getInt("note_id");
                int intern_id = rs.getInt("intern_id");
                String noteContent = rs.getString("note_content");
                Timestamp createdAt = rs.getTimestamp("created_at");
                String noteTitle = rs.getString("note_title");
                String internFullName = rs.getString("internFullName");
                Note note = new Note(noteId, intern_id, noteTitle, noteContent, createdAt, internFullName);
                notes.add(note);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving notes for intern: " + e.getMessage());
        }
        return notes;
    }

    public void addNote(Note note) {
        String xSql = "INSERT INTO internnotes (note_title, note_content ,intern_id,created_at) VALUES (?, ?, ?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, note.getNoteTitle());
            ps.setString(2, note.getNoteContent());
            ps.setInt(3, note.getInternId());
            ps.setTimestamp(4, new Timestamp(new Date().getTime())); // Set created_at here
            ps.executeUpdate();
        } catch (SQLException e) {
            // Handle exception
        }
    }

    public Note getNoteByNoteId(int noteId) {
        Note note = null;
        String xSql = "SELECT i.note_id, i.intern_id, i.note_content, i.created_at, i.note_title, it.full_name AS internFullName "
                + "FROM internnotes i LEFT JOIN intern it ON i.intern_id = it.intern_id "
                + "WHERE i.note_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, noteId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int intern_id = rs.getInt("intern_id");
                String note_content = rs.getString("note_content");
                Timestamp createdAt = rs.getTimestamp("created_at");
                String note_title = rs.getString("note_title");
                String internFullName = rs.getString("internFullName");
                note = new Note(noteId, intern_id, note_title, note_content, createdAt, internFullName);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving note: " + e.getMessage());
        }
        return note;
    }

    public void updateNote(Note note) {
        String xSql = "UPDATE internnotes SET note_title = ?, note_content = ? ,intern_id = ? WHERE note_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, note.getNoteTitle());
            ps.setString(2, note.getNoteContent());
            ps.setInt(3, note.getInternId());
            ps.setInt(4, note.getNoteId()); // Giả sử bạn đã có phương thức getNoteId() trong lớp Note
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating note: " + e.getMessage());
        }
    }

    public void deleteNote(int noteId) {
        String xSql = "DELETE FROM internnotes WHERE note_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, noteId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting note: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        NoteDAO cc = new NoteDAO();
        System.out.println(cc.getNoteByNoteId(1));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author admin
 */
public class Note {

    private int noteId;
    private int internId;
    private String noteTitle;
    private String noteContent;
    private Timestamp created_at;
    private String internFullName;

    public Note() {
    }

    public Note(int noteId, int internId, String noteTitle, String noteContent, Timestamp created_at, String internFullName) {
        this.noteId = noteId;
        this.internId = internId;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.created_at = created_at;
        this.internFullName = internFullName;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getInternFullName() {
        return internFullName;
    }

    public void setInternFullName(String internFullName) {
        this.internFullName = internFullName;
    }

    @Override
    public String toString() {
        return "Note{" + "noteId=" + noteId + ", internId=" + internId + ", noteTitle=" + noteTitle + ", noteContent=" + noteContent + ", created_at=" + created_at + ", internFullName=" + internFullName + '}';
    }

   

}

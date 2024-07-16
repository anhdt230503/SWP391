/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author haidu
 */
public class Certificate {
    
    private int id;
    private int internId;
    private String internName;
    private byte[] pdfData;
    private Timestamp dateOfIssue;

    public Certificate() {
    }

    public Certificate(int id, int internId, String internName, byte[] pdfData, Timestamp dateOfIssue) {
        this.id = id;
        this.internId = internId;
        this.internName = internName;
        this.pdfData = pdfData;
        this.dateOfIssue = dateOfIssue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public String getInternName() {
        return internName;
    }

    public void setInternName(String internName) {
        this.internName = internName;
    }

    public byte[] getPdfData() {
        return pdfData;
    }

    public void setPdfData(byte[] pdfData) {
        this.pdfData = pdfData;
    }

    public Timestamp getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Timestamp dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    @Override
    public String toString() {
        return "Certificate{" + "id=" + id + ", internId=" + internId + ", internName=" + internName + ", pdfData=" + pdfData + ", dateOfIssue=" + dateOfIssue + '}';
    }

}

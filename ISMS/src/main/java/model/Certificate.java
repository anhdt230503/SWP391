/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author haidu
 */
public class Certificate {
    
    private int id;
    private int internId;
    private String internName;
    private String link;
    private Date dateOfIssue;

    public Certificate() {
    }

    public Certificate(int id, int internId, String internName, String link, Date dateOfIssue) {
        this.id = id;
        this.internId = internId;
        this.internName = internName;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    @Override
    public String toString() {
        return "Certificate{" + "id=" + id + ", internId=" + internId + ", internName=" + internName + ", link=" + link + ", dateOfIssue=" + dateOfIssue + '}';
    }

    
}

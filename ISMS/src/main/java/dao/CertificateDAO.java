/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Certificate;

/**
 *
 * @author haidu
 */
public class CertificateDAO extends MyDAO {

    public void saveCertificate(Certificate certificate) {
        xSql = "INSERT INTO Certificate (intern_id, certificate_data)"
                + "VALUES (?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, certificate.getInternId());
            ps.setBytes(2, certificate.getPdfData());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Certificate> getAllCertificate() {
        List<Certificate> list = new ArrayList();
        xSql = "SELECT c.id, c.intern_id, i.full_name, c.certificate_data, c.date_of_issue\n"
                + "FROM Certificate c\n"
                + "JOIN Intern i\n"
                + "ON c.intern_id = i.intern_id;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Certificate certificate = new Certificate();
                certificate.setId(rs.getInt(1));
                certificate.setInternId(rs.getInt(2));
                certificate.setInternName(rs.getString(3));
                certificate.setPdfData(rs.getBytes(4));
                certificate.setDateOfIssue(rs.getTimestamp(5));
                list.add(certificate);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public byte[] getCertificateData(int id) {
        byte[] pdfData = null;
        xSql = "select certificate_data \n"
                + "from certificate\n"
                + "where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pdfData = rs.getBytes(1);
            }
        } catch (Exception e) {
        }
        return pdfData;
    }

    public Certificate getCertificateByIntern(int internId) {
        xSql = "SELECT c.id, c.intern_id, i.full_name, c.certificate_data, c.date_of_issue\n"
                + "FROM Certificate c\n"
                + "JOIN Intern i\n"
                + "ON c.intern_id = i.intern_id\n"
                + "WHERE c.intern_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Certificate certificate = new Certificate();
                certificate.setId(rs.getInt(1));
                certificate.setInternId(rs.getInt(2));
                certificate.setInternName(rs.getString(3));
                certificate.setPdfData(rs.getBytes(4));
                certificate.setDateOfIssue(rs.getTimestamp(5));
                return certificate;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public byte[] getCertificateDataByIntern(int internId) {
        byte[] pdfData = null;
        xSql = "select certificate_data \n"
                + "from certificate\n"
                + "where intern_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            if (rs.next()) {
                pdfData = rs.getBytes(1);
            }
        } catch (Exception e) {
        }
        return pdfData;
    }
    
    public void deleteCertificate(int certificateId) {
        xSql = "DELETE FROM Certificate\n"
                + "WHERE id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, certificateId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        CertificateDAO certificateDAO = new CertificateDAO();

//        System.out.println(certificateDAO.getCertificateData(1));?
        System.out.println(certificateDAO.getCertificateByIntern(1));
        System.out.println(certificateDAO.getCertificateDataByIntern(1));

    }

}

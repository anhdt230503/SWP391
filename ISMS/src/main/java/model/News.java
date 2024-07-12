/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author haidu
 */
public class News {
    
    private int newsId;
    private String title;
    private String content;
    private int managerId;
    private String managerName;
    private Timestamp createdDate;
    private Timestamp publishedDate;
    private boolean isPublished;
    private String featuredImage;

    public News() {
    }

    public News(int newsId, String title, String content, int managerId, String managerName, Timestamp createdDate, Timestamp publishedDate, boolean isPublished, String featuredImage) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.managerId = managerId;
        this.managerName = managerName;
        this.createdDate = createdDate;
        this.publishedDate = publishedDate;
        this.isPublished = isPublished;
        this.featuredImage = featuredImage;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Timestamp publishedDate) {
        this.publishedDate = publishedDate;
    }

    public boolean isIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    @Override
    public String toString() {
        return "News{" + "newsId=" + newsId + ", title=" + title + ", content=" + content + ", managerId=" + managerId + ", managerName=" + managerName + ", createdDate=" + createdDate + ", publishedDate=" + publishedDate + ", isPublished=" + isPublished + ", featuredImage=" + featuredImage + '}';
    }

    
}

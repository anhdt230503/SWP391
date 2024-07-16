/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.News;

/**
 *
 * @author haidu
 */
public class NewsDAO extends MyDAO {

    public void addNews(News news) {
        xSql = "INSERT INTO News (title, content, manager_id, featured_image)\n"
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            ps.setInt(3, news.getManagerId());
            ps.setString(4, news.getFeaturedImage());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<News> getAllNewsByManager(int managerId) {
        List<News> list = new ArrayList<>();
        xSql = "SELECT  n.news_id, n.title, n.content, m.manager_id, m.full_name, n.created_date, n.published_date, n.is_published, n.featured_image\n"
                + "FROM News n \n"
                + "JOIN Manager m\n"
                + "ON n.manager_id = m.manager_id\n"
                + "WHERE n.manager_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, managerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                News news = new News();
                news.setNewsId(rs.getInt(1));
                news.setTitle(rs.getString(2));
                news.setContent(rs.getString(3));
                news.setManagerId(rs.getInt(4));
                news.setManagerName(rs.getString(5));
                news.setCreatedDate(rs.getTimestamp(6));
                news.setPublishedDate(rs.getTimestamp(7));
                news.setIsPublished(rs.getBoolean(8));
                news.setFeaturedImage(rs.getString(9));
                list.add(news);
            }
        } catch (Exception ex) {
        }
        return list;
    }

    public void publishNews(News news) {
        xSql = "UPDATE News\n"
                + "SET is_published = 1,\n"
                + "published_date = ?\n"
                + "WHERE news_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setTimestamp(1, news.getPublishedDate());
            ps.setInt(2, news.getNewsId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void unpublishNews(News news) {
        xSql = "UPDATE News\n"
                + "SET is_published = 0,\n"
                + "published_date = null\n"
                + "WHERE news_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, news.getNewsId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteNews(News news) {
        xSql = "DELETE FROM News\n"
                + "WHERE news_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, news.getNewsId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public News getNews(int newsId) {
        xSql = "SELECT  n.news_id, n.title, n.content, m.manager_id, m.full_name, n.created_date, n.published_date, n.is_published, n.featured_image\n"
                + "FROM News n \n"
                + "JOIN Manager m\n"
                + "ON n.manager_id = m.manager_id\n"
                + "WHERE n.news_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, newsId);
            rs = ps.executeQuery();
            while (rs.next()) {
                News news = new News();
                news.setNewsId(rs.getInt(1));
                news.setTitle(rs.getString(2));
                news.setContent(rs.getString(3));
                news.setManagerId(rs.getInt(4));
                news.setManagerName(rs.getString(5));
                news.setCreatedDate(rs.getTimestamp(6));
                news.setPublishedDate(rs.getTimestamp(7));
                news.setIsPublished(rs.getBoolean(8));
                news.setFeaturedImage(rs.getString(9));
                return news;
            }
        } catch (Exception ex) {
        }
        return null;
    }

    public List<News> getAllNews() {
        List<News> list = new ArrayList<>();
        xSql = "SELECT  n.news_id, n.title, n.content, m.manager_id, m.full_name, n.created_date, n.published_date, n.is_published, n.featured_image\n"
                + "FROM News n \n"
                + "JOIN Manager m\n"
                + "ON n.manager_id = m.manager_id\n";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                News news = new News();
                news.setNewsId(rs.getInt(1));
                news.setTitle(rs.getString(2));
                news.setContent(rs.getString(3));
                news.setManagerId(rs.getInt(4));
                news.setManagerName(rs.getString(5));
                news.setCreatedDate(rs.getTimestamp(6));
                news.setPublishedDate(rs.getTimestamp(7));
                news.setIsPublished(rs.getBoolean(8));
                news.setFeaturedImage(rs.getString(9));
                list.add(news);
            }
        } catch (Exception ex) {
        }
        return list;
    }

    public void editNews(News news) {
        xSql = "UPDATE News\n"
                + "SET title = ?,\n"
                + "content = ?,\n"
                + "featured_image = ?\n"
                + "WHERE news_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            ps.setString(3, news.getFeaturedImage());
            ps.setInt(4, news.getNewsId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}

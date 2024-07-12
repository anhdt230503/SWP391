/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.news;

import dao.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import model.News;

/**
 *
 * @author haidu
 */
public class UnpublishNews extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();

        int newsId = Integer.parseInt(request.getParameter("newsId"));

        News news = new News();
        news.setNewsId(newsId);
        newsDAO.unpublishNews(news);
        
        response.sendRedirect("newsList");
    }


}

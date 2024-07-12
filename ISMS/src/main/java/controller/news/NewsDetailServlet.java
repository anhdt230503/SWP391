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
import model.News;

/**
 *
 * @author haidu
 */
public class NewsDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();

        int newsId = Integer.parseInt(request.getParameter("newsId"));
        News news = newsDAO.getNews(newsId);
        
        request.setAttribute("news", news);
        request.getRequestDispatcher("NewsDetail.jsp").forward(request, response);
        
    } 

}

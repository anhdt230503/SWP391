<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>News Detail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .news-content {
                white-space: pre-wrap; /* Giữ nguyên khoảng trắng và xuống dòng */
            }
        </style>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="container mt-4">
                    <h1 class="mb-3">${news.title}</h1>
                <h6><em>Author: ${news.managerName}</em></h6>
                <div class="mb-1 text-body-secondary">${news.publishedDate}</div>
                <img src="${news.featuredImage}" class="img-fluid mb-3" alt="Featured Image">
                <p class="news-content">${news.content}</p>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

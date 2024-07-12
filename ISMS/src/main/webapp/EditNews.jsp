<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add News</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/> 

    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
            <div class="col-md-7 col-lg-8">
                <h4 class="mt-2 mb-3">Update News</h4>
                <form action="editNews" method="POST">
                    <div class="row g-3">
                        <input type="hidden" value="${news.newsId}" name="newsId"> 
                        <div class="col-sm-12">
                            <label for="title" class="form-label">Title</label>
                            <input type="text" class="form-control" value="${news.title}" id="title" name="title" required="">
                        </div>

                        <div class="col-12">
                            <label for="content" class="form-label">Content</label>
                            <div class="input-group">
                                <textarea class="form-control" id="content" name="content" rows="18">${news.content}</textarea>           
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="imgLink" class="form-label">Images Link</label>
                            <input type="text" class="form-control" id="imgLink" value="${news.featuredImage}" name="imgLink" placeholder="https://www.example.com" required="">
                        </div>
                    </div>
                    <hr class="my-4">
                        <button class="w-100 btn btn-primary btn-lg" type="submit">Update News</button>
                </form>
            </div>

            <script src="js/sidebar.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

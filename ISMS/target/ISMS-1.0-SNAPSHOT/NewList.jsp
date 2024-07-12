<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Intern Candidate List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/> 
        <style>
            .card {
                transition: transform 0.3s ease;
            }
            .card:hover {
                transform: scale(1.05);
            }
            .icon-link svg {
                vertical-align: middle;
            }

            /* Adjust card layout */
            .card-body {
                display: flex; /* Enable flexbox for card content */
                align-items: stretch; /* Stretch items to fill container */
            }

            .card-text {
                flex: 1; /* Allow text to take up available space */
                margin-right: 1rem; /* Add some spacing between text and image */
            }
            .card-img-right {
                width: auto;
                min-width: 40%;
                height: 100%;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="mt-2">
                    <a href="addNews"><button type="button" class="btn btn-sm btn-outline-primary">Create News</button></a>
                </div>
                <div class="container mt-4">
                    <div class="row">
                    <c:forEach items="${newsList}" var="o">
                        <div class="col-md-6 mb-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <div class="card-text">
                                        <h3 class="card-title">${o.title}</h3>
                                        <div class="mb-1 text-body-secondary">Nov 12</div>
                                        <p class="card-text mb-auto">${o.content}</p>
                                        <a href="#" class="icon-link stretched-link mt-auto">
                                            Continue reading
                                            <svg class="bi"><use xlink:href="#chevron-right"></use></svg>
                                        </a>
                                        <div class="btn-group">
                                            <form action="editNews" method="POST">
                                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                            </form>
                                            <form action="publishNews" method="POST">
                                                <button type="button" class="btn btn-sm btn-outline-secondary">Publish</button>
                                            </form>
                                        </div>
                                    </div>
                                    <img src="${o.featuredImage}" class="card-img-right" alt="Featured Post Thumbnail">
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

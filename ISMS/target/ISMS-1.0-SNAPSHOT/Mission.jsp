<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mission List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <style>
        .center-button {
            text-align: center;
            margin-bottom: 20px; 
        }
        .center-button button {
        }
    </style>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="row">
                        <div class="col-8 h3">List of Missions</div>
                        <div class="center-button">
                            <a href="addMission.jsp"><button class="btn btn-primary">Add Mission</button></a>
                        </div>
                        <!-- Display error or success messages if any -->
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">${errorMessage}</div>
                    </c:if>
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success">${successMessage}</div>
                    </c:if>
                </div> 
                <table class="table caption-top table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Status</th>
                            <th>Description</th>
                            <th>Link</th>
                            <th>Start Date</th>
                            <th>Deadline</th>
                            <th>Mentor ID</th>
                            <th>Intern ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="mission" items="${missions}">
                            <tr>
                                <td>${mission.misId}</td>
                                <td>${mission.misName}</td>
                                <td>${mission.misStatus}</td> <!-- Trạng thái sẽ được hiển thị tự động -->
                                <td>${mission.misDescription}</td>
                                <td><a href="${mission.link}" target="_blank">${mission.link}</a></td>
                                <td>${mission.startDate}</td>
                                <td>${mission.deadline}</td>
                                <td>${mission.mentorId}</td>
                                <td>${mission.internId}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

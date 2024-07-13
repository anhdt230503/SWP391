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
        .small-round-button {
            background-color: #4CAF50; /* Màu nền của nút */
            color: white; /* Màu chữ */
            border: none; /* Loại bỏ viền mặc định */
            padding: 10px; /* Giảm khoảng cách bên trong nút để làm nút nhỏ hơn */
            text-align: center; /* Căn giữa chữ */
            text-decoration: none; /* Loại bỏ gạch chân */
            display: inline-block; /* Hiển thị theo kiểu khối nội dòng */
            font-size: 12px; /* Giảm kích thước chữ */
            border-radius: 50%; /* Làm tròn góc để tạo hình tròn */
            cursor: pointer; /* Thay đổi con trỏ chuột khi di chuyển qua nút */
            transition: background-color 0.3s; /* Hiệu ứng chuyển đổi màu nền */
        }

        .small-round-button:hover {
            background-color: #45a049; /* Màu nền khi hover */
        }
    </style>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="row">
                        <h1 style="text-align: center;">List of Missions</h1>

                        <div class="center-button">
                            <a href="AddMissionServlet"><button class="btn btn-primary">Add Mission</button></a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <form method="get" action="mission">
                            <div class="input-group">
                                <select class="form-select" name="status" onchange="this.form.submit()">
                                    <option value="">All Statuses</option>                                <option value="NOT_START" <c:if test="${param.status == 'NOT_START'}">selected</c:if>>NOT_START</option>
                                <option value="ON_GOING" <c:if test="${param.status == 'ON_GOING'}">selected</c:if>>ON_GOING</option>
                                <option value="MISSING" <c:if test="${param.status == 'MISSING'}">selected</c:if>>MISSING</option>
                                <option value="FINISHED" <c:if test="${param.status == 'FINISHED'}">selected</c:if>>FINISHED</option>
                                </select>
                            </div>
                        </form>
                    </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" id="errorAlert">${errorMessage}</div>
                    <script>
                        setTimeout(function () {
                            document.getElementById("errorAlert").style.display = 'none';
                        }, 5000); // 5000 milliseconds = 5 seconds
                    </script>
                </c:if>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                <table class="table caption-top table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Status</th>
                            <th>Description</th>
                            <th>Task</th>
                            <th>Start Date</th>
                            <th>Deadline</th>
                            <th>Mentor Name</th>
                            <th>Intern Name</th>
                            <th>Submit Task</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="mission" items="${missions}">
                            <tr>
                                <td><a href="DetailMission?misId=${mission.misId}">${mission.misId}</a></td>
                                <td>${mission.misName}</td>
                                <td>${mission.misStatus}</td>
                                <td>${mission.misDescription}</td>
                                <td><a href="DownFileMission?link=${mission.link}">${mission.link}</a></td>
                                <td>${mission.startDate}</td>
                                <td>${mission.deadline}</td>
                                <td>${mission.mentorFullName}</td>
                                <td>${mission.internFullName}</td>
                                <td><a href="DownFileMission1?file_path=${mission.file_path}">${mission.file_path}</a></td>
                                <td>
                                    <a href="UpdateMissionServlet?misId=${mission.misId}" class="btn btn-sm text-primary">
                                        <i class="bi bi-pencil"></i>
                                    </a>
                                    <a href="DeleteMissionServlet?misId=${mission.misId}" class="btn btn-sm text-primary" onclick="return confirm('Are you sure you want to delete this mission?');">
                                        <i class="bi bi-trash"></i>
                                    </a>
                                </td>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Intern List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
        <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
            <div class="container mt-5">
                <h2>Assigned Interns</h2>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Intern ID</th>
                            <th scope="col">Student ID</th>
                            <th scope="col">Email</th>
                            <th scope="col">Full Name</th>
                            <th scope="col">Phone Number</th>
                            <th scope="col">Staff ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${internList}" var="intern">
                            <tr>
                                <td>${intern.internId}</td>
                                <td>${intern.studentId}</td>
                                <td>${intern.email}</td>
                                <td>${intern.fullName}</td>
                                <td>${intern.phoneNumber}</td>
                                <td>${intern.staffId}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

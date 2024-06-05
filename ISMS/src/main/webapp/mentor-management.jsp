<%-- 
    Document   : product-management
    Created on : Jun 26, 2023, 12:48:41 AM
    Author     : ADMIN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
        <title>Mentor Management</title>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="row">
                        <div class="col-8 h3">List of Mentor</div>
                        <div class="col-4">
<form action="Addmentor" method="post" class="mt-4">
    <div class="mb-3">
        <label for="fullname" class="form-label">Tên:</label>
        <input type="text" class="form-control" id="fullname" name="fullname" required>
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Email:</label>
        <input type="email" class="form-control" id="email" name="email" required>
    </div>
    <button type="submit" class="btn btn-primary">Add Mentor</button>
</form>
    </div>
</div>
<!--                        <p>${errorMessage}</p>
                    <p>${successMessage}</p>-->
                </div> 
                <table class="table caption-top table-bordered">

                    <thead class="table-light">
                        <tr>
                            <th scope="col">Mentor ID</th>
                            <th scope="col">Email</th>
                            <th scope="col">Full Name</th>
                            <th scope="col">BirthDate</th>
                            <th scope="col">Phone Number</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${mentors}" var="mentors">
                            <tr>
                                <td>${mentors.mentorId}</td>
                                <td>${mentors.email}</td>
                                <td>${mentors.fullname}</td>
                                <td>${mentors.birthDate}</td>
                                <td>${mentors.phoneNumber}</td>
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
    <script>
        document.getElementById('showAddMentorFormBtn').addEventListener('click', function() {
    document.getElementById('addMentorFormContainer').style.display = 'block';
});
    </script>
</html>

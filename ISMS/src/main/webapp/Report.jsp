<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Report</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <jsp:include page="Sidebar.jsp"></jsp:include>
    <div class="main-content">
        <jsp:include page="Topbar.jsp"></jsp:include>
        <div class="container mt-3">
            <h2>Student Report</h2>
            <div class="student-info">
                <p><strong>Student ID:</strong> ${student.studentId}</p>
                <p><strong>Name:</strong> ${student.fullName}</p>  
                <p><strong>Staff Id:</strong> ${student.staffId}</p>  
            </div>
            <form action="SubmitReport" method="post">
                <input type="hidden" id="internId" name="internId" value="${student.internId}">
                <div class="form-group">
                    <label for="skills">Skills:</label>
                    <input type="number" min="0" max="10" step="0.1" class="form-control" id="skills" name="skills" required>
                </div>
                <div class="form-group">
                    <label for="softSkills">Soft Skills:</label>
                    <input type="number" min="0" max="10"  step="0.1" class="form-control" id="softSkills" name="softSkills" required>
                </div>
                <div class="form-group">
                    <label for="attitude">Attitude:</label>
                    <input type="number" min="0" max="10" step="0.1" class="form-control" id="attitude" name="attitude" required>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Submit Report</button>
            </form>
        </div>
    </div>

    <script src="js/sidebar.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>

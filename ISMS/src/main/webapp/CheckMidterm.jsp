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
        <style>
            .form-group {
                margin-bottom: 10px;
            }
            .form-group label {
                display: inline-block;
                width: 120px; /* Adjust width as needed */
                margin-right: 10px;
            }
            .form-group input[type="checkbox"] {
                display: inline-block;
                width: auto;
                margin-right: 5px;
            }
        </style>
        <script>
            function validateForm() {
                const checkboxes = document.querySelectorAll('input[type="checkbox"]');
                let checkedCount = 0;
                checkboxes.forEach(checkbox => {
                    if (checkbox.checked) {
                        checkedCount++;
                    }
                });
                if (checkedCount != 1) {
                    alert("Please select exactly one comment.");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>

                <div class="container mt-3">
                    <div class="card">
                        <div class="card-body">

                            <div class="row">
                                <div class="col-md-6">
                                    <h2 class="card-title">Student Report</h2>
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <th>Student ID:</th>
                                                <td>${student.studentId}</td>
                                        </tr>
                                        <tr>
                                            <th>Name:</th>
                                            <td>${student.fullName}</td>
                                        </tr>
                                        <tr>
                                            <th>Staff Id:</th>
                                            <td>${student.staffId}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div class="col-md-6">
                                <h2>Comments of employer for OJT</h2>
                                <form action="SubmitMidterm" method="post" onsubmit="return validateForm()">
                                    <input type="hidden" id="internId" name="internId" value="${student.internId}">

                                    <div class="form-group">
                                        <label for="Excellent">Excellent:</label>
                                        <input type="checkbox" id="Excellent" name="Excellent">
                                    </div>
                                    <div class="form-group">
                                        <label for="VeryGood">Very good:</label>
                                        <input type="checkbox" id="VeryGood" name="VeryGood">
                                    </div>
                                    <div class="form-group">
                                        <label for="Good">Good:</label>
                                        <input type="checkbox" id="Good" name="Good">
                                    </div>
                                    <div class="form-group">
                                        <label for="Average">Average:</label>
                                        <input type="checkbox" id="Average" name="Average">
                                    </div>
                                    <div class="form-group">
                                        <label for="Poor">Poor:</label>
                                        <input type="checkbox" id="Poor" name="Poor">
                                    </div>
                                    <div class="form-group">
                                        <label for="attitude">Comment</label>
                                        <input type="textarea" class="form-control" id="comment" name="comment" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary mt-3">Submit Report</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

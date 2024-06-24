<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Mission</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        h2 {
            padding-top: 50px;
            text-align: center;
        }
        form {
            width: 50%;
            margin: 0 auto;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input[type="text"],
        textarea,
        select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add Mission</h2>
        <%-- Display error message if there is any --%>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <div class="error"><%= errorMessage %></div>
        <% } %>
        <form id="myForm" action="AddMissionServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
            </div>
            <div class="mb-3">
                <label for="link" class="form-label">Link:</label>
                <input type="file" class="form-control" id="link" name="link" accept=".doc, .pdf" required>
            </div>
            <div class="mb-3">
                <label for="internId" class="form-label">Intern:</label>
                <select class="form-control" id="internId" name="internId" required>
                    <option value="">Select Intern</option>
                    <c:forEach items="${internList}" var="intern">
                        <option value="${intern.internId}">${intern.fullName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="startDate" class="form-label">Start Date:</label>
                <input type="datetime-local" class="form-control" id="startDate" name="startDate" required>
            </div>
            <div class="mb-3">
                <label for="deadline" class="form-label">Deadline:</label>
                <input type="datetime-local" class="form-control" id="deadline" name="deadline" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>

    <script>
        function validateForm() {
            var startDate = new Date(document.getElementById("startDate").value);
            var deadline = new Date(document.getElementById("deadline").value);

            if (deadline < startDate) {
                alert("Deadline must be after Start Date");
                return false;
            }
            return true;
        }
        if (window.history.replaceState) {
            window.history.replaceState(null, null, window.location.href);
        }
        document.addEventListener('DOMContentLoaded', (event) => {
            document.getElementById("myForm").reset();
        });
    </script>
</body>
</html>

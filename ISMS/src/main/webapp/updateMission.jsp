<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Mission</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
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
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: white;
            border-radius: 15px 15px 0 0;
            text-align: center;
        }
        .btn-container1 {
            text-align: center;
            margin-top: 20px;
        }
        .btn1 {
            padding: 10px 20px;
            border: none;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            border-radius: 5px;
            text-transform: uppercase;
            letter-spacing: 1px;
            transition: background-color 0.3s ease;
        }
        .btn1:hover {
            background-color: #0056b3;
        }
    </style>
    <body>
        <div class="container">
            <h2>Update Mission</h2>
            <form action="UpdateMissionServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                <input type="hidden" name="misId" value="${mission.misId}">
                <div>
                    <label for="misName" class="form-label">Mission Name</label>
                    <input type="text" class="form-control" id="misName" name="misName" value="${mission.misName}" required>
                </div>
                <div>
                    <label for="misDescription" class="form-label">Description</label>
                    <textarea class="form-control" id="misDescription" name="misDescription" required>${mission.misDescription}</textarea>
                </div>
                <br>
                <div>

                    <label for="link" class="form-label">Previous file :${mission.link}</label>
                    <p>New file <input type="file" id="link" name="link"></p>

                </div>

                <div class="form-group">
                    <label for="internId">Intern:</label>
                    <select class="form-control" id="internId" name="internId" required>
                        <option value="">${mission.internFullName}</option>
                        <c:forEach items="${internList}" var="intern">
                            <option value="${intern.internId}">${intern.fullName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label for="startDate" class="form-label">Start Date</label>
                    <input type="datetime-local" class="form-control" id="startDate" name="startDate" value="${mission.startDate}">
                </div>
                <div>
                    <label for="deadline" class="form-label">Deadline</label>
                    <input type="datetime-local" class="form-control" id="deadline" name="deadline" value="${mission.deadline}">
                </div>
                <button type="submit" class="btn btn-primary">Update Mission</button>
            </form>
            <div class="btn-container1">
                <button class="btn1" onclick="window.history.back();">Back</button>
            </div>
        </div>
    </body>
    <script>
        function validateForm() {
            var now = new Date();
            var startDate = new Date(document.getElementById("startDate").value);
            var deadline = new Date(document.getElementById("deadline").value);
            var dateError = document.getElementById("dateError");

            dateError.textContent = '';

            if (startDate <= now) {
                alert("The Start Date must be after the task creation time.");
                return false;
            }

            if (deadline < startDate) {
                alert("Deadline must be after Start Date");
                return false;
            }

            var diffTime = Math.abs(deadline - startDate);
            var diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));



            return true;
        }

        if (window.history.replaceState) {
            window.history.replaceState(null, null, window.location.href);
        }

        document.addEventListener('DOMContentLoaded', (event) => {
            document.getElementById("myForm").reset();
        });
    </script>
</html>
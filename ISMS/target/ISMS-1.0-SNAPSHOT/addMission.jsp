<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Mission</title>
    <!-- Add any CSS or JS files here -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        h2 {
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
<h2>Add Mission</h2>
<%-- Display error message if there is any --%>
<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
    <div class="error"><%= errorMessage %></div>
<% } %>
<form action="AddMissionServlet" method="post" onsubmit="return validateForm()">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value=""><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description" rows="4" cols="50"></textarea><br>

    <label for="link">Link:</label><br>
    <input type="text" id="link" name="link" value=""><br>

    <label for="startDate">Start Date:</label><br>
    <input type="datetime-local" id="startDate" name="startDate" value=""><br>

    <label for="deadline">Deadline:</label><br>
    <input type="datetime-local" id="deadline" name="deadline" value=""><br>

    <input type="submit" value="Submit">
</form>

<script>
    function validateForm() {
        var startDate = new Date(document.getElementById("startDate").value);
        var deadline = new Date(document.getElementById("deadline").value);

        if (deadline < startDate) {
            alert("Deadline phải sau Start Date");
            return false;
        }
        return true;
    }
</script>

</body>
</html>

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
    </head>
    <style>
        /* Center the form on the page */
#createForm {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #f9f9f9;
    max-width: 400px;
    margin: auto;
    margin-top: 50px;
}

/* Style form elements */
.form-group {
    width: 100%;
    margin-bottom: 15px;
}

label {
    font-weight: bold;
    margin-bottom: 5px;
    display: inline-block;
}

input[type="text"],
input[type="number"] {
    width: calc(100% - 20px);
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    margin-bottom: 10px;
    transition: border-color 0.3s;
}

/* Add focus effect to input fields */
input[type="text"]:focus,
input[type="number"]:focus {
    border-color: #007bff;
    outline: none;
}

button[type="submit"] {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    border: none;
    border-radius: 5px;
    color: white;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button[type="submit"]:hover {
    background-color: #0056b3;
}

    </style>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="container mt-5">
        <h2>Lab Room Management</h2>
        <button class="btn btn-primary mb-3" id="toggleFormButton">Create Lab Room</button>
       <form id="createForm" action="ListLabRoomsServlet" method="POST">
    <div class="form-group">
        <label for="roomName">Room Name:</label>
        <input type="text" class="form-control" id="roomName" name="roomName" required>
    </div>
    <div class="form-group">
        <label for="mentorId">Mentor:</label>
        <select class="form-control" id="mentorId" name="mentorId" required>
            <option value="">Select Mentor</option>
            <c:forEach items="${listOfMentors}" var="mentor">
                <option value="${mentor.mentorId}">${mentor.fullname}</option>
            </c:forEach>
        </select>
        <div class="invalid-feedback">Please select a mentor.</div>
    </div>
    <button type="submit" class="btn btn-primary">Create Lab Room</button>
</form>
        </div>
                <table class="table caption-top table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th scope="col">Room ID</th>
                            <th scope="col">Room Name</th>
                            <th scope="col">is Assigned</th>
                            <th scope="col">Mentor Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listOfLabRooms}" var="o">
                            <tr>
                                <td>${o.roomId}</td>
                                <td>${o.roomName}</td>
                                <td>${o.assigned}</td>
                                 <td>${o.mentorFullName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
        <script>
            document.getElementById('toggleFormButton').addEventListener('click', function() {
                var form = document.getElementById('createForm');
                if (form.style.display === 'none' || form.style.display === '') {
                    form.style.display = 'block';
                } else {
                    form.style.display = 'none';
                }
            });
        </script>
    </body>
</html>

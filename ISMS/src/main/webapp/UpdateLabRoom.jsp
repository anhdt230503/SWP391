<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update Lab Room</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <style>
        /* Center the form on the page */
        .update-form-container {
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
        select {
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
        select:focus {
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

        /* Add some spacing between the header and the form */
        h2 {
            margin-bottom: 20px;
        }
    </style>
    <body>
        <div class="update-form-container">
            <h2>Update Lab Room</h2>
            <form action="UpdateLabRoomServlet" method="POST">
                <input type="hidden" name="roomId" value="${labRoom.roomId}">
                <div class="form-group">
                    <label for="roomName">Room Name:</label>
                    <input type="text" class="form-control" id="roomName" name="roomName" value="${labRoom.roomName}" required>
                </div>
                <div class="form-group">
                    <label for="mentorId">Mentor:</label>
                    <select class="form-control" id="mentorId" name="mentorId">
                        <option value="">Select Mentor</option>
                        <c:forEach items="${listOfMentors}" var="mentor">
                            <option value="${mentor.mentorId}" ${mentor.mentorId == labRoom.mentorId ? 'selected' : ''}>${mentor.fullname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </div>
            </form>
        </div>     
    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Schedule</title>
        <style>
            .container {
                width: 300px;
                margin: 50px auto;
                text-align: center;
            }

            .form-group {
                margin: 15px 0;
            }

            label {
                display: block;
                margin-bottom: 5px;
            }

            input, select, textarea, button {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
                margin-top: 5px;
            }

            button {
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form action="AddSchedule" method="POST">
                <h2>Schedule</h2>
                <div class="form-group">
                    <label for="mission-name">Mission Name:</label>
                    <select id="mission-name" name="missionName" class="form-control">
                        <c:forEach items="${schedulelist}" var="scl">
                            <option>${scl.misName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="date-schedule" name="date">Date Schedule:</label>
                    <input type="datetime-local" id="date-schedule">
                </div>
                <div class="form-group">
                    <label for="hours-schedule" name="hours">Hours Schedule:</label>
                    <input type="number" min="1" max="8" id="hours-schedule">
                </div>
                <div class="form-group">
                    <label for="description" name="description">Description:</label>
                    <textarea id="description" rows="4"></textarea>
                </div>
                <button type="submit">Add</button>
            </form>
        </div>
    </body>
</html>

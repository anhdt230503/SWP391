<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Schedule</title>
        <style>
            /* Overall Styling */
            body {
                font-family: sans-serif;
                background-color: #f4f4f4;
            }

            .container {
                width: 400px; /* Increased width for better spacing */
                margin: 50px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            h2 {
                color: #333;
            }

            /* Form Styling */
            .form-group {
                margin-bottom: 15px;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            /* Task List Styling */
            .task-group {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px;
                border-bottom: 1px solid #eee;
            }

            .task-group:last-child { /* Remove border from last task */
                border-bottom: none;
            }

            .task-group .task-details span { /* Target the task name span specifically */
                font-weight: bold;
            }

            /* Note Label Styling */
            .task-note::before {
                content: "Note:"; /* Add the label before the note content */
                font-weight: bold;
                background-color: yellow; /* Add background highlight */
            }

            /* Button Styling */
            button {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 10px 20px; /* Increased padding */
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>View Schedule</h2>
            <form action="loadTask" method="POST">
                <div class="form-group">
                    <label for="missionName">Mission Name:</label>
                    <select id="scheduleId" name="scheduleId" class="form-control">
                        <c:forEach items="${list}" var="o">
                            <option value="${o.scheduleId}">${o.misName}</option>
                        </c:forEach>
                    </select>
                    <button type="submit">Load Tasks</button>
                </div>
            </form>
            <form action="updateTask" method="POST">
                <c:forEach items="${taskList}" var="o">
                    <div class="task-group">
                        <div class="task-details"> <span>${o.taskName}</span>
                            <c:if test="${not empty o.note}">  
                                <p class="task-note"> ${o.note}</p>  </c:if>  
                            </div>
                            <span><input type="checkbox" name="selectedTasks" value="${o.taskId}" <c:if test="${checkedTaskIds.contains(o.taskId)}">checked</c:if>></span>
                        </div>
                </c:forEach>
                <div class="btn-container">
                    <button type="submit">Update Tasks</button>
                </div>

            </form>
            <form action="backToSchedule" method="POST">
                <button type="submit" class="btn btn-primary mt-2">Back</button>
            </form>
        </div>
    </body>
</html>

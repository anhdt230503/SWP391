<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Schedule</title>
    <style>
        .container {
            width: 300px;
            margin: 50px auto;
            text-align: center;
            
        }

        .task-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 10px 0;
        }

        button {
            width: 100px;
            margin: 10px;
            padding: 10px;
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
        <h2>View Schedule</h2>
        <div class="form-group">
            <label for="mission-name">Mission Name:</label>
            <select id="mission-name">
                <option>Mission 1</option>
                <option>Mission 2</option>
                <option>Mission 3</option>
            </select>
        </div>
        <div class="task-group">
            <span>Task 1</span>
            <span>Is Done <input type="checkbox"></span>
        </div>
        <div class="task-group">
            <span>Task 2</span>
            <span>Is Done <input type="checkbox"></span>
        </div>
        <div class="task-group">
            <span>Task 3</span>
            <span>Is Done <input type="checkbox"></span>
        </div>
        <div>
            <button onclick="window.location.href='ScheduleList'">Cancel</button>
            <button onclick="window.location.href='ScheduleList'">Ok</button>
        </div>
    </div>
</body>
</html>

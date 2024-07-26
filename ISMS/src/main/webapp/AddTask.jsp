<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Add Task</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <style>
        /* Căn giữa form trên trang */
        .update-form-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 20px 0; /* Chỉ đặt padding trên và dưới */
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
            width: 80%;
            max-width: 400px;
            margin: 50px auto;
        }

        /* Style các phần tử trong form */
        .form-group {
            width: 100%;
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block; /* Đảm bảo label chiếm toàn bộ chiều rộng */
        }

        input[type="text"],
        select,
        textarea,
        input[type="date"] {
            width: 100%; /* Các trường input chiếm toàn bộ chiều rộng của form-group */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        /* Hiệu ứng khi focus vào trường input */
        input[type="text"]:focus,
        select:focus,
        textarea:focus, /* Thêm textarea vào đây */
        input[type="date"]:focus {
            border-color: #007bff;
            outline: none;
        }

        /* Style nút submit */
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

        /* Khoảng cách giữa tiêu đề và form */
        h2 {
            margin-bottom: 20px;
        }

    </style>
    <body>
        <div class="update-form-container">
            <h3>Add Task</h3>
            <form action="addTask" method="POST">
                <div class="form-group">
                    <input type="hidden" value="${scheduleMission.scheduleId}" name="scheduleId" type="text" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="missionName" class="form-label">Mission Name:</label>
                    <input value="${scheduleMission.misName}" type="text" class="form-control" id="missionName" name="missionName" required disabled>
                </div>
                <div class="mb-3">
                    <label for="taskName" class="form-label">Task Name:</label>
                    <input type="text" class="form-control" id="taskName" name="taskName" required>
                </div>
                <div class="mb-3">
                    <label for="hour" class="form-label">Hours:</label>
                    <input type="number" min="1" max="8" class="form-control" id="hour" name="hour" required>
                </div>
                <div class="mb-3">
                    <label for="note" class="form-label">Note:</label>
                    <textarea class="form-control" id="note" name="note" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Add Task</button>
            </form>
            
        </div>     
    </body>
</html>
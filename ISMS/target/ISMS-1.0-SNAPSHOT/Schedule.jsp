<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Intern Candidate List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .container {
                margin: 50px auto;

            }

            .table {
                width: 100%;
                margin: 0 auto;
            }

            .table th, .table td {
                text-align: center;
                vertical-align: middle;
            }

            .progress-bar {
                width: 100%;
                background-color: #f3f3f3;
                border-radius: 5px;
                overflow: hidden;
                position: relative;
            }

            .progress-bar div {
                height: 20px;
                width: 50%;
                background-color: #007bff;
                text-align: right;
                line-height: 20px;
                color: white;
                padding-right: 10px;
                border-radius: 10px;
            }

            .btn-container {
                text-align: right;
                margin-top: 10px;
            }

            button:hover {
                background-color: #0056b3;
            }
            .popup {
                text-align: center;
                width: 400px;
                margin: 0 auto;
                background: #fff;
                padding: 30px;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                display: none;
                z-index: 1000;
            }
            .task-group {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin: 10px 0;
            }
            .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0,0,0,0.5);
                display: none;
                z-index: 999;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="container">
                    <table class="table table-bordered table-responsive">
                        <thead>
                            <tr>
                                <th scope="col">Mission</th>
                                <th scope="col">Progress</th>
                                <th scope="col">Status</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${schedulelist}" var="scl">
                            <tr>
                                <td>${scl.misName}</td>
                                <td>${scl.rate}%</td>
                                <td>${scl.status}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="btn-container">
                    <button class="btn btn-primary" onclick="window.location.href = 'AddSchedule'">Schedule</button>
                    <button class="btn btn-primary" onclick="showPopup()">View Schedule</button>
                </div>
            </div>

            <!-- Popup section -->
            <div class="overlay" id="overlay"></div>
            <div class="popup" id="popup">
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
                    <button class="btn btn-primary" onclick="window.location.href = 'Schedule.jsp'">Cancel</button>
                    <button class="btn btn-primary" onclick="window.location.href = 'Schedule.jsp'">Ok</button>
                </div>
            </div>
        </div>

        <script>
            function showPopup() {
                document.getElementById('popup').style.display = 'block';
                document.getElementById('overlay').style.display = 'block';
            }

            function closePopup() {
                document.getElementById('popup').style.display = 'none';
                document.getElementById('overlay').style.display = 'none';
            }
        </script>

        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

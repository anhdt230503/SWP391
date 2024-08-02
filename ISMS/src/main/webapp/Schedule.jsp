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

            .btn-container {
                text-align: right;
                margin-top: 10px;
            }

            button:hover {
                background-color: #0056b3;
            }

            /* Modal styles */
            .modal {

            }

            .modal-content {
                display: flex;
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 50%; /* Adjust the width as needed */
                max-width: 500px; /* Max width to prevent the modal from being too wide */
                border-radius: 10px;
            }

            /* Close button */
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }


            /* Close button */
            .close {
                color: #aaa;
                position: absolute;
                top: 10px;
                right: 20px;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            .table td:nth-child(6) {
                word-wrap: break-word !important;
            }

        </style>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="container">
                    <table class="table table-secondary">
                        <thead>
                            <tr>
                                <th scope="col">Schedule</th>
                                <th scope="col">Created Date</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">Hours</th>
                                <th scope="col">Note</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="o">
                            <tr class="table-light">
                                <td>
                                    <a href="loadSchedule?scheduleId=${o.scheduleId}">${o.misName}
                                    </a>
                                </td> 
                                <td>${o.createdDate}</td>
                                <td>${o.startDate}</td>
                                <td>${o.hour}</td>
                                <td>${o.note}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="btn-container">
                    <button class="btn btn-primary" onclick="openModal()">Schedule</button>
                    <form action="getMissionName" method="POST">
                        <button type="submit" class="btn btn-primary">View Task</button>
                    </form>
                </div>
            </div>

            <!-- Modal -->
            <div id="scheduleModal" class="modal"> 
                <div class="modal-content">
                    <span class="close" onclick="closeModal()">&times;</span>
                    <div class="container">
                        <h2>Schedule</h2>
                        <form action="addSchedule" method="POST">
                            <div class="mb-3">
                                <label for="missionId" class="form-label">Mission Name:</label>
                                <select id="missionId" name="missionId" class="form-control">
                                    <c:forEach items="${missionList}" var="o">
                                        <option value="${o.misId}">${o.misName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="date" class="form-label">Start Date:</label>
                                <input type="date" class="form-control" id="date" name="date" required>
                            </div>
                            <div class="mb-3">
                                <label for="hour" class="form-label">Hours:</label>
                                <input type="number" class="form-control" id="hour" name="hour" required>
                            </div>
                            <div class="mb-3">
                                <label for="note" class="form-label">Note:</label>
                                <textarea class="form-control" id="note" name="note" rows="3"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Add</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <script>
            // Function to open the modal
            function openModal() {
                document.getElementById("scheduleModal").style.display = "block";
            }

            // Function to close the modal
            function closeModal() {
                document.getElementById("scheduleModal").style.display = "none";
            }

            // Close the modal if the user clicks outside of it
            window.onclick = function (event) {
                var modal = document.getElementById("scheduleModal");
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            };
        </script>

        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

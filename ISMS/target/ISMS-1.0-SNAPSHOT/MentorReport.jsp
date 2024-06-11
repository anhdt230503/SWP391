<<<<<<< HEAD
=======
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Intern Candidate List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/mentorreport.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <jsp:include page="Sidebar.jsp"></jsp:include>
    <div class="main-content">
        <jsp:include page="Topbar.jsp"></jsp:include>

<<<<<<< HEAD
        <!-- Dropdown select -->
        <div class="container mb-3">
            <label for="reportTitle" class="form-label">Select Report Title:</label>
            <select id="reportTitle" name="reportTitle" class="form-select" onchange="filterReportList()" required>
                <option value="Weekly Report">Weekly Report</option>
                <option value="Midterm Report">Midterm Report</option>
                <option value="Final Report">Final Report</option>
            </select>
        </div>

=======
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
        <!-- Modal -->
        <div id="uploadModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <div class="container">
                    <h2>Create Report</h2>
                    <form action="mentorreport" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
<<<<<<< HEAD
                            <label for="description">Description:</label>
                            <textarea id="description" name="description" rows="4" cols="50" placeholder="Enter description..."></textarea>
                        </div>
                        <td><a href="downloadFile?reportFile=${rp.filedata}">File Example</a></td>      
=======
                            <label for="reportTitle">Report Title:</label>
                            <select id="reportTitle" name="reportTitle" required>
                                <option value="Weekly Report">Weekly Report</option>
                                <option value="Midtern Report">Midterm Report</option>
                                <option value="Final Report">Final Report</option>
                            </select>
                        </div>
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
                        <div class="form-group">
                            <label for="reportFile">Upload Report File:</label>
                            <input type="file" id="reportFile" name="reportFile" accept=".xlsx, .xls" required>
                        </div>
<<<<<<< HEAD
                        <button type="submit" class="btn btn-primary">Upload File</button>
=======
                        <button type="submit">Upload File</button>
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
                    </form>
                </div>
            </div>
        </div>

        <button onclick="openModal()">Create Report</button>

        <% 
<<<<<<< HEAD
        String message = (String) request.getAttribute("message");
        if (message != null) {
=======
           String message = (String) request.getAttribute("message");
           if (message != null) {
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
        %>
        <div class="alert alert-success" role="alert">
            <%= message %>
        </div>
        <% } %>

        <div class="table-container">
            <h2>Report List</h2>
            <table id="reportTable" class="table caption-top table-bordered">
                <thead class="table-light">
                    <tr>
<<<<<<< HEAD
=======
                      
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
                        <th scope="col">Title</th>
                        <th scope="col">Date</th>
                        <th scope="col">File</th>                       
                        <th scope="col">Mentor</th>
<<<<<<< HEAD
=======
                       
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOfReport}" var="rp">
                        <tr>                         
                            <td>${rp.reportName}</td>                            
                            <td>${rp.reportDate}</td> 
                            <td><a href="downloadFile?reportFile=${rp.filedata}">Download</a></td>                        
                            <td>${rp.mentorId}</td>
<<<<<<< HEAD
=======
                            
>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script src="js/sidebar.js"></script>
    <script src="js/mentorreport.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
<<<<<<< HEAD
=======

>>>>>>> dd04b15844f6674749c4e44c3d192caab41a8a3d

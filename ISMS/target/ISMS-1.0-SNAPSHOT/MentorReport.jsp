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
    <link href="css/mentorreport.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <jsp:include page="Sidebar.jsp"></jsp:include>
    <div class="main-content">
        <jsp:include page="Topbar.jsp"></jsp:include>

        <!-- Modal -->
        <div id="uploadModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <div class="container">
                    <h2>Create Report</h2>
                    <form action="mentorreport" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="reportTitle">Report Title:</label>
                            <select id="reportTitle" name="reportTitle" required>
                                <option value="Weekly Report">Weekly Report</option>
                                <option value="Midtern Report">Midterm Report</option>
                                <option value="Final Report">Final Report</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="reportFile">Upload Report File:</label>
                            <input type="file" id="reportFile" name="reportFile" accept=".xlsx, .xls" required>
                        </div>
                        <button type="submit">Upload File</button>
                    </form>
                </div>
            </div>
        </div>

        <button onclick="openModal()">Create Report</button>

        <% 
           String message = (String) request.getAttribute("message");
           if (message != null) {
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
                      
                        <th scope="col">Title</th>
                        <th scope="col">Date</th>
                        <th scope="col">File</th>                       
                        <th scope="col">Mentor</th>
                       
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOfReport}" var="rp">
                        <tr>                         
                            <td>${rp.reportName}</td>                            
                            <td>${rp.reportDate}</td> 
                            <td><a href="downloadFile?reportFile=${rp.filedata}">Download</a></td>                        
                            <td>${rp.mentorId}</td>
                            
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

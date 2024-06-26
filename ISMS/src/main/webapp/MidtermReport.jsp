<%-- 
    Document   : WeeklyReport
    Created on : Jun 21, 2024, 2:22:51 PM
    Author     : duong
--%>
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
        <link href="css/mentorreport.css" rel="stylesheet" type="text/css">      

    </head>
    <body>

        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>

                <div id="uploadModal" class="modal">
                    <div class="modal-content">
                        <span class="close" onclick="closeModal()">&times;</span>
                        <div class="container">
                            <h2>Create Report</h2>
                            <form action="UploadMidterm" method="POST" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label>Report Title:</label>
                                    <select id="reportTitleModal" value="reportTitle" name="reportTitle" required>
                                        <option value="Midterm Report">Midterm Report</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="reportDescription">Description:</label>
                                    <textarea id="reportDescription" name="reportDescription" placeholder="Enter description..." cols="50" rows="5" required></textarea>
                                </div>
                                <a href="DownloadExampleMT">Example File</a>
                            <div class="form-group">
                                <label for="reportFile">Upload Report File:</label>
                                <input type="file" id="reportFile" name="reportFile" accept=".xlsx, .xls" required>
                            </div>
                            <button class="btn btn-primary" type="submit">Upload File</button>
                        </form>
                    </div>
                </div>
            </div>

            <button class="btn btn-primary" onclick="openModal()">Create Report</button>
            
            <div  class="report-options mt-3">
                <h2>Midterm Report List</h2>
                <table id="reportTable" class="table caption-top table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Title</th>                      
                            <th scope="col">Description</th>
                            <th scope="col">Date</th>
                            <th scope="col">File</th>
                            <th scope="col">Mentor ID</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listOfMidtermReport}" var="rpm">
                            <tr>
                                <td>${rpm.reportId}</td>
                                <td>${rpm.reportName}</td>
                                <td>${rpm.reportDescription}</td>
                                <td>${rpm.reportDate}</td>                            
                                <td><a href="downloadFile?reportFile=${rpm.filedata}">Download</a></td>
                                <td>${rpm.mentorId}</td>
                                <td class="action-buttons">
                                    <a href="editmtreport?reportId=${rpm.reportId}" class="btn btn-sm text-primary">
                                        <i class="bi bi-pencil"></i>
                                    </a>
                                    <a href="mtreportdelete?reportId=${rpm.reportId}" class="btn btn-sm text-primary">
                                        <i class="bi bi-trash"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>

    <script src="js/sidebar.js"></script>
    <script src="js/mentorreport.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</html>

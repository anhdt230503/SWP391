<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

                <div class="container mt-4">
                    <form id="reportForm" action="mentorreportlist" method="post">
                        <div class="form-group">
                            <label for="reportTitle">Report Title:</label>
                            <select id="reportTitle" name="reportTitle" class="form-control" required>
                                <option value="Weekly Report" <c:if test="${param.reportTitle == 'Weekly Report'}">selected</c:if>>Weekly Report</option>
                            <option value="Midterm Report" <c:if test="${param.reportTitle == 'Midterm Report'}">selected</c:if>>Midterm Report</option>
                            <option value="Final Report" <c:if test="${param.reportTitle == 'Final Report'}">selected</c:if>>Final Report</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Show Report</button>
                    </form>
                </div>

                <!-- Modal -->
                <div id="uploadModal" class="modal">
                    <div class="modal-content">
                        <span class="close" onclick="closeModal()">&times;</span>
                        <div class="container">
                            <h2>Create Report</h2>
                            <form action="mentorreport" method="POST" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="reportTitleModal">Report Title:</label>
                                    <select id="reportTitleModal" name="reportTitle" required>
                                        <option value="Weekly Report">Weekly Report</option>
                                        <option value="Midterm Report">Midterm Report</option>
                                        <option value="Final Report">Final Report</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="reportDescription">Description:</label>
                                    <textarea id="reportDescription" name="reportDescription" placeholder="Enter description..." cols="50" rows="5" required></textarea>
                                </div>
                                <a href="downloadFile?reportFile=${rp.filedata}">Example File</a>
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
            
            
            <div id="editModal" class="modal">
                <div class="modal-content">
                    <span class="close" onclick="closeModal()">&times;</span>
                    <div class="container">
                        <h2>Edit Weekly Report</h2>
                        <form action="editwlreport" method="post">
                            <input type="hidden" id="reportId" name="reportId" value="${report.reportId}">
                            <div class="form-group">
                                <label for="reportName">Report Name:</label>
                                <input type="text" id="reportName" name="reportName" class="form-control" value="${report.reportName}" required>
                            </div>
                            <div class="form-group">
                                <label for="weekReport">Week Report:</label>
                                <input type="text" id="weekReport" name="weekReport" class="form-control" value="${report.weekReport}" required>
                            </div>
                            <div class="form-group">
                                <label for="reportDescription">Description:</label>
                                <textarea id="reportDescription" name="reportDescription" class="form-control" rows="5" required>${report.reportDescription}</textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </form>
                    </div>
                </div>
            </div>
            <c:if test="${param.reportTitle == 'Weekly Report'}">
                <div id="weeklyReport" class="report-options mt-3">
                    <h2>Weekly Report List</h2>
                    <table id="reportTable" class="table caption-top table-bordered">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Title</th>
                                <th scope="col">Week</th>
                                <th scope="col">Description</th>
                                <th scope="col">Date</th>
                                <th scope="col">File</th>
                                <th scope="col">Intern ID</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listOfWeeklyReport}" var="rp">
                                <tr>
                                    <td>${rp.reportId}</td>
                                    <td>${rp.reportName}</td>
                                    <td>${rp.weekreport}</td>
                                    <td>${rp.reportDescription}</td>
                                    <td>${rp.reportDate}</td>
                                    <td><a href="downloadFile?reportFile=${rp.filedata}">Download</a></td>
                                    <td>${rp.internid}</td>
                                    <td class="action-buttons">
                                        <a href="editwlreport?reportid=${rp.reportId}" class="btn btn-sm text-primary" onclick="openModal()">
                                            <i class="bi bi-pencil"></i>
                                        </a>
                                        <a href="wlreportdelete?reportId=${rp.reportId}" class="btn btn-sm text-primary" onclick="confirmDelete('reportId')" >
                                            <i class="bi bi-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <c:if test="${param.reportTitle == 'Midterm Report'}">
                <div id="midtermReport" class="report-options mt-3">
                    <!-- Display Midterm Report List -->
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
            </c:if>

            <c:if test="${param.reportTitle == 'Final Report'}">
                <div id="finalReport" class="report-options mt-3">
                    <!-- Display Final Report List -->
                    <h2>Final Report List</h2>
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
                            <c:forEach items="${listOfFinalReport}" var="rpf">
                                <tr>
                                    <td>${rpf.reportId}</td>
                                    <td>${rpf.reportName}</td>
                                    <td>${rpf.reportDescription}</td>
                                    <td>${rpf.reportDate}</td>                            
                                    <td><a href="downloadFile?reportFile=${rpf.filedata}">Download</a></td>
                                    <td>${rpf.mentorId}</td>
                                    <td class="action-buttons">
                                        <a href="editfnreport?reportId=${rpf.reportId}" class="btn btn-sm text-primary">
                                            <i class="bi bi-pencil"></i>
                                        </a>
                                        <a href="deletefnreport?reportId=${rpf.reportId}" class="btn btn-sm text-primary">
                                            <i class="bi bi-trash"></i>
                                        </a>

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
        <script type="text/javascript">
            function openEditModal(reportId, reportName, weekReport, reportDescription) {
                document.getElementById("reportId").value = reportId;
                document.getElementById("reportName").value = reportName;
                document.getElementById("weekReport").value = weekReport;
                document.getElementById("reportDescription").value = reportDescription;
                document.getElementById("editModal").style.display = "block";
            }
            function closeModal() {
                document.getElementById("editModal").style.display = "none";
            }
            function confirmDelete(itemId) {
                if (confirm("Bạn chắc chắn muốn xóa mục này?")) {
                } else {

                }
            }
        </script>
        <script src="js/sidebar.js"></script>
        <script src="js/mentorreport.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

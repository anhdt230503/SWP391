<%-- 
    Document   : WeeklyReport
    Created on : Jun 21, 2024, 2:22:51 PM
    Author     : duong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
        .modal-content {
            display: flex;
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
            max-width: 500px;
            border-radius: 10px;
            flex-direction: column;
        }

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
    </style>
</head>
<body>

<jsp:include page="Sidebar.jsp"></jsp:include>
<div class="main-content">
    <jsp:include page="Topbar.jsp"></jsp:include>

    <!-- Upload Modal -->
    <div id="uploadModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <div class="container">
                <h2>Create Report</h2>
                <form action="UploadWeekly" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Report Title:</label>
                        <select id="reportTitleModal" name="reportTitle" class="form-control" required>
                            <option value="Weekly Report">Weekly Report</option>
                            <option value="Midterm Report">Midterm Report</option>
                            <option value="Final Report">Final Report</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Week:</label>
                        <input type="number" min="1" max="16" id="reportWeekModal" class="form-control" name="reportweek" required>
                    </div>
                    <div class="form-group">
                        <label for="reportDescription">Description:</label>
                        <textarea id="reportDescription" name="reportDescription" placeholder="Enter description..." cols="50" rows="5" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="reportFile">Upload Report File:</label>
                        <input type="file" id="reportFile" name="reportFile" accept=".xlsx, .xls" required>
                    </div>
                    <button class="btn btn-primary" type="submit">Upload File</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Edit Modal -->
    <div id="editModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <div class="container">
                <h2>Edit Report</h2>
                <form action="editwlreport" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="editReportId" name="reportId" value="">
                    <div class="form-group">
                        <label>Report Title:</label>
                        <select id="editReportTitle" name="reportTitle" class="form-control" required>
                            <option value="Weekly Report">Weekly Report</option>
                            <option value="Midterm Report">Midterm Report</option>
                            <option value="Final Report">Final Report</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Week:</label>
                        <input type="number" min="1" max="16" id="editReportWeek" class="form-control" name="reportweek" required>
                    </div>
                    <div class="form-group">
                        <label for="editReportDescription">Description:</label>
                        <textarea id="editReportDescription" name="reportDescription" placeholder="Enter description..." cols="50" rows="5" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="editReportFile">Upload Report File:</label>
                        <input type="file" id="editReportFile" name="reportFile" accept=".xlsx, .xls" required>
                    </div>
                    <button class="btn btn-primary" type="submit">Update Report</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Button to open Upload Modal -->
    <button class="btn btn-primary" onclick="openModal('uploadModal')">Create Report</button>

    <!-- Weekly Report List -->
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
                    <td>Week ${rp.weekreport}</td>
                    <td>${rp.reportDescription}</td>
                    <td>${rp.reportDate}</td>
                    <td><a href="downloadFile?reportFile=${rp.filedata}">Download</a></td>
                    <td>${rp.internid}</td>
                    <td class="action-buttons">
                        <a href="#" class="btn btn-sm text-primary edit-btn"
                           data-id="${rp.reportId}"
                           data-title="${rp.reportName}"
                           data-week="${rp.weekreport}"
                           data-description="${rp.reportDescription}">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a href="wlreportdelete?reportId=${rp.reportId}" class="btn btn-sm text-primary" onclick="confirmDelete('reportId')">
                            <i class="bi bi-trash"></i>
                        </a>
                    </td>
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
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const editButtons = document.querySelectorAll('.edit-btn');
        editButtons.forEach(btn => {
            btn.addEventListener('click', function(e) {
                e.preventDefault();
                const reportId = btn.getAttribute('data-id');
                const reportTitle = btn.getAttribute('data-title');
                const reportWeek = btn.getAttribute('data-week');
                const reportDescription = btn.getAttribute('data-description');

                document.getElementById('editReportId').value = reportId;
                document.getElementById('editReportTitle').value = reportTitle;
                document.getElementById('editReportWeek').value = reportWeek;
                document.getElementById('editReportDescription').value = reportDescription;

                openModal('editModal');
            });
        });
    });

    function openModal(modalId) {
        const modal = document.getElementById(modalId);
        if (modal) {
            modal.style.display = 'block';
        }
    }

    function closeModal() {
        const modals = document.querySelectorAll('.modal');
        modals.forEach(modal => modal.style.display = 'none');
    }
</script>
</body>
</html>

<%-- 
    Document   : Home
    Created on : Jun 1, 2024, 3:08:28 PM
    Author     : haidu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Intern Candidate List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="row">
                        <div class="col-8 h3">List of Intern Candidate</div>
                        <div class="col-4">
                            <form action="importIntern" method="post" enctype="multipart/form-data">
                                <div class="input-group">
                                    <input type="file" name="file" accept=".xlsx, .xls" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
                                    <button class="btn btn-outline-secondary" type="submit" id="inputGroupFileAddon04">Upload</button>
                                </div>
                            </form>
                        </div>
<!--                        <p>${errorMessage}</p>
                    <p>${successMessage}</p>-->
                </div> 
                <form action="selectIntern" method="post" onsubmit="return limitSelection();">
                    <table class="table caption-top table-bordered">

                        <thead class="table-light">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Student ID</th>
                                <th scope="col">Email</th>
                                <th scope="col">Full Name</th>
                                <th scope="col">Staff ID</th>
                                <th scope="col">Upload Date</th>
                                <th scope="col">Select</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listOfIntern}" var="o">
                                <tr>
                                    <td>${o.internId}</td>
                                    <td>${o.studentId}</td>
                                    <td>${o.email}</td>
                                    <td>${o.fullName}</td>
                                    <td>${o.staffId}</td>
                                    <td>${o.uploadDate}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${selectedInternId.contains(o.internId)}">
                                                <div class="form-check">
                                                    <input class="form-check-input selected" name="selectedInterns" type="checkbox" value="${o.internId}" id="flexCheckChecked" disabled>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-check">
                                                    <input class="form-check-input" name="selectedInterns" type="checkbox" value="${o.internId}" id="flexCheckChecked" onclick="checkSelectionCount();">
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                        
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <input type="submit" value="Select Interns">
                </form>
            </div>
        </div>
        <script src="js/sidebar.js"></script>
        <script src="js/selectIntern.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>


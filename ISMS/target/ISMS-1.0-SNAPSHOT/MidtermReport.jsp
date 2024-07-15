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
                <div  class="report-options mt-3">
                <% if (request.getAttribute("errorMessage") != null) { %>
                <p style="color: red;text-align: center;font-size: 20px;"><%= request.getAttribute("errorMessage") %></p>
                <p><a class="btn btn-primary" href="<%= request.getContextPath() %>/FinalReportList">Quay lại</a></p>
                <% } 
                %>
                <h2>Midterm Report</h2>
                <form style="" action="ExportExcel" method="get">
                    <input type="hidden" name="mentorId" value="${param.mentorId}" />
                    <input type="submit" value="Export to Excel" />
                </form>
                <table id="reportTable" class="table caption-top table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th scope="col">Intern ID</th>
                            <th scope="col">Student ID</th>
                            <th scope="col">Staff ID</th>
                            <th scope="col">Name</th> 
                            <th scope="col">Status Mission</th>
                            <th scope="col">Hours For Work</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listOfFinalReport}" var="rp" varStatus="loop1" >
                            <tr>
                                <td>${rp.internId}</td>
                                <td>${rp.studentId}</td>
                                <td>${rp.staffId}</td>
                                <td>${rp.fullName}</td>
                                <td>${totalfinished[loop1.index]}/${totalmission[loop1.index]}</td>
                                <td></td>
                                <td>
                                    <form action="SubmitReport" method="get">
                                        <input type="hidden" name="internId" value="${rp.internId}" />
                                        <button type="submit" class="btn btn-primary">Report</button>
                                    </form>                   </td>
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

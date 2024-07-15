<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student Report</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .container {
                max-width: 800px;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                margin: 20px auto;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            .text-center {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>

            <div class="main-content">
                <button onclick="window.location.href = 'FinalReportList';">Back</button>
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="container mt-3">
                    <h2 class="text-center">Student Report</h2>
                    <form action="UpdateFinalReport" method="post">
                        <input type="hidden" id="internId" name="internId" value="${student.internId}">
                    <div class="info-box">
                        <table>
                            <thead>
                                <tr>
                                    <th>Student ID:</th>
                                    <td>${student.studentId}</td>
                                </tr>
                                <tr>
                                    <th>Name:</th>
                                    <td>${student.fullName}</td>
                                </tr>
                            </thead>

                            <tbody>
                                <tr>
                                    <th>Skills:</th>
                                    <td>
                                        <c:forEach items="${reports}" var="report">
                                            <input placeholder="${report.skills_score}" type="number" min="0" max="10" step="0.1" name="skills" required/>
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Soft Skills:</th>
                                    <td>
                                        <c:forEach items="${reports}" var="report">
                                            <input placeholder="${report.soft_score}" type="number" min="0" max="10" step="0.1"  name="softSkills" required/>
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Attitude:</th>
                                    <td>
                                        <c:forEach items="${reports}" var="report">
                                            <input placeholder=" ${report.attitue_score}" type="number" min="0" max="10"step="0.1"  name="attitude" required/>
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Final Score:</th>
                                    <td>
                                        <c:forEach items="${reports}" var="report">
                                            ${report.final_score}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Update Report</button>
                </form>

            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

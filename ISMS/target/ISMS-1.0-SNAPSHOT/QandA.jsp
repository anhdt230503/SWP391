<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mission List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <style>
        .center-button {
            text-align: center;
            margin-bottom: 20px;
        }
        .small-round-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 12px;
            border-radius: 50%;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .small-round-button:hover {
            background-color: #45a049;
        }
    </style>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="row">
                        <h1 style="text-align: center;">List of Questions</h1>
                    <c:if test="${sessionScope.acc.roleId == 4}">
                        <div class="center-button">
                            <a href="AddQuestionServlet"><button class="btn btn-primary">Add Question</button></a>
                        </div>
                    </c:if>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" id="errorAlert">${errorMessage}</div>
                    <script>
                        // Automatically hide the error message after 3 seconds
                        setTimeout(function () {
                            document.getElementById('errorAlert').style.display = 'none';
                        }, 3000);
                    </script>
                </c:if>


                <c:if test="${sessionScope.acc.roleId == 3}">
                    <table class="table caption-top table-bordered">
                        <thead class="table-light">
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th> Intern Question</th>
                                <th>Create</th>
                                <th>Your Answer</th>
                                <th>Intern Name</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="qa" items="${qandas}">
                                <tr>
                                    <td>${qa.qandaId}</td>
                                    <td>${qa.questionTitle}</td>
                                    <td>${qa.questionText}</td>
                                    <td>${qa.createdAt}</td>
                                    <td>${qa.answerText}</td>
                                    <td>${qa.internFullName}</td>
                                    <td>${qa.questionStatus}</td>
                                    <td>
                                        <a href="SendAnswer?qandaId=${qa.qandaId}">Send Answer</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </c:if>
                    <c:if test="${sessionScope.acc.roleId == 4}">
                        <table class="table caption-top table-bordered">
                            <thead class="table-light">
                                <tr>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>Your Question</th>
                                    <th>Time Receive</th>
                                    <th>Mentor Answer</th>
                                    <th>Mentor Name</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <c:forEach var="qa" items="${qandas}">
                                <tr>
                                    <td>${qa.qandaId}</td>
                                    <td>${qa.questionTitle}</td>
                                    <td>${qa.questionText}</td>
                                    <td>${qa.submittedAt}</td>
                                    <td>${qa.answerText}</td>
                                    <td>${qa.mentorFullName}</td>
                                    <td>${qa.questionStatus}</td>
                                    <td>
                                        <a href="UpdateQuestionServlet?qandaId=${qa.qandaId}" class="btn btn-sm text-primary">
                                            <i class="bi bi-pencil"></i>
                                        </a>
                                        <a href="DeleteQuestion?qandaId=${qa.qandaId}" class="btn btn-sm text-primary" onclick="return confirm('Are you sure you want to delete this question?');">
                                            <i class="bi bi-trash"></i>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>


            </div>
        </div>
        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

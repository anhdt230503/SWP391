<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FeedBack</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <style>
        .center-button {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="row">
                        <h1 style="text-align: center;">Feedback about Mentor</h1>
                    </div>
                <c:if test="${sessionScope.acc.roleId == 4}">
                    <div class="center-button">
                        <a href="SubmitFeedback"><button class="btn btn-primary">Submit Feedback</button></a>
                    </div>
                </c:if>
                    <form>
                        <table class="table caption-top table-bordered">
                            <thead class="table-light">
                                <tr>
                                    <th>ID</th>
                                    <th>Punctuality</th>
                                    <th>Coverage</th>
                                    <th>Response</th>
                                    <th>Teaching Skills</th>
                                    <th>Support</th>
                                    <th>Feedback Massage</th>
                                    <th>Submission Date</th>
                                    <th>Class</th>
                                    <th>Mentor Name</th>
                                    <th>Intern Name</th>
                                    <c:if test="${sessionScope.acc.roleId == 4}">
                                    <th>Edit</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${sessionScope.acc.roleId == 2}">
                                <c:forEach var="feedback" items="${feedbacks}">
                                    <tr>
                                        <td>${feedback.feedbackId}</a></td>
                                        <td>${feedback.punctuality}</td>
                                        <td>${feedback.coverage}</td>
                                        <td>${feedback.response}</td>
                                        <td>${feedback.teachingSkills}</td>
                                        <td>${feedback.support}</td>
                                        <td>${feedback.feedbackText}</td>
                                        <td>${feedback.submissionDate}</td>
                                        <td>${feedback.labroomName}</td>
                                        <td>${feedback.mentorFullName}</td>
                                        <td>${feedback.internFullName}</td>
                                    </tr>
                                </c:forEach>
                            </c:if> 
                            <c:if test="${sessionScope.acc.roleId == 4}">
                                <tr>
                                    <td><a href="ViewFeedbackServlet?feedbackId=${feedback.feedbackId}">${feedback.feedbackId}</a></td>
                                    <td>${feedback.punctuality}</td>
                                    <td>${feedback.coverage}</td>
                                    <td>${feedback.response}</td>
                                    <td>${feedback.teachingSkills}</td>
                                    <td>${feedback.support}</td>
                                    <td>${feedback.feedbackText}</td>
                                    <td>${feedback.submissionDate}</td>
                                    <td>${feedback.labroomName}</td>
                                    <td>${feedback.mentorFullName}</td>
                                    <td>${feedback.internFullName}</td>
                                    <td><a href="EditFeedbackServlet?feedbackId=${feedback.feedbackId}" class="btn btn-sm text-primary">
                                            <i class="bi bi-pencil"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:if> 
                        </tbody>
                    </table>
                </form>
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
                </body>
                </html>

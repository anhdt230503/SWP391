<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                font-family: Arial, Helvetica, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .container {
                max-width: 800px;
                margin: 40px auto;
                padding: 20px;
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                margin-bottom: 20px;
                font-size: 24px;
                font-weight: bold;
            }

            .content {
                padding: 20px;
                border-bottom: 1px solid #ddd;
            }

            .content p {
                margin-bottom: 20px;
            }

            .content a {
                text-decoration: none;
                color: #337ab7;
            }

            .content a:hover {
                color: #23527c;
            }

            .additional-files {
                margin-top: 20px;
                padding: 20px;
                border-bottom: 1px solid #ddd;
            }

            .additional-files h2 {
                margin-top: 0;
            }

            .blue-button {
                display: block;
                width: 100%;
                padding: 10px 20px;
                margin: 20px 0;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-align: center;
                text-decoration: none;
                font-size: 16px;
            }

            .blue-button:hover {
                background-color: #0056b3;
            }

            .due-date {
                margin-top: 20px;
                padding: 20px;
                border-bottom: 1px solid #ddd;
            }

            .due-date h2 {
                margin-top: 0;
            }

            .submission-details {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }

            .submission-detail {
                flex-basis: 30%;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 5px;
                margin: 0 10px;
            }

            .submission-detail h2 {
                margin-top: 0;
            }

            .submission-detail p {
                margin-bottom: 0;
            }
        </style>
        <script>
            function showAlert() {
                alert("${errorMessage}");
            }
        </script>
    </head>
    <body>
    <from>
        <div class="container">
            <c:if test="${not empty errorMessage}">
                <script>showAlert();</script>
            </c:if>

            <h1>Mission Detail</h1>
            <div class="content">
                <p>Create By Mentor : ${mission.mentorFullName}</p>
                <p>Description Mission: ${mission.misDescription}</p>
                <p><a href="DownFileMisson?link=${mission.link}">${mission.link}</a></p>
            </div>
            <div class="due-date">
                <%-- Check if updateTime is not null --%>
                <%-- Kiểm tra nếu updateTime không null --%>
                <c:if test="${not empty mission.updateTime}">
                    <h4>Updated: ${mission.updateTime}</h4> <!-- updated -->
                </c:if>


                <h4>CREATED : ${mission.created_at}</h4> <!-- updated -->
                <h4>START DATE : ${mission.startDate}</h4>
                <h4>DUE DATE   : ${mission.deadline}</h4>
            </div>

            <div class="submission-details">
                <div class="submission-detail">
                    <h2>SUBMISSION STATUS</h2>
                    <p>${mission.misStatus}</p>
                </div>
                <div class="submission-detail">
                    <h2>SUBMISSION TIME</h2>
                    <p>${mission.submitTime}</p>
                </div>
                <div class="submission-detail">
                    <h2>LINK/FILE ASSIGNMENT</h2>
                    <p><a href="DownFileMisson1?file_path=${mission.file_path}">${mission.file_path}</a></p>
                </div>
            </div>

            <button class="blue-button" onclick="window.location.href = 'SubmitMission?misId=${mission.misId}'">Submit Mission</button>
            <button class="blue-button" onclick="window.location.href = 'mission'">Back</button>
        </div>
    </from>
</body>
</html>

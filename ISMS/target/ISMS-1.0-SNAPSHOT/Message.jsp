<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Message Chat</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            height: 100vh;
        }

        .main-content {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
        }

        .chat-container {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            flex-grow: 1;
        }

        .chat-header {
            background-color: #007bff;
            color: white;
            padding: 10px;
            text-align: center;
            font-size: 1.2em;
            font-weight: bold;
        }

        .chat-messages {
            padding: 10px;
            height: 100%;
            overflow-y: auto;
            background-color: #e5ddd5;
            flex-grow: 1;
        }

        .message {
            display: inline-block;
            max-width: 80%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 15px;
            position: relative;
            clear: both;
        }

        .message.sent {
            background-color: #dcf8c6;
            float: right;
        }

        .message.received {
            background-color: #ffffff;
            float: left;
        }

        .message p {
            margin: 0;
        }

        .timestamp {
            font-size: 0.8em;
            color: #888;
            display: block;
            margin-top: 5px;
            text-align: right;
        }

        .chat-input {
            display: flex;
            padding: 10px;
            border-top: 1px solid #ccc;
            background-color: #f7f7f7;
        }

        .chat-input input {
            flex: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 20px;
            font-size: 1em;
            margin-right: 10px;
        }

        .chat-input button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 20px;
            font-size: 1em;
            cursor: pointer;
        }

        .chat-input button:hover {
            background-color: #0056b3;
        }

        /* Nút phản hồi */
        .message:hover .action-buttons {
            display: flex;
        }

        .action-buttons {
            display: none;
            position: absolute;
            top: 50%;
            right: -80px;
            transform: translateY(-50%);
            gap: 5px;
        }

        .action-buttons button {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 50%;
            width: 30px;
            height: 30px;
            font-size: 1.2em;
            cursor: pointer;
        }

        .action-buttons button:hover {
            background-color: #0056b3;
        }

    </style>

    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <h1 style="text-align: center;">List of Questions</h1>
                <div class="chat-container">
                    <div class="chat-messages">
                    <c:forEach var="qa" items="${qandas}">
                        <div class="message sent">
                            <p>${qa.questionText}</p>
                            <span class="timestamp">${qa.createdAt}</span>
                            <div class="action-buttons">

                                <button onclick="replyMessage(${qa.qandaId})"><i class="bi bi-reply"></i></button>
                                <button onclick="deleteMessage(${qa.qandaId})"><i class="bi bi-three-dots"></i></button>
                            </div>
                        </div>
                        <div class="message received">
                            <p>${qa.answerText}</p>
                            <span class="timestamp">${qa.submittedAt}</span>
                            <div class="action-buttons">
                                <c:if test="${sessionScope.acc.roleId == 3}">
                                <a href="SendAnswer?qandaId=${qa.qandaId}">Send Answer</a>
                                </c:if>
                                <button onclick="deleteMessage(${qa.qandaId})"><i class="bi bi-three-dots"></i></button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${sessionScope.acc.roleId == 4}">

                    <form action="AddQuestionServlet" method="post">
                        <div class="chat-input">
                            <input type="text" placeholder="Type a message..." id="question_text" name="question_text" required>
                            <button type="submit">Send</button>
                        </div>
                    </form>
                </c:if>

                <c:if test="${sessionScope.acc.roleId == 3}">
                    <form action="SendAnswer" method="post">
                        <div class="chat-input">
                            <input type="text" placeholder="Type a message..." id="answer_text" name="answer_text" required>
                            <button type="submit">Send</button>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

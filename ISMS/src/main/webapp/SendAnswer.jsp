<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Add Mission</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            h2 {
                padding-top: 50px;
                text-align: center;
            }
            form {
                width: 50%;
                margin: 0 auto;
            }
            label {
                display: block;
                margin-top: 10px;
            }
            input[type="text"],
            textarea,
            select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                float: right;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            .error {
                color: red;
                text-align: center;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Send Answer</h2>
            <form action="SendAnswer" method="post">

                <input type="hidden" name="qandaId" value="${qanda.qandaId}">

                <div class="mb-3">
                    <label for="title" class="form-label">Title :${qanda.questionTitle}</label>
                </div>
                <div class="mb-3">
                    <label for="question_text" class="form-label">Question : ${qanda.questionText}</label>
                </div>
                <div class="mb-3">
                    <label for="question_text" class="form-label">Your Answer</label>
                    <textarea class="form-control" id="answer_text" name="answer_text" rows="4" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Send Answer</button>
            </form>
        </div>
    </body>
</html>

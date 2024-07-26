<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List of Notes</title>
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
            background-color: #4CAF50; /* Button background color */
            color: white; /* Button text color */
            border: none; /* Remove default border */
            padding: 10px; /* Reduce inner padding for smaller button */
            text-align: center; /* Center the text */
            text-decoration: none; /* Remove underline */
            display: inline-block; /* Display as inline-block */
            font-size: 12px; /* Reduce font size */
            border-radius: 50%; /* Round the corners for circle shape */
            cursor: pointer; /* Change cursor on hover */
            transition: background-color 0.3s; /* Smooth transition for background color */
        }

        .small-round-button:hover {
            background-color: #45a049; /* Background color on hover */
        }
    </style>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="row">
                        <h1 style="text-align: center;">List of Notes</h1>
                    </div>
                    <div class="center-button">
                        <a href="AddNote"><button class="btn btn-primary">Add Note</button></a>
                    </div>

                    <table class="table caption-top table-bordered">
                        <thead class="table-light">
                            <tr>
                                <th>Note ID</th>
                                <th>Note Title</th>
                                <th>Note Content</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="note" items="${notes}">
                            <tr>
                                <td>${note.noteId}</td>
                                <td>${note.noteTitle}</td>
                                <td>${note.noteContent}</td>
                                <td>
                                    <a href="UpdateNote?noteId=${note.noteId}" class="btn btn-sm text-primary">
                                        <i class="bi bi-pencil"></i>
                                    </a>
                                    <a href="DeleteNote?noteId=${note.noteId}" class="btn btn-sm text-primary" onclick="return confirm('Are you sure you want to delete this note?');">
                                        <i class="bi bi-trash"></i>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

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
        <style>
            .container {
                margin: 50px auto;

            }

            .table {
                width: 100%;
                margin: 0 auto;
            }

            .table th, .table td {
                text-align: center;
                vertical-align: middle;
            }

            .btn-container {
                text-align: right;
                margin-top: 10px;
            }

            button:hover {
                background-color: #0056b3;
            }

            /* Modal styles */
            .modal {

            }

            .modal-content {
                display: flex;
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 50%; /* Adjust the width as needed */
                max-width: 500px; /* Max width to prevent the modal from being too wide */
                border-radius: 10px;
            }

            /* Close button */
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }


            /* Close button */
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

            .table td:nth-child(6) {
                word-wrap: break-word !important;
            }

        </style>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="container">
                    <table class="table table-secondary">
                        <thead>
                            <tr>
                                <th scope="col">Intern Name</th>
                                <th scope="col">Certificate Link</th>
                                <th scope="col">Date Of Issue</th>
                                <c:if test="${sessionScope.acc.roleId == 2}">
                                <th scope="col">Action</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="o">
                            <tr class="table-light">
                                <td>${o.internName}</td>
                                <td>
                                    <a href="downloadCertificate?id=${o.id}">Download Certificate</a> 
                                </td>
                                <td>${o.dateOfIssue}</td>
                                <c:if test="${sessionScope.acc.roleId == 2}">
                                    <td>
                                        <a href="deleteCertificate?id=${o.id}" class="btn btn-sm text-primary">
                                            <i class="bi bi bi-trash"></i>
                                        </a>
                                    </td>
                                </c:if>
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

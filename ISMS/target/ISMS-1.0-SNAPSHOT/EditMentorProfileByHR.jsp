<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .card {
                border-radius: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .card-header {
                background-color: #007bff;
                color: white;
                border-radius: 15px 15px 0 0;
                text-align: center;
            }
            .form-control {
                margin-bottom: 15px;
            }
            .btn-back {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="mb-4">User Profile</h1>
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <h2>Mentor Information</h2>
                        </div>
                        
                            <form action="EditMentorProfileByHR" method="POST">
                                <input type="hidden" class="form-control"name="mentorId" value="${mentor.mentorId}" required>

                                <div class="card-body">
                                    <c:if test="${not empty errorMessage}">
                                        <div class="alert alert-danger">
                                            ${errorMessage}
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty successMessage}">
                                        <div class="alert alert-success">
                                            ${successMessage}
                                        </div>
                                    </c:if>

                                    
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">
                                                <strong>Name:</strong> 
                                                <input type="text" name="fullname" value="${mentor.fullname}" class="form-control">
                                            </li>
                                            <li class="list-group-item">
                                                <strong>Email:</strong>
                                                <input type="email" name="email" value="${mentor.email}" class="form-control" >
                                            </li>
                                            <li class="list-group-item">
                                                <strong>Phone Number:</strong> 
                                                <input type="text" name="phoneNumber" value="${mentor.phoneNumber}" class="form-control">
                                            </li>
                                            <li class="list-group-item">
                                                <strong>BirthDate:</strong> 
                                                <input type="date" name="birthDate" value="${mentor.birthDate}" class="form-control">
                                            </li>
                                            <li class="list-group-item text-center">
                                                <input class="btn btn-primary" type="submit" name="submit" value="Submit">
                                            </li>
                                        </ul>
                                    
                                </div>
                            </form>
                        
 
                        <div class="card-footer text-center">
                            <button class="btn btn-secondary btn-back" onclick="window.location.href = 'Home.jsp';">Back Home</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>

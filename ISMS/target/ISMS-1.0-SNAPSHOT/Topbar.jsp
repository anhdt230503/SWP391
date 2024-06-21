<%-- 
    Document   : Avatar
    Created on : Jun 1, 2024, 11:27:09 PM
    Author     : haidu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<link href="css/loginButton.css" rel="stylesheet" type="text/css"/>
<script src="js/sidebar.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>



<nav class="navbar navbar-light bg-light">
    <div class="container-fluid p-md-3 d-flex justify-content-end">
        <div class="user-profile">
            <div class="dropdown">
                <a href="#" class="d-flex align-items-center link-body-emphasis text-decoration-none dropdown-toggle text-dark" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="${avatar}" alt="" width="32" height="32" class="rounded-circle me-2">
                    <strong>${name}</strong>
                </a>
                <ul class="dropdown-menu text-small shadow">
                    <li><a class="dropdown-item" href="#">Profile</a></li>
                    <li><a class="dropdown-item" href="logout">Sign out</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>

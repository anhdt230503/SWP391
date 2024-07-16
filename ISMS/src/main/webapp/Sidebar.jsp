<%-- 
    Document   : Sidebar
    Created on : Jun 1, 2024, 11:15:41 PM
    Author     : haidu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<script src="js/sidebar.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="sidebar">
    <div class="sidebar-header">
        <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-decoration-none">
            <svg class="bi pe-none me-2" width="40" height="32"></svg>
            <span class="fs-4"><i class="bi bi-clipboard-data-fill"></i> DX Lab</span>
        </a>
        <hr>
    </div>
    <ul class="nav flex-column mb-auto mt-3">
        <li>
            <a href="#" class="nav-link mb-2">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                <i class="bi bi-speedometer2 me-2"></i> Dashboard
            </a>
        </li>
        <li class="nav-item">
            <a href="#" class="nav-link mb-2" aria-current="page">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                <i class="bi bi-house me-2"></i> Home
            </a>
        </li>

        <li>
            <a href="MentorManageController" class="nav-link mb-2">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                <i class="bi bi-file-person me-2"></i> Manage Mentor
            </a>
        </li>

        <li>
            <a href="ManagerManageController" class="nav-link mb-2">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                <i class="bi bi-file-person me-2"></i> Manage Manager
            </a>
        </li>
        <li>
            <a href="internList" class="nav-link mb-2">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                <i class="bi bi-file-person me-2"></i> Manage Intern
            </a>
        </li>
        <li>
            <a href="mission" class="nav-link mb-2">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                <i class="bi bi-bullseye me-2"></i> Mission
            </a>
        </li>
        <li>
            <a href="ListLabRoomsServlet" class="nav-link mb-2">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                <i class="bi bi-door-open-fill me-2"></i> Lab Room
            </a>
        </li>
        <li>
            <a href="mentorreportlist" class="nav-link mb-2">
                <svg class="bi pe-none me-2" width="16" height="16"></svg>
                <i class="bi bi-door-open-fill me-2"></i> Mentor Report
            </a>
        </li>
        <c:if test="${sessionScope.acc.roleId == 4}">

            <li>
                <a href="note" class="nav-link mb-2">
                    <svg class="bi pe-none me-2" width="16" height="16"></svg>
                    <i class="bi bi-door-open-fill me-2"></i> Your Note
                </a>

            </li>
        </c:if>
        <c:if test="${sessionScope.acc.roleId == 3 || sessionScope.acc.roleId == 4}">

            <li>
                <a href="QandAServlet" class="nav-link mb-2">
                    <svg class="bi pe-none me-2" width="16" height="16"></svg>
                    <i class="bi bi-door-open-fill me-2"></i> Q&A
                </a>

            </li>
        </c:if>
        <c:if test="${sessionScope.acc.roleId == 2 || sessionScope.acc.roleId == 4}">
            <li>
                <a href="Feedback" class="nav-link mb-2">
                    <svg class="bi pe-none me-2" width="16" height="16"></svg>
                    <i class="bi bi-door-open-fill me-2"></i> FeedBack
                </a>
            </li>
        </c:if>
    </ul>
</div>


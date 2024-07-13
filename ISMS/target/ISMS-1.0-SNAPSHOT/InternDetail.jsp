<%-- 
    Document   : InternDetail.jsp
    Created on : Jul 13, 2024, 7:52:10 PM
    Author     : haidu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="/docs/5.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="modal modal-sheet position-static d-block bg-body-secondary p-4 py-md-5" tabindex="-1" role="dialog" id="modalSignin">
            <div class="modal-dialog" role="document">
                <div class="modal-content rounded-4 shadow">
                    <div class="modal-header p-5 pb-4 border-bottom-0">
                        <h1 class="fw-bold mb-0 fs-2">Intern Detail</h1>
                    </div>
                    <div class="modal-body p-5 pt-0">
                        <form action="editInternDetail" method="POST">
                            <div class="mb-3">
                                <button type="submit" class="btn btn-secondary">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"></path>
                                    </svg>
                                </button>
                            </div>
                            <div class="row">
                                <input type="hidden" name="internId" value="${intern.internId}"> 
                                <div class="col-4 mb-3">
                                    <label for="studentId">Student ID</label>
                                    <input type="text" name="studentId" class="form-control rounded-3" id="studentId" value="${intern.studentId}" disabled>
                                </div>
                                <div class="col-8 mb-3">
                                    <label for="email">Email</label>
                                    <input type="email" name="email" class="form-control rounded-3" id="email" value="${intern.email}" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <label for="fullName">Full Name</label>
                                    <input type="text" name="fullName" class="form-control rounded-3" id="fullName" value="${intern.fullName}" disabled>
                                </div>
                                <div class="col-6 mb-3">
                                    <label for="phoneNumber">Phone Number</label>
                                    <input type="tel" name="phoneNumber" class="form-control rounded-3" id="phoneNumber" value="${intern.phoneNumber}" disabled>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="major">Major</label>
                                <input type="text" name="major" class="form-control rounded-3" id="major" value="${intern.major}" disabled>
                            </div>
                            <div class="mb-3">
                                <label for="company">Company</label>
                                <input type="text" name="company" class="form-control rounded-3" id="company" value="${intern.company}" disabled>
                            </div>
                            <div class="mb-3">
                                <label for="jobTitle">Job Title</label>
                                <input type="text" name="jobTitle" class="form-control rounded-3" id="jobTitle" value="${intern.jobTitle}" disabled>
                            </div>
                            <div class="mb-3">
                                <label for="linkCV">Link CV</label>
                                <input type="text" name="linkCV" class="form-control rounded-3" id="linkCV" value="${intern.linkCv}" disabled>
                            </div>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <label for="staffId">Staff ID</label>
                                    <input type="text" name="staffId" class="form-control rounded-3" id="staffId" value="${intern.staffId}" disabled>
                                </div>
                                <div class="col-6 mb-3">
                                    <label for="status">Status</label>
                                    <input type="text" name="status" class="form-control rounded-3" id="status" value="${intern.status}" disabled>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <label for="midtermWorkTime">Midterm Work Time</label>
                                    <input type="text" name="midtermWorkTime" class="form-control rounded-3" id="midtermWorkTime" value="${intern.midtermWorkTime} / 280h" disabled>
                                </div>
                                <div class="col-6 mb-3">
                                    <label for="finalWorkTime">Final Work Time</label>
                                    <input type="text" name="finalWorkTime" class="form-control rounded-3" id="finalWorkTime" value="${intern.finalWorkTime} / 560h" disabled>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="uploadDate">Upload Date</label>
                                <input type="text" name="uploadDate" class="form-control rounded-3" id="uploadDate" value="${intern.uploadDate}" disabled>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

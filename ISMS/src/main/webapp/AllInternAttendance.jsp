<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Intern Candidate List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="Sidebar.jsp"></jsp:include>
            <div class="main-content">
            <jsp:include page="Topbar.jsp"></jsp:include>
                <div class="table-container">
                    <div class="row">
                        <div class="col-8 h3">List of Intern</div>
                    </div>
                <c:if test="${sessionScope.acc.roleId == 1}">
                    <div class="col-4">
                        <form action="attendanceEndOfDay" method="POST">
                            <button type="button" class="btn btn-primary" onclick="confirmUpdate(this)">Update status and calculate working time</button>
                        </form>
                    </div>
                    <div class="row">
                        <div class="col-6 mb-3">
                            <form action="testMode" method="POST">
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" name="testMode" value="on"> 
                                    <label class="form-check-label" for="flexSwitchCheckDefault">Test Mode</label>
                                </div>
                                <input type="hidden" name="testMode" value="off">
                                <button type="submit" class="btn btn-primary">Submit</button> 
                            </form>
                        </div>
                        <div class="mb-3">
                            <form action="resetData" method="POST">
                                <button type="submit" class="btn btn-warning">Reset Data</button> 
                            </form>
                        </div>
                        <c:choose>
                            <c:when test="${testMode == 'on'}">
                                <div class="col-6">
                                    <form action="testModeInfo" method="POST">
                                        <div class="mb-2">
                                            <label for="dateToCheck">Select date</label>
                                            <input type="date" id="dateToCheck" name="dateToCheck">
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </form>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </c:if>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${internList}" var="o">
                            <tr>
                                <td><a href="internAttendanceHistory?internId=${o.internId}" class="text-decoration-none">${o.fullName}</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            function confirmUpdate(button) {
                Swal.fire({
                    title: 'Confirmation',
                    text: 'Are you sure you want to update all attendance report for today?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes',
                    cancelButtonText: 'Cancel'
                }).then((result) => {
                    if (result.isConfirmed) {
                        const form = button.closest('form');
                        form.submit();
                    }
                });
            }
        </script>
        <script src="js/sidebar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
    </body>
</html>

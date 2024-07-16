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
                        <div class="col-8 h3">View Attendance</div>
                    </div> 
                <c:if test="${sessionScope.acc.roleId == 4}">
                    <div class="row">
                        <div class="col-4">
                            <form action="checkInTime" method="POST">
                                <button type="submit" class="btn btn-primary">Check Attendance</button>
                            </form>
                        </div>
                        <c:if test="${sessionScope.acc.roleId == 4 || sessionScope.acc.roleId == 3}">
                            <div class="col-4 p-3">
                                <span class="badge fs-6 bg-dark-subtle border border-dark-subtle text-dark-emphasis rounded-pill">Midterm Total Work Time: ${midtermWorkTime}h / 280h</span>
                            </div>
                            <div class="col-4 p-3">
                                <span class="badge fs-6 bg-dark-subtle border border-dark-subtle text-dark-emphasis rounded-pill">Final Total Work Time: ${finalWorkTime}h / 560h</span>
                            </div>
                        </c:if>
                    </div>
                    <div class="mt-3 text-danger">
                        <p>${message}</p>  
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <form action="testMode" method="POST">
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" name="testMode" value="on"> 
                                    <label class="form-check-label" for="flexSwitchCheckDefault">Test Mode</label>
                                </div>
                                <input type="hidden" name="testMode" value="off">
                                <button type="submit" class="btn btn-primary">Submit</button> 
                            </form>
                        </div>
                        <c:choose>
                            <c:when test="${testMode == 'on'}">
                                <div class="col-6">
                                    <form action="testModeInfo" method="POST">
                                        <div class="mb-2">
                                            <select name="date" class="form-select form-select-sm" aria-label="Small select example">
                                                <option selected>Select date to check attendance</option>
                                                <c:forEach items="${listOfAttendance}" var="a">
                                                    <option value="${a.attendanceId}">${a.attendDate}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div>
                                            <label for="checkoutTime">Check Out Time:</label>
                                            <input type="time" id="checkoutTime" name="checkoutTime">
                                        </div>
                                        <button type="submit" class="btn btn-primary">Submit</button> 
                                    </form>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </c:if>

                <table class="table caption-top table-bordered mt-3">
                    <thead class="table-light">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Date</th>
                            <th scope="col">Check In Time</th>
                            <th scope="col">Check Out Time</th>
                            <th scope="col">Total Work Time</th>
                            <th scope="col">Status</th>
                                <c:if test="${sessionScope.acc.roleId == 3 || sessionScope.acc.roleId == 1}">
                                <th scope="col">Action</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="count" value="1" />
                        <c:forEach items="${listOfAttendance}" var="o">
                            <tr>
                                <td>${count}</td>
                                <c:set var="count" value="${count + 1}" />
                                <td>${o.attendDate}</td>
                                <td><span class="badge fs-6 bg-primary-subtle border border-primary-subtle text-primary-emphasis rounded-pill">${o.checkInTime}</span></td>
                                <td><span class="badge fs-6 bg-primary-subtle border border-primary-subtle text-primary-emphasis rounded-pill">${o.checkOutTime}</span></td>
                                <td>${o.totalWorkTime}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${o.status == 'PRESENT'}">
                                            <span class="badge fs-6 text-bg-success rounded-pill">Present</span>
                                        </c:when>
                                        <c:when test="${o.status == 'ABSENT'}">
                                            <span class="badge fs-6 text-bg-danger rounded-pill">Absent</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge fs-6 text-bg-secondary rounded-pill">Not yet</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <c:if test="${sessionScope.acc.roleId == 3 || sessionScope.acc.roleId == 1}">
                                    <td>
                                        <a href="loadAttendance?attendanceId=${o.attendanceId}" class="btn btn-sm text-warning">
                                            <i class="bi bi-pencil"></i>
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

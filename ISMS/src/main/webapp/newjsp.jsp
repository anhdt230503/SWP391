<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Intern Selection</title>
    </head>
    <body>
        <h1>Select Interns</h1>
        <form action="SelectInternServlet" method="post">
            <table border="1">
                <tr>
                    <th>Select</th>
                    <th>Intern ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                </tr>
                <c:forEach var="intern" items="${interns}">
                    <tr>
                        <td><input type="checkbox" name="selectedInterns" value="${intern.intern_id}"></td>
                        <td>${intern.intern_id}</td>
                        <td>${intern.full_name}</td>
                        <td>${intern.email}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Select Interns">
        </form>
    </body>
</html>
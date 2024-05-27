<%-- 
    Document   : internList
    Created on : May 27, 2024, 1:04:21 PM
    Author     : haidu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        HELLO 
        <form action="importIntern" method="post" enctype="multipart/form-data">
            <input type="file" name="file" accept=".xlsx, .xls" />
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>

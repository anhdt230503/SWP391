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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>

        <div class="container">
            <div class="row">
                <div class="col-8 h3">Intern Candidate</div>
                <div class="col-4">
                    <form action="importIntern" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" accept=".xlsx, .xls" />
                        <input type="submit" value="Upload" />
<!--                        <div class="input-group">
                            <input type="file" name="file" accept=".xlsx, .xls" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
                            <button class="btn btn-outline-secondary" type="submit" id="inputGroupFileAddon04">Upload</button>
                        </div>-->
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>

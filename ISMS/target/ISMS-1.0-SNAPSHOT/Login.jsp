<%-- 
    Document   : Login
    Created on : May 28, 2024, 12:33:27 PM
    Author     : haidu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="d-flex align-items-center py-4 bg-body-tertiary" data-new-gr-c-s-check-loaded="14.1174.0" data-gr-ext-installed="">

        <main class="form-signin w-100 m-auto">
            <form action="login" method="post">
                <img class="mb-4" src="images/login.png" alt="" width="90" height="90">
                <h1 class="h4 mb-3 fw-normal">Enter your email address</h1>

                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">@fpt.edu.vn</label>
                </div>

                <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
            </form>
        </main>
        
        <script src="js/login.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>


</html>

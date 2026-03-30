<%-- 
    Document   : register
    Created on : 23-Feb-2026, 7:47:44 pm
    Author     : krishnaiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="register" method="post">
            Username:<input type="text" name="username"><br><!-- comment -->
            Login ID:<input type="text" name="login_id"><br><!-- comment -->
            Password:<input type="password" name="password"><br><!-- comment -->
            Email:<input type="email" name="email"><br><!-- comment -->
            Phone:<input type="text" name="phone"><br><!-- comment -->
            <input type="submit" value="Register">
        </form>
        <a href="login.jsp">Already Have an Account</a>
    </body>
</html>

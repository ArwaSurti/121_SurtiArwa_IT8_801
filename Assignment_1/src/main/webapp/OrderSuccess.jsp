<%-- 
    Document   : OrderSuccess
    Created on : 26-Feb-2026, 11:54:47 am
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
        <h2>Order placed Successfully</h2>
        
        <p>Your Order ID:<b>${orderId}</b></p>
        <p>Status:Pending Payment</p><!-- comment -->
        <a href="Product">Continue Shopping</a>
    </body>
</html>

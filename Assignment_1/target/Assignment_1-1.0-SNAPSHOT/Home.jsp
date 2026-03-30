<%-- 
    Document   : Home
    Created on : 23-Feb-2026, 9:17:20 pm
    Author     : krishnaiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="java.util.*, assignment_1.ProductMaster" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
      
     
      <!--<h2>Welcome ${sessionScope.user}</h2>-->
    <table border="1"> 
 <%
List<ProductMaster> product =
    (List<ProductMaster>) request.getAttribute("products");

if (product != null) {
    for (ProductMaster p : product) {
%>

<tr>
    <td><%= p.getProduct_id() %></td>
    <td><%= p.getProduct_name() %></td>
    <td><%= p.getStock() %></td>
    <td><%= p.getPrice() %></td>
    <td>
        <form action="AddToCart" method="post">
            <input type="hidden" name="product_id" value="<%= p.getProduct_id()%>"><!-- comment -->
            <input type="number" name="quantity" value="1" min="1"><!-- comment -->
            <input type="submit" value="Add to cart">
        </form>
    </td>
</tr>
<%
    }
} else {
%>
<tr>
    <td colspan="4">No products available</td>
</tr>
<%
}
%>
</table>  
      
      
    
</html>

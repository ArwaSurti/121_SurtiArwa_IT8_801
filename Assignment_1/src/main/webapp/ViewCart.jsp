<%-- 
    Document   : ViewCart
    Created on : 26-Feb-2026, 11:08:48 am
    Author     : krishnaiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,assignment_1.CartItem" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
            <%
                Map<Integer,CartItem> cart=(Map<Integer,CartItem>) session.getAttribute("cart");
                double grandTotal=0;
                
                if(cart!=null && !cart.isEmpty()){
                    for(CartItem c:cart.values()){
                        grandTotal+=c.getTotalPrice();
            %>
            <tr>
                <td><%= c.getProduct().getProduct_name()%></td>
                <td><%= c.getProduct().getPrice()%></td>
                <td><%= c.getQuantity()%></td>
                <td><%= c.getTotalPrice()%></td>
                <td>
                    <a href="RemoveFromCart?id=<%= c.getProduct().getProduct_id()%>">
                        Remove
                    </a>
                </td>
            </tr>
            <%   } %>
            <tr>
                <td colspan="3"><b>Grand Total</b></td>
                <td colspan="2"><b><%= grandTotal%></b></td>
            </tr>
              <%  }else{%>
                    
              <tr>
                  <td>Cart is empty</td>
              </tr>
                <%  }%>
        </table>
        <form action="CheckOut" method="post">
            <label>Payment Mode:</label>
            <select name="payment_mode">
                <option value="COD">Cash On Delivery</option>
                <option value="UPI">UPI</option>
                <option value="CARD">CARD</option>  
            </select>
            <input type="submit" value="Place Order">
        </form>
    </body>
</html>

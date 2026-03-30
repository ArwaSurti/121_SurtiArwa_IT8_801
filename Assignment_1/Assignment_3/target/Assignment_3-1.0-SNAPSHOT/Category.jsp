<%-- 
    Document   : Category
    Created on : 08-Mar-2026, 4:36:47 pm
    Author     : krishnaiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,asg3.CategoryMaster"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Category" method="post">
            <input type="hidden" value="${e.category_id}" name="id"/>
            <input type="text" name="cname" value="${e.category_name}"/><!-- comment -->
            <% if(request.getAttribute("e")==null){%>
                 <input type="submit" value="Add"/>
             <%}else{%>
              <input type="submit" value="Update"/>
            <% }
           %>
        </form>
        <table border="2">
        <%
            List<CategoryMaster> data = (List<CategoryMaster>) request.getAttribute("data");
            if (data != null) {
                for (CategoryMaster c : data) {%>
                    <tr>
                        <td><%= c.getCategory_id()%></td>
                        <td><%= c.getCategory_name()%></td>
                        <td><a href="Category?action=delete&id=<%=c.getCategory_id()%>">Delete</a></td>
                        <td><a href="Category?action=edit&id=<%=c.getCategory_id()%>">Update</a></td>
                    </tr>
                <%}%>
            <%} else {%>
                <td>Category Not Found</td>
            <%}%>
        </table>

</body>
</html>

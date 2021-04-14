<%-- 
    Document   : act1_2
    Created on : 14/04/2021, 20:41:45
    Author     : Alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
            <% for(int i = 0; i < 5; i++) {%>
            <li><%=(int)(Math.random()*5)%></li>
            <% } %>
        </ul>
    </body>
</html>

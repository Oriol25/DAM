<%-- 
    Document   : pagecolor1
    Created on : 28-abr-2021, 23:49:33
    Author     : poved
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%String foreground = String.valueOf(session.getAttribute("foreground"));%>
<%String background = String.valueOf(session.getAttribute("background"));%>

<!DOCTYPE html>
<html>
    <head>
        <title>Color 1</title>      
        <style>
            body {
                color: <%=foreground%>;
                background: <%=background%>;
            }
        </style>
    </head>
    <body>
        <h1>Choose Colors (1)</h1>
        <p>Hello World</p>
    </body>
</html>

<%-- 
    Document   : pagecolor2
    Created on : 29-abr-2021, 0:09:46
    Author     : poved
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Color 2</title>
        <style>
        body {
            <jsp:useBean id="colors" class="corebeans.Color" scope="session"/>
            <jsp:setProperty name="colors" property="*"/>

                background-color: <jsp:getProperty name="colors" property="background"/>;
                color: <jsp:getProperty name="colors" property="foreground"/>;

            }
        </style>
    </head>
    <body>
        <h1>Choose Colors (2)</h1>
        <p>Hello World</p>
    </body>
</html>
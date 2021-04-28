<%-- 
    Document   : pagecolor3
    Created on : 28-abr-2021, 23:50:28
    Author     : poved
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Color 3</title>
        <style>
        body {
            <jsp:useBean id="colors" class="corebeans.Color" scope="application"/>
            <jsp:setProperty name="colors" property="*"/>

                background-color: <jsp:getProperty name="colors" property="background"/>;
                color: <jsp:getProperty name="colors" property="foreground"/>;

            }
        </style>
    </head>
    <body>
        <h1>Choose Colors (3)</h1>
        <p>Hello World</p>
    </body>
</html>
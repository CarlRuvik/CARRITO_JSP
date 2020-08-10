<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<%
    HttpSession sesionOK=request.getSession(); sesionOK.invalidate();
    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrar sesión</title>
    </head>
    <body>
        <jsp:forward page="index.html"/>
    </body>
</html>

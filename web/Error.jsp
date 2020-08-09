<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%
    String usuario="";
    HttpSession sessionOK=request.getSession();
    
    if(sessionOK.getAttribute("usuario")==null)
    {
%>

<jsp:forward page="index.html">
    <jsp:param 
        name="error" 
        value="Se requiere una cuenta para continuar."
    />
</jsp:forward>
<%
    }
    else
    {
        usuario=(String)sessionOK.getAttribute("usuario");
    }   
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
        <link rel="stylesheet" href="CSS/STYLE.css"/>
    </head>
    <body>
        <table width="800" border="0" align="center" >
            <tr>
                <td width="84" bgcolor="#FF0000">
                    <img src="IMAGES/" width="84" height="77">
                <td width="716" bgcolor="#FF0000"><h1 style="color:#FFFFFF">ERROR >:V</h1></td>
                </td>
            </tr>
            <tr align="center">
                <td colspan="2"></td>
            </tr>
            <tr align="center">
                <td colspan="2">
                    <h4><a href="MostrarProductos.jsp">Ver lista de productos</a> | <a href="AgregarAlCarrito1.jsp">Agregar al carrito de compras</a> | <a>Cerrar sesión</a></h4>
                </td>     
            </tr>
            <tr>
                
            </tr>
            <tr>
                <td colspan="2"></td>
            </tr>
        </table>
        <center>Vuelva pronto.</center>
    </body>
</html>

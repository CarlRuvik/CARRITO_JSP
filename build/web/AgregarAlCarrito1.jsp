<%@page import="Controlador.Producto"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
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
        <title>Agregar producto
            <link rel="stylesheet" href="CSS/STYLE.css"/>
    </head>
    <body>
        <%
            //Debemos obtener la lista de todos los productos 
            Vector<Producto> listaproductos=new Producto().ListaDeProductos();
        %>  
        
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
                <td colspan="2">
                    <table border="0" width="100%">
                        <tr bgcolor="#FF6660" style="color:#FFFFFF">
                            <td width="68%" align="right" valign="top">
                                <h4><span class="EST3">Nombre</span></h4>
                            </td>
                            <td width="16%" align="right" valign="top">
                                <h4><span class="EST3">stock</span></h4>
                            </td>
                            <td width="16%" align="right" valign="top">
                                <h4><span class="EST3">Precio</span></h4>
                            </td>
                            <td width="16%" align="right" valign="top">
                                <h4><span class="EST3">Proceso</span></h4>
                            </td>
                        </tr>
                        <%
                        //Debemos recorrer la lista de productos para mostrarla
                            for(Producto producto:listaproductos)
                            {
                                String direccion="AgregarAlCarrito2.jsp?id="+producto.getId_producto();
                        %>
                        <tr>
                            <td><%=producto.getNombre_producto()%></td>
                            <td align="right" valign="top"><%=producto.getStock_producto()%></td>
                            <td align="right" valign="top"><%=producto.getPrecio_producto()%></td>
                            <td align="right" valign="top"><%=direccion%></td>
                        </tr>
                        <% }%>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2"></td>
            </tr>
        </table>
    </body>
</html>

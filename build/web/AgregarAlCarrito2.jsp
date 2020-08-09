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
        <title>Agregar producto</title>
        <link rel="stylesheet" href="CSS/STYLE.css"/>
    </head>
    <body>        
        <%
            //Debemos obtener la búsuqeda de un solo producto para agregarlo al carrito
            Producto producto=new Producto().BuscarProducto(Integer.parseInt(request.getParameter("id")));
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
                        <form method="post" name="Agregar" action="AgregarAlCarrito">
                            <table width="100%" border="0">
                                <tr>
                                    <td width="17%" bgcolor="#FF0000">
                                        <span class="EST3">Código:</span>
                                    </td>
                                    <td width="83%" bgcolor="#FF0000">
                                        <label><input type="text" name="txtId" id="txtId"
                                                      readonly size="10" value="<%=producto.getId_producto()%>"></label>
                                    </td>
                                    <td width="17%" bgcolor="#FF0000">
                                        <span class="EST3">Producto:</span>
                                    </td>
                                    <td width="83%" bgcolor="#FF0000">
                                        <label><input type="text" name="txtProducto" id="txtProducto"
                                                      readonly size="60" value="<%=producto.getNombre_producto()%>"></label>
                                    </td>
                                    <td width="17%" bgcolor="#FF0000">
                                        <span class="EST3">Precio:</span>
                                    </td>
                                    <td width="83%" bgcolor="#FF0000">
                                        <label><input type="text" name="txtPrecio" id="txtPrecio"
                                                      readonly size="15" value="<%=producto.getPrecio_producto()%>"></label>
                                    </td>
                                    <td width="17%" bgcolor="#FF0000">
                                        <span class="EST3">Stock</span>
                                    </td>
                                    <td width="83%" bgcolor="#FF0000">
                                        <label><input type="text" name="txtStock" id="txtStock"
                                                      readonly size="15" value="<%=producto.getStock_producto()%>"></label>
                                    </td>
                                    <td width="17%" bgcolor="#FF0000">
                                        <span class="EST3">Cantidad:</span>
                                    </td>
                                    <td width="83%" bgcolor="#FF0000">
                                        <label><input type="text" name="txtCantidad" id="txtCantidad"
                                                      readonly size="15" value="1"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="submit" name="button" value="Realizar compra"></td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"></td>
                </tr>
        </table>
    </body>
</html>

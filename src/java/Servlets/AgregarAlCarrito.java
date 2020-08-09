package Servlets;

import Controlador.DetalleDeLaVenta;
import Controlador.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AgregarAlCarrito", urlPatterns = {"/AgregarAlCarrito"})
public class AgregarAlCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //Debemos obtener la sesión para saber que usuario es el que está realizando la compra
            HttpSession sesion=request.getSession();
       
            //Realizamos las instancias del producto y detalle de la venta:
            Vector<Producto> stockProducto=null;
            Vector<DetalleDeLaVenta> detalleVenta=null;
            
            //Debemos verificar si ese usuario ya agregó algo al carrito:
            if(sesion.getAttribute("Dventa")!=null)
            {
                 //Quiere decir que si hay algo en el carrito del usuario
                detalleVenta=(Vector<DetalleDeLaVenta>)sesion.getAttribute("Dventa");
                stockProducto=(Vector<Producto>)sesion.getAttribute("stockProducto");
            }
            else
            {
                //El carrito de ese usuario está vacío
                detalleVenta=new Vector<DetalleDeLaVenta>();
                stockProducto=new Vector<Producto>();
            }
            
            //AHora si obtenemos los parámetros del formulario:
            int id,cantidad;
            
            id=Integer.parseInt(request.getParameter("ttxtId").trim());
            cantidad=Integer.parseInt(request.getParameter("txtCantidad".trim()));
            
            Producto producto=new Producto();
            
            producto=producto.BuscarProducto(id);

            double subtot=cantidad*producto.getPrecio_producto();

            //Creamos el detalle de la venta
            DetalleDeLaVenta dventa=new DetalleDeLaVenta();
            
            dventa.setItem_Dventa(detalleVenta.size()+1);
            dventa.setId_producto(id);
            dventa.setSubtotal_Dventa(subtot);
            dventa.setCantidad_Dventa(cantidad);
            
            //Agregamos al vector
            detalleVenta.add(dventa);
            
            //Enviamos el detalle a las sesion para asociar al usuario con su correspondiente compra
            sesion.setAttribute("Dventa", detalleVenta);
            
            //Actualizamos el stock
            producto.setStock_producto(producto.getStock_producto()-cantidad);
            stockProducto.add(producto);
            sesion.setAttribute("stockProducto", stockProducto);
            response.sendRedirect("VerCarrito.jsp");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgregarAlCarrito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarAlCarrito at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

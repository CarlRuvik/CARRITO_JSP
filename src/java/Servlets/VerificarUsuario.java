package Servlets;

import Controlador.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerificarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //Primero dbemos obtener los parámetros necesarios desde el formulario(index.html):
            String srvusername,srvpassword;
            
            srvusername=request.getParameter("txtUsuario");
            srvpassword=request.getParameter("txtPassword");
            
            //Instanciamos al usuario
            Usuario usuario=new Usuario();
            usuario.VerificarUsuario(srvusername, srvpassword);
            
            //Ahora verificaremos si el usuario es cliente o administrador
            if(usuario!=null)//Será diferente de null si el usuario existe en la BD
            {
                //Usaremos el manejo de sesiones
                HttpSession sesion=request.getSession(true);
                sesion.setAttribute("Usuario", usuario);
                
                //Manjeamos una segunda sesión para verificar quien es:
                HttpSession sesionOK=request.getSession();
                sesionOK.setAttribute("Usuario", srvusername);
                
                //Ahora debemos reconocerlo:
                if(usuario.getPrivilegio_usuario()==0)//Si el no. de privilegio es 0 entonces el usuario es cliente
                {
                    response.sendRedirect("MostrarProductosCliente.jsp");
                }
                else//Si no, entonces el usuario es un administrador
                {
                    response.sendRedirect("MostrarProductosAdmin.jsp");
                }
            }
            else//Si el usuario no existe o la contraseña es erronea
            {
                response.sendRedirect("Error.jsp");
            }
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

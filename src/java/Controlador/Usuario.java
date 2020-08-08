package Controlador;

import java.sql.*;
import java.util.*;

//Esta clase representa la tabla de usuarios de la BD

public class Usuario 
{
    private int id_usuario,privilegio_usuario;
    private String nombre_usuario,apellido_usuario,username_usuario,password_usuario;
    
    //creamos el constructor:
    public Usuario()
    {
        
    }
    
    //CRUD
    //Método que verifica si el usurio es cliente o administrador
    public Usuario VerificarUsuario(String username,String password)
    {
        //Inicializamos las variables u objetos
        Usuario usuario=null;
        Connection conexion=null;
        PreparedStatement PS =null;
        ResultSet RS =null;
        
        try
        {
            conexion=ConexionMySql.getConnection();
            
            String query="SELECT*FROM usuario WHERE username_ussuario=? AND password_usuario=?";
            PS=conexion.prepareStatement(query);
            PS.setString(1,username);
            PS.setString(2,password);
            RS=PS.executeQuery();
            
            //Buscamos dentro de la tabl ausuario de la BD:
            while(RS.next())
            {
                //Hacemos la instancia del usuario
                usuario=new Usuario();
                usuario.setId_usuario(RS.getInt("id_usuario"));
                usuario.setNombre_usuario(RS.getString("nombre_usuario"));
                usuario.setApellido_usuario(RS.getString("apellido_usuario"));
                usuario.setUsername_usuario(RS.getString("username_usuario"));
                usuario.setPassword_usuario(RS.getString("password_usuario"));
                usuario.setPrivilegio_usuario(RS.getInt("privilegio_usuario"));
                break;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error. Usuario incorrecto o inexistente.");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            //Debemos poner al usuario en null para que no guarde los datos de usuario por seguridad:
            usuario=null;
        }
        finally
        {
            try
            {
                RS.close();
                PS.close();
                conexion.close(); 
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
        }
        return usuario;
    }
    
    //creamos los getters y setters
    public int getId_usuario() 
    {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) 
    {
        this.id_usuario = id_usuario;
    }

    public int getPrivilegio_usuario() 
    {
        return privilegio_usuario;
    }

    public void setPrivilegio_usuario(int privilegio_usuario) 
    {
        this.privilegio_usuario = privilegio_usuario;
    }

    public String getNombre_usuario() 
    {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) 
    {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() 
    {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) 
    {
        this.apellido_usuario = apellido_usuario;
    }

    public String getUsername_usuario() 
    {
        return username_usuario;
    }

    public void setUsername_usuario(String username_usuario) 
    {
        this.username_usuario = username_usuario;
    }

    public String getPassword_usuario() 
    {
        return password_usuario;
    }

    public void setPassword_usuario(String password_usuario) 
    {
        this.password_usuario = password_usuario;
    }
    
}

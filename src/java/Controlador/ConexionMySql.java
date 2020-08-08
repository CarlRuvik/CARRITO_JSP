package Controlador;

import java.sql.*;
import java.util.*;

public class ConexionMySql 
{
    public static Connection getConnection()
    {
        String url,username,password;
        
        url="jdbc:mysql://localhost:3306/ventas";
        username="root";
        password="Enderman7";
        
        try
        {
            return DriverManager.getConnection(url,username,password);
        }
        catch(SQLException e)
        {
            System.out.println("Error al conectar en la base de datos.");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        return null;
    }
}

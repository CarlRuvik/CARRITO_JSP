package Controlador;

import java.sql.*;
import java.util.*;

public class Venta 
{
    private int id_venta,id_ususario;
    private String fecha_venta;
    private double pagototal_venta;

public Venta()
{

}

//Método para obtener la última venta generada
private int UltimoIdVentaInsertado(Connection conexion)
{
    int idv=0;
    
    PreparedStatement PS=null;
    ResultSet RS=null;
    
    try
    {
        String query="SELECT MAX(id_venta) AS idven FROM venta";
        PS=conexion.prepareStatement(query);
        RS=PS.executeQuery();
        
        //Buscamos en la tabla venta
        while(RS.next())
        {
            idv=RS.getInt("idven");
            break;
        }
    }
    catch(SQLException e)
    {
        System.out.println("Error al buscar en la tabla venta");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
        idv=0;
    }
    finally
    {
        try
        {
            RS.close();
            PS.close();
            
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    return idv;
}

//Método para registrar la venta(usuario)
public boolean RegistrarVenta(Venta venta,Vector<DetalleDeLaVenta> listaDventa)
{
    boolean registro=false;
    Connection conexion=null;
    PreparedStatement PS=null;
    
    try
    {
        conexion=ConexionMySql.getConnection();
        String query="INSERT INTO venta(fecha_venta,id_usuario,pagototal_venta) VALUES(?,?,?)";
        
        PS=conexion.prepareStatement(query);
        PS.setString(1, venta.getFecha_venta());
        PS.setInt(2, venta.getId_ususario());
        PS.setDouble(3, venta.getPagototal_venta());
        
        //Ahora verificaremos que se ejecute el registro:
        if(PS.executeUpdate()==1)
        {
            int id=this.UltimoIdVentaInsertado(conexion);
            registro=this.RegistrarDetalleDeLaVenta(id_venta, listaDventa, conexion);
        }
        else
        {
            registro=false;
        }
    }
    catch(SQLException e)
    {
        System.out.println("Error al registrar la venta.");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
        registro=false;
    }
    finally
    {
        try
        {
            PS.close();
            conexion.close();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    return registro;
}


/*Método para registrar a partir de la venta en el detalle de la venta para 
saber que productos se compraron al momento de pagar (programa)*/
public boolean RegistrarDetalleDeLaVenta(int id_venta,Vector<DetalleDeLaVenta> listaDventa,Connection conexion)
{
    boolean registro=false;
    PreparedStatement PS=null;
    
    try
    {
        for(DetalleDeLaVenta DLV:listaDventa)
        {
            String query="INSERT INTO dventa VALUES(?,?,?,?,?);";
            PS=conexion.prepareStatement(query);
            
            PS.setInt(1, id_venta);
            PS.setInt(2, DLV.getItem_Dventa());
            PS.setInt(3, DLV.getId_producto());
            PS.setInt(4, DLV.getCantidad_Dventa());
            PS.setDouble(5, DLV.getSubtotal_Dventa());
            
            if(PS.executeUpdate()==1)
            {
                registro=true;
            }
            else
            {
                registro=false;
                break;
            }
        }
    }
    catch(SQLException e)
    {
        System.out.println("Error al registrar el detalle de la venta.");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
    }
    finally
    {
        try
        {
            PS.close();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    return registro;
}


    public int getId_venta() 
    {
        return id_venta;
    }

    public void setId_venta(int id_venta) 
    {
        this.id_venta = id_venta;
    }

    public int getId_ususario() 
    {
        return id_ususario;
    }

    public void setId_ususario(int id_ususario) 
    {
        this.id_ususario = id_ususario;
    }

    public String getFecha_venta() 
    {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) 
    {
        this.fecha_venta = fecha_venta;
    }

    public double getPagototal_venta() 
    {
        return pagototal_venta;
    }

    public void setPagototal_venta(double pagototal_venta) 
    {
        this.pagototal_venta = pagototal_venta;
    }

}

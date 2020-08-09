package Controlador;

import java.sql.*;
import java.util.*;


public class Producto 
{
    private int id_producto, stock_producto;
    private String nombre_producto;
    private double precio_producto;
    
    public Producto()
    {
        
    }
    
    //Ahora programaremos los métodos que se encargarán de la automatización del proceso:
    //Método que obtiene la lista de productos de la BD
    //Usaremos un vector por que un producto siempre tendrá los mismos atributos, nunca aumentarán o disminuirán.
    public Vector<Producto> ListaDeProductos()
    {
        //Hacemos una instancia del vector:
        Vector<Producto> listaproductos=new Vector<Producto>();
        
        //Inicializamos los objetos:
        Connection conexion= null;
        PreparedStatement PS=null;
        ResultSet RS=null;
        
        try
        {
            conexion=ConexionMySql.getConnection();
            
            String query="SELECT*FROM producto";
            PS=conexion.prepareStatement(query);
            RS=PS.executeQuery();
            
            //Ahora buscamos dentro de la tabla
            while(RS.next())
            {
                //Instanciamos al producto
                Producto producto=new Producto();
                producto.setId_producto(RS.getInt("id_producto"));
                producto.setNombre_producto(RS.getString("nombre_producto"));
                producto.setPrecio_producto(RS.getInt("precio_producto"));
                producto.setStock_producto(RS.getInt("stock_producto"));
                
                listaproductos.add(producto);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al conectar con la tabla producto.");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
            listaproductos=null;
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
        return listaproductos;
    }
    
    //Método que busca el producto por código (id)
    public Producto BuscarProducto(int id_producto)
    {
        //Instanciamos al producto
        Producto producto=new Producto();
        
        //Inicializamos los objetos:
        Connection conexion= null;
        PreparedStatement PS=null;
        ResultSet RS=null;
        
        try
        {
            conexion=ConexionMySql.getConnection();
            
            String query="SELECT*FROM producto WHERE id_producto=?";
            PS=conexion.prepareStatement(query);
            PS.setInt(1, id_producto);
            RS=PS.executeQuery();
            
            //Ahora buscamos dentro de la tabla
            while(RS.next())
            {
                producto.setId_producto(RS.getInt("id_producto"));
                producto.setNombre_producto(RS.getString("nombre_producto"));
                producto.setPrecio_producto(RS.getInt("precio_producto"));
                producto.setStock_producto(RS.getInt("stock_producto"));
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al conectar con la tabla producto.");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            
            producto=null;
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
        return producto;
    }
    
    //Método que actualiza el stock al momento de que se vende algún producto
    //Primero necesitamos confirmar la venta con ayuda de un boolean
    public boolean ActualizarStock(Vector<Producto> listaproductosvendidos)
    {
        boolean stockactualizado=false;//Nos servirá para confirmar que la venta se realizó
        
        Connection conexion=null;
        PreparedStatement PS=null;
        
        try
        {
            conexion=ConexionMySql.getConnection();
            
            //Debemos recorrer la lista de todos los productos para saber cuales son los que se vendieron
            //ya que así podremos hacer una lista de los productos que se vendieron
            for(Producto producto:listaproductosvendidos)
            {
                String query="UPDATE producto SET stock_producto=? WHERE id_producto=?";
                PS=conexion.prepareStatement(query);
                
                //pasamos los datos necesarios a la query:
                PS.setInt(1, producto.getStock_producto());
                PS.setInt(2, producto.getId_producto());
                
                //Debemos verificar si se actualizó la tabla correctamente:
                if(PS.executeUpdate()==1)//Si nuestra query se ejecutó correctamente
                {
                    stockactualizado=true;//Entonces la venta si se realizó
                }
                else
                {
                    stockactualizado=false;//Si no, la venta no se realizó
                    break;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error al actualizar el stock.");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        finally
        {
            try
            {
                PS.close();
                conexion.close();
                stockactualizado=false;
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
        }
        return stockactualizado;
    }
    

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) 
    {
        this.id_producto = id_producto;
    }

    public int getStock_producto() 
    {
        return stock_producto;
    }

    public void setStock_producto(int stock_producto) 
    {
        this.stock_producto = stock_producto;
    }

    public String getNombre_producto() 
    {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) 
    {
        this.nombre_producto = nombre_producto;
    }

    public double getPrecio_producto() 
    {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) 
    {
        this.precio_producto = precio_producto;
    }
    
    
}

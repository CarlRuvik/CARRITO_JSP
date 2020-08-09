package Controlador;

public class DetalleDeLaVenta 
{
    private int id_venta,item_Dventa,id_producto,cantidad_Dventa;
    private double subtotal_Dventa;
    
    public DetalleDeLaVenta()
    {
        
    }

    public int getId_venta() 
    {
        return id_venta;
    }

    public void setId_venta(int id_venta) 
    {
        this.id_venta = id_venta;
    }

    public int getItem_Dventa() 
    {
        return item_Dventa;
    }

    public void setItem_Dventa(int item_Dventa) 
    {
        this.item_Dventa = item_Dventa;
    }

    public int getId_producto() 
    {
        return id_producto;
    }

    public void setId_producto(int id_producto) 
    {
        this.id_producto = id_producto;
    }

    public int getCantidad_Dventa() 
    {
        return cantidad_Dventa;
    }

    public void setCantidad_Dventa(int cantidad_Dventa) 
    {
        this.cantidad_Dventa = cantidad_Dventa;
    }

    public double getSubtotal_Dventa() 
    {
        return subtotal_Dventa;
    }

    public void setSubtotal_Dventa(double subtotal_Dventa) 
    {
        this.subtotal_Dventa = subtotal_Dventa;
    }
    
    
}

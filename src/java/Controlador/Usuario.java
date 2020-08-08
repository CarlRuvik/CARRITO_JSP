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
    
    //creamos los getters y setters

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getPrivilegio_usuario() {
        return privilegio_usuario;
    }

    public void setPrivilegio_usuario(int privilegio_usuario) {
        this.privilegio_usuario = privilegio_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getUsername_usuario() {
        return username_usuario;
    }

    public void setUsername_usuario(String username_usuario) {
        this.username_usuario = username_usuario;
    }

    public String getPassword_usuario() {
        return password_usuario;
    }

    public void setPassword_usuario(String password_usuario) {
        this.password_usuario = password_usuario;
    }
    
}

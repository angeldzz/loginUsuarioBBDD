/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daw.loginv1;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author zx23student3283
 */
public class Modelo {
    
    //atributo para contener la conexion
    private Connection conexion;  
    
    
    public Modelo(){
        String url = "jdbc:mysql://localhost:3306/practlogin";
        String usuario = "practlogin";
        String contraseña = "practlogin";
        
        
        try {
            //Crear la conexion
        this.conexion = DriverManager.getConnection(url,usuario,contraseña);
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String datosValidarUsuario(String usuario){
        
    //variable con la contraseña del usuario, si existe el usuario
    String pass = null;
    
    //consulta a la base de datos para buscar la contraseña
    String consulta = "Select Pass from usuarios where Usu = ?";

    try {
            //Preparacion de la consulta
            PreparedStatement consultaUsu = this.conexion.prepareStatement(consulta);
            consultaUsu.setString(1, usuario);
            
            //ejecutar la consulta
            ResultSet salida = consultaUsu.executeQuery();
            
            //comprobar si hay resultado
            if (salida.next()) {
                pass = salida.getString("Pass");
            }
            consultaUsu.close();
            salida.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return pass;
    }
    
    public void cerrarBD(){
    try {
        this.conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean esAdmin(String usuario) {
        Boolean admin = false;
        
        String consulta = "Select rol from usuarios where Usu = ?";

    try {
            //Preparacion de la consulta
            PreparedStatement consultaUsu = this.conexion.prepareStatement(consulta);
            consultaUsu.setString(1, usuario);
            
            //ejecutar la consulta
            ResultSet salida = consultaUsu.executeQuery();
            
            //comprobar si hay resultado
            if (salida.next()) {
                if ( salida.getInt("rol")==0) {
                    admin = true;
                }

            }
            consultaUsu.close();
            salida.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return admin;
    }
    public void guardarDatos(String usuario, String contraseña,Boolean admin){
        int esAdmin = 1;
        if (admin) {
            esAdmin = 0;
        }
        String consulta = "Insert into usuarios(Usu,Pass,rol) Values(?,?,?)";
        try {
            //Preparacion de la consulta
            PreparedStatement insertar = this.conexion.prepareStatement(consulta);
            insertar.setString(1, usuario);
            insertar.setString(2, BCrypt.withDefaults().hashToString(12, contraseña.toCharArray()));
            insertar.setInt(3, esAdmin);
            insertar.execute();
            //ejecutar la consulta
            insertar.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> listaNombres() {
        ArrayList<String> nombres = new ArrayList();
        try {
            String consulta = "Select Usu from usuarios";
            
            Statement consultaUsu = this.conexion.createStatement();
            
            //ejecutar la consulta
            ResultSet salida = consultaUsu.executeQuery(consulta);
            while(salida.next()){
                nombres.add(salida.getString("Usu"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nombres;
    }
}

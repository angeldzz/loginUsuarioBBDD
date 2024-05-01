/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.daw.loginv1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zx23student3283
 */
public class AltaUsuarioController implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private TextField tfPass;
    @FXML
    private CheckBox ckAdmin;
    Modelo modelo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelo = new Modelo();
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Aceptar(ActionEvent event) {
        try {
            Boolean adminS = false;
            if (this.ckAdmin.isSelected()) {
                adminS = true;
            }
            this.modelo.guardarDatos(this.tfUsuario.getText(), this.tfPass.getText(),adminS);
            App.setRoot("primary");
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

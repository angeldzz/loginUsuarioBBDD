package com.daw.loginv1;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class SecondaryController implements Initializable{

    @FXML
    private Menu menuGU;
    @FXML
    private Button btnHacerAlgo;
    @FXML
    private CheckBox cbPuente;
    @FXML
    private ChoiceBox<String> chbLugares;
    @FXML
    private ListView<String> lvListaNombres;
    @FXML
    private ToggleGroup tgSelector;
    @FXML
    private TableView<Persona> tvMiTabla;
    @FXML
    private TableColumn<Persona, String> c1;
    @FXML
    private TableColumn<Persona, String> c2;
    @FXML
    private TableColumn<Persona, Integer> c3;
    @FXML
    private TableColumn<Persona, String> c4;
    @FXML
    private TableColumn<Persona, String> c5;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (App.getAdmin()) {
            menuGU.setVisible(true);
        }
        chbLugares.getItems().addAll("Playa","Montaña","Asturias","En casa");
        
    }
    
    @FXML
    private void altaUsuario(ActionEvent event) {
        try {
            App.setRoot("altaUsuario");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void cerrar(ActionEvent event) {
        try {
            App.resetAdmin();
            App.setRoot("primary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void hacerAlgo(ActionEvent event) {
       ArrayList<String> listanombres =  App.getModelo().listaNombres();
       for(String nombre : listanombres){
       this.lvListaNombres.getItems().add(nombre);
       }
       
    }
    
}
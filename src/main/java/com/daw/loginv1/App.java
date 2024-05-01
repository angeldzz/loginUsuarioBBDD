package com.daw.loginv1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Modelo modelo;
    private static Scene scene;
    private static Boolean esAdmin = false;

    public static void setAdmin() {
        esAdmin = true;
    }
    public static void resetAdmin() {
        esAdmin = false;
    }
    public static Boolean getAdmin() {
            return esAdmin;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    protected static Modelo getModelo(){
    return modelo;
    }
    
    public static void main(String[] args) {
        
        //modelo de la base de datos
        //se conecta a la base de datos
        modelo = new Modelo();
        
        launch();
    }

}
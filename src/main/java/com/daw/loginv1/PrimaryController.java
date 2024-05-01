package com.daw.loginv1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class PrimaryController implements Initializable {

    @FXML
    private TextField tfuser;
    @FXML
    private PasswordField tfpass;

    Modelo modelo;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.modelo = App.getModelo();
    }

    @FXML
    private void entrar(ActionEvent event) {
        //comprobar que usuario y contraseña son correctos

        String usuVista = tfuser.getText();
        String passVista = tfpass.getText();

        System.out.println("pass: " + passVista + " encriptada: " + BCrypt.withDefaults().hashToString(12, passVista.toCharArray()));

        //passBD es la contraseña encriptada
        String passBD = this.modelo.datosValidarUsuario(usuVista);

        BCrypt.Result validador = BCrypt.verifyer().verify(passVista.toCharArray(), passBD);

        if (passBD != null && validador.verified) {

            try {

                if (this.modelo.esAdmin(usuVista)) {
                    App.setAdmin();
                }
                App.setRoot("secondary");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            dialogoError("Error en usuario o contraseña");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        System.out.println("Saliendo...");
        System.exit(0);
    }

    private void dialogoError(String error) {
        //crear una ventana para mostrar el error
        Alert nv = new Alert(Alert.AlertType.ERROR);

        //Caracteristicas de la ventana
        nv.setTitle("Error");
        nv.setContentText(error);

        //Mostrar la ventana
        nv.showAndWait();
    }

    public TextField getTfuser() {
        return tfuser;
    }

}

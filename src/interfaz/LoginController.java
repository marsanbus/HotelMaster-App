/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package interfaz;

import Principal.ConnectionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import javafx.scene.control.Alert;
import java.sql.ResultSet;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class LoginController implements Initializable {

    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoContraseña;
    @FXML
    private Button btnAcceder;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnNuevoUsuario;

    /*private Button btnNuevoHotel;*/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnNuevoUsuario.setOnAction(event -> {
            try {
                // Cargar el archivo FXML del registro
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registro.fxml"));
                Parent root = fxmlLoader.load();

                // Crear una nueva ventana (Stage)
                Stage stage = new Stage();
                stage.setTitle("Registro de Usuario");
                stage.setScene(new Scene(root));

                // Mostrar la nueva ventana
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        campoContraseña.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                AccederLogin(null);
            }
        });
        /*btnNuevoHotel.setOnAction(event -> {
            try {
                // Cargar el archivo FXML del registro
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registroHotel.fxml"));
                Parent root = fxmlLoader.load();

                // Crear una nueva ventana (Stage)
                Stage stage = new Stage();
                stage.setTitle("Registro de Hotel");
                stage.setScene(new Scene(root));

                // Mostrar la nueva ventana
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        })*/;
    }

    @FXML
    private void LoginNombre(ActionEvent event) {
    }

    @FXML
    private void LoginContraseña(ActionEvent event) {
    }

    @FXML
    private void AccederLogin(ActionEvent event) {
        ConnectionDB conexionBD = new ConnectionDB();
        try {
            conexionBD.openConnection();
            PreparedStatement statement = conexionBD.getConnection().prepareStatement("SELECT nombre FROM usuarios WHERE nombre = ? AND contraseña = ?");
            statement.setString(1, campoNombre.getText());
            statement.setString(2, campoContraseña.getText());

            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                try {
                    // Cargar el archivo FXML del registro
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selector.fxml"));
                    Parent root = fxmlLoader.load();

                    // Crear una nueva ventana (Stage)
                    Stage stage = new Stage();
                    stage.setTitle("Selector");
                    stage.setScene(new Scene(root));

                    // Mostrar la nueva ventana
                    stage.show();

                    // Cerramos la ventana de Login
                    Stage loginStage = (Stage) campoNombre.getScene().getWindow();
                    loginStage.close();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("AVISO");
                alert.setHeaderText("¡ERROR!");
                alert.setContentText("Alguno de los campos introducidos es incorrecto");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LoginController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LimpiarLogin(ActionEvent event) {
        campoNombre.setText("");
        campoContraseña.setText("");
    }
}

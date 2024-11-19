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

            // Consulta para verificar nombre y contraseña, y obtener el departamento
            PreparedStatement statement = conexionBD.getConnection().prepareStatement(
                    "SELECT departamento FROM trabajadores WHERE nombre = ? AND contraseña = ?");
            statement.setString(1, campoNombre.getText());
            statement.setString(2, campoContraseña.getText());

            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                // Obtener el departamento del trabajador
                String departamento = resultado.getString("departamento");

                // Variable para el archivo FXML a cargar
                String fxmlFile;

                // Determinar qué ventana abrir según el departamento
                switch (departamento) {
                    case "administracion":
                        fxmlFile = "selector.fxml";
                        break;
                    case "recepcion":
                        fxmlFile = "recepcion.fxml";
                        break;
                    case "limpieza":
                        fxmlFile = "limpieza.fxml";
                        break;
                    case "barra":
                        fxmlFile = "barra.fxml";
                        break;
                    case "sala":
                        fxmlFile = "sala.fxml";
                        break;
                    default:
                        throw new IllegalStateException("Departamento desconocido: " + departamento);
                }

                // Cargar el archivo FXML correspondiente
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = fxmlLoader.load();

                // Crear y mostrar una nueva ventana (Stage)
                Stage stage = new Stage();
                stage.setTitle("Panel - " + departamento);
                stage.setMaximized(true);
                stage.setScene(new Scene(root));
                stage.show();

                // Cerrar la ventana de Login
                Stage loginStage = (Stage) campoNombre.getScene().getWindow();
                loginStage.close();

            } else {
                // Si no se encuentra el usuario o la contraseña es incorrecta
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("AVISO");
                alert.setHeaderText("¡ERROR!");
                alert.setContentText("Alguno de los campos introducidos es incorrecto");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LoginController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void LimpiarLogin(ActionEvent event) {
        campoNombre.setText("");
        campoContraseña.setText("");
    }
}

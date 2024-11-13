/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package interfaz;

import Principal.ConnectionDB;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class RegistroController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidos;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfContraseña;
    @FXML
    private TextField tfHotel;
    @FXML
    private Button btnAgregar;
    @FXML
    private ComboBox<String> cbDepartamento;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbDepartamento.getItems().addAll("Administracion", "Recepcion", "Limpieza", "Barra", "Sala");
    }

    @FXML
    private void CancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void AgregarTrabajador(ActionEvent event) {
        if (tfNombre.getText().compareTo("") == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("¡ERROR!");
            alert.setContentText("El campo del nombre está vacío");
            alert.showAndWait();
        } else {
            if (tfApellidos.getText().compareTo("") == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AVISO");
                alert.setHeaderText("¡ERROR!");
                alert.setContentText("El campo de los apellidos está vacío");
                alert.showAndWait();
            } else {
                if (tfCorreo.getText().compareTo("") == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("AVISO");
                    alert.setHeaderText("¡ERROR!");
                    alert.setContentText("El campo del correo está vacío");
                    alert.showAndWait();
                } else {
                    if (tfContraseña.getText().compareTo("") == 0) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("AVISO");
                        alert.setHeaderText("¡ERROR!");
                        alert.setContentText("El campo de la contraseña está vacío");
                        alert.showAndWait();
                    } else {
                        if (tfHotel.getText().compareTo("") == 0) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("AVISO");
                            alert.setHeaderText("¡ERROR!");
                            alert.setContentText("El campo del hotel está vacío");
                            alert.showAndWait();
                        } else {
                            if (cbDepartamento.getValue().compareTo("") == 0) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("AVISO");
                                alert.setHeaderText("¡ERROR!");
                                alert.setContentText("El departamento no ha sido seleccionado");
                                alert.showAndWait();
                            } else {
                                ConnectionDB conexionBD = new ConnectionDB();
                                try {
                                    conexionBD.openConnection();
                                    PreparedStatement statementInsertar = conexionBD.getConnection().prepareStatement("INSERT INTO usuarios (nombre, apellidos, correo, contraseña, rol, departamento, hotel) VALUES (?, ?, ?, ?, empleado, ?, ?)");
                                    statementInsertar.setString(1, tfNombre.getText());
                                    statementInsertar.setString(2, tfApellidos.getText());
                                    statementInsertar.setString(3, tfCorreo.getText());
                                    statementInsertar.setString(4, tfContraseña.getText());
                                    statementInsertar.setString(6, tfHotel.getText());
                                    statementInsertar.setString(7, cbDepartamento.getValue());
                                    

                                    if (statementInsertar.executeUpdate() == 1) {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("AVISO");
                                        alert.setHeaderText("CORRECTO");
                                        alert.setContentText("El alumno se ha agregado correctamente");
                                        alert.showAndWait();
                                        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                                        stage.close();
                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("AVISO");
                                        alert.setHeaderText("¡ERROR!");
                                        alert.setContentText("Alguno de los campos introducidos es incorrecto");
                                        alert.showAndWait();
                                    }
                                } catch (SQLException ex) {
                                    java.util.logging.Logger.getLogger(RegistroHotelController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

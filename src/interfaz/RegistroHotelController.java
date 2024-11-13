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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class RegistroHotelController implements Initializable {

    @FXML
    private TextField campoNombreHotel;
    @FXML
    private TextField campoDireccionHotel;
    @FXML
    private TextField campoCiudadHotel;
    @FXML
    private TextField campoPaisHotel;
    @FXML
    private TextField campoHabIndividuales;
    @FXML
    private TextField campoHabDobles;
    @FXML
    private TextField campoHabTriples;
    @FXML
    private TextField campoHabSuites;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void IngresarNombreHotel(ActionEvent event) {
    }

    @FXML
    private void IngresarDireccionHotel(ActionEvent event) {
    }

    @FXML
    private void IngresarCiudadHotel(ActionEvent event) {
    }

    @FXML
    private void IngresarPaisHotel(ActionEvent event) {
    }

    @FXML
    private void IngresarHabIndividuales(ActionEvent event) {
    }

    @FXML
    private void IngresarHabDobles(ActionEvent event) {
    }

    @FXML
    private void IngresarHabTriples(ActionEvent event) {
    }

    @FXML
    private void IngresarHabSuites(ActionEvent event) {
    }

    @FXML
    private void GuardarHotel(ActionEvent event) {
        if (campoNombreHotel.getText().compareTo("") == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("¡ERROR!");
            alert.setContentText("El campo del nombre está vacío");
            alert.showAndWait();
        } else {
            if (campoDireccionHotel.getText().compareTo("") == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AVISO");
                alert.setHeaderText("¡ERROR!");
                alert.setContentText("El campo de la dirección está vacío");
                alert.showAndWait();
            } else {
                if (campoCiudadHotel.getText().compareTo("") == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("AVISO");
                    alert.setHeaderText("¡ERROR!");
                    alert.setContentText("El campo de la ciudad está vacío");
                    alert.showAndWait();
                } else {
                    if (campoPaisHotel.getText().compareTo("") == 0) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("AVISO");
                        alert.setHeaderText("¡ERROR!");
                        alert.setContentText("El campo del país está vacío");
                        alert.showAndWait();
                    } else {
                        if (campoHabIndividuales.getText().compareTo("") == 0) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("AVISO");
                            alert.setHeaderText("¡ERROR!");
                            alert.setContentText("El campo de las habitaciones individuales está vacío");
                            alert.showAndWait();
                        } else {
                            if (campoHabDobles.getText().compareTo("") == 0) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("AVISO");
                                alert.setHeaderText("¡ERROR!");
                                alert.setContentText("El campo de las habitaciones dobles está vacío");
                                alert.showAndWait();
                            } else {
                                if (campoHabTriples.getText().compareTo("") == 0) {
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("AVISO");
                                    alert.setHeaderText("¡ERROR!");
                                    alert.setContentText("El campo de las habitaciones triples está vacío");
                                    alert.showAndWait();
                                } else {
                                    if (campoHabSuites.getText().compareTo("") == 0) {
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("AVISO");
                                        alert.setHeaderText("¡ERROR!");
                                        alert.setContentText("El campo de las suites está vacío");
                                        alert.showAndWait();
                                    } else {
                                        ConnectionDB conexionBD = new ConnectionDB();
                                        try {
                                            conexionBD.openConnection();
                                            PreparedStatement statementInsertar = conexionBD.getConnection().prepareStatement("INSERT INTO hoteles (nombre, direccion, ciudad, pais, num_individual, num_doble, num_triple, num_suite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                                            statementInsertar.setString(1, campoNombreHotel.getText());
                                            statementInsertar.setString(2, campoDireccionHotel.getText());
                                            statementInsertar.setString(3, campoCiudadHotel.getText());
                                            statementInsertar.setString(4, campoPaisHotel.getText());
                                            statementInsertar.setInt(5, Integer.parseInt(campoHabIndividuales.getText()));
                                            statementInsertar.setInt(6, Integer.parseInt(campoHabDobles.getText()));
                                            statementInsertar.setInt(7, Integer.parseInt(campoHabTriples.getText()));
                                            statementInsertar.setInt(8, Integer.parseInt(campoHabSuites.getText()));

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
    }

    @FXML
    private void CancelarHotel(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}

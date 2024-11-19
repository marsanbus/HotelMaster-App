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
import java.sql.ResultSet;

/**
 * FXML Controller class
 *
 * @author Marcos
 */
public class RegistroController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfContraseña;
    @FXML
    private Button btnAgregar;
    @FXML
    private ComboBox<String> cbDepartamento;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<String> cbHotel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbDepartamento.getItems().addAll("Administracion", "Recepcion", "Limpieza", "Barra", "Sala");
        cargarHoteles();
    }

    @FXML
    private void CancelarRegistro(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void cargarHoteles() {
        ConnectionDB conexionBD = new ConnectionDB();
        try {
            conexionBD.openConnection();
            PreparedStatement statement = conexionBD.getConnection().prepareStatement("SELECT nombre FROM hoteles");
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                String nombreHotel = resultado.getString("nombre");
                cbHotel.getItems().add(nombreHotel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private int obtenerIdHotel(String nombreHotel) {
        int idHotel = -1; // Valor por defecto, significa que no se encontró el hotel
        ConnectionDB conexionBD = new ConnectionDB();
        try {
            conexionBD.openConnection();
            PreparedStatement statement = conexionBD.getConnection().prepareStatement("SELECT id FROM hoteles WHERE nombre = ?");
            statement.setString(1, nombreHotel);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                idHotel = resultado.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idHotel;
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
            if (tfContraseña.getText().compareTo("") == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AVISO");
                alert.setHeaderText("¡ERROR!");
                alert.setContentText("El campo de la contraseña está vacío");
                alert.showAndWait();
            } else {
                if (cbHotel.getValue().compareTo("") == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("AVISO");
                    alert.setHeaderText("¡ERROR!");
                    alert.setContentText("El departamento no ha sido seleccionado");
                    alert.showAndWait();
                } else {
                    if (cbDepartamento.getValue().compareTo("") == 0) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("AVISO");
                        alert.setHeaderText("¡ERROR!");
                        alert.setContentText("El departamento no ha sido seleccionado");
                        alert.showAndWait();
                    } else {
                        String nombreHotel = cbHotel.getValue();

                        int idHotel = obtenerIdHotel(nombreHotel);

                        if (idHotel == -1) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("AVISO");
                            alert.setHeaderText("¡ERROR!");
                            alert.setContentText("No se pudo encontrar el id del hotel");
                            alert.showAndWait();
                        } else {
                            ConnectionDB conexionBD = new ConnectionDB();
                            try {
                                conexionBD.openConnection();
                                PreparedStatement statementInsertar = conexionBD.getConnection().prepareStatement("INSERT INTO trabajadores (nombre, contraseña, departamento, hotel_id) VALUES (?, ?, ?, ?)");
                                statementInsertar.setString(1, tfNombre.getText());
                                statementInsertar.setString(2, tfContraseña.getText());
                                statementInsertar.setString(3, cbDepartamento.getValue());
                                statementInsertar.setInt(4, idHotel);

                                if (statementInsertar.executeUpdate() == 1) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("AVISO");
                                    alert.setHeaderText("CORRECTO");
                                    alert.setContentText("El trabajador se ha agregado correctamente");
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

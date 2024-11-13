package Principal;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Esta es la clase principal de la aplicación, la cuál inicia la interfaz principal.
 * 
 * @author Marcos
 */
public class Principal extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Abre la interfaz del archivo Interfaz.fxml
        Parent root = FXMLLoader.load(getClass().getResource("/interfaz/login.fxml"));
        primaryStage.setResizable(true);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
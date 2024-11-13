package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Marcos
 */
public class ConnectionDB {
    // Utilizamos el objeto Connection para gestionar la conexión con la base de datos
    private Connection conexionBD;
    
    // Creamos el método para comenzar la conexión con la base de datos
    public void openConnection() throws SQLException{
        this.conexionBD = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hotelmaster", "root", "");
    }
    
    // Creamos el método para terminar la conexión con la base de datos
    public void closeConnection() throws SQLException{
        // Cerramos la conexión
        if (this.conexionBD != null && !this.conexionBD.isClosed()) {
            this.conexionBD.close();
        }
    }
    
    // Creamos el método para saber cuál es la conexión actual
    public Connection getConnection(){
        // Devolvemos el objeto Connection para utilizarlo más adelante con las operaciones con la base de datos
        return this.conexionBD;
    }
}

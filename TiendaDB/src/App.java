import UI.LoginMenu;

import java.sql.Connection;
import java.sql.SQLException;
import Config.ConnectDB;
import Repository.Pantalla;

public class App {
    public static void main(String[] args) throws Exception {
        ConnectDB cdb = null;
        Connection connection = null;
        try {
            connection = cdb.getConn();
            System.out.println("Conexion exitosa");
            Pantalla.limpiarPantalla();
            LoginMenu.mostrsarlogin();
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        } finally {
            cdb.closeConnection(connection);
        }
    }
}
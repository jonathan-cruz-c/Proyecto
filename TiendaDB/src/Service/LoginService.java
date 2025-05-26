package Service;

import Config.ConnectDB;
import Model.Usuario;
import Repository.LoginRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginService {
    public static Usuario iniciarSesion(String correouser, String contraseña) throws SQLException {
        Connection connection = null;
        Usuario usuario = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return null;
            }
            usuario = LoginRepository.login(connection, correouser, contraseña);
        } catch (SQLException e) {
            System.out.println("Error en LoginService: " + e.getMessage());
        }
        return usuario;
    }

    public static void registrarse(Usuario nuevousuario) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            nuevousuario.setRol_id(2);
            LoginRepository.loginRegistro(connection, nuevousuario);
        } catch (Exception e) {
            System.out.println("Error en LoginService al registrar usuario: " + e.getMessage());
        }
    }
}
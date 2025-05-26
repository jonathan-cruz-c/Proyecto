package Service;

import Config.ConnectDB;
import Model.Usuario;
import Repository.UsuarioRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuariosService {
    // ! Insertar usuario
    public static void insertarUsuario(Usuario usuario, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar con la base de datos");
                return;
            }
            UsuarioRepository.insertarUsu(connection, usuario, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en UsuarioService al insertar: " + e.getMessage());
        }
    }

    // ! Actualizar usuario
    public static void actualizarUsuario(Usuario usuario, int idcliente, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar con la base de datos");
                return;
            }
            UsuarioRepository.actualizarUsu(connection, usuario, idcliente, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en UsuarioService al actualizar: " + e.getMessage());
        }
    }

    // ! Eliminar usuario
    public static void eliminarUsuario(int ideliminar, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar con la base de datos");
                return;
            }
            UsuarioRepository.eliminarUsu(connection, ideliminar, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en UsuarioService al eliminar: " + e.getMessage());
        }
    }

    // ! Consultar usuarios (admin ve todos, cliente solo el suyo)
    public static ArrayList<Usuario> consultarUsuarios(int idsolicitante) {
        Connection connection = null;
        ArrayList<Usuario> listausuarios = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar con la base de datos");
                return listausuarios;
            }
            listausuarios = UsuarioRepository.consultarUsu(connection, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en UsuarioService al consultar: " + e.getMessage());
        }
        return listausuarios;
    }
}
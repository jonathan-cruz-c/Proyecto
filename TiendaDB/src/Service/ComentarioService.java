package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Comentario;
import Repository.ComentarioRepository;

public class ComentarioService {
    // ! Insertar un comentario
    public static void insertarComentario(int productoid, String comentario, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                ComentarioRepository.insertarComentario(connection, productoid, comentario, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en ComentarioService.insertarComentario: " + e.getMessage());
        }
    }

    // ! Actualizar un comentario
    public static void actualizarComentario(int idcomentario, String nuevocomentario, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                ComentarioRepository.actualizarComentario(connection, idcomentario, nuevocomentario, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en ComentarioService.actualizarComentario: " + e.getMessage());
        }
    }

    // ! Eliminar un comentario
    public static void eliminarComentario(int idcomentario, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                ComentarioRepository.eliminarComentario(connection, idcomentario, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en ComentarioService.eliminarComentario: " + e.getMessage());
        }
    }

    // ! Consultar todos los comentarios
    public static ArrayList<Comentario> consultarComentarios() {
        Connection connection = null;
        ArrayList<Comentario> listaComentarios = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                listaComentarios = ComentarioRepository.consultarComentarios(connection);
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en ComentarioService.consultarComentarios: " + e.getMessage());
        }
        return listaComentarios;
    }
}
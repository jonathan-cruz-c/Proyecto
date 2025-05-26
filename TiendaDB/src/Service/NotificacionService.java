package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Notificacion;
import Repository.NotificacionRepository;

public class NotificacionService {
    // ! Insertar notificación (solo admin)
    public static void insertar(Notificacion notificacion, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                NotificacionRepository.insertarNotificacion(connection, notificacion, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en NotificacionService al insertar: " + e.getMessage());
        }
    }

    // ! Actualizar notificación (solo admin)
    public static void actualizar(Notificacion notificacion, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                NotificacionRepository.actualizarNotificacion(connection, notificacion, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en NotificacionService al actualizar: " + e.getMessage());
        }
    }

    // ! Eliminar notificación (admin cualquiera, cliente solo las suyas)
    public static void eliminar(int idnotificacion, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                NotificacionRepository.eliminarNotificacion(connection, idnotificacion, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en NotificacionService al eliminar: " + e.getMessage());
        }
    }

    // ! Consultar notificaciones (admin todas, cliente solo las suyas)
    public static ArrayList<Notificacion> consultar(int idsolicitante) {
        Connection connection = null;
        ArrayList<Notificacion> notificaciones = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                notificaciones = NotificacionRepository.consultarNotificaciones(connection, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error en NotificacionService al consultar: " + e.getMessage());
        }
        return notificaciones;
    }
}
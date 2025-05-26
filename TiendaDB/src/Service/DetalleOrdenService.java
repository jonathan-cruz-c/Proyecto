package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.DetalleOrden;
import Repository.DetalleOrdenRepository;

public class DetalleOrdenService {
    // ! Insertar un nuevo detalle de orden
    public static void insertarDetalleOrden(int ordenid, int direccionid, int pagoid, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                DetalleOrdenRepository.insertarDetalleOrden(connection, ordenid, direccionid, pagoid, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en DetalleOrdenService.insertarDetalleOrden: " + e.getMessage());
        }
    }

    // ! Actualizar un detalle de orden (solo admin)
    public static void actualizarDetalleOrden(DetalleOrden detalle, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                DetalleOrdenRepository.actualizarDetalleOrden(connection, detalle, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en DetalleOrdenService.actualizarDetalleOrden: " + e.getMessage());
        }
    }

    // ! Eliminar un detalle de orden (solo admin)
    public static void eliminarDetalleOrden(int iddetalle, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                DetalleOrdenRepository.eliminarDetalleOrden(connection, iddetalle, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en DetalleOrdenService.eliminarDetalleOrden: " + e.getMessage());
        }
    }

    // ! Consultar detalles de orden (admin ve todos, cliente solo los suyos)
    public static ArrayList<DetalleOrden> consultarDetalles(int idsolicitante) {
        Connection connection = null;
        ArrayList<DetalleOrden> detalles = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                detalles = DetalleOrdenRepository.consultarDetalles(connection, idsolicitante);
            } else {
                System.out.println("No se pudo establecer conexión con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en DetalleOrdenService.consultarDetalles: " + e.getMessage());
        }
        return detalles;
    }
}
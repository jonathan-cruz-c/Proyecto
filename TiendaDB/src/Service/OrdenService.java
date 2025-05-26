package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Orden;
import Repository.OrdenRepository;

public class OrdenService {
    // ! Insertar una orden
    public static void insertarOrden(Orden orden, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            OrdenRepository.insertarOrd(connection, orden, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en OrdenService.insertarOrden: " + e.getMessage());
        }
    }

    // ! Actualizar una orden
    public static void actualizarOrden(Orden orden, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            OrdenRepository.actualizarOrd(connection, orden, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en OrdenService.actualizarOrden: " + e.getMessage());
        }
    }

    // ! Eliminar una orden
    public static void eliminarOrden(int idorden, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            OrdenRepository.eliminarOrd(connection, idorden, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en OrdenService.eliminarOrden: " + e.getMessage());
        }
    }

    // ! Consultar órdenes (devuelve ArrayList)
    public static ArrayList<Orden> consultarOrdenes(int idsolicitante) {
        Connection connection = null;
        ArrayList<Orden> listaOrdenes = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return listaOrdenes;
            }
            listaOrdenes = OrdenRepository.consultarOrd(connection, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en OrdenService.consultarOrdenes: " + e.getMessage());
        }
        return listaOrdenes;
    }
}
package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Inventario;
import Repository.InventarioRepository;

public class InventarioService {
    // ! Insertar inventario (solo admin)
    public static void insertarInventario(Inventario inventario, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                InventarioRepository.insertarInventario(connection, inventario, idsolicitante);
            } else {
                System.out.println("Error al conectar a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en InventarioService.insertarInventario: " + e.getMessage());
        }
    }

    // ! Actualizar inventario (solo admin)
    public static void actualizarInventario(Inventario inventario, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                InventarioRepository.actualizarInventario(connection, inventario, idsolicitante);
            } else {
                System.out.println("Error al conectar a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en InventarioService.actualizarInventario: " + e.getMessage());
        }
    }

    // ! Eliminar inventario (solo admin)
    public static void eliminarInventario(int idinventario, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                InventarioRepository.eliminarInventario(connection, idinventario, idsolicitante);
            } else {
                System.out.println("Error al conectar a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en InventarioService.eliminarInventario: " + e.getMessage());
        }
    }

    // ! Consultar inventarios (solo admin)
    public static ArrayList<Inventario> consultarInventarios(int idsolicitante) {
        Connection connection = null;
        ArrayList<Inventario> inventarios = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                inventarios = InventarioRepository.consultarInventarios(connection, idsolicitante);
            } else {
                System.out.println("Error al conectar a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error en InventarioService.consultarInventarios: " + e.getMessage());
        }
        return inventarios;
    }
}
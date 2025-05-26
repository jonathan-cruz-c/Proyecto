package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Producto;
import Repository.ProductoRepository;

public class ProductoService {
    // ! Insertar producto (solo admin)
    public static void insertarProducto(Producto producto, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            ProductoRepository.insertarProd(connection, producto, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en ProductoService.insertarProducto: " + e.getMessage());
        }
    }

    // ! Actualizar producto (solo admin)
    public static void actualizarProducto(Producto producto, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            ProductoRepository.actualizarProd(connection, producto, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en ProductoService.actualizarProducto: " + e.getMessage());
        }
    }

    // ! Eliminar producto (solo admin)
    public static void eliminarProducto(int idproducto, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            ProductoRepository.eliminarProd(connection, idproducto, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en ProductoService.eliminarProducto: " + e.getMessage());
        }
    }

    // ! Consultar todos los productos
    public static ArrayList<Producto> consultarProductos() {
        Connection connection = null;
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return productos;
            }
            productos = ProductoRepository.consultarProd(connection);
        } catch (SQLException e) {
            System.out.println("Error en ProductoService.consultarProductos: " + e.getMessage());
        }
        return productos;
    }
}
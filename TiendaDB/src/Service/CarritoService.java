package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Carrito;
import Repository.CarritoRepository;

public class CarritoService {
    // ! Insertar un carrito
    public static void insertarCarrito(int productoid, int usuarioid, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                CarritoRepository.insertarCarrito(connection, productoid, usuarioid, idsolicitante);
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en CarritoService al insertar: " + e.getMessage());
        }
    }

    // ! Actualizar un carrito
    public static void actualizarCarrito(Carrito carrito, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                CarritoRepository.actualizarCarrito(connection, carrito, idsolicitante);
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en CarritoService al actualizar: " + e.getMessage());
        }
    }

    // ! Eliminar un carrito
    public static void eliminarCarrito(int carritoid, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                CarritoRepository.eliminarCarrito(connection, carritoid, idsolicitante);
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en CarritoService al eliminar: " + e.getMessage());
        }
    }

    // ! Consultar carritos
    public static ArrayList<Carrito> consultarCarritos(int idsolicitante) {
        Connection connection = null;
        ArrayList<Carrito> listaCarritos = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection != null) {
                listaCarritos = CarritoRepository.consultarCarritos(connection, idsolicitante);
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error en CarritoService al consultar: " + e.getMessage());
        }
        return listaCarritos;
    }
}
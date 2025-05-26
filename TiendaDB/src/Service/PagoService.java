package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Pago;
import Repository.PagoRepository;

public class PagoService {
    // ! Insertar pago (solo admin)
    public static void insertarPago(int idsolicitante, int productoid) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            PagoRepository.insertarPago(connection, idsolicitante, productoid);
        } catch (SQLException e) {
            System.out.println("Error en PagoService al insertar pago: " + e.getMessage());
        }
    }

    // ! Actualizar pago (solo admin)
    public static void actualizarPago(Pago pago, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            PagoRepository.actualizarPago(connection, pago, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en PagoService al actualizar pago: " + e.getMessage());
        }
    }

    // ! Eliminar pago (solo admin)
    public static void eliminarPago(int idpago, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            PagoRepository.eliminarPago(connection, idpago, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en PagoService al eliminar pago: " + e.getMessage());
        }
    }

    // ! Consultar pagos (admin ve todos, cliente ve solo los suyos)
    public static ArrayList<Pago> consultarPagos(int idsolicitante) {
        Connection connection = null;
        ArrayList<Pago> listaPagos = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return listaPagos;
            }
            listaPagos = PagoRepository.consultarPagos(connection, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en PagoService al consultar pagos: " + e.getMessage());
        }
        return listaPagos;
    }
}
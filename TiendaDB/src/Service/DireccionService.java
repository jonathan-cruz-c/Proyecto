package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Direccion;
import Repository.DireccionRepository;

public class DireccionService {
    // ! Insertar dirección
    public static void agregarDireccion(Direccion direccion, int idusuarioSolicita) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar con la base de datos");
                return;
            }
            DireccionRepository.insertarDirec(connection, direccion, idusuarioSolicita);
        } catch (SQLException e) {
            System.out.println("Error al insertar dirección en DireccionService: " + e.getMessage());
        }
    }

    // ! Actualizar dirección (admin cualquiera, cliente solo las suyas)
    public static void actualizarDireccion(Direccion direccion, int idusuariosolicita) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar con la base de datos");
                return;
            }
            DireccionRepository.actualizarDirec(connection, direccion, idusuariosolicita);
        } catch (SQLException e) {
            System.out.println("Error al actualizar dirección en DireccionService: " + e.getMessage());
        }
    }

    // ! Eliminar dirección (solo admin)
    public static void eliminarDireccion(int iddireccion, int idusuariosolicita) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar con la base de datos");
                return;
            }
            DireccionRepository.eliminarDirec(connection, iddireccion, idusuariosolicita);
        } catch (SQLException e) {
            System.out.println("Error al eliminar dirección en DireccionService: " + e.getMessage());
        }
    }

    // ! Consultar direcciones (admin ve todas, cliente solo las suyas)
    public static ArrayList<Direccion> obtenerDirecciones(int idusuariosolicita) {
        ArrayList<Direccion> lista = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar con la base de datos");
                return lista;
            }
            lista = DireccionRepository.consultarDirec(connection, idusuariosolicita);
        } catch (SQLException e) {
            System.out.println("Error al consultar direcciones en DireccionService: " + e.getMessage());
        }
        return lista;
    }
}
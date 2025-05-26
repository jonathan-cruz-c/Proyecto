package Service;

import Config.ConnectDB;
import Model.Rol;
import Repository.RolRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RolService {
    // ! Insertar rol
    public static void insertarRol(Rol rol, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            RolRepository.insertarRol(connection, rol, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en RolService.insertarRol: " + e.getMessage());
        }
    }

    // ! Actualizar rol
    public static void actualizarRol(Rol rol, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            RolRepository.actualizarRol(connection, rol, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en RolService.actualizarRol: " + e.getMessage());
        }
    }

    // ! Eliminar rol
    public static void eliminarRol(int idrol, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            RolRepository.eliminarRol(connection, idrol, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en RolService.eliminarRol: " + e.getMessage());
        }
    }

    // ! Consultar roles
    public static ArrayList<Rol> consultarRoles(int idsolicitante) {
        Connection connection = null;
        ArrayList<Rol> lista = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return lista;
            }
            lista = RolRepository.consultarRol(connection, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en RolService.consultarRoles: " + e.getMessage());
        }
        return lista;
    }
}
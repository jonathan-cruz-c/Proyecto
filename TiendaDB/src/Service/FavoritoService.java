package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Favorito;
import Repository.FavoritoRepository;

public class FavoritoService {
    // ! Insertar Favorito
    public static void insertarFavorito(Favorito favorito, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("No se pudo conectar a la base de datos");
                return;
            }
            FavoritoRepository.insertarFavorito(connection, favorito, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en FavoritoService (insertar): " + e.getMessage());
        }
    }

    // ! Actualizar Favorito
    public static void actualizarFavorito(Favorito favorito, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("No se pudo conectar a la base de datos");
                return;
            }
            FavoritoRepository.actualizarFavorito(connection, favorito, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en FavoritoService (actualizar): " + e.getMessage());
        }
    }

    // ! Eliminar Favorito
    public static void eliminarFavorito(int idfavorito, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("No se pudo conectar a la base de datos");
                return;
            }
            FavoritoRepository.eliminarFavorito(connection, idfavorito, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en FavoritoService (eliminar): " + e.getMessage());
        }
    }

    // ! Consultar Favorito
    public static ArrayList<Favorito> consultarFavoritos(int idsolicitante) {
        Connection connection = null;
        ArrayList<Favorito> favoritos = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("No se pudo conectar a la base de datos");
                return favoritos;
            }
            favoritos = FavoritoRepository.consultarFavoritos(connection, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en FavoritoService (consultar): " + e.getMessage());
        }
        return favoritos;
    }
}
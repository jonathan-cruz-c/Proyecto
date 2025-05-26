package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Config.ConnectDB;
import Model.Categoria;
import Repository.CategoriaRepository;

public class CategoriaService {
    // ! Insertar categoría (solo admin)
    public static void insertarCategoria(Categoria categoria, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            CategoriaRepository.insertarCate(connection, categoria, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en CategoriaService al insertar: " + e.getMessage());
        }
    }

    // ! Actualizar categoría (solo admin)
    public static void actualizarCategoria(Categoria categoria, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            CategoriaRepository.actualizarCate(connection, categoria, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en CategoriaService al actualizar: " + e.getMessage());
        }
    }

    // ! Eliminar categoría (solo admin)
    public static void eliminarCategoria(int idcategoria, int idsolicitante) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return;
            }
            CategoriaRepository.eliminarCate(connection, idcategoria, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en CategoriaService al eliminar: " + e.getMessage());
        }
    }

    // ! Consultar categorías (admin ve ID, cliente no)
    public static ArrayList<Categoria> consultarCategorias(int idsolicitante) {
        Connection connection = null;
        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        try {
            connection = ConnectDB.getConn();
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos");
                return listaCategorias;
            }
            listaCategorias = CategoriaRepository.consultarCate(connection, idsolicitante);
        } catch (SQLException e) {
            System.out.println("Error en CategoriaService al consultar: " + e.getMessage());
        }
        return listaCategorias;
    }
}
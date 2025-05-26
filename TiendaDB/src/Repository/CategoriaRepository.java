package Repository;

import java.util.ArrayList;
import Model.Categoria;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriaRepository {
    // ! Inserta Categoria (solo admin)
    public static void insertarCate(Connection connection, Categoria categoria, int idsolicitante) throws SQLException {
        PreparedStatement rps = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String verificarrol = "select rol_id from tienda.usuarios where id=?";
            rps = connection.prepareStatement(verificarrol);
            rps.setInt(1, idsolicitante);
            rs = rps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                String insert = "insert into tienda.categorias (nombre) values (?)";
                ps = connection.prepareStatement(insert);
                ps.setString(1, categoria.getNombre());
                ps.executeUpdate();
                System.out.println("Categoría insertado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar categoría: " + e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (rps != null)
                    rps.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            }
        }
    }

    // ! Actualiza datos de la tabla Categoria (solo admin)
    public static void actualizarCate(Connection connection, Categoria categoria, int idsolicitante)
            throws SQLException {
        PreparedStatement rps = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String verificarrol = "select rol_id from tienda.usuarios where id=?";
            rps = connection.prepareStatement(verificarrol);
            rps.setInt(1, idsolicitante);
            rs = rps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                String update = "update tienda.categorias set nombre=? where id=?";
                ps = connection.prepareStatement(update);
                ps.setString(1, categoria.getNombre());
                ps.setInt(2, categoria.getId());
                int actualizado = ps.executeUpdate();
                if (actualizado > 0) {
                    System.out.println("Categoría actualizada correctamente");
                } else {
                    System.out.println("Categoría no encontrada");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (rps != null)
                    rps.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            }
        }
    }

    // ! Eliminar categoria
    public static void eliminarCate(Connection connection, int idcategoria, int idsolicitante) throws SQLException {
        PreparedStatement rps = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String verificarrol = "select rol_id from tienda.usuarios where id=?";
            rps = connection.prepareStatement(verificarrol);
            rps.setInt(1, idsolicitante);
            rs = rps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                String delete = "delete from tienda.categorias where id=?";
                ps = connection.prepareStatement(delete);
                ps.setInt(1, idcategoria);
                int eliminado = ps.executeUpdate();
                if (eliminado > 0) {
                    System.out.println("Categoría eliminada correctamente");
                } else {
                    System.out.println("Categoría no encontrada");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (rps != null)
                    rps.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            }
        }
    }

    // ! Consultar categorias
    public static ArrayList<Categoria> consultarCate(Connection connection, int idsolicitante) throws SQLException {
        ArrayList<Categoria> al = new ArrayList<>();
        PreparedStatement rps = null;
        ResultSet rrs = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String verificarrol = "select rol_id from tienda.usuarios where id=?";
            rps = connection.prepareStatement(verificarrol);
            rps.setInt(1, idsolicitante);
            rrs = rps.executeQuery();
            if (rrs.next()) {
                int rol = rrs.getInt("rol_id");
                String consulta = "select * from tienda.categorias";
                ps = connection.prepareStatement(consulta);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setNombre(rs.getString("nombre"));
                    if (rol == 1) {
                        categoria.setId(rs.getInt("id"));
                    }
                    al.add(categoria);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar categorías: " + e.getMessage());
        } finally {
            try {
                if (rrs != null)
                    rrs.close();
                if (rps != null)
                    rps.close();
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            }
        }
        return al;
    }
}
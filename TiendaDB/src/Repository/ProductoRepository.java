package Repository;

import java.util.ArrayList;
import Model.Producto;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoRepository {
    // ! Inserta Producto (solo admin)
    public static void insertarProd(Connection connection, Producto producto, int idsolicitante) throws SQLException {
        PreparedStatement verrolps = null;
        ResultSet rsrol = null;
        PreparedStatement ps = null;

        try {
            String verificarrol = "select rol_id from tienda.usuarios where id = ?";
            verrolps = connection.prepareStatement(verificarrol);
            verrolps.setInt(1, idsolicitante);
            rsrol = verrolps.executeQuery();
            if (rsrol.next() && rsrol.getInt("rol_id") == 1) {
                String insert = "insert into tienda.productos (categoría_id, nombre, descripcion, precio, stock) values (?, ?, ?, ?, ?)";
                ps = connection.prepareStatement(insert);
                ps.setInt(1, producto.getCategoría_id());
                ps.setString(2, producto.getNombre());
                ps.setString(3, producto.getDescripcion());
                ps.setDouble(4, producto.getPrecio());
                ps.setInt(5, producto.getStock());
                ps.executeUpdate();
                System.out.println("Producto insertado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
        } finally {
            try {
                if (rsrol != null)
                    rsrol.close();
                if (verrolps != null)
                    verrolps.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            }
        }
    }

    // ! Actualizar producto (solo admin)
    public static void actualizarProd(Connection connection, Producto producto, int idsolicitante) throws SQLException {
        PreparedStatement verrolps = null;
        ResultSet rsrol = null;
        PreparedStatement ps = null;

        try {
            String verificarrol = "select rol_id from tienda.usuarios where id = ?";
            verrolps = connection.prepareStatement(verificarrol);
            verrolps.setInt(1, idsolicitante);
            rsrol = verrolps.executeQuery();
            if (rsrol.next() && rsrol.getInt("rol_id") == 1) {
                String update = "update tienda.productos set categoría_id = ?, nombre = ?, descripcion = ?, precio = ?, stock = ? where id = ?";
                ps = connection.prepareStatement(update);
                ps.setInt(1, producto.getCategoría_id());
                ps.setString(2, producto.getNombre());
                ps.setString(3, producto.getDescripcion());
                ps.setDouble(4, producto.getPrecio());
                ps.setInt(5, producto.getStock());
                ps.setInt(6, producto.getId());
                ps.executeUpdate();
                System.out.println("Producto actualizado correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        } finally {
            try {
                if (rsrol != null)
                    rsrol.close();
                if (verrolps != null)
                    verrolps.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            }
        }
    }

    public static void eliminarProd(Connection connection, int idProducto, int idsolicitante) throws SQLException {
        PreparedStatement verrolps = null;
        ResultSet rsrol = null;
        PreparedStatement ps = null;

        try {
            String verificarrol = "select rol_id from tienda.usuarios where id = ?";
            verrolps = connection.prepareStatement(verificarrol);
            verrolps.setInt(1, idsolicitante);
            rsrol = verrolps.executeQuery();
            if (rsrol.next() && rsrol.getInt("rol_id") == 1) {
                String delete = "delete from tienda.productos where id = ?";
                ps = connection.prepareStatement(delete);
                ps.setInt(1, idProducto);
                int eliminado = ps.executeUpdate();
                if (eliminado > 0) {
                    System.out.println("Producto eliminado correctamente");
                } else {
                    System.out.println("No se encontró el producto");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        } finally {
            try {
                if (rsrol != null)
                    rsrol.close();
                if (verrolps != null)
                    verrolps.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            }
        }
    }

    // ! Consultar Producto
    public static ArrayList<Producto> consultarProd(Connection connection) throws SQLException {
        ArrayList<Producto> al = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "select * from tienda.productos";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setCategoría_id(rs.getInt("categoría_id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getInt("precio"));
                p.setStock(rs.getInt("stock"));
                al.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar productos: " + e.getMessage());
        } finally {
            try {
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
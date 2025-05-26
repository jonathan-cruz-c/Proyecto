package Repository;

import java.util.ArrayList;
import Model.Inventario;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventarioRepository {
    // ! Insertar Inventario (solo admin)
    public static void insertarInventario(Connection connection, Inventario inventario, int idsolicitante)
            throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String verificar = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(verificar);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                ps.close();
                String insert = "insert into tienda.inventarios (producto_id, cantidad) values (?, ?)";
                ps = connection.prepareStatement(insert);
                ps.setInt(1, inventario.getProducto_id());
                ps.setInt(2, inventario.getCantidad());
                ps.executeUpdate();
                System.out.println("Inventario insertado correctamente");
            } else {
                System.out.println("Acceso denegado. Solo administradores pueden insertar inventario.");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar inventario: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    // ! Actualizar Inventario (solo admin)
    public static void actualizarInventario(Connection connection, Inventario inventario, int idsolicitante)
            throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String verificar = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(verificar);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                ps.close();
                String update = "update tienda.inventarios set producto_id = ?, cantidad = ? where id = ?";
                ps = connection.prepareStatement(update);
                ps.setInt(1, inventario.getProducto_id());
                ps.setInt(2, inventario.getCantidad());
                ps.setInt(3, inventario.getId());
                ps.executeUpdate();
                System.out.println("Inventario actualizado correctamente");
            } else {
                System.out.println("Acceso denegado. Solo administradores pueden actualizar inventario");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar inventario: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    // ! Eliminar Inventario (solo admin)
    public static void eliminarInventario(Connection connection, int idinventario, int idsolicitante)
            throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String verificar = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(verificar);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                ps.close();
                String delete = "delete from tienda.inventarios where id = ?";
                ps = connection.prepareStatement(delete);
                ps.setInt(1, idinventario);
                ps.executeUpdate();
                System.out.println("Inventario eliminado correctamente");
            } else {
                System.out.println("Acceso denegado. Solo administradores pueden eliminar inventario.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar inventario: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    // ! Consultar Inventario (solo admin)
    public static ArrayList<Inventario> consultarInventarios(Connection connection, int idsolicitante)
            throws SQLException {
        ArrayList<Inventario> al = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String verificar = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(verificar);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                ps.close();
                rs.close();
                String query = "select * from tienda.inventarios";
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Inventario inv = new Inventario();
                    inv.setId(rs.getInt("id"));
                    inv.setProducto_id(rs.getInt("producto_id"));
                    inv.setCantidad(rs.getInt("cantidad"));
                    al.add(inv);
                }
            } else {
                System.out.println("Acceso denegado. Solo administradores pueden consultar inventario");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar inventarios: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return al;
    }
}
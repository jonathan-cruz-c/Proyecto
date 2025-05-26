package Repository;

import java.util.ArrayList;
import Model.Orden;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdenRepository {
    // ! Inserta Producto
    public static void insertarOrd(Connection connection, Orden orden, int idsolicitante) throws SQLException {
        PreparedStatement ps = null;
        try {
            String verificar = "select rol_id from tienda.usuarios where id=?";
            PreparedStatement verrol = connection.prepareStatement(verificar);
            verrol.setInt(1, idsolicitante);
            ResultSet rs = verrol.executeQuery();
            if (rs.next()) {
                int rol = rs.getInt("rol_id");
                if (rol == 1) { // Admin puede insertar con todos los campos
                    String insert = "insert into tienda.ordenes (usuario_id, producto_id, cantidad) values (?, ?, ?)";
                    ps = connection.prepareStatement(insert);
                    ps.setInt(1, orden.getUsuario_id());
                    ps.setInt(2, orden.getProducto_id());
                    ps.setInt(3, orden.getCantidad());
                    ps.executeUpdate();
                    System.out.println("Orden agregada correctamente (Admin).");
                } else { // Cliente solo puede insertar sus propias órdenes
                    String insert = "insert into tienda.ordenes (usuario_id, producto_id, cantidad) values (?, ?, ?)";
                    ps = connection.prepareStatement(insert);
                    ps.setInt(1, idsolicitante);
                    ps.setInt(2, orden.getProducto_id());
                    ps.setInt(3, orden.getCantidad());
                    ps.executeUpdate();
                    System.out.println("Orden realizada correctamente (Cliente).");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al insertar orden: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
            }
        }
    }

    // ! Actualizar orden (solo admin)
    public static void actualizarOrd(Connection connection, Orden orden, int idsolicitante) throws SQLException {
        PreparedStatement ps = null;
        try {
            String verificar = "select rol_id from tienda.usuarios where id=?";
            PreparedStatement verrol = connection.prepareStatement(verificar);
            verrol.setInt(1, idsolicitante);
            ResultSet rs = verrol.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                String update = "update tienda.ordenes set usuario_id=?, producto_id=?, cantidad=? where id=?";
                ps = connection.prepareStatement(update);
                ps.setInt(1, orden.getUsuario_id());
                ps.setInt(2, orden.getProducto_id());
                ps.setInt(3, orden.getCantidad());
                ps.setInt(4, orden.getId());
                int actualizado = ps.executeUpdate();
                if (actualizado > 0) {
                    System.out.println("Orden actualizada correctamente");
                } else {
                    System.out.println("No se encontró la orden.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar orden: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
            }
        }
    }

    // ! Eliminar orden (solo admin)
    public static void eliminarOrd(Connection connection, int idorden, int idsolicitante) throws SQLException {
        PreparedStatement ps = null;
        try {
            String verificar = "select rol_id from tienda.usuarios where id=?";
            PreparedStatement verrol = connection.prepareStatement(verificar);
            verrol.setInt(1, idsolicitante);
            ResultSet rs = verrol.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                String delete = "delete from tienda.ordenes where id=?";
                ps = connection.prepareStatement(delete);
                ps.setInt(1, idorden);
                int eliminado = ps.executeUpdate();
                if (eliminado > 0) {
                    System.out.println("Orden eliminada correctamente");
                } else {
                    System.out.println("No se encontró la orden");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar orden: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
            }
        }
    }

    // ! Consultar órdenes (admin ve todo, cliente solo las suyas)
    public static ArrayList<Orden> consultarOrd(Connection connection, int idsolicitante) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Orden> al = new ArrayList<>();
        try {
            String verificar = "select rol_id from tienda.usuarios where id=?";
            PreparedStatement verrol = connection.prepareStatement(verificar);
            verrol.setInt(1, idsolicitante);
            ResultSet resultado = verrol.executeQuery();
            if (resultado.next()) {
                int rol = resultado.getInt("rol_id");
                String consulta;
                if (rol == 1) {
                    consulta = "select * from tienda.ordenes";
                    ps = connection.prepareStatement(consulta);
                } else {
                    consulta = "select * from tienda.ordenes where usuario_id=?";
                    ps = connection.prepareStatement(consulta);
                    ps.setInt(1, idsolicitante);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Orden orden = new Orden();
                    orden.setId(rs.getInt("id"));
                    orden.setUsuario_id(rs.getInt("usuario_id"));
                    orden.setProducto_id(rs.getInt("producto_id"));
                    orden.setCantidad(rs.getInt("cantidad"));
                    al.add(orden);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar órdenes: " + e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
            }
        }
        return al;
    }
}
package Repository;

import java.util.ArrayList;
import Model.DetalleOrden;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetalleOrdenRepository {
    // ! Inserta detalle de orden (solo admin o cliente insertando su propio pedido)
    public static void insertarDetalleOrden(Connection connection, int ordenid, int direccionid, int pagoid,
            int idsolicitante) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String rolquery = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(rolquery);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rol = rs.getInt("rol_id");
                ps.close();
                rs.close();
                if (rol == 1) {
                    String insert = "insert into tienda.detalles_ordenes (orden_id, direccion_id, pago_id, fecha_llegada) "
                            + "values (?, ?, ?, current_date + interval '3 day')";
                    ps = connection.prepareStatement(insert);
                    ps.setInt(1, ordenid);
                    ps.setInt(2, direccionid);
                    ps.setInt(3, pagoid);
                    ps.executeUpdate();
                    System.out.println("Detalle de orden insertado por admin");
                } else {
                    String verificaorden = "select usuario_id from tienda.ordenes where id = ?";
                    ps = connection.prepareStatement(verificaorden);
                    ps.setInt(1, ordenid);
                    rs = ps.executeQuery();
                    if (rs.next() && rs.getInt("usuario_id") == idsolicitante) {
                        ps.close();
                        rs.close();
                        String insert = "insert into tienda.detalles_ordenes (orden_id, direccion_id, pago_id, fecha_llegada) "
                                + "values (?, ?, ?, current_date + interval '3 day')";
                        ps = connection.prepareStatement(insert);
                        ps.setInt(1, ordenid);
                        ps.setInt(2, direccionid);
                        ps.setInt(3, pagoid);
                        ps.executeUpdate();
                        System.out.println("Detalle de orden insertado");
                    } else {
                        System.out.println("No autorizado. La orden no pertenece al usuario.");
                    }
                }
            } else {
                System.out.println("Usuario no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar detalle de orden: " + e.getMessage());
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

    // ! Actualizar detalle de orden (solo admin)
    public static void actualizarDetalleOrden(Connection connection, DetalleOrden detalle, int idsolicitante)
            throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String rolquery = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(rolquery);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                ps.close();
                String update = "update tienda.detalles_ordenes set orden_id = ?, direccion_id = ?, pago_id = ?, fecha_llegada = ? where id = ?";
                ps = connection.prepareStatement(update);
                ps.setInt(1, detalle.getOrden_id());
                ps.setInt(2, detalle.getDireccion_id());
                ps.setInt(3, detalle.getPago_id());
                ps.setDate(4, detalle.getFecha_llegada());
                ps.setInt(5, detalle.getId());
                int od = ps.executeUpdate();
                if (od > 0) {
                    System.out.println("Detalle actualizado correctamente");
                } else {
                    System.out.println("Detalle no encontrado");
                }
            } else {
                System.out.println("No autorizado. Solo el administrador puede actualizar detalles de orden");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar detalle: " + e.getMessage());
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

    // ! Eliminar detalle de orden (solo admin)
    public static void eliminarDetalleOrden(Connection connection, int iddetalle, int idsolicitante)
            throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String rolquery = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(rolquery);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                ps.close();
                String delete = "delete from tienda.detalles_ordenes where id = ?";
                ps = connection.prepareStatement(delete);
                ps.setInt(1, iddetalle);
                int de = ps.executeUpdate();
                if (de > 0) {
                    System.out.println("Detalle eliminado correctamente");
                } else {
                    System.out.println("Detalle no encontrado");
                }
            } else {
                System.out.println("No autorizado. Solo el administrador puede eliminar detalles de orden");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar detalle: " + e.getMessage());
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

    // ! Consultar detalle de orden
    public static ArrayList<DetalleOrden> consultarDetalles(Connection connection, int idsolicitante)
            throws SQLException {
        ArrayList<DetalleOrden> al = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String rolquery = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(rolquery);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rol = rs.getInt("rol_id");
                ps.close();
                rs.close();
                if (rol == 1) {
                    String query = "SELECT * FROM tienda.detalles_ordenes";
                    ps = connection.prepareStatement(query);
                } else {
                    String query = "select d.* from tienda.detalles_ordenes d "
                            + "inner join tienda.ordenes o on d.orden_id = o.id " + "where o.usuario_id = ?";
                    ps = connection.prepareStatement(query);
                    ps.setInt(1, idsolicitante);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    DetalleOrden d = new DetalleOrden();
                    d.setId(rs.getInt("id"));
                    d.setOrden_id(rs.getInt("orden_id"));
                    d.setDireccion_id(rs.getInt("direccion_id"));
                    d.setPago_id(rs.getInt("pago_id"));
                    d.setFecha_llegada(rs.getDate("fecha_llegada"));
                    al.add(d);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar detalles: " + e.getMessage());
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
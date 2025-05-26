package Repository;

import java.util.ArrayList;
import Model.Pago;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PagoRepository {
    // ! Inserta Pago (solo admin)
    public static void insertarPago(Connection connection, int idsolicitante, int productoid) throws SQLException {
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
                String precioquery = "select precio from tienda.productos where id = ?";
                ps = connection.prepareStatement(precioquery);
                ps.setInt(1, productoid);
                rs = ps.executeQuery();
                if (rs.next()) {
                    double monto = rs.getDouble("precio");
                    ps.close();
                    rs.close();
                    String insert = "insert into tienda.pagos (fecha_pago, monto) values (current_date + interval '3 day', ?)";
                    ps = connection.prepareStatement(insert);
                    ps.setDouble(1, monto);
                    ps.executeUpdate();
                    if (rol == 1) {
                        System.out.println("Pago insertado correctamente");
                    }
                } else {
                    System.out.println("Producto no encontrado");
                }
            } else {
                System.out.println("Usuario no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar pago: " + e.getMessage());
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

    // ! Actualizar Pago (solo admin)
    public static void actualizarPago(Connection connection, Pago pago, int idsolicitante) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String rolquery = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(rolquery);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                ps.close();
                String update = "update tienda.pagos set fecha_pago = ?, monto = ? where id = ?";
                ps = connection.prepareStatement(update);
                ps.setDate(1, pago.getFecha_pago());
                ps.setDouble(2, pago.getMonto());
                ps.setInt(3, pago.getId());
                int updated = ps.executeUpdate();
                if (updated > 0) {
                    System.out.println("Pago actualizado correctamente");
                } else {
                    System.out.println("Pago no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar pago: " + e.getMessage());
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

    // ! Eliminar Pago (solo admin)
    public static void eliminarPago(Connection connection, int idpago, int idsolicitante) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String rolquery = "SELECT rol_id FROM tienda.usuarios WHERE id = ?";
            ps = connection.prepareStatement(rolquery);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                ps.close();
                String delete = "delete from tienda.pagos where id = ?";
                ps = connection.prepareStatement(delete);
                ps.setInt(1, idpago);
                int deleted = ps.executeUpdate();
                if (deleted > 0) {
                    System.out.println("Pago eliminado correctamente");
                } else {
                    System.out.println("Pago no encontrado");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar pago: " + e.getMessage());
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

    // ! Consultar Pago
    public static ArrayList<Pago> consultarPagos(Connection connection, int idsolicitante) throws SQLException {
        ArrayList<Pago> al = new ArrayList<>();
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
                String consulta;
                if (rol == 1) {
                    consulta = "select * from tienda.pagos";
                    ps = connection.prepareStatement(consulta);
                } else {
                    consulta = "select p.* from tienda.pagos p "
                            + "inner join tienda.detalles_ordenes e on p.id = e.pago_id "
                            + "inner join tienda.ordenes o on e.orden_id = o.id " + "where o.usuario_id = ?";
                    ps = connection.prepareStatement(consulta);
                    ps.setInt(1, idsolicitante);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Pago pago = new Pago();
                    pago.setId(rs.getInt("id"));
                    pago.setFecha_pago(rs.getDate("fecha_pago"));
                    pago.setMonto(rs.getDouble("monto"));
                    al.add(pago);
                }
            } else {
                System.out.println("Usuario no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar pagos: " + e.getMessage());
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
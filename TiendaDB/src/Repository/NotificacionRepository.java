package Repository;

import java.util.ArrayList;
import Model.Notificacion;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotificacionRepository {
    // ! Insertar Notificación (solo admin)
    public static void insertarNotificacion(Connection connection, Notificacion notificacion, int idsolicitante)
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
                String insert = "insert into tienda.notificaciones (usuario_id, mensaje) values (?, ?)";
                ps = connection.prepareStatement(insert);
                ps.setInt(1, notificacion.getUsuario_id());
                ps.setString(2, notificacion.getMensaje());
                ps.executeUpdate();
                System.out.println("Notificación insertada correctamente.");
            } else {
                System.out.println("Acceso denegado. Solo los administradores pueden insertar notificaciones.");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar notificación: " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    // ! Actualizar Notificación (solo admin)
    public static void actualizarNotificacion(Connection connection, Notificacion notificacion, int idsolicitante)
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
                String update = "update tienda.notificaciones set usuario_id = ?, mensaje = ? where id = ?";
                ps = connection.prepareStatement(update);
                ps.setInt(1, notificacion.getUsuario_id());
                ps.setString(2, notificacion.getMensaje());
                ps.setInt(3, notificacion.getId());
                ps.executeUpdate();
                System.out.println("Notificación actualizada correctamente.");
            } else {
                System.out.println("Acceso denegado. Solo los administradores pueden actualizar notificaciones.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar notificación: " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    // ! Eliminar Notificación (admin cualquiera, cliente solo las suyas)
    public static void eliminarNotificacion(Connection connection, int idnotificacion, int idsolicitante)
            throws SQLException {
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
                    String delete = "delete from tienda.notificaciones where id = ?";
                    ps = connection.prepareStatement(delete);
                    ps.setInt(1, idnotificacion);
                    ps.executeUpdate();
                    System.out.println("Notificación eliminada (admin).");
                } else {
                    String verificar = "select id from tienda.notificaciones where id = ? and usuario_id = ?";
                    ps = connection.prepareStatement(verificar);
                    ps.setInt(1, idnotificacion);
                    ps.setInt(2, idsolicitante);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        ps.close();
                        String delete = "delete from tienda.notificaciones where id = ?";
                        ps = connection.prepareStatement(delete);
                        ps.setInt(1, idnotificacion);
                        ps.executeUpdate();
                        System.out.println("Notificación eliminada");
                    } else {
                        System.out.println("No puedes eliminar esta notificación.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar notificación: " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    // ! Consultar Notificaciones (admin todas, cliente solo las suyas)
    public static ArrayList<Notificacion> consultarNotificaciones(Connection connection, int idsolicitante)
            throws SQLException {
        ArrayList<Notificacion> al = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String verificar = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(verificar);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rol = rs.getInt("rol_id");
                ps.close();
                rs.close();
                String query;
                if (rol == 1) {
                    query = "select * from tienda.notificaciones";
                    ps = connection.prepareStatement(query);
                } else {
                    query = "select * from tienda.notificaciones where usuario_id = ?";
                    ps = connection.prepareStatement(query);
                    ps.setInt(1, idsolicitante);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Notificacion n = new Notificacion();
                    n.setId(rs.getInt("id"));
                    n.setUsuario_id(rs.getInt("usuario_id"));
                    n.setMensaje(rs.getString("mensaje"));
                    n.setFecha(rs.getDate("fecha"));
                    al.add(n);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar notificaciones: " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
        return al;
    }
}
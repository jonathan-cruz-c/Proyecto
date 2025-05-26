package Repository;

import java.util.ArrayList;
import Model.Rol;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Solo los admin van a poder usar las funciones
public class RolRepository {

    // ! Inserta Roles (solo admin)
    public static void insertarRol(Connection connection, Rol rol, int idsolicitante) throws SQLException {
        PreparedStatement rps = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String verificarrol = "select rol_id from tienda.usuarios where id=?";
            rps = connection.prepareStatement(verificarrol);
            rps.setInt(1, idsolicitante);
            rs = rps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                String insert = "insert into tienda.roles (nom_rol) values (?)";
                ps = connection.prepareStatement(insert);
                ps.setString(1, rol.getNom_rol());
                ps.executeUpdate();
                System.out.println("Rol insertado correctamente.");
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error al insertar rol: " + e.getMessage());

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

    // ! Actualiza datos de la tabla Roles (solo admin)
    public static void actualizarRol(Connection connection, Rol rol, int idsolicitante) throws SQLException {
        PreparedStatement rps = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String verificarrol = "select rol_id from tienda.usuarios where id=?";
            rps = connection.prepareStatement(verificarrol);
            rps.setInt(1, idsolicitante);
            rs = rps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                String update = "update tienda.roles set nom_rol=? where id=?";
                ps = connection.prepareStatement(update);
                ps.setString(1, rol.getNom_rol());
                ps.setInt(2, rol.getId());
                int actualizado = ps.executeUpdate();
                if (actualizado > 0) {
                    System.out.println("Rol actualizado correctamente.");
                } else {
                    System.out.println("Rol no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar rol: " + e.getMessage());
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

    // ! Eliminar Roles (solo admin)
    public static void eliminarRol(Connection connection, int idrol, int idsolicitante) throws SQLException {
        PreparedStatement rps = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String verificarrol = "select rol_id from tienda.usuarios where id=?";
            rps = connection.prepareStatement(verificarrol);
            rps.setInt(1, idsolicitante);
            rs = rps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                String delete = "delete from tienda.roles where id=?";
                ps = connection.prepareStatement(delete);
                ps.setInt(1, idrol);
                int eliminado = ps.executeUpdate();
                if (eliminado > 0) {
                    System.out.println("Rol eliminado correctamente");
                } else {
                    System.out.println("Rol no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar rol: " + e.getMessage());
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

    // ! Consultar Roles (solo admin)
    public static ArrayList<Rol> consultarRol(Connection connection, int idsolicitante) throws SQLException {
        ArrayList<Rol> al = new ArrayList<>();
        PreparedStatement rps = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ResultSet rrs = null;

        try {
            String verificarrol = "select rol_id from tienda.usuarios where id=?";
            rps = connection.prepareStatement(verificarrol);
            rps.setInt(1, idsolicitante);
            rs = rps.executeQuery();
            if (rs.next() && rs.getInt("rol_id") == 1) {
                String select = "select * from tienda.roles";
                ps = connection.prepareStatement(select);
                rrs = ps.executeQuery();
                while (rrs.next()) {
                    Rol rol = new Rol();
                    rol.setId(rrs.getInt("id"));
                    rol.setNom_rol(rrs.getString("nom_rol"));
                    al.add(rol);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar roles: " + e.getMessage());
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
        return al;
    }
}
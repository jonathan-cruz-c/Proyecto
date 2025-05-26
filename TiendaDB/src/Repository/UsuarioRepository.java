package Repository;

import java.util.ArrayList;
import Model.Usuario;
import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioRepository {
    // ! Inserta usuarios
    public static void insertarUsu(Connection connection, Usuario usuario, int idactual) throws SQLException {
        PreparedStatement ps = null;
        ConnectDB.getConn();
        try {
            // Se hace query para ver el rol del usuario
            String query = "select rol_id from tienda.usuarios where id=?";
            PreparedStatement verrol = connection.prepareStatement(query);
            verrol.setInt(1, idactual);
            ResultSet rs = verrol.executeQuery();
            if (rs.next()) { // Devuelve true si hay una fila disponible
                int rolactual = rs.getInt("rol_id"); // Contiene los datos devueltos por una consulta SQL
                // varifica si es diferente el id del usuario 1
                if (usuario.getRol_id() == 1 && rolactual != 1) {
                    // Se inserta un usuario nuevo pero con el rol cliente
                    String insert = "insert into tienda.usuarios (rol_id, username, nombre, apellido, correo, contraseña, telefono) values (?,?,?,?,?,?,?)";
                    int cliente = 2;
                    usuario.setId(cliente);
                    ps = connection.prepareStatement(insert);
                    ps.setInt(1, usuario.getRol_id());
                    ps.setString(2, usuario.getUsername());
                    ps.setString(3, usuario.getNombre());
                    ps.setString(4, usuario.getApellido());
                    ps.setString(5, usuario.getCorreo());
                    ps.setString(6, usuario.getContraseña());
                    ps.setString(7, usuario.getTelefono());
                    ps.executeUpdate();
                    System.out.println("Usuario agregado correctamente");
                } else {
                    // Solo el que tenga el rol admin va a poder crear otro usuario admin
                    String insert = "insert into tienda.usuarios (rol_id, username, nombre, apellido, correo, contraseña, telefono) values (?,?,?,?,?,?,?)";
                    ps = connection.prepareStatement(insert);
                    ps.setInt(1, usuario.getRol_id());
                    ps.setString(2, usuario.getUsername());
                    ps.setString(3, usuario.getNombre());
                    ps.setString(4, usuario.getApellido());
                    ps.setString(5, usuario.getCorreo());
                    ps.setString(6, usuario.getContraseña());
                    ps.setString(7, usuario.getTelefono());
                    ps.executeUpdate();
                    System.out.println("Usuario agregado correctamente");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al insertar el usuario" + e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    // ! Actualiza datos de la tabla usuario
    public static void actualizarUsu(Connection connection, Usuario usuario, int idcliente, int idsolicite)
            throws SQLException {
        PreparedStatement ps = null;
        ConnectDB.getConn();
        // Hace la actualizacion si tiene el rol admin
        String updateadmin = "update tienda.usuarios set rol_id=?, username=?, nombre=?, apellido=?, correo=?, contraseña=?, telefono=? where id=?";
        // Hace la actualizacion cunado es cliente en su propia informacion
        String updatecliente = "update tienda.usuarios set username=?, nombre=?, apellido=?, correo=?, contraseña=?, telefono=? where id=?";
        try {
            if (idsolicite == 1) { // verifica si es admin
                ps = connection.prepareStatement(updateadmin);
                ps.setInt(1, usuario.getRol_id());
                ps.setString(2, usuario.getUsername());
                ps.setString(3, usuario.getNombre());
                ps.setString(4, usuario.getApellido());
                ps.setString(5, usuario.getCorreo());
                ps.setString(6, usuario.getContraseña());
                ps.setString(7, usuario.getTelefono());
                ps.setInt(8, usuario.getId());
            } else { // Si no es admin
                ps = connection.prepareStatement(updatecliente);
                ps.setString(1, usuario.getUsername());
                ps.setString(2, usuario.getNombre());
                ps.setString(3, usuario.getApellido());
                ps.setString(4, usuario.getCorreo());
                ps.setString(5, usuario.getContraseña());
                ps.setString(6, usuario.getTelefono());
                ps.setInt(7, usuario.getId());
            }
            int actualizado = ps.executeUpdate();
            if (actualizado > 0) {
                System.out.println("Usuario actualizado correctamente");
            } else {
                System.out.println("No se encontró el usuario.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    // ! Eliminar usuarios
    public static void eliminarUsu(Connection connection, int ideliminar, int idsolicite) throws SQLException {
        PreparedStatement ps = null;
        ConnectDB.getConn();
        try {
            // Ve que rol tiene
            String queryrol = "select rol_id from tienda.usuarios where id=?";
            PreparedStatement verrol = connection.prepareStatement(queryrol);
            verrol.setInt(1, idsolicite);
            ResultSet rs = verrol.executeQuery();
            if (rs.next()) { // Devuelve true si hay una fila disponible
                int rolactual = rs.getInt("rol_id"); // Contiene los datos devueltos por una consulta SQL
                if (rolactual == 1 || ideliminar == idsolicite) { // Solo permitir si es admin o si está eliminando su
                                                                  // propia cuenta
                    String delete = "delete from tienda.usuarios where id=?";
                    ps = connection.prepareStatement(delete);
                    ps.setInt(1, ideliminar);
                    int eliminado = ps.executeUpdate();
                    if (eliminado > 0) {
                        System.out.println("Usuario eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró el usuario.");
                    }
                } else {
                    System.out.println("Usuario solicitante no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    // ! Consultar usuarios solo las admin pueden ver los usuarios
    public static ArrayList<Usuario> consultarUsu(Connection connection, int idsolicitante) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Usuario> al = new ArrayList<>();
        ConnectDB.getConn();
        try {
            String verificar = "select rol_id from tienda.usuarios where id=?";
            PreparedStatement verrol = connection.prepareStatement(verificar);
            verrol.setInt(1, idsolicitante);
            ResultSet resultado = verrol.executeQuery();
            if (resultado.next()) {
                int rol = resultado.getInt("rol_id");
                if (rol == 1) { // Si el rol_id es 1, significa que es administrador
                    String consulta = "select * from tienda.usuarios";
                    ps = connection.prepareStatement(consulta);
                } else {
                    String consulta = "select * from tienda.usuarios where id = ?";
                    ps = connection.prepareStatement(consulta);
                    ps.setInt(1, idsolicitante);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setRol_id(rs.getInt("rol_id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContraseña(rs.getString("contraseña"));
                    usuario.setTelefono(rs.getString("telefono"));
                    al.add(usuario);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar usuarios: " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
        return al;
    }
}
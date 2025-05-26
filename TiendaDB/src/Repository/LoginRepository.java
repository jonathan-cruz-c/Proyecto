package Repository;

import Model.Usuario;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginRepository {
    public static Usuario login(Connection connection, String correouser, String contraseña) throws SQLException {
        Usuario usuario = null;
        String query = "select * from tienda.usuarios where (correo=? or username=?) and contraseña=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, correouser);
            ps.setString(2, correouser);
            ps.setString(3, contraseña);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setRol_id(rs.getInt("rol_id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setTelefono(rs.getString("telefono"));
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
        }
        return usuario;
    }

    public static void loginRegistro(Connection connection, Usuario usuario) throws SQLException {
        try {
            String insert = "insert into tienda.usuarios (rol_id, username, nombre, apellido, correo, contraseña, telefono) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert);
            ps.setInt(1, usuario.getRol_id());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getContraseña());
            ps.setString(7, usuario.getTelefono());
            ps.executeUpdate();
            ps.close();
            System.out.println("Usuario registrado correctamente");
        } catch (Exception e) {
            System.out.println("Error al registro del usuario: " + e.getMessage());
        }
    }
}
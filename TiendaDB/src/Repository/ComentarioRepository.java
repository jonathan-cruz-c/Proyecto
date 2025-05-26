package Repository;

import java.util.ArrayList;
import Model.Comentario;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComentarioRepository {
    // ! Insertar comentario
    public static void insertarComentario(Connection connection, int productoid, String comentario, int idsolicitante)
            throws SQLException {
        PreparedStatement ps = null;
        try {
            String insert = "insert into tienda.comentarios (usuario_id, producto_id, comentario) "
                    + "values (?, ?, ?)";
            ps = connection.prepareStatement(insert);
            ps.setInt(1, idsolicitante);
            ps.setInt(2, productoid);
            ps.setString(3, comentario);
            ps.executeUpdate();
            System.out.println("Comentario insertado correctamente");
        } catch (Exception e) {
            System.out.println("Error al insertar comentario: " + e.getMessage());
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    // ! Actualizar comentario
    public static void actualizarComentario(Connection connection, int idcomentario, String nuevocomentario,
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
                    String update = "update tienda.comentarios set comentario = ? where id = ?";
                    ps = connection.prepareStatement(update);
                    ps.setString(1, nuevocomentario);
                    ps.setInt(2, idcomentario);
                    int co = ps.executeUpdate();
                    if (co > 0) {
                        System.out.println("Comentario actualizado por admin");
                    } else {
                        System.out.println("Error a actualizar comentario");
                    }
                } else {
                    String update = "update tienda.comentarios set comentario = ? where id = ? and usuario_id = ?";
                    ps = connection.prepareStatement(update);
                    ps.setString(1, nuevocomentario);
                    ps.setInt(2, idcomentario);
                    ps.setInt(3, idsolicitante);
                    int co = ps.executeUpdate();
                    if (co > 0) {
                        System.out.println("Comentario actualizado por cliente");
                    } else {
                        System.out.println("No autorizado. El comentario no te pertenece");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar comentario: " + e.getMessage());
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

    // ! Eliminar comentario
    public static void eliminarComentario(Connection connection, int idcomentario, int idsolicitante)
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
                    String delete = "delete from tienda.comentarios where id = ?";
                    ps = connection.prepareStatement(delete);
                    ps.setInt(1, idcomentario);
                    int co = ps.executeUpdate();
                    if (co > 0) {
                        System.out.println("Comentario eliminado por admin");
                    } else {
                        System.out.println("Error a eliminar");
                    }
                } else {
                    String delete = "delete from tienda.comentarios where id = ? and usuario_id = ?";
                    ps = connection.prepareStatement(delete);
                    ps.setInt(1, idcomentario);
                    ps.setInt(2, idsolicitante);
                    int co = ps.executeUpdate();
                    if (co > 0) {
                        System.out.println("Comentario eliminado por cliente");
                    } else {
                        System.out.println("No autorizado. El comentario no te pertenece");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar comentario: " + e.getMessage());
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

    // ! Consultar todos los comentarios
    public static ArrayList<Comentario> consultarComentarios(Connection connection) throws SQLException {
        ArrayList<Comentario> al = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from tienda.comentarios";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setId(rs.getInt("id"));
                comentario.setUsuario_id(rs.getInt("usuario_id"));
                comentario.setProducto_id(rs.getInt("producto_id"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setFecha(rs.getString("fecha"));
                al.add(comentario);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar comentarios: " + e.getMessage());
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
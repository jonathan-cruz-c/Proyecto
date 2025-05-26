package Repository;

import java.util.ArrayList;
import Model.Favorito;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FavoritoRepository {
    // ! Insertar Favorito (admin puede todos, cliente solo producto_id)
    public static void insertarFavorito(Connection connection, Favorito favorito, int idsolicitante)
            throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String verificarRol = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(verificarRol);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rol = rs.getInt("rol_id");
                ps.close();
                String insertar = "insert into tienda.favoritos (usuario_id, producto_id) values (?, ?)";
                ps = connection.prepareStatement(insertar);
                ps.setInt(1, idsolicitante);
                ps.setInt(2, favorito.getProducto_id());
                ps.executeUpdate();
                if (rol == 1) {
                    System.out.println("Favorito insertado correctamente admin");
                } else {
                    System.out.println("Favorito insertado correctamente");
                }
            } else {
                System.out.println("Usuario no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar favorito: " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    // ! Actualizar Favorito (admin cualquiera, cliente solo los suyos)
    public static void actualizarFavorito(Connection connection, Favorito favorito, int idsolicitante)
            throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String rolQuery = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(rolQuery);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rol = rs.getInt("rol_id");
                rs.close();
                ps.close();
                if (rol == 1) {
                    String update = "update tienda.favoritos set usuario_id = ?, producto_id = ? where id = ?";
                    ps = connection.prepareStatement(update);
                    ps.setInt(1, favorito.getUsuario_id());
                    ps.setInt(2, favorito.getProducto_id());
                    ps.setInt(3, favorito.getId());
                    ps.executeUpdate();
                    System.out.println("Favorito actualizado (admin)");
                } else {
                    String verificar = "select id from tienda.favoritos where id = ? and usuario_id = ?";
                    ps = connection.prepareStatement(verificar);
                    ps.setInt(1, favorito.getId());
                    ps.setInt(2, idsolicitante);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        ps.close();
                        String update = "update tienda.favoritos set producto_id = ? where id = ?";
                        ps = connection.prepareStatement(update);
                        ps.setInt(1, favorito.getProducto_id());
                        ps.setInt(2, favorito.getId());
                        ps.executeUpdate();
                        System.out.println("Favorito actualizado");
                    } else {
                        System.out.println("No puedes actualizar este favorito");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar favorito: " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    // ! Eliminar Favorito (admin cualquiera, cliente solo los suyos)
    public static void eliminarFavorito(Connection connection, int idfavorito, int idsolicitante) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String rolquery = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(rolquery);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rol = rs.getInt("rol_id");
                rs.close();
                ps.close();
                if (rol == 1) {
                    String delete = "delete from tienda.favoritos where id = ?";
                    ps = connection.prepareStatement(delete);
                    ps.setInt(1, idfavorito);
                    ps.executeUpdate();
                    System.out.println("Favorito eliminado (admin)");
                } else {
                    // cliente solo los suyos
                    String verificar = "select id from tienda.favoritos where id = ? and usuario_id = ?";
                    ps = connection.prepareStatement(verificar);
                    ps.setInt(1, idfavorito);
                    ps.setInt(2, idsolicitante);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        ps.close();
                        String delete = "delete from tienda.favoritos where id = ?";
                        ps = connection.prepareStatement(delete);
                        ps.setInt(1, idfavorito);
                        ps.executeUpdate();
                        System.out.println("Favorito eliminado");
                    } else {
                        System.out.println("No puedes eliminar este favorito");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar favorito: " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
    }

    // ! Consultar Favoritos (admin todos, cliente solo los suyos)
    public static ArrayList<Favorito> consultarFavoritos(Connection connection, int idsolicitante) throws SQLException {
        ArrayList<Favorito> al = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String rolquery = "select rol_id from tienda.usuarios where id = ?";
            ps = connection.prepareStatement(rolquery);
            ps.setInt(1, idsolicitante);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rol = rs.getInt("rol_id");
                rs.close();
                ps.close();
                String query;
                if (rol == 1) {
                    query = "select * from tienda.favoritos";
                    ps = connection.prepareStatement(query);
                } else {
                    query = "select * from tienda.favoritos where usuario_id = ?";
                    ps = connection.prepareStatement(query);
                    ps.setInt(1, idsolicitante);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Favorito fav = new Favorito();
                    fav.setId(rs.getInt("id"));
                    fav.setUsuario_id(rs.getInt("usuario_id"));
                    fav.setProducto_id(rs.getInt("producto_id"));
                    fav.setFecha_agregado(rs.getString("fecha_agregado"));
                    al.add(fav);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar favoritos: " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }
        return al;
    }
}
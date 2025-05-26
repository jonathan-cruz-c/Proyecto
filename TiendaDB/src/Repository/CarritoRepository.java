package Repository;

import java.util.ArrayList;
import Model.Carrito;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarritoRepository {
    // ! Insertar carrito
    public static void insertarCarrito(Connection connection, int productoid, int usuarioid, int idsolicitante)
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
                    String insert = "insert into tienda.carritos (usuario_id, producto_id) values (?, ?)";
                    ps = connection.prepareStatement(insert);
                    ps.setInt(1, usuarioid);
                    ps.setInt(2, productoid);
                    ps.executeUpdate();
                    System.out.println("Carrito insertado por administrador");
                } else {
                    String insert = "insert into tienda.carritos (usuario_id, producto_id) values (?, ?)";
                    ps = connection.prepareStatement(insert);
                    ps.setInt(1, idsolicitante);
                    ps.setInt(2, productoid);
                    ps.executeUpdate();
                    System.out.println("Producto agregado al carrito por cliente");
                }
            } else {
                System.out.println("Usuario solicitante no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error al insertar en carrito: " + e.getMessage());
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

    // ! Actualizar carrito (solo admin)
    public static void actualizarCarrito(Connection connection, Carrito carrito, int idsolicitante)
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
                    String update = "update tienda.carritos set usuario_id = ?, producto_id = ? where id = ?";
                    ps = connection.prepareStatement(update);
                    ps.setInt(1, carrito.getUsuario_id());
                    ps.setInt(2, carrito.getProducto_id());
                    ps.setInt(3, carrito.getId());
                    int ca = ps.executeUpdate();
                    if (ca > 0) {
                        System.out.println("Carrito actualizado correctamente por administrador");
                    } else {
                        System.out.println("Carrito no encontrado");
                    }
                } else {
                    String verificar = "select usuario_id from tienda.carritos where id = ?";
                    ps = connection.prepareStatement(verificar);
                    ps.setInt(1, carrito.getId());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        int usuarioidcarrito = rs.getInt("usuario_id");
                        if (usuarioidcarrito == idsolicitante) {
                            ps.close();
                            rs.close();
                            String update = "update tienda.carritos set producto_id = ? where id = ?";
                            ps = connection.prepareStatement(update);
                            ps.setInt(1, carrito.getProducto_id());
                            ps.setInt(2, carrito.getId());
                            int ca = ps.executeUpdate();
                            if (ca > 0) {
                                System.out.println("Carrito actualizado correctamente por cliente");
                            } else {
                                System.out.println("Carrito no encontrado");
                            }
                        } else {
                            System.out.println("No autorizado. Solo puedes actualizar tu propio carrito");
                        }
                    } else {
                        System.out.println("Carrito no encontrado");
                    }
                }
            } else {
                System.out.println("Usuario solicitante no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar carrito: " + e.getMessage());
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

    // ! Eliminar carrito
    public static void eliminarCarrito(Connection connection, int idcarrito, int idsolicitante) throws SQLException {
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
                    String delete = "delete FROM tienda.carritos where id = ?";
                    ps = connection.prepareStatement(delete);
                    ps.setInt(1, idcarrito);
                    int ca = ps.executeUpdate();
                    if (ca > 0) {
                        System.out.println("Carrito eliminado por admin.");
                    } else {
                        System.out.println("No se pudo eliminar");
                    }
                } else {
                    String delete = "delete from tienda.carritos where id = ? and usuario_id = ?";
                    ps = connection.prepareStatement(delete);
                    ps.setInt(1, idcarrito);
                    ps.setInt(2, idsolicitante);
                    int ca = ps.executeUpdate();
                    if (ca > 0) {
                        System.out.println("Carrito eliminado por cliente");
                    } else {
                        System.out.println("No autorizado. El carrito no pertenece al usuario");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar carrito: " + e.getMessage());
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

    // ! Consultar carritos
    public static ArrayList<Carrito> consultarCarritos(Connection connection, int idsolicitante) throws SQLException {
        ArrayList<Carrito> al = new ArrayList<>();
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
                    String query = "select * from tienda.carritos";
                    ps = connection.prepareStatement(query);
                } else {
                    String query = "select * from tienda.carritos where usuario_id = ?";
                    ps = connection.prepareStatement(query);
                    ps.setInt(1, idsolicitante);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Carrito carrito = new Carrito();
                    carrito.setId(rs.getInt("id"));
                    carrito.setUsuario_id(rs.getInt("usuario_id"));
                    carrito.setProducto_id(rs.getInt("producto_id"));
                    al.add(carrito);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar carritos: " + e.getMessage());
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
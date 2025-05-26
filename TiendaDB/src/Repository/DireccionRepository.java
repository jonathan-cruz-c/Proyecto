package Repository;

import java.util.ArrayList;
import Model.Direccion;
// import Config.ConnectDB;

import java.sql.Connection;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DireccionRepository {
    // ! Inserta Direccion
    public static void insertarDirec(Connection connection, Direccion direccion, int idsolicitante)
            throws SQLException {
        PreparedStatement ps = null;
        try {
            String insert = "insert into tienda.direcciones(ciudad, estado, codigo_postal, colonia) values (?,?,?,?)";
            ps = connection.prepareStatement(insert);
            ps.setString(1, direccion.getCiudad());
            ps.setString(2, direccion.getEstado());
            ps.setString(3, direccion.getCodigo_postal());
            ps.setString(4, direccion.getColonia());
            ps.executeUpdate();
            System.out.println("Dirección insertada correctamente.");
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al insertar dirección: " + e.getMessage());
        }
    }

    // ! Actualizar dirección (admin cualquier dirección / cliente solo si es suya)
    public static void actualizarDirec(Connection connection, Direccion direccion, int idusuariosolicita)
            throws SQLException {
        try {
            String selectrol = "select rol_id from tienda.usuarios where id = ?";
            PreparedStatement psrol = connection.prepareStatement(selectrol);
            psrol.setInt(1, idusuariosolicita);
            ResultSet rsrol = psrol.executeQuery();
            if (rsrol.next()) {
                int rol = rsrol.getInt("rol_id");
                if (rol == 1) {
                    String update = "update tienda.direcciones set ciudad = ?, estado = ?, codigo_postal = ?, colonia = ? where id=?";
                    PreparedStatement ps = connection.prepareStatement(update);
                    ps.setString(1, direccion.getCiudad());
                    ps.setString(2, direccion.getEstado());
                    ps.setString(3, direccion.getCodigo_postal());
                    ps.setString(4, direccion.getColonia());
                    ps.setInt(5, direccion.getId());
                    int actualizado = ps.executeUpdate();
                    if (actualizado > 0) {
                        System.out.println("Dirección actualizada correctamente");
                    } else {
                        System.out.println("No se encontró la dirección");
                    }
                    ps.close();
                } else {
                    String verificar = "select 1 from tienda.direcciones d inner join tienda.detalles_ordenes e on d.id = e.direccion_id inner join tienda.ordenes o on e.orden_id = o.id where d.id = ? and o.usuario_id = ?";
                    PreparedStatement psver = connection.prepareStatement(verificar);
                    psver.setInt(1, direccion.getId());
                    psver.setInt(2, idusuariosolicita);
                    ResultSet rsver = psver.executeQuery();
                    if (rsver.next()) {
                        String update = "update tienda.direcciones set ciudad = ?, estado = ?, codigo_postal = ?, colonia = ? where id = ?";
                        PreparedStatement ps = connection.prepareStatement(update);
                        ps.setString(1, direccion.getCiudad());
                        ps.setString(2, direccion.getEstado());
                        ps.setString(3, direccion.getCodigo_postal());
                        ps.setString(4, direccion.getColonia());
                        ps.setInt(5, direccion.getId());
                        int actualizado = ps.executeUpdate();
                        if (actualizado > 0) {
                            System.out.println("Dirección actualizada correctamente");
                        } else {
                            System.out.println("No se encontró la dirección");
                        }
                        ps.close();
                    } else {
                        System.out.println("No tienes permiso para actualizar esta dirección");
                    }
                    rsver.close();
                    psver.close();
                }
            }
            psrol.close();
            rsrol.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar dirección: " + e.getMessage());
        }
    }

    // ! Eliminar dirección (solo admin)
    public static void eliminarDirec(Connection connection, int iddireccion, int idusuariosolicita)
            throws SQLException {
        try {
            String queryrol = "select rol_id from tienda.usuarios where id = ?";
            PreparedStatement psrol = connection.prepareStatement(queryrol);
            psrol.setInt(1, idusuariosolicita);
            ResultSet rsrol = psrol.executeQuery();
            if (rsrol.next()) {
                int rol = rsrol.getInt("rol_id");
                if (rol == 1) {
                    String delete = "delete from tienda.direcciones where id = ?";
                    PreparedStatement ps = connection.prepareStatement(delete);
                    ps.setInt(1, iddireccion);
                    int eliminado = ps.executeUpdate();
                    if (eliminado > 0) {
                        System.out.println("Dirección eliminada correctamente.");
                    } else {
                        System.out.println("No se encontró la dirección.");
                    }
                    ps.close();
                }
            }
            psrol.close();
            rsrol.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar dirección: " + e.getMessage());
        }
    }

    // ! Consultar direcciones (admin ve todas / cliente solo las suyas)
    public static ArrayList<Direccion> consultarDirec(Connection connection, int idusuariosolicita)
            throws SQLException {
        ArrayList<Direccion> al = new ArrayList<>();
        try {
            String queryrol = "select rol_id from tienda.usuarios where id = ?";
            PreparedStatement psrol = connection.prepareStatement(queryrol);
            psrol.setInt(1, idusuariosolicita);
            ResultSet rsrol = psrol.executeQuery();
            if (rsrol.next()) {
                int rol = rsrol.getInt("rol_id");
                String consulta;
                if (rol == 1) {
                    consulta = "select * from tienda.direcciones";
                } else {
                    consulta = "select d.* from tienda.direcciones d inner join tienda.detalles_ordenes e on d.id = e.direccion_id inner join tienda.ordenes o on e.orden_id = o.id where o.usuario_id = ?";
                }
                PreparedStatement ps;
                if (rol == 1) {
                    ps = connection.prepareStatement(consulta);
                } else {
                    ps = connection.prepareStatement(consulta);
                    ps.setInt(1, idusuariosolicita);
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Direccion d = new Direccion();
                    d.setId(rs.getInt("id"));
                    d.setCiudad(rs.getString("ciudad"));
                    d.setEstado(rs.getString("estado"));
                    d.setCodigo_postal(rs.getString("codigo_postal"));
                    d.setColonia(rs.getString("colonia"));
                    al.add(d);
                }
                rs.close();
                ps.close();
            }
            psrol.close();
            rsrol.close();
        } catch (Exception e) {
            System.out.println("Error al consultar direcciones: " + e.getMessage());
        }
        return al;
    }
}
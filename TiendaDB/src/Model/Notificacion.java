package Model;

import java.sql.Date;

public class Notificacion {
    int id;
    int usuario_id;
    String mensaje;
    Date fecha;

    public Notificacion() {
    }

    public Notificacion(int id, int usuario_id, String mensaje, Date fecha) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Notificacion [id=" + id + ", usuario_id=" + usuario_id + ", mensaje=" + mensaje + ", fecha=" + fecha
                + "]";
    }
}
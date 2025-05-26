package Model;

import java.sql.Date;

public class DetalleOrden {
    int id;
    int orden_id;
    int direccion_id;
    int pago_id;
    Date fecha_llegada;

    public DetalleOrden() {
    }

    public DetalleOrden(int id, int orden_id, int direccion_id, int pago_id, Date fecha_llegada) {
        this.id = id;
        this.orden_id = orden_id;
        this.direccion_id = direccion_id;
        this.pago_id = pago_id;
        this.fecha_llegada = fecha_llegada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrden_id() {
        return orden_id;
    }

    public void setOrden_id(int orden_id) {
        this.orden_id = orden_id;
    }

    public int getDireccion_id() {
        return direccion_id;
    }

    public void setDireccion_id(int direccion_id) {
        this.direccion_id = direccion_id;
    }

    public int getPago_id() {
        return pago_id;
    }

    public void setPago_id(int pago_id) {
        this.pago_id = pago_id;
    }

    public Date getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(Date fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    @Override
    public String toString() {
        return "DetalleOrden [id=" + id + ", orden_id=" + orden_id + ", direccion_id=" + direccion_id + ", pago_id="
                + pago_id + ", fecha_llegada=" + fecha_llegada + "]";
    }
}
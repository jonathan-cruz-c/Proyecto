package Model;

import java.sql.Date;

public class Pago {
    int id;
    Date fecha_pago;
    double monto;

    public Pago() {
    }

    public Pago(int id, Date fecha_pago, double monto) {
        this.id = id;
        this.fecha_pago = fecha_pago;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Pago [id=" + id + ", fecha_pago=" + fecha_pago + ", monto=" + monto + "]";
    }
}
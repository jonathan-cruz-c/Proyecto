package Model;

public class Orden {
    int id;
    int usuario_id;
    int producto_id;
    int cantidad;

    public Orden() {
    }

    public Orden(int id, int usuario_id, int producto_id, int cantidad) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
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

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Orden [id=" + id + ", usuario_id=" + usuario_id + ", producto_id=" + producto_id + ", cantidad="
                + cantidad
                + "]";
    }
}
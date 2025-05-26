package Model;

public class Inventario {
    int id;
    int producto_id;
    int cantidad;

    public Inventario() {
    }

    public Inventario(int id, int producto_id, int cantidad) {
        this.id = id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Inventario [id=" + id + ", producto_id=" + producto_id + ", cantidad=" + cantidad + "]";
    }
}

package Model;

public class Carrito {
    int id;
    int usuario_id;
    int producto_id;

    public Carrito() {
    }

    public Carrito(int id, int usuario_id, int producto_id) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.producto_id = producto_id;
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

    @Override
    public String toString() {
        return "Carrito [id=" + id + ", usuario_id=" + usuario_id + ", producto_id=" + producto_id + "]";
    }
}
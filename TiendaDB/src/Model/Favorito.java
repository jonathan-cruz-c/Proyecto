package Model;

public class Favorito {
    int id;
    int usuario_id;
    int producto_id;
    String fecha_agregado;

    public Favorito() {
    }

    public Favorito(int id, int usuario_id, int producto_id, String fecha_agregado) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.producto_id = producto_id;
        this.fecha_agregado = fecha_agregado;
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

    public String getFecha_agregado() {
        return fecha_agregado;
    }

    public void setFecha_agregado(String fecha_agregado) {
        this.fecha_agregado = fecha_agregado;
    }

    @Override
    public String toString() {
        return "Favorito [id=" + id + ", usuario_id=" + usuario_id + ", producto_id=" + producto_id
                + ", fecha_agregado="
                + fecha_agregado + "]";
    }

}

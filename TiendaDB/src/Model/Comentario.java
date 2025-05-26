package Model;

public class Comentario {
    int id;
    int usuario_id;
    int producto_id;
    String comentario;
    String fecha;

    public Comentario() {
    }

    public Comentario(int id, int usuario_id, int producto_id, String comentario, String fecha) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.producto_id = producto_id;
        this.comentario = comentario;
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

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Comentario [id=" + id + ", usuario_id=" + usuario_id + ", producto_id=" + producto_id + ", comentario="
                + comentario + ", fecha=" + fecha + "]";
    }
}
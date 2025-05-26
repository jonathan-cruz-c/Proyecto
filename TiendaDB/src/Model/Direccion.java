package Model;

public class Direccion {
    int id;
    String ciudad;
    String estado;
    String codigo_postal;
    String colonia;

    public Direccion() {
    }

    public Direccion(int id, String ciudad, String estado, String codigo_postal, String colonia) {
        this.id = id;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigo_postal = codigo_postal;
        this.colonia = colonia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    @Override
    public String toString() {
        return "Direccion [id=" + id + ", ciudad=" + ciudad + ", estado=" + estado + ", codigo_postal=" + codigo_postal
                + ", colonia=" + colonia + "]";
    }
}
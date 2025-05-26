package Model;

public class Producto {
    int id;
    int categoría_id;
    String nombre;
    String descripcion;
    double precio;
    int stock;

    public Producto() {
    }

    public Producto(int id, int categoría_id, String nombre, String descripcion, double precio, int stock) {
        this.id = id;
        this.categoría_id = categoría_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoría_id() {
        return categoría_id;
    }

    public void setCategoría_id(int categoría_id) {
        this.categoría_id = categoría_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", categoría_id=" + categoría_id + ", nombre=" + nombre + ", descripcion="
                + descripcion + ", precio=" + precio + ", stock=" + stock + "]";
    }
}
package Model;

public class Usuario {
    int id;
    int rol_id;
    String username;
    String nombre;
    String apellido;
    String correo;
    String contraseña;
    String telefono;

    public Usuario() {
    }

    public Usuario(int id, int rol_id, String username, String nombre, String apellido, String correo,
            String contraseña, String telefono) {
        this.id = id;
        this.rol_id = rol_id;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", rol_id=" + rol_id + ", username=" + username + ", nombre=" + nombre
                + ", apellido=" + apellido + ", correo=" + correo + ", contraseña=" + contraseña + ", telefono="
                + telefono + "]";
    }
}
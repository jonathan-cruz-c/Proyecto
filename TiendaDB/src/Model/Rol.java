package Model;

public class Rol {
    int id;
    String nom_rol;

    public Rol() {
    }

    public Rol(int id, String nom_rol) {
        this.id = id;
        this.nom_rol = nom_rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_rol() {
        return nom_rol;
    }

    public void setNom_rol(String nom_rol) {
        this.nom_rol = nom_rol;
    }

    @Override
    public String toString() {
        return "Carrito [id=" + id + ", nom_rol=" + nom_rol + "]";
    }
}
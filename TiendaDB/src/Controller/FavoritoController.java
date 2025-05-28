package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Favorito;
import Service.FavoritoService;

public class FavoritoController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar favorito
    public static void insertarFavorito(int idsolicitante, int rol) {
        try {
            Favorito favorito = new Favorito();
            if (rol == 1) {
                System.out.println("===== INSERTAR FAVORITO (ADMIN)=====");
                System.out.print("Ingrese el ID del usuario: ");
                favorito.setUsuario_id(sc.nextInt());
            } else {
                System.out.println("===== INSERTAR FAVORITO =====");
                favorito.setUsuario_id(idsolicitante);
            }
            System.out.print("Ingrese el ID del producto: ");
            favorito.setProducto_id(sc.nextInt());
            sc.nextLine();
            FavoritoService.insertarFavorito(favorito, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en insertarFavorito: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar favorito
    public static void actualizarFavorito(int idsolicitante, int rol) {
        try {
            Favorito favorito = new Favorito();
            System.out.print("Ingrese el ID del favorito: ");
            favorito.setId(sc.nextInt());
            if (rol == 1) {
                System.out.println("===== ACTUALIZAR FAVORITO (ADMIN) =====");
                System.out.print("Ingrese el nuevo ID del usuario: ");
                favorito.setUsuario_id(sc.nextInt());
            } else {
                System.out.println("===== ACTUALIZAR FAVORITO =====");
                favorito.setUsuario_id(idsolicitante);
            }
            System.out.print("Ingrese el nuevo ID del producto: ");
            favorito.setProducto_id(sc.nextInt());
            sc.nextLine();
            FavoritoService.actualizarFavorito(favorito, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en actualizarFavorito: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar favorito
    public static void eliminarFavorito(int idsolicitante, int rol) {
        try {
            System.out.println("===== ELIMINAR FAVORITO =====");
            System.out.print("Ingrese el ID del favorito a eliminar: ");
            int idFavorito = sc.nextInt();
            sc.nextLine();
            FavoritoService.eliminarFavorito(idFavorito, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en eliminarFavorito: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar favoritos
    public static void consultarFavoritos(int idsolicitante, int rol) {
        try {
            System.out.println("===== CONSULTAR FAVORITOS =====");
            ArrayList<Favorito> favoritos = FavoritoService.consultarFavoritos(idsolicitante);
            if (favoritos.isEmpty()) {
                System.out.println("No hay favoritos registrados");
            } else {
                for (Favorito f : favoritos) {
                    System.out.println("ID: " + f.getId() +
                            " | Usuario ID: " + f.getUsuario_id() +
                            " | Producto ID: " + f.getProducto_id() +
                            " | Fecha agregado: " + f.getFecha_agregado());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en consultarFavoritos: " + e.getMessage());
            sc.nextLine();
        }
    }
}
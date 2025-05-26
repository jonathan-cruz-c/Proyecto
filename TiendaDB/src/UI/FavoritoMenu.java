package UI;

import java.util.Scanner;

import Controller.FavoritoController;
import Model.Usuario;
import Repository.Pantalla;

public class FavoritoMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarFavoritoMenu(Usuario usuarioactual) {
        int op = 0;
        int rol = usuarioactual.getRol_id();
        int id = usuarioactual.getId();
        do {
            try {
                if (rol == 1) { // ADMIN
                    System.out.println("===== MENÚ DE FAVORITOS (ADMIN) =====");
                    System.out.println("1. Insertar favorito");
                    System.out.println("2. Actualizar favorito");
                    System.out.println("3. Eliminar favorito");
                    System.out.println("4. Consultar todos los favoritos");
                    System.out.println("5. Regresar");
                } else { // CLIENTE
                    System.out.println("===== MENÚ DE FAVORITOS =====");
                    System.out.print("1. Insertar favorito");
                    System.out.println("\tNota: se necesita el id del producto");
                    System.out.print("2. Actualizar mis favorito");
                    System.out.println("\tNota: se necesita el id del favorito y el nuevo id del producto");
                    System.out.print("3. Eliminar mis favorito");
                    System.out.println("\tNota: se necesita el id del favorito a eliminar");
                    System.out.println("4. Consultar mis favoritos");
                    System.out.println("5. Regresar");
                }
                System.out.print("Seleccione una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                switch (op) {
                    case 1:
                        Pantalla.limpiarPantalla();
                        FavoritoController.insertarFavorito(id, rol);
                        break;
                    case 2:
                        Pantalla.limpiarPantalla();
                        FavoritoController.actualizarFavorito(id, rol);
                        break;
                    case 3:
                        Pantalla.limpiarPantalla();
                        FavoritoController.eliminarFavorito(id, rol);
                        break;
                    case 4:
                        Pantalla.limpiarPantalla();
                        FavoritoController.consultarFavoritos(id, rol);
                        break;
                    case 5:
                        Pantalla.limpiarPantalla();
                        System.out.println("Regresando...");
                        break;
                    default:
                        Pantalla.limpiarPantalla();
                        System.out.println("Opción inválida.");
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error en el menú de favoritos: " + e.getMessage());
                sc.nextLine();
            }
        } while (op != 5);
    }
}
package UI;

import java.util.Scanner;

import Controller.CategoriaController;
import Model.Usuario;
import Repository.Pantalla;

public class CategoriaMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuCategorias(Usuario usuario) {
        int op = 0;
        do {
            try {
                if (usuario.getRol_id() == 1) {
                    // Menú para ADMIN
                    System.out.println("===== MENÚ DE CATEGORÍAS (ADMIN) =====");
                    System.out.println("1. Insertar categoría");
                    System.out.println("2. Actualizar categoría");
                    System.out.println("3. Eliminar categoría");
                    System.out.println("4. Consultar categorías");
                    System.out.println("5. Regresar");
                } else {
                    // Menú para CLIENTE
                    System.out.println("===== MENÚ DE CATEGORÍAS =====");
                    System.out.println("1. Consultar categorías");
                    System.out.println("2. Regresar");
                }
                System.out.print("Selecciona una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                if (usuario.getRol_id() == 1) {
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            CategoriaController.insertarCategoria(usuario.getId());
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            CategoriaController.actualizarCategoria(usuario.getId());
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            CategoriaController.eliminarCategoria(usuario.getId());
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            CategoriaController.consultarCategorias(usuario.getId());
                            break;
                        case 5:
                            System.out.println("Regresando ...");
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                } else {
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            CategoriaController.consultarCategorias(usuario.getId());
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            System.out.println("Regresando...");
                            break;
                        default:
                            Pantalla.limpiarPantalla();
                            System.out.println("Opción inválida.");
                    }
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error en el menú de categorías: " + e.getMessage());
                sc.nextLine();
            }
        } while ((usuario.getRol_id() == 1 && op != 5) || (usuario.getRol_id() != 1 && op != 2));
    }
}
package UI;

import java.util.Scanner;

import Controller.DireccionController;
import Model.Usuario;
import Repository.Pantalla;

public class DireccionMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuDirecciones(Usuario usuario) {
        int op = 0;
        do {
            try {
                if (usuario.getRol_id() == 1) {
                    // Menú para ADMIN
                    System.out.println("===== MENÚ DE DIRECCIONES (ADMIN) =====");
                    System.out.println("1. Insertar dirección");
                    System.out.println("2. Actualizar dirección");
                    System.out.println("3. Eliminar dirección");
                    System.out.println("4. Consultar direcciones");
                    System.out.println("5. Regresar");
                } else {
                    // Menú para CLIENTE
                    System.out.println("===== MENÚ DE DIRECCIONES =====");
                    System.out.println("1. Insertar dirección");
                    System.out.print("2. Actualizar mis dirección");
                    System.out.println("\tNota: se necesita el id de la dirección a actualizar");
                    System.out.println("3. Consultar mis direcciones");
                    System.out.println("4. Regresar");
                }
                System.out.print("Selecciona una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                if (usuario.getRol_id() == 1) {
                    // ADMIN
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            DireccionController.insertarDireccion(usuario);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            DireccionController.actualizarDireccion(usuario);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            DireccionController.eliminarDireccion(usuario);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            DireccionController.consultarDirecciones(usuario);
                            break;
                        case 5:
                            Pantalla.limpiarPantalla();
                            System.out.println("Regresando...");
                            break;
                        default:
                            Pantalla.limpiarPantalla();
                            System.out.println("Opción inválida.");
                    }
                } else {
                    // CLIENTE
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            DireccionController.insertarDireccion(usuario);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            DireccionController.actualizarDireccion(usuario);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            DireccionController.consultarDirecciones(usuario);
                            break;
                        case 4:
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
                System.out.println("Error en el menú de direcciones: " + e.getMessage());
                sc.nextLine();
            }
        } while ((usuario.getRol_id() == 1 && op != 5) || (usuario.getRol_id() != 1 && op != 4));
    }
}
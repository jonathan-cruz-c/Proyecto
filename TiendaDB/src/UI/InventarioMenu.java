package UI;

import java.util.Scanner;

import Controller.InventarioController;
import Model.Usuario;
import Repository.Pantalla;

public class InventarioMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarInventarioMenu(Usuario usuarioactual) {
        int op = 0;
        int rol = usuarioactual.getRol_id();
        do {
            try {
                if (rol == 1) { // ADMIN
                    System.out.println("===== MENÚ DE INVENTARIO (ADMIN) =====");
                    System.out.println("1. Insertar inventario");
                    System.out.println("2. Actualizar inventario");
                    System.out.println("3. Eliminar inventario");
                    System.out.println("4. Consultar inventario");
                    System.out.println("5. Regresar");
                } else { // CLIENTE
                    System.out
                    .println("No tienes acceso al inventario. Solo los administradores pueden ver este menú.");
                    System.out.println("1. Regresar");
                }
                System.out.print("Seleccione una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                if (rol == 1) { // ADMIN
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            InventarioController.insertarInventario(usuarioactual);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            InventarioController.actualizarInventario(usuarioactual);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            InventarioController.eliminarInventario(usuarioactual);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            InventarioController.consultarInventarios(usuarioactual);
                            break;
                        case 5:
                            Pantalla.limpiarPantalla();
                            System.out.println("Regresando...");
                            break;
                        default:
                            Pantalla.limpiarPantalla();
                            System.out.println("Opción inválida.");
                    }
                } else { // CLIENTE
                    if (op == 1) {
                        Pantalla.limpiarPantalla();
                        System.out.println("Regresando...");
                    } else {
                        Pantalla.limpiarPantalla();
                        System.out.println("Opción inválida.");
                    }
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error en el menú de inventario: " + e.getMessage());
                sc.nextLine();
            }
        } while ((rol == 1 && op != 5) || (rol != 1 && op != 1));
    }
}
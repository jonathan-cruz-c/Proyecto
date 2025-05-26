package UI;

import java.util.Scanner;

import Controller.OrdenController;
import Model.Usuario;
import Repository.Pantalla;

public class OrdenMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuOrden(Usuario usuario) {
        int op = 0;
        int rol = usuario.getRol_id();
        int id = usuario.getId();
        do {
            try {
                if (rol == 1) { // ADMINISTRADOR
                    System.out.println("===== MENÚ DE ÓRDENES (ADMIN) =====");
                    System.out.println("1. Insertar orden");
                    System.out.println("2. Consultar todas las órdenes");
                    System.out.println("3. Actualizar orden");
                    System.out.println("4. Eliminar orden");
                    System.out.println("5. Regresar");
                    System.out.print("Selecciona una opción (número): ");
                    op = sc.nextInt();
                    sc.nextLine();
                    Pantalla.limpiarPantalla();
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            OrdenController.insertarOrden(id, rol);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            OrdenController.consultarOrdenes(id, rol);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            OrdenController.actualizarOrden(id, rol);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            OrdenController.eliminarOrden(id, rol);
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
                    System.out.println("===== MENÚ DE ÓRDENES (CLIENTE) =====");
                    System.out.print("1. Insertar orden");
                    System.out.println("\tNota: se necesita el id del producto");
                    System.out.println("2. Consultar mis órdenes");
                    System.out.println("3. Regresar");
                    System.out.print("Selecciona una opción (número): ");
                    op = sc.nextInt();
                    sc.nextLine();
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            OrdenController.insertarOrden(id, rol);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            OrdenController.consultarOrdenes(id, rol);
                            break;
                        case 3:
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
                System.out.println("Error en el menú de órdenes: " + e.getMessage());
                sc.nextLine();
            }
        } while ((rol == 1 && op != 5) || (rol != 1 && op != 3));
    }
}
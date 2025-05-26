package UI;

import java.util.Scanner;

import Controller.DetalleOrdenController;
import Model.Usuario;
import Repository.Pantalla;

public class DetalleOrdenMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuDetalleOrden(Usuario usuario) {
        int op = 0;
        int rol = usuario.getRol_id();
        int id = usuario.getId();
        do {
            try {
                if (rol == 1) { // ADMIN
                    System.out.println("===== MENÚ DETALLE ORDEN (ADMIN) =====");
                    System.out.println("1. Insertar detalle de orden");
                    System.out.println("2. Consultar todos los detalles");
                    System.out.println("3. Actualizar detalle de orden");
                    System.out.println("4. Eliminar detalle de orden");
                    System.out.println("5. Regresar");
                    System.out.print("Selecciona una opción (número): ");
                    op = sc.nextInt();
                    sc.nextLine();
                    Pantalla.limpiarPantalla();
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            DetalleOrdenController.insertar(id);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            DetalleOrdenController.consultar(id);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            DetalleOrdenController.actualizar(id);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            DetalleOrdenController.eliminar(id);
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
                    System.out.println("===== MENÚ DETALLE ORDEN =====");
                    System.out.print("1. Insertar detalle de mi orden");
                    System.out.println(
                            "\tNota: se necesita el id de la orden, id de la dirección y el id del pago (el admin te lo asigna)");
                    System.out.println("2. Consultar mis detalles de orden");
                    System.out.println("3. Regresar");
                    System.out.print("Selecciona una opción (número): ");
                    op = sc.nextInt();
                    sc.nextLine();
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            DetalleOrdenController.insertar(id);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            DetalleOrdenController.consultar(id);
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
                System.out.println("Error en el menú de Detalles de Orden: " + e.getMessage());
                sc.nextLine();
            }
        } while ((rol == 1 && op != 5) || (rol != 1 && op != 3));
    }
}
package UI;

import java.util.Scanner;

import Controller.PagoController;
import Model.Usuario;
import Repository.Pantalla;

public class PagoMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuPago(Usuario usuario) {
        int op = 0;
        int rol = usuario.getRol_id();
        int id = usuario.getId();
        do {
            try {
                if (rol == 1) { // ADMIN
                    System.out.println("===== MENÚ DE PAGOS (ADMIN) =====");
                    System.out.println("1. Insertar pago");
                    System.out.println("2. Consultar todos los pagos");
                    System.out.println("3. Actualizar pago");
                    System.out.println("4. Eliminar pago");
                    System.out.println("5. Regresar");
                    System.out.print("Selecciona una opción (número): ");
                    op = sc.nextInt();
                    sc.nextLine();
                    Pantalla.limpiarPantalla();
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            PagoController.insertarPago(id, rol);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            PagoController.consultarPagos(id, rol);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            PagoController.actualizarPago(id, rol);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            PagoController.eliminarPago(id, rol);
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
                    System.out.println("===== MENÚ DE PAGOS =====");
                    System.out.println("Nota: Los admin te asignan el pago");
                    System.out.println("1. Consultar mis pagos");
                    System.out.println("2. Regresar");
                    System.out.print("Selecciona una opción (número): ");
                    op = sc.nextInt();
                    sc.nextLine();
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            PagoController.consultarPagos(id, rol);
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
                System.out.println("Error en el menú de pagos: " + e.getMessage());
                sc.nextLine();
            }
        } while ((rol == 1 && op != 5) || (rol != 1 && op != 2));
    }
}
package UI;

import java.util.Scanner;

import Controller.NotificacionController;
import Model.Usuario;
import Repository.Pantalla;

public class NotificacionMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarNotificacionMenu(Usuario usuarioactual) {
        int op = 0;
        int rol = usuarioactual.getRol_id();
        do {
            try {
                if (rol == 1) { // ADMIN
                    System.out.println("===== MENÚ DE NOTIFICACIONES (ADMIN) =====");
                    System.out.println("1. Insertar notificación");
                    System.out.println("2. Actualizar notificación");
                    System.out.println("3. Eliminar notificación");
                    System.out.println("4. Consultar todas las notificaciones");
                    System.out.println("5. Regresar");
                } else { // CLIENTE
                    System.out.println("===== MENÚ DE NOTIFICACIONES =====");
                    System.out.print("1. Eliminar mi notificación");
                    System.out.println("\tNota: se necesita el id de la notificación a eliminar");
                    System.out.println("2. Consultar mis notificaciones");
                    System.out.println("3. Regresar");
                }
                System.out.print("Seleccione una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                if (rol == 1) {
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            NotificacionController.solicitarInsertar(usuarioactual);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            NotificacionController.solicitarActualizar(usuarioactual);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            NotificacionController.solicitarEliminar(usuarioactual);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            NotificacionController.solicitarConsulta(usuarioactual);
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
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            NotificacionController.solicitarEliminar(usuarioactual);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            NotificacionController.solicitarConsulta(usuarioactual);
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
                System.out.println("Error en el menú de notificaciones: " + e.getMessage());
                sc.nextLine();
            }
        } while ((rol == 1 && op != 5) || (rol != 1 && op != 3));
    }
}
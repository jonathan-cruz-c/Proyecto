package UI;

import java.util.Scanner;

import Controller.ComentarioController;
import Model.Usuario;
import Repository.Pantalla;

public class ComentarioMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarComentarioMenu(Usuario usuarioactual) {
        int op = 0;
        int id = usuarioactual.getId();
        int rol = usuarioactual.getRol_id();
        do {
            try {
                if (rol != 1) { // Cliente
                    System.out.println("===== MENÚ DE COMENTARIOS =====");
                    System.out.println("1. Consultar comentarios");
                    System.out.print("2. Insertar comentario");
                    System.out.println("\tNota: se necesita el id del producto");
                    System.out.print("3. Actualizar mi comentario");
                    System.out.println("\tNota: se necesita el id del comentario a actualizar");
                    System.out.print("4. Eliminar mi comentario");
                    System.out.println("\tNota: se necesita el id del comentario a eliminar");
                    System.out.println("5. Regresar");
                } else { // Admin
                    System.out.println("===== MENÚ DE COMENTARIOS (ADMIN) =====");
                    System.out.println("1. Consultar comentarios");
                    System.out.println("2. Actualizar cualquier comentario");
                    System.out.println("3. Eliminar cualquier comentario");
                    System.out.println("4. Regresar");
                }
                System.out.print("Seleccione una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                if (rol != 1) { // Cliente
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            ComentarioController.consultarComentarios();
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            ComentarioController.insertarComentario(usuarioactual);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            ComentarioController.actualizarComentario(usuarioactual);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            ComentarioController.eliminarComentario(usuarioactual);
                            break;
                        case 5:
                            Pantalla.limpiarPantalla();
                            System.out.println("Regresando...");
                            break;
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");
                    }
                } else { // Admin
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            ComentarioController.consultarComentarios();
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            ComentarioController.actualizarComentario(usuarioactual);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            ComentarioController.eliminarComentario(usuarioactual);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            System.out.println("Regresando...");
                            break;
                        default:
                            Pantalla.limpiarPantalla();
                            System.out.println("Opción inválida. Intente de nuevo");
                    }
                }
                System.out.println(); // Espacio entre operaciones
            } catch (Exception e) {
                System.out.println("Error en el menú de comentarios: " + e.getMessage());
                sc.nextLine();
            }
        } while ((rol != 1 && op != 5) || (rol == 1 && op != 4));
    }
}
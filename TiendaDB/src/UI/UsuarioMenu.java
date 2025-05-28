package UI;

import java.util.Scanner;

import Controller.UsuarioController;
import Model.Usuario;
import Repository.Pantalla;

public class UsuarioMenu {
    static Scanner sc = new Scanner(System.in);

    public static boolean mostrarMenuUsuario(Usuario usuarioactual) {
        int op = 0;
        do {
            try {
                // Mostrar menú diferente según el rol
                if (usuarioactual.getRol_id() == 1) { // ADMIN
                    System.out.println("===== MENÚ DE USUARIOS (ADMIN) =====");
                    System.out.println("1. Insertar usuario");
                    System.out.println("2. Actualizar usuario");
                    System.out.println("3. Eliminar usuario");
                    System.out.println("4. Consultar usuarios");
                    System.out.println("5. Regresar");
                } else { // CLIENTE
                    System.out.println("===== MENÚ DE USUARIOS =====");
                    System.out.print("1. Actualizar mi perfil");
                    System.out.println("\tNota: se necesita tu id para actualizar");
                    System.out.println("2. Eliminar mi cuenta");
                    System.out.println("3. Consultar mi perfil");
                    System.out.println("4. Regresar");
                }
                System.out.print("Seleccione una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                if (usuarioactual.getRol_id() == 1) { // ADMIN
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            UsuarioController.insertarUsuario(usuarioactual.getId());
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            UsuarioController.actualizarUsuario(usuarioactual.getId());
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            UsuarioController.eliminarUsuario(usuarioactual.getId());
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            UsuarioController.consultarUsuarios(usuarioactual.getId());
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
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            UsuarioController.actualizarUsuario(usuarioactual.getId());
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            UsuarioController.eliminarUsuario(usuarioactual.getId());
                            System.out.println("Tu cuenta ha sido eliminada. Regresando al menú principal...");
                            return true; // Esto cerrará sesión
                        case 3:
                            Pantalla.limpiarPantalla();
                            UsuarioController.consultarUsuarios(usuarioactual.getId());
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
                System.out.println("Error en el menú de usuario: " + e.getMessage());
                sc.nextLine();
            }
        } while ((usuarioactual.getRol_id() == 1 && op != 5) || (usuarioactual.getRol_id() != 1 && op != 4));
        return false; //Si no eliminó la cuenta, se queda en sesión
    }
}
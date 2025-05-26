package UI;

import java.util.Scanner;
import Controller.RolController;
import Model.Usuario;
import Repository.Pantalla;

public class RolMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuRol(Usuario usuarioactual) {
        if (usuarioactual.getRol_id() != 1) {
            System.out.println("Acceso denegado. Este menú es solo para administradores");
            System.out.println("Presiona Enter para continuar...");
            sc.nextLine();
            return;
        }
        int op = 0;
        do {
            try {
                System.out.println("===== MENÚ DE ROLES (ADMIN) =====");
                System.out.println("1.- Insertar rol");
                System.out.println("2.- Actualizar rol");
                System.out.println("3.- Eliminar rol");
                System.out.println("4.- Consultar roles");
                System.out.println("5.- Regresar");
                System.out.print("Seleccione una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                switch (op) {
                    case 1:
                        Pantalla.limpiarPantalla();
                        RolController.insertarRol(usuarioactual.getId());
                        break;
                    case 2:
                        Pantalla.limpiarPantalla();
                        RolController.actualizarRol(usuarioactual.getId());
                        break;
                    case 3:
                        Pantalla.limpiarPantalla();
                        RolController.eliminarRol(usuarioactual.getId());
                        break;
                    case 4:
                        Pantalla.limpiarPantalla();
                        RolController.consultarRoles(usuarioactual.getId());
                        break;
                    case 5:
                        Pantalla.limpiarPantalla();
                        System.out.println("Regresando...");
                        break;
                    default:
                        Pantalla.limpiarPantalla();
                        System.out.println("Opción inválida.");
                        break;
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error en el menú de roles: " + e.getMessage());
                sc.nextLine();
            }
        } while (op != 5);
    }
}
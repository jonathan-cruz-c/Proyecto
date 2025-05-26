package UI;

import Controller.LoginController;
import Model.Usuario;
import Repository.Pantalla;
import java.util.Scanner;

public class LoginMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrsarlogin() {
        int op = 0;
        Usuario usuarioactual = null;
        do {
            try {
                System.out.println("------Bienvenido a Amazon------\n");
                System.out.println("===== MENÚ PRINCIPAL =====");
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrarse");
                System.out.println("3. Salir");
                System.out.print("ingresa una opcion de un numero valido: ");
                op = sc.nextInt();
                switch (op) {
                    case 1:
                        Pantalla.limpiarPantalla();
                        usuarioactual = LoginController.login();
                        if (usuarioactual != null) {
                            MenuPrincipal.mostrarMenuPrincipal(usuarioactual);
                        }
                        break;
                    case 2:
                        Pantalla.limpiarPantalla();
                        LoginController.registro();
                        break;
                    case 3:
                        Pantalla.limpiarPantalla();
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        Pantalla.limpiarPantalla();
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error en el menú: " + e.getMessage());
                sc.nextLine();
            }
        } while (op != 3);
    }
}
package Controller;

import Model.Usuario;
import Service.LoginService;

import java.util.Scanner;

public class LoginController {
    static Scanner sc = new Scanner(System.in);

    public static Usuario login() {
        try {
            System.out.println("===== INICIAR SESIÓN =====");
            System.out.print("Ingrese el correo o nombre de usuario: ");
            String correouser = sc.nextLine();
            System.out.print("Ingresa la contraseña: ");
            String contraseña = sc.nextLine();
            Usuario usuario = LoginService.iniciarSesion(correouser, contraseña);
            if (usuario != null) {
                String rolTexto = "Cliente";
                if (usuario.getRol_id() == 1) {
                    rolTexto = "Admin";
                }
                System.out.println("Bienvenido, " + usuario.getNombre() + " (" + rolTexto + ")");
                return usuario;
            } else {
                System.out.println("Algo salió incorrecto");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error en LoginController: " + e.getMessage());
            return null;
        }
    }

    public static void registro() {
        try {
            System.out.println("===== REGISTRARSE =====");
            Usuario usuarionuevo = new Usuario();
            usuarionuevo.setRol_id(2);
            System.out.print("Ingresa un nombre de usuario: ");
            usuarionuevo.setUsername(sc.nextLine());
            System.out.print("Ingresa tu nombre(s): ");
            usuarionuevo.setNombre(sc.nextLine());
            System.out.print("Ingresa tu apellido(s): ");
            usuarionuevo.setApellido(sc.nextLine());
            System.out.print("Ingresa un Correo: ");
            usuarionuevo.setCorreo(sc.nextLine());
            System.out.print("Ingresa una Contraseña: ");
            usuarionuevo.setContraseña(sc.nextLine());
            System.out.print("Ingresa tu numero de telefono: ");
            usuarionuevo.setTelefono(sc.nextLine());
            LoginService.registrarse(usuarionuevo);
        } catch (Exception e) {
            System.out.println("Error en LoginController al registrarse: " + e.getMessage());
        }
    }
}
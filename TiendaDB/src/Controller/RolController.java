package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Rol;
import Service.RolService;

public class RolController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar Rol (solo admin)
    public static void insertarRol(int idsolicitante) {
        try {
            System.out.println("===== INSERTAR ROL =====");
            System.out.print("Ingrese el nombre del nuevo rol: ");
            String nombrerol = sc.nextLine();
            Rol rol = new Rol();
            rol.setNom_rol(nombrerol);
            RolService.insertarRol(rol, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en RolController.insertarRol: " + e.getMessage());
        }
    }

    // ! Actualizar Rol (solo admin)
    public static void actualizarRol(int idsolicitante) {
        try {
            System.out.println("===== ACTUALIZAR ROL =====");
            System.out.print("Ingrese el ID del rol a actualizar: ");
            int idrol = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el nuevo nombre del rol: ");
            String nuevonombre = sc.nextLine();
            Rol rol = new Rol();
            rol.setId(idrol);
            rol.setNom_rol(nuevonombre);
            RolService.actualizarRol(rol, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en RolController.actualizarRol: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar Rol (solo admin)
    public static void eliminarRol(int idsolicitante) {
        try {
            System.out.println("===== ELIMINAR ROL =====");
            System.out.print("Ingrese el ID del rol a eliminar: ");
            int idrol = sc.nextInt();
            sc.nextLine();
            RolService.eliminarRol(idrol, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en RolController.eliminarRol: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar Roles (solo admin)
    public static void consultarRoles(int idsolicitante) {
        try {
            System.out.println("===== LISTA DE ROLES =====");
            ArrayList<Rol> roles = RolService.consultarRoles(idsolicitante);
            if (roles.isEmpty()) {
                System.out.println("No se encontraron roles o no tiene permisos");
            } else {
                for (Rol rol : roles) {
                    System.out.println("ID: " + rol.getId() + " | Nombre: " + rol.getNom_rol());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en RolController.consultarRoles: " + e.getMessage());
        }
    }
}
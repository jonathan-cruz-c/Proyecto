package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Direccion;
import Model.Usuario;
import Service.DireccionService;

public class DireccionController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar dirección
    public static void insertarDireccion(Usuario usuario) {
        try {
            System.out.println("===== Insertar Dirección =====");
            System.out.print("Ingresa la ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("Ingresa el estado: ");
            String estado = sc.nextLine();
            System.out.print("Ingresa el código postal: ");
            String cp = sc.nextLine();
            System.out.print("Ingresa la colonia: ");
            String colonia = sc.nextLine();
            Direccion nueva = new Direccion();
            nueva.setCiudad(ciudad);
            nueva.setEstado(estado);
            nueva.setCodigo_postal(cp);
            nueva.setColonia(colonia);
            DireccionService.agregarDireccion(nueva, usuario.getId());
        } catch (Exception e) {
            System.out.println("Error en insertarDireccion: " + e.getMessage());
        }
    }

    // ! Actualizar dirección (admin cualquier ID / cliente solo las suyas)
    public static void actualizarDireccion(Usuario usuario) {
        try {
            System.out.println("===== Actualizar Dirección =====");
            System.out.print("Ingrese el ID de la dirección a actualizar: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingresa la nueva ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("Ingresa el nuevo estado: ");
            String estado = sc.nextLine();
            System.out.print("Ingresa el nuevo código postal: ");
            String cp = sc.nextLine();
            System.out.print("Ingresa la nueva colonia: ");
            String colonia = sc.nextLine();
            Direccion direccion = new Direccion();
            direccion.setId(id);
            direccion.setCiudad(ciudad);
            direccion.setEstado(estado);
            direccion.setCodigo_postal(cp);
            direccion.setColonia(colonia);
            DireccionService.actualizarDireccion(direccion, usuario.getId());
        } catch (Exception e) {
            System.out.println("Error en actualizarDireccion: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar dirección (solo admin)
    public static void eliminarDireccion(Usuario usuario) {
        try {
            if (usuario.getRol_id() != 1) {
                System.out.println("Solo los administradores pueden eliminar direcciones");
                return;
            }
            System.out.println("===== Eliminar Dirección =====");
            System.out.print("Ingrese el ID de la dirección a eliminar: ");
            int id = sc.nextInt();
            sc.nextLine();
            DireccionService.eliminarDireccion(id, usuario.getId());
        } catch (Exception e) {
            System.out.println("Error en eliminarDireccion: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar direcciones (admin ve todas / cliente las suyas)
    public static void consultarDirecciones(Usuario usuario) {
        try {
            System.out.println("===== Lista de Direcciones =====");
            ArrayList<Direccion> lista = DireccionService.obtenerDirecciones(usuario.getId());
            if (lista.isEmpty()) {
                System.out.println("No hay direcciones disponibles");
                return;
            }
            for (Direccion d : lista) {
                System.out.println("ID: " + d.getId());
                System.out.println("Ciudad: " + d.getCiudad());
                System.out.println("Estado: " + d.getEstado());
                System.out.println("Código Postal: " + d.getCodigo_postal());
                System.out.println("Colonia: " + d.getColonia());
                System.out.println("-----------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error en consultarDirecciones: " + e.getMessage());
        }
    }
}
package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Categoria;
import Service.CategoriaService;

public class CategoriaController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar categoría (solo admin)
    public static void insertarCategoria(int idusuario) {
        try {
            System.out.println("===== INSERTAR CATEGORÍA =====");
            System.out.print("Ingrese el nombre de la categoría: ");
            String nombre = sc.nextLine();
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            CategoriaService.insertarCategoria(categoria, idusuario);
        } catch (Exception e) {
            System.out.println("Error al insertar categoría: " + e.getMessage());
        }
    }

    // ! Actualizar categoría (solo admin)
    public static void actualizarCategoria(int idusuario) {
        try {
            System.out.println("===== ACTUALIZAR CATEGORÍA =====");
            System.out.print("Ingrese el ID de la categoría a actualizar: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el nuevo nombre de la categoría: ");
            String nuevonombre = sc.nextLine();
            Categoria categoria = new Categoria();
            categoria.setId(id);
            categoria.setNombre(nuevonombre);
            CategoriaService.actualizarCategoria(categoria, idusuario);
        } catch (Exception e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar categoría (solo admin)
    public static void eliminarCategoria(int idusuario) {
        try {
            System.out.println("===== ELIMINAR CATEGORÍA =====");
            System.out.print("Ingrese el ID de la categoría a eliminar: ");
            int id = sc.nextInt();
            sc.nextLine();
            CategoriaService.eliminarCategoria(id, idusuario);
        } catch (Exception e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar categorías (admin ve ID, cliente no)
    public static void consultarCategorias(int idusuario) {
        try {
            System.out.println("===== LISTA DE CATEGORÍAS =====");
            ArrayList<Categoria> lista = CategoriaService.consultarCategorias(idusuario);
            if (lista.isEmpty()) {
                System.out.println("No hay categorías registradas");
            } else {
                for (Categoria categoria : lista) {
                    if (categoria.getId() != 0) {
                        System.out.println("ID: " + categoria.getId() + " | Nombre: " + categoria.getNombre());
                    } else {
                        System.out.println("Nombre: " + categoria.getNombre());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar categorías: " + e.getMessage());
        }
    }
}
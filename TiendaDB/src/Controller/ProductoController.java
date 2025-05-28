package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Producto;
import Service.ProductoService;

public class ProductoController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar producto (solo admin)
    public static void insertarProducto(int idsolicitante, int rolsolicitante) {
        try {
            if (rolsolicitante != 1) {
                System.out.println("Solo los administradores pueden insertar productos");
                return;
            }
            System.out.println("=== INSERTAR PRODUCTO ===");
            System.out.print("Ingrese el ID de categoría: ");
            int categoriaId = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingresa el nombre del producto: ");
            String nombre = sc.nextLine();
            System.out.print("Ingresa una descripción del producto: ");
            String descripcion = sc.nextLine();
            System.out.print("Ingresa el precio del producto: ");
            double precio = sc.nextDouble();
            sc.nextLine();
            System.out.print("Ingresa el stock que hay: ");
            int stock = sc.nextInt();
            sc.nextLine();
            Producto producto = new Producto();
            producto.setCategoría_id(categoriaId);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            ProductoService.insertarProducto(producto, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en ProductoController.insertarProducto: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar producto (solo admin)
    public static void actualizarProducto(int idsolicitante, int rolsolicitante) {
        try {
            if (rolsolicitante != 1) {
                System.out.println("Solo los administradores pueden actualizar productos");
                return;
            }
            System.out.println("=== ACTUALIZAR PRODUCTO ===");
            System.out.print("Ingrese el ID del producto a actualizar: ");
            int idproducto = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingresa el nuevo ID de categoría: ");
            int categoriaId = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingresa el nuevo nombre del producto: ");
            String nombre = sc.nextLine();
            System.out.print("Ingresa la nueva descripción del producto: ");
            String descripcion = sc.nextLine();
            System.out.print("Ingresa el nuevo precio del producto: ");
            double precio = sc.nextDouble();
            sc.nextLine();
            System.out.print("Ingresa el nuevo stock que hay: ");
            int stock = sc.nextInt();
            sc.nextLine();
            Producto producto = new Producto();
            producto.setId(idproducto);
            producto.setCategoría_id(categoriaId);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            ProductoService.actualizarProducto(producto, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en ProductoController.actualizarProducto: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar producto (solo admin)
    public static void eliminarProducto(int idsolicitante, int rolsolicitante) {
        try {
            if (rolsolicitante != 1) {
                System.out.println("Solo los administradores pueden eliminar productos");
                return;
            }
            System.out.println("=== ELIMINAR PRODUCTO ===");
            System.out.print("Ingrese el ID del producto a eliminar: ");
            int idProducto = sc.nextInt();
            sc.nextLine();
            ProductoService.eliminarProducto(idProducto, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en ProductoController.eliminarProducto: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar productos (admin y cliente)
    public static void consultarProductos() {
        try {
            System.out.println("=== LISTA DE PRODUCTOS DISPONIBLES ===");
            ArrayList<Producto> productos = ProductoService.consultarProductos();
            if (productos.isEmpty()) {
                System.out.println("No hay productos registrados");
            } else {
                for (Producto p : productos) {
                    System.out.println("ID: " + p.getId());
                    System.out.println("Categoría ID: " + p.getCategoría_id());
                    System.out.println("Nombre: " + p.getNombre());
                    System.out.println("Descripción: " + p.getDescripcion());
                    System.out.println("Precio: $" + p.getPrecio());
                    System.out.println("Stock: " + p.getStock());
                    System.out.println("-----------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en ProductoController.consultarProductos: " + e.getMessage());
            sc.nextLine();
        }
    }
}
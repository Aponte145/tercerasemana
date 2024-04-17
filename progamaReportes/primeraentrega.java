package progamaReportes;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import progamaReportes.modelos.*;
import java.util.List;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import java.util.ArrayList;

/**
 * @apiNote esta clase genera archivos con datos aleatorios, el usuario y genera
 *          un informe de los datos generados en los archivos txt
 * @author Oscar Elias Aponte P, Cristian Alejandro Muñoz O, William Fernando
 *         Riaño M
 * @version 1.0
 * @since 2024-03-26
 */

// Clase principal
public class primeraentrega {

    // importamos la clase vendedor

    public static void main(String[] args) {
        // Llama al metodo GenerarInforme
        primeraentrega instancia = new primeraentrega();

        instancia.generarDatos();

    }

    // metodo para genera un archivo con datos aleatorios el usuario puede escoger
    // que informe desea generar entre (productos vendidos por vendedor, vendedores
    // y productos vendidos)
    public static void GenerarInforme() {
        // Especifica la cantidad de archivos que deseas generar

        try (// scanner para leer la opcion del usuario
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Seleccione la accion que desea realizar:");
            System.out.println("1. Generar Datos de prueba");
            System.out.println("2. Generar Informes ");
            System.out.println("4. Salir");
            int opcion = scanner.nextInt();

            // toma la opcion del usuario y genera el archivo correspondiente
            switch (opcion) {
                case 1:
                    String fileName = "ProductosVendidosPorVendedor.txt";
                    GenerateInfoFile(fileName);
                    GenerarInforme();
                    break;
                case 2:
                    String fileName2 = "Vendedores.txt";
                    GenerateInfoFile(fileName2);
                    GenerarInforme();
                    break;
                case 3:
                    String fileName3 = "ProductosVendidos.txt";
                    GenerateInfoFile(fileName3);
                    GenerarInforme();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    GenerarInforme();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    // Función para generar un archivo con datos aleatorios

    public static void GenerateInfoFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            // Especifica la cantidad de registros que deseas generar por archivo
            int numberOfRecords = 10;
            // define un objeto Random para generar datos aleatorios
            Random random = new Random();

            for (int i = 0; i < numberOfRecords; i++) {
                // Genera datos aleatorios para cada campo
                String tipoDocumento = "TIPO" + (random.nextInt(5) + 1); // Ejemplo: TIPO1, TIPO2, etc.
                String numeroDocumento = String.valueOf(random.nextInt(1000000) + 1);
                // Genera nombres aleatorios para el vendedor que no sea numerico
                String nombresVendedor = "Nombre " + generarNombre(10);

                String apellidosVendedor = "Apellido " + generarNombre(10); // Genera apellidos aleatorios

                // Escribe el registro en el archivo
                writer.write(tipoDocumento + ";" + numeroDocumento + ";" + nombresVendedor + ";" + apellidosVendedor);
                writer.newLine();
            }

            writer.close();
            System.out.println("Archivo generado: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Función para generar un nombre aleatorio
    public static String generarNombre(int longitud) {
        String letras = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder nombre = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(letras.length());
            nombre.append(letras.charAt(index));
        }

        return nombre.toString();
    }

    public static void createSalesMenFile(int randomSalesCount, String name, long id) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(name + ".txt"));
            Random random = new Random();
            for (int i = 0; i < randomSalesCount; i++) {
                String productName = "Producto" + (random.nextInt(5) + 1);
                int quantity = random.nextInt(10) + 1;
                writer.write(id + ";" + name + ";" + productName + ";" + quantity);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // genrar datos
    public void generarDatos() {

        // preguntar al usuario que datos desea generar, vendedores, ventas, productos
        // vendidos.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la data que deseas generar: ");
        System.out.println("1. Vendedores");
        System.out.println("2. Productos");
        System.out.println("3. Ventas");
        System.out.println("4. Analizar Ventas por Vendedor");
        int opcion = scanner.nextInt();

        switch (opcion) {

            case 1:
                // se le pregunta al usuario cuantos vendedores desea generar
                System.out.println("Ingrese la cantidad de vendedores que desea generar: ");
                int cantidadVendedores = scanner.nextInt();
                // se llama al metodo crearvendedoresList
                crearvendedoresList(cantidadVendedores);
                System.out.println("Archivo de vendedores generado");
                generarDatos();
                break;

            case 2:
                // se le pregunta al usuario cuantas ventas desea generar
                System.out.println("Ingrese la cantidad de archivos de productos que desea generar: ");

                int cantidadProductos = scanner.nextInt();
                // se llama al metodo createSalesMenFile
                generarProductosCocina(cantidadProductos);
                System.out.println("Archivo de productos generado");
                generarDatos();
                break;
            case 3:
                // se le pregunta al usuario cuantos productos desea generar
                System.out.println("Ingrese la cantidad de venta que desea generar: ");
                int cantidadVentas = scanner.nextInt();
                // se llama al metodo generarProductosCocina

                generarProductosVendidosPorVendedor(cantidadVentas);
                System.out.println("Archivo de venta generados");
                generarDatos();
                break;
            case 4:
                System.out.println("Analizando Archivos...");
                generarInformeProductosVendidosPorVendedor();
                generarDatos();
                break;
            default:
                System.out.println("Opcion no valida");

                break;

            // si el usuario selecciona vendedores

            // si el usuario selecciona una opcion no valida
        }

    }

    // metodo que genera aleatoriamente un archivo con datos de vendedores
    public void crearvendedoresList(int cantidadVendedores) {
        // Llama al método createSalesMenList de manera estática
        List<vendedor> listaVendedores = vendedor.createSalesMenList(cantidadVendedores);
        // crea un archivo con los datos de los vendedores
        try {
            // verifica si el archivo existe
            File file = new File("Vendedores.txt");
            if (file.exists()) {
                eliminarArchivo("Vendedores.txt");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("Vendedores.txt"));
            for (vendedor vendedor : listaVendedores) {
                writer.write(
                        vendedor.getNombre() + ";" + vendedor.getApellido() + ";" + vendedor.getTipoDocumento() + ";"
                                + vendedor.getNumeroDocumento());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    // metodo que genera productos de cocina
    public void generarProductosCocina(int cantidadProductos) {
        // Llama al método generarProductosCocina de manera estática
        List<ProductoCocina> listaProductos = ProductoCocina.generarProductosCocina(cantidadProductos);
        // crea un archivo con los datos de los productos
        try {
            // verifica si el archivo existe
            File file = new File("ProductosCocina.txt");
            if (file.exists()) {
                eliminarArchivo("ProductosCocina.txt");
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductosCocina.txt"));
            for (ProductoCocina producto : listaProductos) {
                writer.write(producto.getIdProducto() + ";" + producto.getNombreProducto() + ";"
                        + producto.getPrecioPorUnidad());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    // metodo que genera aleatoriamente un archivo con datos de productos vendidos
    public void generarProductosVendidosPorVendedor(int cantidadArchivos) {
        // valida que existan los archivos de vendedores
        File file = new File("Vendedores.txt");
        if (!file.exists()) {
            System.out.println("El archivo de vendedores no existe");
            return;
        }
        // valida que existan los archivos de productos
        File file2 = new File("ProductosCocina.txt");
        if (!file2.exists()) {
            System.out.println("El archivo de productos no existe");
            return;
        }
        // Abre el archivo de vendedores y crea una lista con los datos
        List<vendedor> listaVendedores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Vendedores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                vendedor vendedor = new vendedor(data[0], data[1], data[2], Long.parseLong(data[3]));
                listaVendedores.add(vendedor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Abre el archivo de productos y crea una lista con los datos
        List<ProductoCocina> listaProductos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ProductosCocina.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                ProductoCocina producto = new ProductoCocina(Integer.parseInt(data[0]), data[1],
                        Integer.parseInt(data[2]));
                listaProductos.add(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        for (int i = 0; i < cantidadArchivos; i++) {
            try {

                // verifica si el archivo existe
                File file3 = new File("ProductosVendidosPorVendedor" + (i + 1) + ".txt");
                if (file3.exists()) {
                    eliminarArchivo("ProductosVendidosPorVendedor" + (i + 1) + ".txt");
                }

                // Genera un nombre aleatorio para el archivo
                String fileName = "ProductosVendidosPorVendedor" + (i + 1) + ".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                // Selecciona un vendedor aleatorio
                vendedor vendedor = listaVendedores.get(random.nextInt(listaVendedores.size()));
                // Escribe los datos del vendedor en la primera línea
                writer.write(vendedor.getTipoDocumento() + ";" + vendedor.getNumeroDocumento());
                writer.newLine();
                // Genera una cantidad aleatoria de productos vendidos
                int cantidadProductos = random.nextInt(5) + 1;
                for (int j = 0; j < cantidadProductos; j++) {
                    // Selecciona un producto aleatorio
                    ProductoCocina producto = listaProductos.get(random.nextInt(listaProductos.size()));
                    // Genera una cantidad aleatoria de productos vendidos
                    int cantidadVendida = random.nextInt(10) + 1;
                    // Escribe los datos del producto vendido en una línea
                    writer.write(producto.getIdProducto() + ";" + cantidadVendida);
                    writer.newLine();
                }
                writer.close();
                System.out.println("Archivo generado: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // metodo que elimina si existe un archivo
    public void eliminarArchivo(String nombreArchivo) {
        try {
            // Crea un objeto File con el nombre del archivo
            File file = new File(nombreArchivo);
            // Verifica si el archivo existe
            if (file.exists()) {
                // Elimina el archivo
                file.delete();
                System.out.println("Archivo eliminado: " + nombreArchivo);
            } else {
                System.out.println("El archivo no existe: " + nombreArchivo);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // crea un archivo con los datos de los productos vendidos por vendedor donde no
    // se repiten los vendedores y se muestre la suma en valor de los productos
    // vendidos
    public void generarInformeProductosVendidosPorVendedor() {
        // Crea un arreglo para almacenar los datos
        List<String> datos = new ArrayList<>();
        Map<String, StringBuilder> ventasPorVendedor = new HashMap<>();

        // cantidad de productos vendidos por vendedor
        File directory = new File(".");
        // Obtiene la lista de archivos del directorio
        File[] files = directory.listFiles();
        // Recorre la lista de archivos
        for (File file : files) {
            // Verifica si el archivo es un archivo regular y si el nombre empieza con
            if (file.isFile() && file.getName().startsWith("ProductosVendidosPorVendedor")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String vendedorActual = null;
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (vendedorActual == null) {
                            vendedorActual = line;
                            ventasPorVendedor.put(vendedorActual, new StringBuilder());
                        } else {
                            ventasPorVendedor.get(vendedorActual).append(line).append("\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Tomamos el archivo de vendedores y creamos un arreglo con los datos
        List<String> vendedores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Vendedores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                vendedores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        // tomamos el archivo de productos y creamos un arreglo con los datos
        List<String> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ProductosCocina.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                productos.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        // imprime los datos de los productos vendidos por vendedor
        for (String vendedor : vendedores) {
            String[] data = vendedor.split(";");
            String key = data[2] + ";" + data[3];
            StringBuilder ventas = ventasPorVendedor.get(key);
            if (ventas != null) {
                System.out.println("Vendedor: " + data[0] + " " + data[1]);
                System.out.println("Ventas:");
                String[] lines = ventas.toString().split("\n");
                int total = 0;
                for (String line : lines) {
                    String[] values = line.split(";");
                    int idProducto = Integer.parseInt(values[0]);
                    int cantidad = Integer.parseInt(values[1]);
                    String producto = productos.get(idProducto - 1);
                    String[] productoData = producto.split(";");
                    int precio = Integer.parseInt(productoData[2]);
                    total += precio * cantidad;
                    System.out.println(
                            productoData[1] + ": " + cantidad + " x $" + precio + " = $" + (precio * cantidad));
                }
                System.out.println("Total: $" + total);
                System.out.println();
            }
        }

    }

}

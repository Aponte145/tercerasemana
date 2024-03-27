import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

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

    public static void main(String[] args) {
        // Llama al metodo GenerarInforme
        GenerarInforme();

    }

    // metodo para genera un archivo con datos aleatorios el usuario puede escoger
    // que informe desea generar entre (productos vendidos por vendedor, vendedores
    // y productos vendidos)
    public static void GenerarInforme() {
        // Especifica la cantidad de archivos que deseas generar

        try (// scanner para leer la opcion del usuario
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Seleccione el informe que desea generar: ");
            System.out.println("1. Productos vendidos por vendedor");
            System.out.println("2. Vendedores");
            System.out.println("3. Productos vendidos");
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
}
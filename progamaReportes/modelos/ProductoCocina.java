package progamaReportes.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class ProductoCocina {
    private int idProducto;
    private String nombreProducto;
    private int precioPorUnidad;

    public ProductoCocina(int idProducto, String nombreProducto, int precioPorUnidad) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioPorUnidad = precioPorUnidad;
    }

    // Getters
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public static List<ProductoCocina> generarProductosCocina(int cantidadProductos) {
        String[] nombresProductos = { "Sarten", "Olla", "Cuchillo", "Tabla de cortar", "Colador", "Batidora",
                "Exprimidor", "Plato", "Vaso", "Cacerola", "Espatula", "Rallador", "Cuchara", "Tenedor",
                "Cuchillo de pan", "Taza medidora", "Balanza de cocina", "Termometro de cocina",
                "Bandeja para horno", "Tapa" };

        List<ProductoCocina> productos = new ArrayList<>();
        Random random = new Random();
        Set<String> nombresUtilizados = new HashSet<>(); // Conjunto para almacenar nombres de productos utilizados

        while (productos.size() < cantidadProductos) {
            int idProducto = productos.size() + 1;
            String nombreProducto = nombresProductos[random.nextInt(nombresProductos.length)];

            // Verifica si el nombre del producto ya ha sido utilizado
            if (!nombresUtilizados.contains(nombreProducto)) {
                nombresUtilizados.add(nombreProducto);
                // limitamos el precio por unidad a un rango de 10 a 100
                int precioPorUnidad = random.nextInt(91) + 10;

                ProductoCocina producto = new ProductoCocina(idProducto, nombreProducto, precioPorUnidad);
                productos.add(producto);
            }
        }

        return productos;
    }
}

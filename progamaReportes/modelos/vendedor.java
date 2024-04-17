package progamaReportes.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class vendedor {
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private long numeroDocumento;

    public vendedor(String nombre, String apellido, String tipoDocumento, long numeroDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    private static class VendedorData {
        String nombre;
        String apellido;
        String tipoDocumento;
        long numeroDocumento;

        public VendedorData(String nombre, String apellido, String tipoDocumento, long numeroDocumento) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.tipoDocumento = tipoDocumento;
            this.numeroDocumento = numeroDocumento;
        }

    }

    public static List<vendedor> createSalesMenList(int cantidadVendedores) {
        List<VendedorData> datosVendedores = generarDatosVendedores();
        List<vendedor> vendedores = new ArrayList<>();
        Random random = new Random();
        Set<String> documentosUtilizados = new HashSet<>();

        while (vendedores.size() < cantidadVendedores && !datosVendedores.isEmpty()) {
            int indice = random.nextInt(datosVendedores.size());
            VendedorData datos = datosVendedores.remove(indice);

            // Genera una cadena única para verificar si ya ha sido utilizado
            String documento = datos.tipoDocumento + "-" + datos.numeroDocumento;

            // Verifica si el documento ya ha sido utilizado
            if (!documentosUtilizados.contains(documento)) {
                documentosUtilizados.add(documento);

                vendedor vendedor = new vendedor(datos.nombre, datos.apellido, datos.tipoDocumento,
                        datos.numeroDocumento);
                vendedores.add(vendedor);
            }
        }

        return vendedores;
    }

    private static List<VendedorData> generarDatosVendedores() {
        String[] NombreVendedor = { "Juan", "Pedro", "Maria", "Luis", "Carlos", "Sofia", "Alejandro", "Ana", "David",
                "Laura", "Daniel", "Carolina", "Jorge", "Natalia", "Gabriel", "Valentina", "Santiago", "Camila",
                "Oscar", "Andrea" };
        String[] ApellidoVendedor = { "Perez", "Gomez", "Rodriguez", "Gonzalez", "Martinez", "Fernandez", "Lopez",
                "Sanchez", "Perez", "Garcia", "Rodriguez", "Martinez", "Ramirez", "Diaz", "Torres", "Chavez", "Castro",
                "Morales", "Herrera", "Rivera" };
        String[] TipoDocumento = { "CC", "CC", "CE", "CC", "CE", "CC", "CC", "CE", "CC", "CE", "CC", "CE", "CC", "CE",
                "CC", "CE", "CC", "CE", "CC", "CE" };
        long[] numeroDocumento = { 123456789, 987654321, 123456789, 987654321, 123456789, 987654322, 123456791,
                987654323, 123456792, 987654324, 123456793, 987654325, 123456794, 987654326, 123456795, 987654327,
                123456796, 987654328, 123456797, 987654329 };

        List<VendedorData> datosVendedores = new ArrayList<>();
        for (int i = 0; i < NombreVendedor.length; i++) {
            datosVendedores.add(
                    new VendedorData(NombreVendedor[i], ApellidoVendedor[i], TipoDocumento[i], numeroDocumento[i]));
        }
        return datosVendedores;
    }
}

import java.util.ArrayList;

public class HistorialVentas {
    private ArrayList<Venta> historial;

    public HistorialVentas() {
        historial = new ArrayList<>();
    }

    public void agregarVenta(Venta venta) {
        historial.add(venta);
    }

    public void mostrarHistorialVentas(ArrayList<Venta> productosVendidos) {
        if (historial.isEmpty()) {
            System.out.println("No hay ventas registradas en el historial.");
            return;
        }

        System.out.println("----- HISTORIAL DE VENTAS -----");
        for (Venta venta : historial) {
            if (productosVendidos.contains(venta)) {
                venta.mostrarInformacion();
                System.out.println("----------------------------------");
            }
        }
    }

    public void limpiarHistorial() {
        historial.clear();
        System.out.println("El historial de ventas ha sido limpiado.");
    }
}
abstract class Venta {
    private String nombre;
    private double precio;
    private int cantidad;

    public Venta(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public abstract void mostrarInformacion();
}
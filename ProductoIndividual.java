class ProductoIndividual extends Venta {
    public ProductoIndividual(String nombre, double precio, int cantidad) {
        super(nombre, precio, cantidad);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Información del producto:");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Cantidad: " + getCantidad());
    }
}
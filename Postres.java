class Postres extends Venta {
    private String tipoPostre;

    public Postres(String tipoPostre, double precio, int cantidad) {
        super("Postre", precio, cantidad);
        this.tipoPostre = tipoPostre;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Información del postre:");
        System.out.println("Tipo: " + tipoPostre);
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Cantidad: " + getCantidad());
    }
}
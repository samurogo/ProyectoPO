class Bebidas extends Venta {
    private String tipoBebida;
    private String saborBebida;

    public Bebidas(String tipoBebida, String saborBebida, double precio, int cantidad) {
        super("Bebida", precio, cantidad);
        this.tipoBebida = tipoBebida;
        this.saborBebida = saborBebida;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Información de la bebida:");
        System.out.println("Tipo: " + tipoBebida);
        System.out.println("Sabor: " + saborBebida);
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Cantidad: " + getCantidad());
    }
}

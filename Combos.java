class Combos extends Venta {
    private String tipoCombo;

    public Combos(String tipoCombo, double precio, int cantidad) {
        super("Combo", precio, cantidad);
        this.tipoCombo = tipoCombo;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Información del combo:");
        System.out.println("Tipo: " + tipoCombo);
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Cantidad: " + getCantidad());

        if (tipoCombo.equalsIgnoreCase("hamburguesa")) {
            System.out.println("Este combo cambia diariamente. Consulte el combo del día en la sucursal.");
        } else if (tipoCombo.equalsIgnoreCase("alitas")) {
            System.out.println("Este combo cambia diariamente. Consulte el combo del día en la sucursal.");
        } else if (tipoCombo.equalsIgnoreCase("hot dog")) {
            System.out.println("Este combo cambia diariamente. Consulte el combo del día en la sucursal.");
        } else if (tipoCombo.equalsIgnoreCase("pizza")) {
            System.out.println("Este combo cambia diariamente. Consulte el combo del día en la sucursal.");
        }
    }
}
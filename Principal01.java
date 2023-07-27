import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Venta> ventas = new ArrayList<>();
        HistorialVentas historialVentas = new HistorialVentas();

        int opcion = 0;
        while (opcion != 17) {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        Venta producto = capturarInformacionProducto(scanner);
                        if (producto != null) {
                            ventas.add(producto);
                            historialVentas.agregarVenta(producto);
                        }
                        break;
                    case 2:
                        verVentaDeProductos(ventas);
                        break;
                    case 3:
                        eliminarVentaDeProducto(ventas, scanner);
                        break;
                    case 4:
                        Venta combo = capturarInformacionCombo(scanner);
                        if (combo != null) {
                            ventas.add(combo);
                            historialVentas.agregarVenta(combo);
                        }
                        break;
                    case 5:
                        mostrarCombos(ventas);
                        break;
                    case 6:
                        eliminarCombo(ventas, scanner);
                        break;
                    case 7:
                        Venta bebida = capturarInformacionBebida(scanner);
                        if (bebida != null) {
                            ventas.add(bebida);
                            historialVentas.agregarVenta(bebida);
                        }
                        break;
                    case 8:
                        verBebidas(ventas);
                        break;
                    case 9:
                        eliminarBebidas(ventas, scanner);
                        break;
                    case 10:
                        Venta postre = capturarInformacionPostre(scanner);
                        if (postre != null) {
                            ventas.add(postre);
                            historialVentas.agregarVenta(postre);
                        }
                        break;
                    case 11:
                        verPostres(ventas);
                        break;
                    case 12:
                        eliminarPostres(ventas, scanner);
                        break;
                    case 13:
                        mostrarInformacion(ventas);
                        break;
                    case 14:
                        venderProductos(ventas, scanner);
                        break;
                    case 15:
                        historialVentas.mostrarHistorialVentas(ventas);
                        break;
                    case 16:
                        historialVentas.limpiarHistorial();
                        break;
                    case 17:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    //////////////////////////// INICIO MENU////////////////////////////
    public static void mostrarMenu() {
        System.out.println("---------- MENÚ ----------");
        System.out.println("1.  Ingresar información de productos");
        System.out.println("2.  Ver productos");
        System.out.println("3.  Eliminar productos");
        System.out.println("4.  Ingresar información de combo");
        System.out.println("5.  Ver Combos");
        System.out.println("6.  Eliminar combo");
        System.out.println("7.  Ingresar información de bebida");
        System.out.println("8.  Ver bebidas");
        System.out.println("9.  Eliminar bebidas");
        System.out.println("10. Ingresar información de postre");
        System.out.println("11. Ver postres");
        System.out.println("12. Eliminar postre");
        System.out.println("13. Mostrar inventario");
        System.out.println("14. Realizar venta");
        System.out.println("15. Historial ventas");
        System.out.println("16. Limpiar historial");
        System.out.println("17. SALIR DEL MENÚ");
        System.out.println("Ingrese una opción: ");
    }
    //////////////////////////// FIN MENU////////////////////////////

    //////////////////////////// INICIO PRODUCTOS////////////////////////////
    public static Venta capturarInformacionProducto(Scanner scanner) {
        String nombre = "";

        boolean nombreValido = false;
        while (!nombreValido) {
            System.out.println("Ingrese el nombre del producto:");
            nombre = scanner.next();
            if (!nombre.matches("[a-zA-Z]+")) {
                System.out.println(
                        "Error: El nombre del producto no puede contener números ni caracteres especiales ni espacios.");
            } else {
                nombreValido = true;
            }
        }
        double precio = 0;
        boolean precioValido = false;
        do {
            try {
                System.out.println("Ingrese el precio del producto:");
                precio = scanner.nextDouble();
                if (precio < 1) {
                    System.out.println("Error: Debes ingresar un precio válido.");
                } else {
                    precioValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "Error: Debes ingresar un valor numérico para el precio y el nombre no puede tener espacios.");
                scanner.nextLine();
            }
        } while (!precioValido);

        int cantidad = 0;
        boolean cantidadValida = false;
        do {
            try {
                System.out.println("Ingrese la cantidad deseada del producto:");
                cantidad = scanner.nextInt();
                if (cantidad < 1) {
                    System.out.println("Error: Debes ingresar una cantidad válida.");
                } else {
                    cantidadValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un valor numérico para la cantidad.");
                scanner.nextLine();
            }
        } while (!cantidadValida);

        return new ProductoIndividual(nombre, precio, cantidad);
    }
    //////////////////////////// FIN PRODUCTO////////////////////////////

    //////////////////////////// INICIO VER PRODUCTOS////////////////////////////
    public static void verVentaDeProductos(ArrayList<Venta> productos) {
        System.out.println("----- PRODUCTOS INDIVIDUALES DISPONIBLES -----");
        boolean productosEncontrados = false;

        for (Venta producto : productos) {
            if (producto instanceof ProductoIndividual) {
                productosEncontrados = true;
                producto.mostrarInformacion();
                System.out.println();
            }
        }

        if (!productosEncontrados) {
            System.out.println("No hay productos individuales registrados.");
        }
        System.out.println("----------------------------------");
    }
    //////////////////////////// FIN VER PRODUCTOS////////////////////////////

    //////////////////////////// INICIO ELIMINAR PRODU////////////////////////////
    public static void eliminarVentaDeProducto(ArrayList<Venta> productos, Scanner scanner) {
        System.out.println("----- ELIMINAR PRODUCTO INDIVIDUAL -----");
        if (productos.isEmpty()) {
            System.out.println("No hay productos individuales para eliminar.");
            return;
        }

        System.out.println("Lista de productos individuales:");
        int countProductos = 0;
        for (int i = 0; i < productos.size(); i++) {
            Venta producto = productos.get(i);
            if (producto instanceof ProductoIndividual) {
                countProductos++;
                System.out.println(countProductos + ". ");
                producto.mostrarInformacion();
                System.out.println();
            }
        }

        System.out.println("Ingrese el número del producto individual que desea eliminar:");
        int numProductoEliminar;
        try {
            numProductoEliminar = scanner.nextInt();
            if (numProductoEliminar >= 1 && numProductoEliminar <= countProductos) {
                int indexProductoEliminar = -1;
                int productoCounter = 0;

                for (int i = 0; i < productos.size(); i++) {
                    Venta producto = productos.get(i);
                    if (producto instanceof ProductoIndividual) {
                        productoCounter++;
                        if (productoCounter == numProductoEliminar) {
                            indexProductoEliminar = i;
                            break;
                        }
                    }
                }

                if (indexProductoEliminar != -1) {
                    productos.remove(indexProductoEliminar);
                    System.out.println("Producto individual eliminado correctamente.");
                } else {
                    System.out.println("El número ingresado no corresponde a un producto individual.");
                }
            } else {
                System.out.println("Número de producto individual inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            scanner.nextLine();
        }
    }
    //////////////////////////// FIN ELIMINAR PRODUCTO////////////////////////////

    //////////////////////////// INICIO COMBO////////////////////////////
    public static Combos capturarInformacionCombo(Scanner scanner) {
        String tipoCombo = "";
        double precio = 0;
        int cantidad = 0;

        boolean nombreValido = false;
        while (!nombreValido) {
            System.out.println("Ingrese el tipo de combo:");
            tipoCombo = scanner.next();
            if (!tipoCombo.matches("[a-zA-Z]+")) {
                System.out.println("Error: El nombre del producto no puede contener números ni caracteres especiales.");
            } else {
                nombreValido = true;
            }
        }

        boolean precioValido = false;
        do {
            try {
                System.out.println("Ingrese el precio del combo:");
                precio = scanner.nextDouble();
                if (precio < 1) {
                    System.out.println("Error: Debes ingresar un precio válido.");
                } else {
                    precioValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "Error: Debes ingresar un valor numérico para el precio y el nombre no puede contener espacios.");
                scanner.nextLine();
            }
        } while (!precioValido);

        boolean cantidadValida = false;
        do {
            try {
                System.out.println("Ingrese la cantidad deseada del combo:");
                cantidad = scanner.nextInt();
                if (cantidad < 1) {
                    System.out.println("Error: Debes ingresar una cantidad válida.");
                } else {
                    cantidadValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un valor numérico para la cantidad.");
                scanner.nextLine();
            }
        } while (!cantidadValida);

        Combos combo = new Combos(tipoCombo, precio, cantidad);
        System.out.println("Información del combo ingresada correctamente.");

        return combo;
    }
    //////////////////////////// FIN COMBO////////////////////////////

    //////////////////////////// INICIO MOSTRAR COMBOS////////////////////////////
    public static void mostrarCombos(ArrayList<Venta> ventas) {
        System.out.println("----- COMBOS DISPONIBLES -----");
        boolean combosEncontrados = false;
        for (Venta venta : ventas) {
            if (venta instanceof Combos) {
                combosEncontrados = true;
                venta.mostrarInformacion();
                System.out.println();
            }
        }
        if (!combosEncontrados) {
            System.out.println("No hay combos registrados.");
        }
        System.out.println("----------------------------------");
    }
    //////////////////////////// FIN MOSTRAR COMBOS////////////////////////////

    //////////////////////////// INICIO ELIMINAR COMBO////////////////////////////
    public static void eliminarCombo(ArrayList<Venta> ventas, Scanner scanner) {
        System.out.println("----- ELIMINAR COMBO -----");
        boolean combosEncontrados = false;
        int countCombos = 0;

        for (Venta venta : ventas) {
            if (venta instanceof Combos) {
                combosEncontrados = true;
                countCombos++;
                System.out.println(countCombos + ". ");
                venta.mostrarInformacion();
                System.out.println();
            }
        }

        if (!combosEncontrados) {
            System.out.println("No hay combos para eliminar.");
            return;
        }

        System.out.println("Ingrese el número del combo que desea eliminar:");
        int numComboEliminar;
        try {
            numComboEliminar = scanner.nextInt();
            if (numComboEliminar >= 1 && numComboEliminar <= countCombos) {
                int indexComboEliminar = -1;
                int comboCounter = 0;

                for (int i = 0; i < ventas.size(); i++) {
                    Venta venta = ventas.get(i);
                    if (venta instanceof Combos) {
                        comboCounter++;
                        if (comboCounter == numComboEliminar) {
                            indexComboEliminar = i;
                            break;
                        }
                    }
                }

                if (indexComboEliminar != -1) {
                    ventas.remove(indexComboEliminar);
                    System.out.println("Combo eliminado correctamente.");
                } else {
                    System.out.println("El número ingresado no corresponde a un combo.");
                }
            } else {
                System.out.println("Número de combo inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            scanner.nextLine();
        }
    }
    //////////////////////////// FIN ELIMINAR COMBO////////////////////////////

    //////////////////////////// INICIO BEBIDAS////////////////////////////
    public static Bebidas capturarInformacionBebida(Scanner scanner) {
        String tipoBebida = "";
        String saborBebida = "";
        double precio = 0;
        int cantidad = 0;

        boolean tipoBebidaValido = false;
        while (!tipoBebidaValido) {
            System.out.println("Ingrese el tipo de bebida:");
            tipoBebida = scanner.next();
            if (!tipoBebida.matches("^[a-zA-Z]+$")) {
                System.out.println("Error: El tipo de bebida no puede contener números ni caracteres especiales.");
            } else {
                tipoBebidaValido = true;
            }
        }

        boolean saborBebidaValido = false;
        while (!saborBebidaValido) {
            System.out.println("Ingrese el sabor de la bebida:");
            saborBebida = scanner.next();
            if (!saborBebida.matches("^[a-zA-Z]+$")) {
                System.out.println("Error: El sabor de la bebida no puede contener números ni caracteres especiales.");
            } else {
                saborBebidaValido = true;
            }
        }

        boolean precioValido = false;
        do {
            try {
                System.out.println("Ingrese el precio de la bebida:");
                precio = scanner.nextDouble();
                if (precio < 1) {
                    System.out.println("Error: Debes ingresar un precio válido.");
                } else {
                    precioValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un valor numérico para el precio.");
                scanner.nextLine();
            }
        } while (!precioValido);

        boolean cantidadValida = false;
        do {
            try {
                System.out.println("Ingrese la cantidad deseada de la bebida:");
                cantidad = scanner.nextInt();
                if (cantidad < 1) {
                    System.out.println("Error: Debes ingresar una cantidad válida.");
                } else {
                    cantidadValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un valor numérico para la cantidad.");
                scanner.nextLine();
            }
        } while (!cantidadValida);

        Bebidas bebida = new Bebidas(tipoBebida, saborBebida, precio, cantidad);
        System.out.println("Información de la bebida ingresada correctamente.");

        return bebida;
    }
    //////////////////////////// FIN BEBIDAS////////////////////////////

    //////////////////////////// INICIO VER BEBIDAS////////////////////////////
    public static void verBebidas(ArrayList<Venta> ventas) {
        System.out.println("----- BEBIDAS DISPONIBLES -----");
        boolean bebidasEncontradas = false;
        for (Venta venta : ventas) {
            if (venta instanceof Bebidas) {
                bebidasEncontradas = true;
                venta.mostrarInformacion();
                System.out.println();
            }
        }
        if (!bebidasEncontradas) {
            System.out.println("No hay bebidas registradas.");
        }
        System.out.println("----------------------------------");
    }
    //////////////////////////// FIN VER BEBIDAS////////////////////////////

    //////////////////////////// INICIO ELIMINAR BEBIDAS////////////////////////////
    public static void eliminarBebidas(ArrayList<Venta> ventas, Scanner scanner) {
        System.out.println("----- ELIMINAR BEBIDA -----");
        boolean bebidasEncontradas = false;
        int countBebidas = 0;

        for (Venta venta : ventas) {
            if (venta instanceof Bebidas) {
                bebidasEncontradas = true;
                countBebidas++;
                System.out.println(countBebidas + ". ");
                venta.mostrarInformacion();
                System.out.println();
            }
        }

        if (!bebidasEncontradas) {
            System.out.println("No hay bebidas para eliminar.");
            return;
        }

        System.out.println("Ingrese el número de la bebida que desea eliminar:");
        int numBebidaEliminar;
        try {
            numBebidaEliminar = scanner.nextInt();
            if (numBebidaEliminar >= 1 && numBebidaEliminar <= countBebidas) {
                int indexBebidaEliminar = -1;
                int bebidaCounter = 0;

                for (int i = 0; i < ventas.size(); i++) {
                    Venta venta = ventas.get(i);
                    if (venta instanceof Bebidas) {
                        bebidaCounter++;
                        if (bebidaCounter == numBebidaEliminar) {
                            indexBebidaEliminar = i;
                            break;
                        }
                    }
                }

                if (indexBebidaEliminar != -1) {
                    ventas.remove(indexBebidaEliminar);
                    System.out.println("Bebida eliminada correctamente.");
                } else {
                    System.out.println("El número ingresado no corresponde a una bebida.");
                }
            } else {
                System.out.println("Número de bebida inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            scanner.nextLine();
        }
    }
    //////////////////////////// FIN ELIMINAR BEBIDAS////////////////////////////

    //////////////////////////// INICIO POSTRES////////////////////////////
    public static Postres capturarInformacionPostre(Scanner scanner) {
        String tipoPostre = "";
        double precio = 0;
        int cantidad = 0;

        boolean tipoPostreValido = false;
        while (!tipoPostreValido) {
            System.out.println("Ingrese el tipo de postre:");
            tipoPostre = scanner.next();

            if (!tipoPostre.matches("^[a-zA-Z]+$")) {
                System.out.println("Error: El tipo de postre no puede contener números.");
            } else {
                tipoPostreValido = true;
            }
        }

        boolean precioValido = false;
        do {
            try {
                System.out.println("Ingrese el precio del postre:");
                precio = scanner.nextDouble();
                if (precio < 1) {
                    System.out.println("Error: Debes ingresar un precio válido.");
                } else {
                    precioValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un valor numérico para el precio.");
                scanner.nextLine();
            }
        } while (!precioValido);

        boolean cantidadValida = false;
        do {
            try {
                System.out.println("Ingrese la cantidad deseada del postre:");
                cantidad = scanner.nextInt();
                if (cantidad < 1) {
                    System.out.println("Error: Debes ingresar una cantidad válida.");
                } else {
                    cantidadValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un valor numérico para la cantidad.");
                scanner.nextLine();
            }
        } while (!cantidadValida);

        Postres postre = new Postres(tipoPostre, precio, cantidad);
        System.out.println("Información del postre ingresada correctamente.");

        return postre;
    }
    //////////////////////////// FIN POSTRES////////////////////////////

    //////////////////////////// INICIO VER POSTRES////////////////////////////
    public static void verPostres(ArrayList<Venta> ventas) {
        System.out.println("----- VER POSTRES DISPONIBLES -----");
        boolean postresEncontrados = false;
        for (Venta venta : ventas) {
            if (venta instanceof Postres) {
                postresEncontrados = true;
                venta.mostrarInformacion();
                System.out.println();
            }
        }
        if (!postresEncontrados) {
            System.out.println("No hay postres registrados.");
        }
        System.out.println("----------------------------------");
    }
    //////////////////////////// FIN VER POSTRES////////////////////////////

    //////////////////////////// INICIO ELIMINAR POSTRE////////////////////////////
    public static void eliminarPostres(ArrayList<Venta> ventas, Scanner scanner) {
        System.out.println("----- ELIMINAR POSTRE -----");
        boolean postresEncontrados = false;
        int countPostres = 0;

        for (Venta venta : ventas) {
            if (venta instanceof Postres) {
                postresEncontrados = true;
                countPostres++;
                System.out.println(countPostres + ". ");
                venta.mostrarInformacion();
                System.out.println();
            }
        }

        if (!postresEncontrados) {
            System.out.println("No hay postres para eliminar.");
            return;
        }

        System.out.println("Ingrese el número del postre que desea eliminar:");
        int numPostreEliminar;
        try {
            numPostreEliminar = scanner.nextInt();
            if (numPostreEliminar >= 1 && numPostreEliminar <= countPostres) {
                int indexPostreEliminar = -1;
                int postreCounter = 0;

                for (int i = 0; i < ventas.size(); i++) {
                    Venta venta = ventas.get(i);
                    if (venta instanceof Postres) {
                        postreCounter++;
                        if (postreCounter == numPostreEliminar) {
                            indexPostreEliminar = i;
                            break;
                        }
                    }
                }

                if (indexPostreEliminar != -1) {
                    ventas.remove(indexPostreEliminar);
                    System.out.println("Postre eliminado correctamente.");
                } else {
                    System.out.println("El número ingresado no corresponde a un postre.");
                }
            } else {
                System.out.println("Número de postre inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            scanner.nextLine();
        }
    }
    //////////////////////////// FIN ELIMINAR POSTRE////////////////////////////

    //////////////////////////// MOSTRAR INFORMACION////////////////////////////
    public static void mostrarInformacion(ArrayList<Venta> ventas) {
        System.out.println("----- INFORMACIÓN INGRESADA -----");

        for (Venta venta : ventas) {
            System.out.println("----------------------------------");
            venta.mostrarInformacion();
            System.out.println();
        }
    }
    //////////////////////////// FIN MOSTRAR INFORMACION////////////////////////////

    public static void venderProductos(ArrayList<Venta> ventas, Scanner scanner) {
        System.out.println("----- VENDER PRODUCTOS -----");
        int opcionVenta = 0;
        while (opcionVenta != 5) {
            System.out.println("Seleccione el tipo de producto que desea vender:");
            System.out.println("1. Producto Individual");
            System.out.println("2. Combo");
            System.out.println("3. Bebida");
            System.out.println("4. Postre");
            System.out.println("5. Regresar al menú principal");
            try {
                opcionVenta = scanner.nextInt();
                switch (opcionVenta) {
                    case 1:
                        venderProductoIndividual(ventas, scanner);
                        break;
                    case 2:
                        venderCombo(ventas, scanner);
                        break;
                    case 3:
                        venderBebida(ventas, scanner);
                        break;
                    case 4:
                        venderPostre(ventas, scanner);
                        break;
                    case 5:
                        System.out.println("Regresando al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine();
            }
        }
    }

    public static void venderProductoIndividual(ArrayList<Venta> ventas, Scanner scanner) {
        System.out.println("----- VENDER PRODUCTO INDIVIDUAL -----");
        if (ventas.isEmpty()) {
            System.out.println("No hay productos individuales disponibles para vender.");
            return;
        }

        boolean productosEncontrados = false;
        int countProductos = 0;
        for (Venta venta : ventas) {
            if (venta instanceof ProductoIndividual) {
                productosEncontrados = true;
                countProductos++;
                System.out.println(countProductos + ". ");
                venta.mostrarInformacion();
                System.out.println();
            }
        }

        if (!productosEncontrados) {
            System.out.println("No hay productos individuales disponibles para vender.");
            return;
        }

        System.out.println("Ingrese el número del producto individual que desea vender:");
        int numProductoVender;
        try {
            numProductoVender = scanner.nextInt();
            if (numProductoVender >= 1 && numProductoVender <= countProductos) {
                int productoCounter = 0;

                for (Venta venta : ventas) {
                    if (venta instanceof ProductoIndividual) {
                        productoCounter++;
                        if (productoCounter == numProductoVender) {
                            System.out.println("Producto vendido correctamente.");
                            ventas.remove(venta);
                            return;
                        }
                    }
                }
            } else {
                System.out.println("Número de producto individual inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            scanner.nextLine();
        }
    }

    public static void venderCombo(ArrayList<Venta> ventas, Scanner scanner) {
        System.out.println("----- VENDER COMBO -----");
        if (ventas.isEmpty()) {
            System.out.println("No hay combos disponibles para vender.");
            return;
        }

        boolean combosEncontrados = false;
        int countCombos = 0;
        for (Venta venta : ventas) {
            if (venta instanceof Combos) {
                combosEncontrados = true;
                countCombos++;
                System.out.println(countCombos + ". ");
                venta.mostrarInformacion();
                System.out.println();
            }
        }
        if (!combosEncontrados) {
            System.out.println("No hay combos disponibles para vender.");
            return;
        }
        System.out.println("Ingrese el número del combo que desea vender:");
        int numComboVender;
        try {
            numComboVender = scanner.nextInt();
            if (numComboVender >= 1 && numComboVender <= countCombos) {
                int comboCounter = 0;

                for (Venta venta : ventas) {
                    if (venta instanceof Combos) {
                        comboCounter++;
                        if (comboCounter == numComboVender) {
                            System.out.println("Combo vendido correctamente.");
                            ventas.remove(venta);
                            return;
                        }
                    }
                }
            } else {
                System.out.println("Número de combo inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            scanner.nextLine();
        }
    }

    public static void venderBebida(ArrayList<Venta> ventas, Scanner scanner) {
        System.out.println("----- VENDER BEBIDA -----");
        if (ventas.isEmpty()) {
            System.out.println("No hay bebidas disponibles para vender.");
            return;
        }

        boolean bebidasEncontradas = false;
        int countBebidas = 0;
        for (Venta venta : ventas) {
            if (venta instanceof Bebidas) {
                bebidasEncontradas = true;
                countBebidas++;
                System.out.println(countBebidas + ". ");
                venta.mostrarInformacion();
                System.out.println();
            }
        }

        if (!bebidasEncontradas) {
            System.out.println("No hay bebidas disponibles para vender.");
            return;
        }

        System.out.println("Ingrese el número de la bebida que desea vender:");
        int numBebidaVender;
        try {
            numBebidaVender = scanner.nextInt();
            if (numBebidaVender >= 1 && numBebidaVender <= countBebidas) {
                int bebidaCounter = 0;

                for (Venta venta : ventas) {
                    if (venta instanceof Bebidas) {
                        bebidaCounter++;
                        if (bebidaCounter == numBebidaVender) {
                            System.out.println("Bebida vendida correctamente.");
                            ventas.remove(venta);
                            return;
                        }
                    }
                }
            } else {
                System.out.println("Número de bebida inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            scanner.nextLine();
        }
    }

    public static void venderPostre(ArrayList<Venta> ventas, Scanner scanner) {
        System.out.println("----- VENDER POSTRE -----");
        if (ventas.isEmpty()) {
            System.out.println("No hay postres disponibles para vender.");
            return;
        }

        boolean postresEncontrados = false;
        int countPostres = 0;
        for (Venta venta : ventas) {
            if (venta instanceof Postres) {
                postresEncontrados = true;
                countPostres++;
                System.out.println(countPostres + ". ");
                venta.mostrarInformacion();
                System.out.println();
            }
        }

        if (!postresEncontrados) {
            System.out.println("No hay postres disponibles para vender.");
            return;
        }

        System.out.println("Ingrese el número del postre que desea vender:");
        int numPostreVender;
        try {
            numPostreVender = scanner.nextInt();
            if (numPostreVender >= 1 && numPostreVender <= countPostres) {
                int postreCounter = 0;

                for (Venta venta : ventas) {
                    if (venta instanceof Postres) {
                        postreCounter++;
                        if (postreCounter == numPostreVender) {
                            System.out.println("Postre vendido correctamente.");
                            ventas.remove(venta);
                            return;
                        }
                    }
                }
            } else {
                System.out.println("Número de postre inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero.");
            scanner.nextLine();
        }
    }

}
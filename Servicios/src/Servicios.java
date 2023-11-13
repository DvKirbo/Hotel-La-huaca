import java.util.ArrayList;
import java.util.Scanner;

public class Servicios {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Servicio> servicios = new ArrayList<>();

        int opcion;
        do {
            System.out.println("----- Menu de Servicios -----");
            System.out.println("1. Crear Servicio");
            System.out.println("2. Borrar Servicio");
            System.out.println("3. Actualizar Servicio");
            System.out.println("4. Lista de Servicios");
            System.out.print("Ingrese la opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    crearServicio(scanner, servicios);
                    break;
                case 2:
                    borrarServicio(scanner, servicios);
                    break;
                case 3:
                    actualizarServicio(scanner, servicios);
                    break;
                case 4:
                    mostrarServicios(servicios);
                    break;
                default:
                    System.out.println("Opcion incorrecta, por favor ingresela de nuevo");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void crearServicio(Scanner scanner, ArrayList<Servicio> servicios) {
        System.out.print("Ingrese el nombre del servicio: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el precio del servicio: ");
        double precio = scanner.nextDouble();

        Servicio nuevoServicio = new Servicio(nombre, precio);
        servicios.add(nuevoServicio);

        System.out.println("Servicio creado con éxito.");
    }
    
    private static void borrarServicio(Scanner scanner, ArrayList<Servicio> servicios) {
    System.out.print("Ingrese el nombre del servicio que desea borrar: ");
    String nombreBorrar = scanner.nextLine();

    for (int i = 0; i < servicios.size(); i++) {
        Servicio servicio = servicios.get(i);
        if (servicio.nombre.equals(nombreBorrar)) {
            servicios.remove(i);
            System.out.println("Servicio Eliminado");
            return;
        }
    }
    System.out.println("El servicio no existe por favor intente otra vez");
}
  
    
    private static void actualizarServicio(Scanner scanner, ArrayList<Servicio> servicios) {
        System.out.print("Ingrese el nombre del servicio que desea actualizar: ");
        String nombreActualizar = scanner.nextLine();

        for (Servicio servicio : servicios) {
            if (servicio.nombre.equals(nombreActualizar)) {
                System.out.print("Ingrese el nuevo nombre del servicio: ");
                String nuevoNombre = scanner.nextLine();

                System.out.print("Ingrese el nuevo precio del servicio: ");
                double nuevoPrecio = scanner.nextDouble();

                servicio.nombre = nuevoNombre;
                servicio.precio = nuevoPrecio;

                System.out.println("Servicio actualizado con éxito.");
                return;
            }
        }
        System.out.println("El servicio no existe por favor intente otra vez");
    }

    private static void mostrarServicios(ArrayList<Servicio> servicios) {
        System.out.println("----- Lista de Servicios -----");
        for (Servicio servicio : servicios) {
            System.out.println("Nombre: " + servicio.nombre );
            System.out.println("Precio: " + servicio.precio);
        }
        System.out.println("***********************************");
    }
}    

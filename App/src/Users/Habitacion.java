package Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Habitacion {
    private int id_Habitacion;
    private static String tipo_habitacion;
    private int precio_dia;
    private static String estado_habitacion;

    public Habitacion(int id_Habitacion, String tipo_habitacion, int precio_dia, String estado_habitacion) {
        this.id_Habitacion = id_Habitacion;
        this.tipo_habitacion = tipo_habitacion;
        this.precio_dia = precio_dia;
        this.estado_habitacion = estado_habitacion;
    }

    private static List<Habitacion> habitaciones = new ArrayList<>();

    public static void registrar_habitacion(Scanner scanner) {
        System.out.println("Ingrese el ID de la habitación:");
        int id_Habitacion = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        System.out.println("Ingrese el tipo de habitación:");
        System.out.println(" 1: Sencilla \n 2: Doble \n 3: Matrimonial");
        int tipo = scanner.nextInt();
        switch (tipo) {
            case 1:
                tipo_habitacion = "Sencilla";
                break;
            case 2:
                tipo_habitacion = "Doble";
                break;
            case 3:
                tipo_habitacion = "Matrimonial";
                break;
        }
       
        System.out.println("Ingrese el precio por día:");
        int precio_dia = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        System.out.println("Ingrese el estado de la habitación:");
        System.out.println("Ingrese el tipo de habitación:");
        System.out.println(" 1: Ocupada \n 2: Disponible \n 3: Falta Limpieza");
        int tipo1 = scanner.nextInt();
        switch (tipo1) {
            case 1:
                estado_habitacion = "Ocupada";
                break;
            case 2:
                estado_habitacion = "Disponible";
                break;
        }

        Habitacion nuevaHabitacion = new Habitacion(id_Habitacion, tipo_habitacion, precio_dia, estado_habitacion);
        habitaciones.add(nuevaHabitacion);

        
    }


    private int getId_Habitacion() {
        return id_Habitacion;
    }

    public void setEstadoHabitacion(String nuevoEstado) {
        Habitacion.estado_habitacion = nuevoEstado;
    }
    
    public static void cambiarEstadoHabitacion(Scanner scanner) {
        System.out.println("Ingrese el ID de la habitación:");
        int id = scanner.nextInt();
        scanner.nextLine();  
    
        System.out.println("Ingrese el nuevo estado de la habitación:");
        String nuevoEstado = scanner.nextLine();
    
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId_Habitacion() == id) {
                habitacion.setEstadoHabitacion(nuevoEstado);
                break;
            }
        }
    }
    

    public static void eliminarHabitacion(int id) {
        habitaciones.removeIf(habitacion -> habitacion.getId_Habitacion() == id);
    }

        

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Habitacion.registrar_habitacion(scanner);
        Habitacion.cambiarEstadoHabitacion(scanner);
        System.out.println("Ingrese el ID de la habitación a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Habitacion.eliminarHabitacion(id);
    }
}

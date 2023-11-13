package Users;

import java.sql.*;
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

    private static Connection establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://dpg-cl3f35iuuipc738c8ejg-a.oregon-postgres.render.com:5432/hoteldb_5c25";
            String usuario = "root";
            String contraseña = "aZUmvnskO4TeHmb2sGdSoDOQjqtQUVkN";
            return DriverManager.getConnection(url, usuario, contraseña);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void cargarHabitacionesDesdeDB() {
        try (Connection conexion = establecerConexion();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM habitaciones")) {
    
            habitaciones.clear();  
    
            while (resultSet.next()) {
                int id_Habitacion = resultSet.getInt("id_Habitacion");
                String tipo_habitacion = resultSet.getString("tipo_habitacion");
                int precio_dia = resultSet.getInt("precio_dia");
                String estado_habitacion = resultSet.getString("estado_habitacion");
    
                Habitacion habitacion = new Habitacion(id_Habitacion, tipo_habitacion, precio_dia, estado_habitacion);
                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void establecer_tipo_habitacion(Scanner scanner){
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
                default:
                    establecer_tipo_habitacion(scanner);
                break;
            }
    }

    public static void establecer_estado_habitacion(Scanner scanner){
        System.out.println("Ingrese el estado de la habitación:");
        System.out.println(" 1: Ocupada \n 2: Disponible");
        int tipo1 = scanner.nextInt();
        switch (tipo1) {
            case 1:
                estado_habitacion = "Ocupada";
                break;
            case 2:
                estado_habitacion = "Disponible";
                break;
            default:
                establecer_estado_habitacion(scanner);
            break;
        }
    }

    public static void registrar_habitacion(Scanner scanner) {
        System.out.println("Ingrese el ID de la habitación:");
        int id_Habitacion = scanner.nextInt();
        scanner.nextLine(); 


        establecer_tipo_habitacion(scanner);
       
        System.out.println("Ingrese el precio por día:");
        int precio_dia = scanner.nextInt();
        scanner.nextLine();  

        establecer_estado_habitacion(scanner);

        Habitacion nuevaHabitacion = new Habitacion(id_Habitacion, tipo_habitacion, precio_dia, estado_habitacion);
        habitaciones.add(nuevaHabitacion);

        
    }



    private int getId_Habitacion() {
        return id_Habitacion;
    }

    public void setEstadoHabitacion(String nuevoEstado) {
        Habitacion.estado_habitacion = nuevoEstado;
    }
    
    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public int getPrecio_dia() {
        return precio_dia;
    }

    public String getEstado_habitacion() {
        return estado_habitacion;
    }
    
    public static void cambiarEstadoHabitacion(Scanner scanner) {
        System.out.println("Ingrese el ID de la habitación a cambiar:");
        int id = scanner.nextInt();
        scanner.nextLine();
    
        System.out.println("Ingrese el nuevo estado de la habitación:");
        System.out.println(" 1: Ocupada \n 2: Disponible");
        String nuevoEstado = "";
        int tipo1 = scanner.nextInt();
        switch (tipo1) {
            case 1:
                nuevoEstado = "Ocupada";
                break;
            case 2:
                nuevoEstado = "Disponible";
                break;
            default:
                cambiarEstadoHabitacion(scanner);
                break;
        }
    
        try (Connection conexion = establecerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(
                     "UPDATE habitaciones SET estado_habitacion = ? WHERE id_Habitacion = ?")) {
    
            preparedStatement.setString(1, nuevoEstado);
            preparedStatement.setInt(2, id);
    
            int filasAfectadas = preparedStatement.executeUpdate();
    
            if (filasAfectadas > 0) {
                System.out.println("Estado de la habitación actualizado correctamente.");
            } else {
                System.out.println("No se encontró una habitación con el ID proporcionado.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void listarHabitaciones(Scanner scanner) {
        try (Connection conexion = establecerConexion();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM habitaciones")) {
    
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay habitaciones registradas.");
                menu(scanner);
            } else {
                System.out.println("Lista de habitaciones:");
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id_Habitacion"));
                    System.out.println("Tipo: " + resultSet.getString("tipo_habitacion"));
                    System.out.println("Precio por día: " + resultSet.getInt("precio_dia"));
                    System.out.println("Estado: " + resultSet.getString("estado_habitacion"));
                    System.out.println("------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void menu(Scanner scanner){
        System.out.println("1. Modificar Estado");
        System.out.println("2. Listar Habitaciones");
        int opcion = scanner.nextInt();
    
        switch (opcion) {
            case 1:
                cambiarEstadoHabitacion(scanner);
                break;
            case 2:
                listarHabitaciones(scanner);
                break;
            default:
                System.out.println("Número inválido, ingresar nuevamente");
                menu(scanner);
                break;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        cargarHabitacionesDesdeDB();
        menu(scanner);
        

    }
}

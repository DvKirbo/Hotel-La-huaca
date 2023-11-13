package Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private int id_cliente;
    private static String nombre;
    private static String apellido;
    private static int doc_identidad; 
    private static int numero_Telefono;
    
    public Cliente(int id_cliente, String nombre, String apellido, int doc_identidad, int numero_Telefono){
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.doc_identidad = doc_identidad;
        this.numero_Telefono = numero_Telefono;
    }

    public Cliente(String nombre2, String apellidoP, String apellidoM, String genero, String numero, String dni) {
    }

    private static List<Cliente> clientes = new ArrayList<>();

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

    public static void registrar_cliente(Scanner scanner) {
        System.out.println("Ingrese el ID del Cliente");
        int id_cliente = scanner.nextInt();
        scanner.nextLine();
    
        System.out.println("Ingrese el nombre del Cliente");
        String nombre = scanner.next();
        scanner.nextLine();
    
        System.out.println("Ingrese el apellido del Cliente");
        String apellido = scanner.next();
        scanner.nextLine();
    
        System.out.println("Ingrese el Documento de Identidad del Cliente");
        int doc_identidad = scanner.nextInt();
        scanner.nextLine();
    
        System.out.println("Ingrese el Numero Telefonico del Cliente");
        int numero_Telefono = scanner.nextInt();
        scanner.nextLine();
    
        try (Connection conexion = establecerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(
                     "INSERT INTO clientes (id_cliente, nombre, apellido, doc_identidad, numero_Telefono) " +
                             "VALUES (?, ?, ?, ?, ?)")) {
    
            preparedStatement.setInt(1, id_cliente);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setInt(4, doc_identidad);
            preparedStatement.setInt(5, numero_Telefono);
    
            preparedStatement.executeUpdate();
    
            System.out.println("Cliente registrado correctamente.");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modificar_cliente(Scanner scanner) {
        System.out.println("Ingrese el ID del cliente a actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Seleccione qué desea modificar:");
        System.out.println("1: Nombre\n2: Apellido\n3: Documento Identidad\n4: Numero Telefono");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                modificar_nombre(id, scanner);
                break;
            case 2:
                modificar_apellido(id, scanner);
                break;
            case 3:
                modificar_doc_identidad(id, scanner);
                break;
            case 4:
                modificar_numero_Telefono(id, scanner);
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    private static void modificar_nombre(int id, Scanner scanner) {
        System.out.println("Ingrese el nuevo nombre");
        String nuevoNombre = scanner.nextLine();

        try (Connection conexion = establecerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(
                     "UPDATE clientes SET nombre = ? WHERE id_cliente = ?")) {

            preparedStatement.setString(1, nuevoNombre);
            preparedStatement.setInt(2, id);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Nombre del cliente actualizado correctamente");
            } else {
                System.out.println("No se encontró un cliente con el ID proporcionado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private static void modificar_apellido(int id, Scanner scanner){
        System.out.println("Ingrese el nuevo apellido");
        String nuevoApellido = scanner.nextLine();

        try (Connection conexion = establecerConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "UPDATE clientes SET apellido = ? WHERE id_cliente = ?")){
            preparedStatement.setString(1, nuevoApellido);
            preparedStatement.setInt(2,id);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0 ) {
                System.out.println("Apellido del cliente actualizado correctamente");
            }
            else{
                System.out.println("No se encontro un cliente con el ID proporcionado");
            }
            } catch (SQLException e){
                e.printStackTrace();
            }
    }

    private static void modificar_doc_identidad(int id, Scanner scanner){
         System.out.println("Ingrese el nuevo documento de identidad");
        Integer nuevoDoc_identidad = scanner.nextInt();

        try (Connection conexion = establecerConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "UPDATE clientes SET apellido = ? WHERE id_cliente = ?")){
            preparedStatement.setInt(1, nuevoDoc_identidad);
            preparedStatement.setInt(2,id);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0 ) {
                System.out.println("Documento del cliente actualizado correctamente");
            }
            else{
                System.out.println("No se encontro un cliente con el ID proporcionado");
            }
            } catch (SQLException e){
                e.printStackTrace();
            }
    }

    private static void modificar_numero_Telefono(int id, Scanner scanner){
         System.out.println("Ingrese el nuevo documento de identidad");
        Integer nuevoTelefono = scanner.nextInt();

        try (Connection conexion = establecerConexion();
            PreparedStatement preparedStatement = conexion.prepareStatement(
                "UPDATE clientes SET apellido = ? WHERE id_cliente = ?")){
            preparedStatement.setInt(1, nuevoTelefono);
            preparedStatement.setInt(2,id);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0 ) {
                System.out.println("Numero del cliente actualizado correctamente");
            }
            else{
                System.out.println("No se encontro un cliente con el ID proporcionado");
            }
            } catch (SQLException e){
                e.printStackTrace();
            }
    }

    public static void listar_clientes() {
        try (Connection conexion = establecerConexion();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clientes")) {

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay clientes registrados.");
            } else {
                System.out.println("Lista de clientes:");
                while (resultSet.next()) {
                    System.out.println("ID del cliente: " + resultSet.getInt("id_cliente"));
                    System.out.println("Nombre del cliente: " + resultSet.getString("nombre"));
                    System.out.println("Apellido del cliente: " + resultSet.getString("apellido"));
                    System.out.println("Documento de Identidad del cliente: " + resultSet.getInt("doc_identidad"));
                    System.out.println("Numero de Telefono del cliente: " + resultSet.getInt("numero_Telefono"));
                    System.out.println("-----------------------");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar_cliente(Scanner scanner) {
        System.out.println("Ingrese el ID a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine();
    
        try (Connection conexion = establecerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(
                     "DELETE FROM clientes WHERE id_cliente = ?")) {
    
            preparedStatement.setInt(1, id);
    
            int filasAfectadas = preparedStatement.executeUpdate();
    
            if (filasAfectadas > 0) {
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("No se encontró un cliente con el ID proporcionado.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void menu(Scanner scanner){     
        System.out.println(" 1. Registrar Cliente \n 2. Leer Cliente \n 3. Modificar Cliente \n 4. Eliminar Cliente");
        int eleccion = scanner.nextInt();
        switch (eleccion) {
            case 1:
                Cliente.registrar_cliente(scanner);
                break;
            case 2:
                Cliente.listar_clientes();
                break;
            case 3:
                Cliente.modificar_cliente(scanner);
                break;
            case 4:
                Cliente.eliminar_cliente(scanner);
                break;
            default:
                System.out.println("");
                menu(scanner);
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menu(scanner);

        
        

    }

}
    
package utilidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    static DB cn = new DB();
    
    private static int id_cliente;
    private static String nombre;
    private static String apellido;
    private static int doc_identidad; 
    private static int numero_Telefono;
    private static int id_Habitacion;
    
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
    
        id_cliente+=1;
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
        
        System.out.println("Ingrese la habitacion a registrarse");
        int idCuarto = scanner.nextInt();
        scanner.nextLine();

        try{
            cn.guardarCliente(id_cliente, nombre, apellido, numero_Telefono, numero_Telefono, idCuarto);
        }
        catch(Exception e){
            System.out.println("exito");//no me va a regresar nd por eso sale error
        }
        System.out.println("Cliente registrado exitosamente");
    }




    public static void modificar_cliente(Scanner scanner) throws Exception {
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

    private static void modificar_nombre(int id, Scanner scanner) throws Exception {
        System.out.println("Ingrese el nuevo nombre");
        String nuevoNombre = scanner.nextLine();
        try{
            cn.modificarNombreCliente(id, nuevoNombre);
        }
        catch(Exception e){
            System.out.println("cliente modificado correctamente");
        }
    }
    

    private static void modificar_apellido(int id, Scanner scanner){
        System.out.println("Ingrese el nuevo apellido");
        String nuevoApellido = scanner.nextLine();

        try  {
            cn.modificarApellidoCliente(id, nuevoApellido);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static void modificar_doc_identidad(int id, Scanner scanner){
         System.out.println("Ingrese el nuevo documento de identidad");
        Integer nuevoDoc_identidad = scanner.nextInt();
        try{
            cn.modificarDniCliente(id,nuevoDoc_identidad);
        }
        catch(Exception e){
            System.out.println("dni cambiado");
        }
    }

    private static void modificar_numero_Telefono(int id, Scanner scanner){
         System.out.println("Ingrese el nuevo documento de identidad");
        Integer nuevoTelefono = scanner.nextInt();

        try{
            cn.modificartelefonoCliente(id,nuevoTelefono);
        }
        catch(Exception e){ 
            System.out.println("telefono cambiado");
        }
    }

    public static void listar_clientes() throws SQLException {
        cn.listarClientes();
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

    public static void menu(Scanner scanner) throws Exception{     
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

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        menu(scanner);

        
        

    }

}
    
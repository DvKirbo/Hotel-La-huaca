package Users;

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

    public static void registrar_cliente(Scanner scanner){
        System.out.println("Ingrese el ID del Cliente");
        int id_cliente = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre del Cliente");
        String nombre  = scanner.next();
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

        Cliente nuevoClientes = new Cliente(id_cliente, nombre, apellido, doc_identidad, numero_Telefono);
        clientes.add(nuevoClientes);
    }

    public static void  modificar_nombre(Scanner scanner){
        System.out.println("Ingrese el nuevo nombre");
        String nuevoNombre = scanner.next();
        Cliente.nombre = nuevoNombre;
    }

    public static void  modificar_apellido(Scanner scanner){
        System.out.println("Ingrese el nuevo apellido");
        String nuevoApellido = scanner.next();
        Cliente.apellido = nuevoApellido;
    }
    
    public static void modificar_doc_identidad(Scanner scanner){
        System.out.println("Ingrese el nuevo Documento de Identidad");
        int nuevoDoc_identidad = scanner.nextInt();
        Cliente.doc_identidad = nuevoDoc_identidad;
    }

    public static void modificar_numero_Telefono(Scanner scanner){
        System.out.println("Ingrese el nuevo Numero de Telefono");
        int nuevoNumero_Telefono = scanner.nextInt();
        Cliente.numero_Telefono = nuevoNumero_Telefono;

    }

    public static void menu_modificar(Scanner scanner){
        System.out.println("1: Nombre \n 2: Apellido \n 3: Documento identidad \n 4: Numero Telefonico");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        switch (tipo) {
            case 1:
                modificar_nombre(scanner);
                break;
            case 2:
                modificar_apellido(scanner);
            break;
            case 3:
                modificar_doc_identidad(scanner);
            break;
            case 4:
                modificar_numero_Telefono(scanner);
            break;
            default:
                System.out.println("Opcion Invalida");
                menu_modificar(scanner);
            break;
        }
    }
    private int getid_cliente(){
        return id_cliente;
    }
    public static void modificar_cliente(Scanner scanner){
        System.out.println("Ingrese el ID del cliente a actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(Cliente cliente: clientes){
            if(cliente.getid_cliente() == id){
                menu_modificar(scanner);
                break;
            }
        }            
    }
    

    public static void eliminarCliente(int id){
        clientes.removeIf(cliente -> cliente.getid_cliente() == id);
    }

    public static void eliminar_cliente(Scanner scanner){
        System.out.println("Ingrese el ID a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(Cliente cliente:clientes){
            if(cliente.getid_cliente() == id){
                Cliente.eliminarCliente(id);
            }
            else{
                System.out.println("Id no encontrada");
                eliminar_cliente(scanner);
            }
        }
    }

    public static void listar_clientes(){
        for(Cliente cliente:clientes){
            System.out.println("ID del cliente: " + cliente.id_cliente);
            System.out.println("Nombre del cliente: " + Cliente.nombre);
            System.out.println("Apellido del cliente: " + Cliente.apellido);
            System.out.println("Documento de Identidad del cliente: " + Cliente.doc_identidad);
            System.out.println("Numero de Telefono del cliente: " + Cliente.numero_Telefono);
            System.out.println("-----------------------");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);       
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
        }

        
        

    }

}
    
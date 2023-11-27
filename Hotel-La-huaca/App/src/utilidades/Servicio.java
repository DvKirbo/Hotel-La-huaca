package utilidades;

import java.sql.SQLException;
import java.util.Scanner;

public class Servicio {
    private int idServicio;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private int cuarto;
    DB con = new DB();
              

    public Servicio(String nombre, String descripcion, double precio, int cantidad, int cuarto, DB con) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.cuarto = cuarto;
        this.con = con;
    }

    public void crearServicio(Scanner scanner) throws SQLException {
        System.out.print("Ingrese el nombre del servicio: ");
        nombre = scanner.nextLine();
        System.out.print("Ingrese la descripcion del servicio del servicio: ");
        descripcion = scanner.nextLine();
        System.out.println("Ingrese el precio del servicio: ");
        double precio = scanner.nextDouble();
        System.out.println("Ingrese la cantidad del servicio: ");
        cantidad= scanner.nextInt();
        System.out.println("Ingrese la habitacion donde va el servicio: ");
        cuarto = scanner.nextInt();
        System.out.println("Ingrese el id del servicio: ");
        idServicio = scanner.nextInt();
        DB.guardarServicio(nombre, descripcion, precio, cantidad, cuarto, idServicio);

        System.out.println("Servicio creado con éxito.");
    }
    
    public static void borrarServicio(Scanner scanner) {
    System.out.print("Ingrese el nombre del servicio que desea borrar: ");
    String nombreBorrar = scanner.nextLine();

    System.out.println("El servicio no existe por favor intente otra vez");
    }
  
    public static void actualizarServicio(Scanner scanner) {
        System.out.print("Ingrese el nombre del servicio que desea actualizar: ");
        String nombreActualizar = scanner.nextLine();

        System.out.println("El servicio no existe por favor intente otra vez");
    }

    public static void mostrarServicios() {
        System.out.println("----- Lista de Servicios -----");
        
        System.out.println("*************");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCuarto() {
        return cuarto;
    }

    public void setCuarto(int cuarto) {
        this.cuarto = cuarto;
    }

    public DB getCon() {
        return con;
    }

    public void setCon(DB con) {
        this.con = con;
    }    
}

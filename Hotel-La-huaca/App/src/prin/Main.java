package prin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import utilidades.*;


public class Main {
    static Cliente cln;
    static String errorOpc="";
    static int opc;
    static Scanner scaner = new Scanner(System.in);
    static char continuar;
        static String esd ="\u2510";
        static String esi ="\u250C";
        static String eid ="\u2518";
        static String eii ="\u2514";
        static String lado = "│";
        static String base = "\u2500";
        static DB conDb = new DB();
    public static void gotoXY(int x, int y){
        System.out.printf("%c[%d;%df",0x1B,y,x);
    }
    
    public static void borrar(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static int getFilasConsola() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "tput lines 2>/dev/tty"});
            process.waitFor();
            return Integer.parseInt(new BufferedReader(new InputStreamReader(process.getInputStream())).readLine().trim());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getColumnasConsola() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "tput cols 2>/dev/tty"});
            process.waitFor();
            return Integer.parseInt(new BufferedReader(new InputStreamReader(process.getInputStream())).readLine().trim());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static void menuPrincipal(){
        borrar();
        //Desactivamos el cursor
        System.out.print("\33[?25l\t");
        String opciones [] = {"Registro cliente", "Registro empleado", "Estado habitaciones"};
        //System.out.println("\033[48;5;24m");  
        //System.out.println("");
        int columnas = getColumnasConsola();
        int filas = getFilasConsola();
        System.out.println(filas + " "+ columnas);
        gotoXY(columnas, filas);

        String f="\u2588";
        String title[][] = {
        {"1","1","2","0","0","1","1","2","0","0","1","1","1","1","1","1","1","2","0","0","1","1","1","1","1","1","1","1","2","0","0","1","1","1","1","1","1","1","2","0","0","1","1","2","0","0","0"},
        {"1","1","2","0","0","1","1","2","0","0","1","1","1","1","1","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","0","0","1","1","2","0","0","0"},
        {"1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","0","0","1","1","2","0","0","0"},
        {"1","1","1","1","1","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","1","1","1","1","1","1","1","2","0","0","1","1","2","0","0","0"},
        {"1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","0","0","1","1","2","0","0","0"},
        {"1","1","2","0","0","1","1","2","0","0","1","1","1","1","1","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","0","0","1","1","2","0","0","0"},
        {"1","1","2","0","0","1","1","2","0","0","1","1","1","1","1","1","1","2","0","0","0","0","0","1","1","2","0","0","0","0","0","1","1","1","1","1","1","1","2","0","0","1","1","1","1","1","1"}
        };//47

        String title2 [][] = {
            {"1","1","2","0","0","0","0","0","1","1","1","1","1","1","1","1","2"},
            {"1","1","2","0","0","0","0","0","1","1","0","0","0","0","1","1","2"},
            {"1","1","2","0","0","0","0","0","1","1","0","0","0","0","1","1","2"},
            {"1","1","2","0","0","0","0","0","1","1","1","1","1","1","1","1","2"},
            {"1","1","2","0","0","0","0","0","1","1","2","0","0","0","1","1","2"},
            {"1","1","2","0","0","0","0","0","1","1","2","0","0","0","1","1","2"},
            {"1","1","1","1","1","1","0","0","1","1","2","0","0","0","1","1","2"},
        };//+3

        String title3 [][] = {
            {"1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","1","1","1","1","1","1","2","0","0","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","2"},
            {"1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","0","0","0","0","1","1","2","0","0","1","1","2","0","0","0","0","0","1","1","0","0","0","0","1","1","2"},
            {"1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","0","0","0","0","1","1","2","0","0","1","1","2","0","0","0","0","0","1","1","0","0","0","0","1","1","2"},
            {"1","1","1","1","1","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","1","1","1","1","1","1","2","0","0","1","1","2","0","0","0","0","0","1","1","1","1","1","1","1","1","2"},
            {"1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","0","1","1","2","0","0","1","1","2","0","0","0","0","0","1","1","2","0","0","0","1","1","2"},
            {"1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","1","1","2","0","0","0","1","1","2","0","0","1","1","2","0","0","0","0","0","1","1","2","0","0","0","1","1","2"},
            {"1","1","2","0","0","1","1","2","0","0","1","1","1","1","1","1","1","2","0","0","1","1","2","0","0","0","1","1","2","0","0","1","1","1","1","1","1","0","0","1","1","2","0","0","0","1","1","2"},
        };//+16

        gotoXY(10, 10);
        for(int i=0;i<7;i++){
            for(int j=0;j<47;j++){
                gotoXY(20+j,5+i);
                if(title[i][j]=="0"){
                    System.out.print("\033[38;5;24m"+ " ");
                }else if (title[i][j]=="1"){
                    System.out.print("\033[38;5;50m"+f);
                }else if (title[i][j]=="2"){
                    System.out.print("\033[38;5;24m"+f);
                }
            }
        }


        //gotoXY(10+40, 10);
        for(int i=0;i<7;i++){
            for(int j=0;j<17;j++){
                gotoXY(74+j,5+i);
                if(title2[i][j]=="0"){
                    System.out.print("\033[38;5;24m"+ " ");
                }else if (title2[i][j]=="1"){
                    System.out.print("\033[38;5;50m"+f);
                }else if (title2[i][j]=="2"){
                    System.out.print("\033[38;5;24m"+f);
                }
            }
        }

        for(int i=0;i<7;i++){
            for(int j=0;j<48;j++){
                gotoXY(98+j,5+i);
                if(title3[i][j]=="0"){
                    System.out.print("\033[38;5;24m"+ " ");
                }else if (title3[i][j]=="1"){
                    System.out.print("\033[38;5;50m"+f);
                }else if (title3[i][j]=="2"){
                    System.out.print("\033[38;5;24m"+f);
                }
            }
        }

        opc=0;//
        System.out.print("\033[38;5;83m");
        gotoXY(65, 18);
        System.out.print(esi);
        for(int i=0;i<31;i++)System.out.print("\u2500");
        System.out.print(esd);
        gotoXY(65, 20);
        System.out.print("«\t1)Reservaciones\t»");
        gotoXY(65, 22);
        System.out.print("«\t2)Habitaciones\t»");
        gotoXY(65, 24);
        System.out.print("«\t3)Servicios\t»");
        gotoXY(65, 26);
        System.out.print("«\t4)Facturacion\t»");
        gotoXY(65, 28);
        System.out.print("«\t5)Trabajadores\t»");
        gotoXY(65, 30);
        System.out.print("«\t6)Salir\t»");
        
        gotoXY(65, 32);
        System.out.print(eii);
        for(int i=0;i<31;i++)System.out.print("\u2500");
        System.out.print(eid);
        gotoXY(65, 46);
        System.out.print("»");
        try {
            errorOpc = scaner.next();
            opc=Integer.parseInt(errorOpc);
        } catch (Exception e) {
            System.out.print("SELECCIONE UNA OPCION CORRECTA");
            scaner.nextLine();
            scaner.nextLine();
        }
    }





    public static void login() throws Exception{
            boolean continuar;
        do{
            borrar();
            System.out.println("usuario:");
            String user =  scaner.next();
            System.out.println("contraseña");
            String password =scaner.next();
            System.out.println();
            continuar = conDb.consultarUsuarios(user, password) ;
            if(continuar){
                borrar();
                System.out.println("Bienvenido .... ");
            }
            else{
                borrar();
                scaner.nextLine();
                System.out.println("contraseña o usuario incorrecto");
            }
            scaner.nextLine();
        }while(!continuar);
    }



    public static void guardarServicio() throws SQLException{
        System.out.println("Ingrese el nombre: ");
        String nombre = scaner.next();
        System.out.println("Ingrese la descripcion del servicio: ");
        String descripcion = scaner.next();
        System.out.println("Ingrece el precio del srevicio: ");
        double precio = scaner.nextDouble();
        System.out.println("Ingrese la cantidad del servicio: ");
        int cantidad= scaner.nextInt();
        System.out.println("Ingrese la habitacion donde va a ir el servicio: ");
        int cuarto = scaner.nextInt();
        try{

            conDb.guardarServicio(nombre, descripcion, precio, cantidad, cuarto);
        }catch(Exception e){

        }
    }

    public static void mostrarServicios(){
        try{
            conDb.listarServicios();
        }catch(Exception e){

        }
    }
    
    public static void modificarServicios() throws SQLException{
        System.out.println("Ingrese el id del servicio a modificar: ");
        int id = scaner.nextInt();
        System.out.println("Ingrese el nuevo nombre: ");
        String nombre = scaner.next();
        System.out.println("Ingrese la nueva descripcion: ");
        String descripcion = scaner.next();
        System.out.println("Ingrese el nuevo precio");
        double precio = scaner.nextDouble();
        System.out.println("Ingrese la nueva cantidad: ");
        int cantidad = scaner.nextInt();
        try{

            conDb.modificarServicios(id, nombre, descripcion, precio, cantidad);
        }catch(Exception e){

        }

    }


    public static void ElminarServicio() throws SQLException{
        System.out.println("Ingrese el id del servicio que desea elminar: ");
        int id = scaner.nextInt();
        try{

            conDb.eliminarServicios(id);
        }catch(Exception e){
            
        }
    }
    public static void menuServicios() throws SQLException{
        
        int opcion;
        do {
            borrar();
            System.out.println("----- Menu de Servicios -----");
            System.out.println("1. Crear Servicio");
            System.out.println("2. Borrar Servicio");
            System.out.println("3. Actualizar Servicio");
            System.out.println("4. Lista de Servicios");
            System.out.print("Ingrese la opcion: ");

            opcion = scaner.nextInt();
            scaner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                borrar();
                guardarServicio();
                scaner.next();
                scaner.nextLine();
                    //crearServicio(scaner, servicios);
                    break;
                case 2:
                borrar();
                mostrarServicios();
                scaner.next();
                scaner.nextLine();
                    //borrarServicio(scanner, servicios);
                    break;
                case 3:
                borrar();
                modificarServicios();
                scaner.next();
                scaner.nextLine();
                    //actualizarServicio(scanner, servicios);
                    break;
                case 4:
                borrar();
                scaner.next();
                scaner.nextLine();
                    //mostrarServicios(servicios);
                    break;
                default:
                    System.out.println("Opcion incorrecta, por favor ingresela de nuevo");
            }
        } while (opcion != 0);
    }

    public static void eliminarReserva() throws SQLException{
        System.out.println("Ingrese el id de la reserva a eliminar");
        int Id = scaner.nextInt();
        try{

            conDb.eliminar_reserva(Id);
        }catch(Exception e){

        }
    }
    public static void mostraReservas(){

    }

    public static void menuRegistrar() throws Exception{
        System.out.println("Ingrese los detalles de la reserva:");
        System.out.print("Id Cliente: ");
        int id = scaner.nextInt();
        scaner.nextLine(); 
        System.out.print("Nombre: ");
        String nombre = scaner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scaner.nextLine();
        System.out.print("Documento de Identidad: ");
        int dni = scaner.nextInt();
        System.out.print("Número de teléfono:  ");
        int telefono = scaner.nextInt();
        System.out.print("Habitación:  ");
        int cuarto = scaner.nextInt();

            try{

                conDb.guardarCliente(id, nombre, apellido, telefono, dni, cuarto);
                scaner.nextLine();
                conDb.cambiarhabitacion(id, nombre, apellido, dni, id, telefono, cuarto);
                conDb.guardar2(id, nombre, apellido, dni, id, telefono, cuarto, cuarto);
            }catch(Exception e){

            }
            scaner.nextLine();
            System.out.println();
            //borrar();
            //System.out.println("guardado");
        
        // Realizar la inserción de datos en la tabla de reservas
    }

    public static void menuModificar() throws SQLException{
        System.out.println("Ingrese el id de la reserva para modificar: ");
        int id = scaner.nextInt();
        System.out.println("Ingrese los datos a cambiar");
        System.out.println("nombre: ");
        String nombre = scaner.next();
        System.out.println("Apellido: ");
        String apellido=scaner.next();
        System.out.println("id Habitacion: ");
        int cuarto = scaner.nextInt();
        try{
            conDb.modificarcliente(id, nombre, apellido, cuarto);
        }catch(Exception e){

        }
    }

    public static void menuReserva() throws Exception{
        int opcion= 0;
        do{
            borrar();
            System.out.println("Seleccione una opcion");
            System.out.println("1)Registrar reserva: ");
            System.out.println("2)mostrar reserva: ");
            System.out.println("3)modificar datos reserva: ");
            System.out.println("4)Eliminar reservas: ");
            System.out.println("5)Salir  ");
            opcion =scaner.nextInt();
            switch (opcion) {
                case 1:
                    borrar();
                    menuRegistrar();
                    break;
                case 2:
                borrar();
                conDb.mostrarCliente();
                scaner.next();
                scaner.nextLine();
                case 3:
                    borrar();
                    menuModificar();
                    scaner.next();
                    scaner.nextLine();
                case 4:
                    borrar();
                    eliminarReserva();
                    scaner.next();
                    scaner.nextLine();
                default:
                    System.out.println("Seleccione una opcion correcta");
                    break;
            }
        }while(opcion!=5);
    }



    public static void main(String[] args) throws Exception  {
        login();
        do{
            menuPrincipal();
            switch (opc){
                case 1:
                        do{

                            borrar();
                            menuReserva();
                            gotoXY(80, 25);
                            System.out.print("Desea continuar? (S/N):   ");
                            continuar = scaner.next().charAt(0);
                            System.out.println(continuar);
                        }while(continuar!='n'||continuar!='n');
                        borrar();
                        
                        scaner.nextLine();
                        scaner.nextLine();
                    break;
                case 2:
                    borrar();
                    conDb.listarHabitacion();
                    //conDb.getHabitaciones();
                    scaner.nextLine();
                    scaner.nextLine();
                    break;
                case 3://servicios
                    borrar();
                    menuServicios();
                    //conDb.getHabitaciones();
                    scaner.nextLine();
                    scaner.nextLine();
                    break;
                case 4://FActuracion
                    borrar();
                    conDb.listarHabitacion();
                    //conDb.getHabitaciones();
                    scaner.nextLine();
                    scaner.nextLine();
                    break;
                    
                case 5://trabajadores
                    borrar();
                    conDb.listarHabitacion();
                    //conDb.getHabitaciones();
                    scaner.nextLine();
                    scaner.nextLine();
                    break;
                case 6:
                    borrar();
                    System.out.print("\033[38;5;222m");
                    //System.out.print("\033[48;5;24m");
                    gotoXY(10, 10);
                    System.out.println(" _______  ______   ___   _______  _______ ");
                    gotoXY(10, 11);
                    System.out.println("|   _   ||      | |   | |       ||       |");
                    gotoXY(10, 12);
                    System.out.println("|  |_|  ||  _    ||   | |   _   ||  _____|");
                    gotoXY(10, 13);
                    System.out.println("|       || | |   ||   | |  | |  || |_____ ");
                    gotoXY(10, 14);
                    System.out.println("|       || |_|   ||   | |  |_|  ||_____  |");
                    gotoXY(10, 15);
                    System.out.println("|   _   ||       ||   | |       | _____| |");
                    gotoXY(10, 15);
                    System.out.println("|__| |__||______| |___| |_______||_______|");
                    scaner.nextLine();
                    scaner.nextLine();
                    break;
                default:
                //borrar();
                //System.out.println("SELECCIONE UNA OPCION CORRECTA");
                //scaner.nextLine();
                break;
            }
        }while(opc!=6); 

    }

    

}

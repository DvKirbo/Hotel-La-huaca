package prueba;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import Users.Cliente;

public class Main {
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
        static ArrayList <Cliente> cliente = new ArrayList<Cliente>();


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
        System.out.print("«\t1)Registrar Cliente\t»");
        gotoXY(65, 22);
        System.out.print("«\t2)Mostrar Cliente\t»");
        gotoXY(65, 24);
        System.out.print("«\t3)Salir del Programa\t»");
        
        
        gotoXY(65, 26);
        System.out.print(eii);
        for(int i=0;i<31;i++)System.out.print("\u2500");
        System.out.print(eid);
        gotoXY(65, 30);
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

    public static void Registrar_cliente(){
        System.out.print("\033[38;5;83m");
        String nombre;
        String ApellidoP;
        String ApellidoM;
        String Genero;
        String numero;
        String Dni;
        int opcg=0;

        gotoXY(40,3);
            
            //marcos
        System.out.print(esi);
        for(int i=0;i<90;i++)System.out.print(base);
        System.out.print(esd);

        for(int i=0;i<3;i++){
            gotoXY(40,4+i);System.out.print(lado);
        }
        for(int i=0;i<3;i++){
            gotoXY(40+91,4+i);System.out.print(lado);
        }

        gotoXY(40,7);
        System.out.print(eii);
        for(int i=0;i<90;i++)System.out.print(base);
        System.out.print(eid);
        
        
        gotoXY(55+20,5);
        System.out.print("- REGISTRO DE CLIENTES -");
        
        //campos para llenar
        gotoXY(55,11);
        System.out.print("Nombres");
        gotoXY(55,13);
        System.out.print("Apellido paterno: ");
        gotoXY(55,15);
        System.out.print("Apellido materno");
        gotoXY(55,17);
        System.out.print("telefono");
        gotoXY(55,19);
        System.out.print("sexo (M | F)");
        gotoXY(55,21);
        System.out.print("Ingrese el dni");

        System.out.println("\033[38;5;163m");
        gotoXY(75,11);
        scaner.nextLine();
        System.out.print("»");
        nombre = scaner.nextLine();

        gotoXY(75,13);
        System.out.print("»");
        ApellidoP = scaner.next();

        gotoXY(75,15);
        System.out.print("»");
        ApellidoM = scaner.next();

        gotoXY(75,17);
        System.out.print("»");
        Genero = scaner.next();
        Genero =Genero.toUpperCase();


        gotoXY(75,19);
        System.out.print("»");
        numero = scaner.next();


        gotoXY(75,21);
        System.out.print("»");
        Dni = scaner.next();
        cliente.add(new Cliente(nombre, ApellidoP, ApellidoM, Genero, numero, Dni));
    }



    public static void mostrarClientes(){
        int cont=0;
        gotoXY(0, 0);
        System.out.print("Nombre");
        gotoXY(20,0);
        System.out.print("|Apellido P");
        gotoXY(35, 0);
        System.out.print("|Apellido M");
        gotoXY(50, 0);
        System.out.print("|Genero");
        gotoXY(65, 0);
        System.out.print("|Numero");
        gotoXY(80, 0);
        System.out.print("|DNI\t\t\t\t\tCLIENTES");

        gotoXY(1, 2);
        for(int i=0;i<160;i++)System.out.print("-");
        for(Cliente cli : cliente){

        gotoXY(1, 4+cont);
        System.out.print(cli.getNombre());
        gotoXY(21,4+cont);
        System.out.print(cli.getApellidoP());
        gotoXY(36, 4+cont);
        System.out.print(cli.getApellidoM());
        gotoXY(51, 4+cont);
        System.out.print(cli.getGenero());
        gotoXY(66, 4+cont);
        System.out.print(cli.getNumero());
        gotoXY(81, 4+cont);
        System.out.print(cli.getDni());
        cont+=1;
        }

    }





    public static void main(String[] args)  {
        do{
            menuPrincipal();
            switch (opc){
                case 1:
                        do{

                            borrar();
                            Registrar_cliente();
                            gotoXY(80, 25);
                            System.out.print("Desea continuar? (S/N):   ");
                            continuar = scaner.next().charAt(0);
                            System.out.println(continuar);
                        }while(continuar!='n'||continuar!='n');
                        borrar();
                        for(Cliente cli : cliente){
                            System.out.println(cli.toString());
                            System.out.println();
                        }
                        scaner.nextLine();
                        scaner.nextLine();
                    break;
                case 2:
                    borrar();
                    mostrarClientes();
                    scaner.nextLine();
                    scaner.nextLine();
                    break;
                case 3:
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
        }while(opc!=3); 

    }

    

}

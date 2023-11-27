package prueba;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {   
        Scanner scaner = new Scanner(System.in);
        String fill = "\u2588";
        System.out.println(fill);
        /* for(int i=0; i<10;i++ ){
            System.out.print(relleno);   
        } */

        //para el color numero noma,//para el relleno con m

        //gotoxy
        String red= "\033[31m";
        String mode = "\33[38;5;222m";
        System.out.println();
        System.out.printf("%shola %s \n",mode, fill );
        System.out.printf("%10s", fill);

        System.out.println("\033[1m Bold text");
        
        System.out.println("\033[0;44;31m");//para llenar todo//waos

        System.out.println("\\x1b[1;31mHello");
        System.out.println("\33[1010H");
        System.out.println("\33[38;5;{200}m");
        System.out.println("\33[5Bhola");
        System.out.println("\33\\x1b[1;31mHello");
        System.out.println("\\x1b[2;37;41mWorld");
        System.out.println(fill);
        System.out.println();
        for(int i=0; i<20;i++){
            for (int j=0;j<20;j++){
                System.out.printf("%s%s", mode, fill);
            }
            System.out.println();
        }


        //hacemos el cursor invisible 
        //System.out.println("\33[?25l\t");
        //scaner.nextLine();
    }
}
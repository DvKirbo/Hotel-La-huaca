package prueba;

public class gotoxy {
    
    public static void main(String[] args) {
        //System.out.println("\033[1;31mHello");
        //System.out.println("\033[2;37;41mWorld");
        //rgb
        //ESC[38;2;{r};{g};{b}m	
        System.out.println("\033[38;2;100;150;120;34m\t");
        System.out.println("*");
        System.out.println("\033[10;10H");
        //System.out.println("\033[10;10f\t");
        System.out.printf("%c[%d;%df",0x1B,10,10);
        System.out.print("*");

    }
}

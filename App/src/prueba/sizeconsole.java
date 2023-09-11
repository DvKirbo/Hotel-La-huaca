/* package prueba;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class sizeconsole {
    
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

    public static void main(String[] args) {
        int filas = getFilasConsola();
        int columnas = getColumnasConsola();
        
        System.out.println("Filas de la consola: " + filas);
        System.out.println("Columnas de la consola: " + columnas);
    }
}
 */
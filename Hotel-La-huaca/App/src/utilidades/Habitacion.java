package utilidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Habitacion {
    static DB cn = new DB();

    private static int id_Habitacion;
    private static String tipo_habitacion;
    private static int precio_dia;
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
    
    public static void listarhabitacion() throws SQLException{
        cn.listarHabitacion();
    }

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        listarhabitacion();

    }
}

package Users;

import java.beans.Statement;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class DB {
        final public static String user = "root";
        final public static String post = "5432";
        final public static String password = "aZUmvnskO4TeHmb2sGdSoDOQjqtQUVkN";
        final public static String url = "jdbc:postgresql://dpg-cl3f35iuuipc738c8ejg-a.oregon-postgres.render.com:5432/hoteldb_5c25";
        
        
        public static boolean consultarUsuarios(String usuario, String contr) throws Exception{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT * FROM public.\"Users\";");
            
            
            while(rs.next()){
                int id = rs.getInt("idUsuario");
                String correo =(String)  rs.getString("correo");
                String contra = rs.getString("password");
                System.out.println(correo+ " " +  " "+ contra+" " + id);
                if(correo.equals(usuario)){
                    if(contra.equals(contr)){
                        return true;
                    }
                }
            }
            return false;
        }

        public static void getHabitaciones() throws Exception{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT * FROM public.habitaciones;");
            
            while(rs.next()){
                int id = rs.getInt("id_Habitacion");
                String tipo = rs.getString("tipo_habitacion");
                boolean estado = rs.getBoolean("estado_habitacion");
                float precio = rs.getFloat("precio_dia");
                String word;
                if(estado){
                    word= "disponible";
                } 
                else {
                    word = "ocupado";
                }
                System.out.println(id + "\t"+ tipo+ "\t"+ word+ "\t\t"+ precio );
            }
        }
        public static void guardarCliente(int id, String nombre, String apellido, int numero , int dni, int habitacion) throws Exception{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("INSERT INTO public.clientes(id_cliente, nombre, apellido, doc_identidad, \"numero_Telefono\", \"idHabitacion\")VALUES (" +id+", '"+nombre+"','"+apellido+"',"+numero+","+dni+","+habitacion+");");
        }
        

        public static void modificarNombreCliente(int id , String nombre) throws Exception{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("UPDATE public.clientes set nombre="+nombre+"where = id_cliente ="+id );
        }

        public static void modificarApellidoCliente(int id , String apellido) throws Exception{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("UPDATE public.clientes set apellido="+apellido+"where = id_cliente ="+id );
        }

        public static void modificarDniCliente(int id , int dni) throws Exception{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("UPDATE public.clientes set  doc_identidad ="+dni+"where = id_cliente ="+id );
        }

        public static void modificartelefonoCliente(int id ,int telefono) throws Exception{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("UPDATE public.clientes set nombre="+telefono+"where = id_cliente ="+id );
        }

        public static void listarClientes() throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT * FROM public.clientes;");
            System.out.println("Clientes: ");
            while (rs.next()) {
                
                int id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int doc = rs.getInt("doc_identidad");
                int cell = rs.getInt("numero_Telefono");
                int habitacion = rs.getInt("idHabitacion");
                System.out.println(id+  "\t"+ nombre +"\t" + apellido + "\t" + doc+ "\t" + cell +"\t"+ habitacion);   
            }
        }

        public static void listarHabitacion() throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT * FROM public.habitaciones;");
            System.out.println("Habitaciones:");
            while (rs.next()){
                int id = rs.getInt("id_Habitacion");
                String tipo = rs.getString("tipo_habitacion");
                boolean estado_habitacion = rs.getBoolean("estado_habitacion");
                double precio_Dia = rs.getDouble("precio_dia");
                System.out.println(id+"\t"+tipo+"\t"+estado_habitacion+"\t"+precio_Dia);
            }
        }


        public static void main(String[] args) throws Exception {
            try{
                guardarCliente(3, "wasa", "wasa", 0, 0, 0);
                System.out.println("exito");
            }
            catch(Exception e){
                System.out.println("exito");
            }
            System.out.println("wasa");
        }
}

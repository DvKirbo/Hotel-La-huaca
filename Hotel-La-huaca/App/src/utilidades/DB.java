package utilidades;

import java.beans.Statement;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.PreparedStatement;


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

        public static void guardarServicio(String nombre, String descripcion, double precio, int cantidad, int cuarto, int idServicio) throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            //ResultSet rs =stmt.executeQuery("SELECT * FROM public.clientes;");
            PreparedStatement statement = conn.prepareStatement("INSERT INTO public.servicios(\r\n" + //
                    "\tnombre, descripcion, precio, cantidad)\r\n" + //
                    "\tVALUES (?, ?, ?, ?);");
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setDouble(3, precio);
            statement.setInt(4, cantidad);
            statement.executeUpdate();
            statement.close();
            
            statement = conn.prepareStatement("INSERT INTO public.\"parserServicios\"(\r\n" + //
                    "\t\"idparServicios\", habitacion, servcio)\r\n" + //
                    "\tVALUES ( ?, ?);");
                    statement.setInt(1 , cuarto);
                    statement.setInt(2, idServicio);
                    statement.executeUpdate();
                    statement.close();
                    String cadena = Integer.toString(idServicio);
            statement = conn.prepareStatement("UPDATE public.habitaciones\r\n" + //
                    "\tSET \"serviciosConsumidos\"=?\r\n" + //
                    "\tWHERE"+ idServicio+";");
            statement.executeUpdate();
            statement.close();
        }

        public static void guardar2(int id, String nombre, String apellido, int doc, int cantidad, int telefono, int cuarto, int random) throws SQLException{
              Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            PreparedStatement statement2 = conn.prepareStatement("INSERT INTO public.reserva(\r\n" + //
"\t\"idCliente\", \"idHabitacion\", \"estadoReserva\", \"fechaEntrada\", \"fechaSalida\")\r\n" + //
                    "\tVALUES ('"+Integer.toString(id) +"', '"+Integer.toString(cuarto)+"',TRUE, '2023-11-27', '2023-11-27',"+Integer.toString(cantidad)+");");
            statement2.setInt(1, id);
            statement2.setInt(2, cuarto);
            statement2.setBoolean(3, true);
            statement2.setDate(4, null);
            statement2.setDate(5, null);
            statement2.executeUpdate();
            statement2.execute();
            statement2.close();
            conn.close();
            
        }
        public static void cambiarhabitacion(int id, String nombre, String apellido, int doc, int cantidad, int telefono, int cuarto) throws SQLException {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "UPDATE public.habitaciones SET estado_habitacion=FALSE WHERE \"id_Habitacion\"="+"'"+ Integer.toString(cuarto)+"'"+";";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, cuarto); // Set the parameter for the 'id' column
            statement.executeUpdate();
            statement.close();
            conn.close();
        }
        public static void guardarCliente(int id, String nombre, String apellido, int doc, int cantidad, int telefono, int cuarto) throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            //ResultSet rs =stmt.executeQuery("SELECT * FROM public.clientes;");
            PreparedStatement statement = conn.prepareStatement("\"INSERT INTO clientes (id_cliente, nombre, apellido, doc_identidad,\\\"numero_Telefono\\\", \\\"idHabitacion\\\") VALUES (?, ?, ?, ?, ?, ?)\";");
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setString(3, apellido);
            statement.setInt(4, doc);
            statement.setInt(5, telefono);
            statement.setInt(6,cuarto);
            statement.executeUpdate();
            statement.close();
            conn.close();


        }

        public static void eliminar_reserva(int id) throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            //ResultSet rs =stmt.executeQuery("SELECT * FROM public.clientes;");
            PreparedStatement statement = conn.prepareStatement("DELETE FROM public.clientes WHERE id_cliente='"+Integer.toString(id)+"';");
            System.out.println("eliminado correctamente");
        }


        public static void mostrarCliente() throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT id_cliente, nombre, apellido, doc_identidad, \"numero_Telefono\", \"idHabitacion\"\r\n" + //
                    "\tFROM public.clientes;");
             while(rs.next()){
                int id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String estado = rs.getString("apellido");
                int numero = rs.getInt("numero_Telefono");
                int cuarto = rs.getInt("idHabitacion");
               System.out.println(id + "\t"+ nombre+ "\t"+ estado+ "\t\t"+ numero +"\t"+cuarto+"\t\t" );
            }
        }

        public static void modificarcliente(int id, String nombre, String apellido, int cuarto) throws SQLException{
             Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            //ResultSet rs =stmt.executeQuery("SELECT * FROM public.clientes;");
            PreparedStatement statement = conn.prepareStatement("UPDATE public.clientes SET nombre='"+nombre+"', apellido= '"+apellido+"', \"idHabitacion\"='"+101+"' WHERE id_cliente = '"+id+"';");
            
        }

        public static void mostrarReserva() throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT \"idCliente\", \"idHabitacion\", \"estadoReserva\", \"fechaEntrada\", \"fechaSalida\", \"idReserva\"\r\n" + //
                    "\tFROM public.reserva;");
            
            while(rs.next()){
                int id = rs.getInt("idCliente");
                int cuarto = rs.getInt("idHabitacion");
                boolean estado = rs.getBoolean("estadoReserva");
                Date fechaEntrada = rs.getDate("fechaEntrada");
                Date fechaSalida = rs.getDate("fechaSalida");
                int idREserva = rs.getInt("idReserva");
                System.out.println(idREserva+"\t\t"+id + "\t"+ cuarto+ "\t"+ estado+ "\t\t"+ fechaEntrada +"\t"+fechaSalida+"\t\t" );
            }
        }


        public static void guardarServicio(String nombre,String descripcion, double precio, int cantidad, int cuarto) throws SQLException{
             Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            //ResultSet rs =stmt.executeQuery("SELECT * FROM public.clientes;");
            PreparedStatement statement = conn.prepareStatement("INSERT INTO public.servicios( nombre, descripcion, precio, cantidad, cuarto) VALUES ('"+nombre+"', '"+descripcion+"', '"+precio+"', '"+cantidad+"', '"+cuarto+"');");
            
        }


        public static void modificarServicios(int id, String nombre, String descripcion, double precio, int cantidad) throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            //ResultSet rs =stmt.executeQuery("SELECT * FROM public.clientes;");
            PreparedStatement statement = conn.prepareStatement("UPDATE public.servicios SET nombre="+nombre+", descripcion="+descripcion+", precio="+precio+", cantidad="+cantidad+"  WHERE \"idServicios\" = '"+id+"';");
            
        }

        public static void eliminarServicios(int id) throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            //ResultSet rs =stmt.executeQuery("SELECT * FROM public.clientes;");
            PreparedStatement statement = conn.prepareStatement("DELETE FROM public.servicios WHERE \"idServicios\" = " +id+" ;");
           
        }

        public static void listarServicios() throws SQLException{
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
                ResultSet rs =stmt.executeQuery("SELECT nombre, descripcion, precio, cantidad, \"idServicios\", cuarto FROM public.servicios;");
            while(rs.next()){
                String nombre= rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                int id = rs.getInt("idServicios");
                int cuarto = rs.getInt("cuarto");
                System.out.println( id+"\t\t"+ nombre + "\t"+ descripcion+ "\t"+ precio+ "\t\t"+ cantidad +"\t"+cuarto+"\t\t");
            }
          
        }

        public static void listarHabitacion() throws SQLException {
            Connection conn = DriverManager.getConnection(url, user, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.habitaciones;");
            
            System.out.println("---------------------------------------------------------------");
            System.out.println("| ID\t| Tipo\t\t| Estado\t| Precio por Día\t|");
            System.out.println("---------------------------------------------------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id_Habitacion");
                String tipo = rs.getString("tipo_habitacion");
                boolean estado_habitacion = rs.getBoolean("estado_habitacion");
                double precio_Dia = rs.getDouble("precio_dia");
                
                System.out.printf("| %d\t| %-10s\t| %-6s\t| %.2f\t\t|\n", id, tipo, estado_habitacion, precio_Dia);
            }
            
            System.out.println("---------------------------------------------------------------");
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

package Users;

public class Reserva {
    private int id_reserva;
    private int id_cliente;
    private int id_habitacion;
    private String fecha_entrada;
    private String fecha_salida;
    private boolean estado_reserva;
    public Reserva(int id_reserva,int id_cliente, int id_habitacion, String fecha_entrada, String fecha_salida, boolean estado_reserva){
        this.id_reserva = id_reserva;
        this.id_cliente = id_cliente;
        this.id_habitacion = id_habitacion;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.estado_reserva = estado_reserva;
    }
    public static void dispibilidad(){

    }

    public static void registrar_reserva(){

    }

    public static void eliminar_reserva(){
        
    }
}
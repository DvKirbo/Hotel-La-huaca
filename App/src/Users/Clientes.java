package Users;

public class Clientes {
    private String nombre;
    private String ApellidoP;
    private String ApellidoM;
    private String Genero;
    private String numero;
    private String Dni;

    public Clientes(String nombre, String apellidoP, String apellidoM, String genero, String numero, String dni) {
        this.nombre = nombre;
        this.ApellidoP = apellidoP;
        this.ApellidoM = apellidoM;
        this.Genero = genero;
        this.numero = numero;
        this.Dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return ApellidoP;
    }

    public void setApellidoP(String apellidoP) {
        ApellidoP = apellidoP;
    }

    public String getApellidoM() {
        return ApellidoM;
    }

    public void setApellidoM(String apellidoM) {
        ApellidoM = apellidoM;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }
    
    public String toString(){
        return "Nombre: "+ nombre +"\nApellido P: "+ApellidoP+"\nApellido M: "+ApellidoP + "\nNumero: "+ numero + "\nGenero: "+Genero+"\nDni: "+Dni;
    }

}
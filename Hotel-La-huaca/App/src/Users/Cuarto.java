package Users;

import java.util.ArrayList;

public class Cuarto {
    private int id;
    private String tipo;
    private boolean estado;
    private float precio;
    public Cuarto(int id, String tipo, boolean estado, float precio) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.precio = precio;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

package Models;

import java.util.Objects;

public class Banda {
    private String codigo;
    private String nombre;
    private String paisOrigen;
    private int numIntegrantes;
    private String emailContacto;

    public Banda() {
        // Constructor vacío
    }

    public Banda(String codigo, String nombre, String paisOrigen, int numIntegrantes, String emailContacto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.numIntegrantes = numIntegrantes;
        this.emailContacto = emailContacto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public int getNumIntegrantes() {
        return numIntegrantes;
    }

    public void setNumIntegrantes(int numIntegrantes) {
        this.numIntegrantes = numIntegrantes;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Banda other = (Banda) obj;
        return Objects.equals(codigo, other.codigo);
    }

    @Override
    public String toString() {
        return "Banda [codigo=" + codigo + ", nombre=" + nombre + ", paisOrigen=" + paisOrigen + 
               ", numIntegrantes=" + numIntegrantes + ", emailContacto=" + emailContacto + "]";
    }
}
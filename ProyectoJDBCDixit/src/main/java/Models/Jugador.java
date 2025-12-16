package Models;

public class Jugador {

    private int id;
    private String nombre;
    private String email;
    private int puntosTotales;

    public Jugador() {
    }

    public Jugador(int id, String nombre, String email, int puntosTotales) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.puntosTotales = puntosTotales;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getPuntosTotales() { return puntosTotales; }
    public void setPuntosTotales(int puntosTotales) { this.puntosTotales = puntosTotales; }

    @Override
    public String toString() {
        return "Jugador [id=" + id + ", nombre=" + nombre + ", email=" + email + ", puntosTotales=" + puntosTotales + "]";
    }
}

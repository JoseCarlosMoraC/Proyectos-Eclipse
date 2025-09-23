package EldenRing.EldenRing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Clase que representa un Encuentro de un Sinluz
// Contiene nombre, fecha, dificultad y lista de enemigos
public class Encuentro {
    private String nombre;
    private LocalDate fecha;
    private int dificultad;
    private List<String> enemigos;

    // Uso ArrayList porque no se especifica que los enemigos tengan que ser únicos
    public Encuentro(String nombre, LocalDate fecha, int dificultad) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.enemigos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getDificultad() {
        return dificultad;
    }

    public List<String> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(List<String> enemigos) {
        this.enemigos = enemigos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fecha, dificultad, enemigos);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Encuentro other = (Encuentro) obj;
        return dificultad == other.dificultad &&
               Objects.equals(nombre, other.nombre) &&
               Objects.equals(fecha, other.fecha) &&
               Objects.equals(enemigos, other.enemigos);
    }

    @Override
    public String toString() {
        return "Encuentro [nombre=" + nombre + ", fecha=" + fecha +
               ", dificultad=" + dificultad + ", enemigos=" + enemigos + "]";
    }
}

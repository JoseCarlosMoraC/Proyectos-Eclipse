package Models;

import java.util.Objects;

public class Concierto {
    private int id;
    private String fecha;
    private String escenario;
    private GeneroMusical generoMusical;
    private Banda bandaHeadliner;

    public Concierto() {
        // Constructor vacío
    }

    public Concierto(int id, String fecha, String escenario, GeneroMusical generoMusical, Banda bandaHeadliner) {
        this.id = id;
        this.fecha = fecha;
        this.escenario = escenario;
        this.generoMusical = generoMusical;
        this.bandaHeadliner = bandaHeadliner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEscenario() {
        return escenario;
    }

    public void setEscenario(String escenario) {
        this.escenario = escenario;
    }

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }

    public Banda getBandaHeadliner() {
        return bandaHeadliner;
    }

    public void setBandaHeadliner(Banda bandaHeadliner) {
        this.bandaHeadliner = bandaHeadliner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Concierto other = (Concierto) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Concierto [id=" + id + ", fecha=" + fecha + ", escenario=" + escenario + 
               ", generoMusical=" + generoMusical + ", bandaHeadliner=" + bandaHeadliner + "]";
    }
}
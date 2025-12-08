package Tema1.Simulacro.Formula.Models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
 * Clase Equipo: representa cada equipo de Fórmula 1.
 * El enunciado exige almacenar sus datos (id, nombre, puntos) y además
 * mantener la relación con sus pilotos, por eso incluye un Set<Piloto>.
 */
public class Equipo {
    private String identificadorEquipo; // Identificador único del equipo
    private String nombreEquipo;
    private int puntos;
    private Set<Piloto> pilotos; // Set para evitar pilotos duplicados

    /*
     * Constructor principal. El enunciado pide almacenar pilotos asociados,
     * pero los iniciaremos siempre como Set vacío para añadirlos después.
     */
    public Equipo(String identificadorEquipo, String nombreEquipo, int puntos, Set<Piloto> pilotos) {
        super();
        this.identificadorEquipo = identificadorEquipo;
        this.nombreEquipo = nombreEquipo;
        this.puntos = puntos;
        this.pilotos = new HashSet<Piloto>(); // Se inicializa vacío siempre
    }

    public Equipo() {
        this.pilotos = new HashSet<Piloto>();
    }

    public String getIdentificadorEquipo() {
        return identificadorEquipo;
    }

    public void setIdentificadorEquipo(String identificadorEquipo) {
        this.identificadorEquipo = identificadorEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Set<Piloto> getPilotos() {
        return pilotos;
    }

    public void setPilotos(Set<Piloto> pilotos) {
        this.pilotos = pilotos;
    }

    /*
     * equals y hashCode basados en el identificador.
     * El enunciado pide usar Set, por lo que es necesario para evitar duplicados.
     */
    @Override
    public int hashCode() {
        return Objects.hash(identificadorEquipo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipo other = (Equipo) obj;
        return Objects.equals(identificadorEquipo, other.identificadorEquipo);
    }

    @Override
    public String toString() {
        return "Equipo [identificadorEquipo=" + identificadorEquipo + ", nombreEquipo=" + nombreEquipo + ", puntos="
                + puntos + ", pilotos=" + pilotos + "]";
    }
}

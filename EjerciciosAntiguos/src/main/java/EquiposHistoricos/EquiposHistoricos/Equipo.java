package EquiposHistoricos.EquiposHistoricos;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Equipo {
    private String nombre;
    private int añoFundacion;
    private int presupuesto;
    private int posicionFinal;
    private Set<Jugador> jugadores;   // TreeSet mantiene orden por numFicha
    private int añoInicio;            // Año en el que compitió ese equipo

    public Equipo(String nombre, int añoFundacion, int presupuesto, int posicionFinal, int añoInicio) {
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.presupuesto = presupuesto;
        this.posicionFinal = posicionFinal;
        this.jugadores = new TreeSet<>();
        this.añoInicio = añoInicio;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getPosicionFinal() {
        return posicionFinal;
    }

    public void setPosicionFinal(int posicionFinal) {
        this.posicionFinal = posicionFinal;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getAñoInicio() {
        return añoInicio;
    }

    public void setAñoInicio(int añoInicio) {
        this.añoInicio = añoInicio;
    }

    // Comparación por nombre + año de inicio
    @Override
    public int hashCode() {
        return Objects.hash(nombre, añoInicio);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo other = (Equipo) obj;
        return añoInicio == other.añoInicio && Objects.equals(nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "Equipo [nombre=" + nombre + ", añoFundacion=" + añoFundacion + ", presupuesto=" + presupuesto
                + ", posicionFinal=" + posicionFinal + ", jugadores=" + jugadores + ", añoInicio=" + añoInicio + "]";
    }
}

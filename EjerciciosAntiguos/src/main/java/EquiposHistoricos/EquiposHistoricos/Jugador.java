package EquiposHistoricos.EquiposHistoricos;

import java.util.Objects;

public class Jugador implements Comparable<Jugador> {
    private int numFicha;          // Identificador único
    private String nombre;
    private String apellido;
    private int edad;
    private Posicion posicion;
    private int sueldo;

    public Jugador(int numFicha, String nombre, String apellido, int edad, Posicion posicion, int sueldo) {
        this.numFicha = numFicha;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.posicion = posicion;
        this.sueldo = sueldo;
    }

    // Getters y setters

    public int getNumFicha() {
        return numFicha;
    }

    public void setNumFicha(int numFicha) {
        this.numFicha = numFicha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    // Solo el numFicha se usa para evitar duplicados
    @Override
    public int hashCode() {
        return Objects.hash(numFicha);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jugador other = (Jugador) obj;
        return numFicha == other.numFicha;
    }

    @Override
    public String toString() {
        return "Jugador [numFicha=" + numFicha + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
                + ", posicion=" + posicion + ", sueldo=" + sueldo + "]";
    }

    // Compara jugadores por número de ficha
    @Override
    public int compareTo(Jugador j) {
        return Integer.compare(this.numFicha, j.numFicha);
    }
}

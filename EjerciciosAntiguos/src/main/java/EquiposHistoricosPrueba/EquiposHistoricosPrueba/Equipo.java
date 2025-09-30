package EquiposHistoricosPrueba.EquiposHistoricosPrueba;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Equipo {
private String nombre;
private int añoFundacion;
private double presupuesto;
private int posicionLiga;
private int añoInicio;
private Set<Jugador> coleccionJugadores;

public Equipo(String nombre, int añoFundacion, double presupuesto, int posicionLiga, int añoInicio,
		Set<Jugador> coleccionJugadores) {
	super();
	this.nombre = nombre;
	this.añoFundacion = añoFundacion;
	this.presupuesto = presupuesto;
	this.posicionLiga = posicionLiga;
	this.añoInicio = añoInicio;
	this.coleccionJugadores = new TreeSet<>();
	
}


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


public double getPresupuesto() {
	return presupuesto;
}


public void setPresupuesto(double presupuesto) {
	this.presupuesto = presupuesto;
}


public int getPosicionLiga() {
	return posicionLiga;
}


public void setPosicionLiga(int posicionLiga) {
	this.posicionLiga = posicionLiga;
}


public int getAñoInicio() {
	return añoInicio;
}


public void setAñoInicio(int añoInicio) {
	this.añoInicio = añoInicio;
}


public Set<Jugador> getColeccionJugadores() {
	return coleccionJugadores;
}


public void setColeccionJugadores(Set<Jugador> coleccionJugadores) {
	this.coleccionJugadores = coleccionJugadores;
}


@Override
public int hashCode() {
    return Objects.hash(nombre, añoFundacion); // orden: da igual, pero sé coherente
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null || getClass() != obj.getClass())
        return false;
    Equipo other = (Equipo) obj;
    return añoFundacion == other.añoFundacion &&
           Objects.equals(nombre, other.nombre);
}

@Override
public String toString() {
	return "Equipo [nombre=" + nombre + ", añoFundacion=" + añoFundacion + ", presupuesto=" + presupuesto
			+ ", posicionLiga=" + posicionLiga + ", añoInicio=" + añoInicio + ", coleccionJugadores="
			+ coleccionJugadores + "]";
}


}

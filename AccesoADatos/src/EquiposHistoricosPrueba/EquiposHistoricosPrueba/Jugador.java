package EquiposHistoricosPrueba.EquiposHistoricosPrueba;

import java.util.Objects;

public class Jugador implements Comparable<Jugador>{
private int numFicha;
private String nombre;
private String apellidos;
private int edad;
private Posicion posicion;
private double sueldo;

public Jugador(int numFicha, String nombre, String apellidos, int edad, Posicion posicion, double sueldo) {
	super();
	this.numFicha = numFicha;
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.edad = edad;
	this.posicion = posicion;
	this.sueldo = sueldo;
}

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

public String getApellidos() {
	return apellidos;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
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

public double getSueldo() {
	return sueldo;
}

public void setSueldo(double sueldo) {
	this.sueldo = sueldo;
}

@Override
public int hashCode() {
	return Objects.hash(numFicha);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Jugador other = (Jugador) obj;
	return numFicha == other.numFicha;
}

@Override
public String toString() {
	return "Jugador [numFicha=" + numFicha + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad
			+ ", posicion=" + posicion + ", sueldo=" + sueldo + "]";
}
@Override
public int compareTo(Jugador j) {
    // Ordeno de mayor a menor número de descargas usando Integer.compare porque es primitivo
    return Integer.compare(this.numFicha, j.numFicha);
}

}

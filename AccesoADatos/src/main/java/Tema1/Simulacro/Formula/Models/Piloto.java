package Tema1.Simulacro.Formula.Models;

import java.util.Objects;

public class Piloto {
private int identificadorPiloto;
private String nombre;
private String pais;
private int puntos;

public Piloto(int identificadorPiloto, String nombre, String pais, int puntos) {
	super();
	this.identificadorPiloto = identificadorPiloto;
	this.nombre = nombre;
	this.pais = pais;
	this.puntos = puntos;
}

public Piloto() {
	// TODO Auto-generated constructor stub
}

public int getIdentificadorPiloto() {
	return identificadorPiloto;
}

public void setIdentificadorPiloto(int identificadorPiloto) {
	this.identificadorPiloto = identificadorPiloto;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getPais() {
	return pais;
}

public void setPais(String pais) {
	this.pais = pais;
}

public int getPuntos() {
	return puntos;
}

public void setPuntos(int puntos) {
	this.puntos = puntos;
}

@Override
public int hashCode() {
	return Objects.hash(identificadorPiloto);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Piloto other = (Piloto) obj;
	return identificadorPiloto == other.identificadorPiloto;
}

@Override
public String toString() {
	return "Piloto [identificadorPiloto=" + identificadorPiloto + ", nombre=" + nombre + ", pais=" + pais + ", puntos="
			+ puntos + "]";
}


}
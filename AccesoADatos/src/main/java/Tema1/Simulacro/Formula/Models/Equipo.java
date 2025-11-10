package Tema1.Simulacro.Formula.Models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Equipo {
private int identificadorEquipo;
private String nombreEquipo;
private int puntos;
private Set<Piloto> pilotos;

public Equipo(int identificadorEquipo, String nombreEquipo, int puntos, Set<Piloto> pilotos) {
	super();
	this.identificadorEquipo = identificadorEquipo;
	this.nombreEquipo = nombreEquipo;
	this.puntos = puntos;
	this.pilotos = new HashSet<Piloto>();
}

public int getIdentificadorEquipo() {
	return identificadorEquipo;
}

public void setIdentificadorEquipo(int identificadorEquipo) {
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
	return identificadorEquipo == other.identificadorEquipo;
}

@Override
public String toString() {
	return "Equipo [identificadorEquipo=" + identificadorEquipo + ", nombreEquipo=" + nombreEquipo + ", puntos="
			+ puntos + ", pilotos=" + pilotos + "]";
}


}

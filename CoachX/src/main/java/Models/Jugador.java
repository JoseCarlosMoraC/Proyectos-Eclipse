package Models;

import java.time.LocalDate;
import java.util.Objects;

public class Jugador {
private int id;
private String nombre;
private int dorsal;
private LocalDate fechaNacimiento;
private String posicion;
private boolean estadoFisico;
private Estadistica estadisticas;

public Jugador(int id, String nombre, int dorsal, LocalDate fechaNacimiento, String posicion, boolean estadoFisico,
		Estadistica estadisticas) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.dorsal = dorsal;
	this.fechaNacimiento = fechaNacimiento;
	this.posicion = posicion;
	this.estadoFisico = estadoFisico;
	this.estadisticas = estadisticas;
}

public Jugador() {
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public int getDorsal() {
	return dorsal;
}

public void setDorsal(int dorsal) {
	this.dorsal = dorsal;
}

public LocalDate getFechaNacimiento() {
	return fechaNacimiento;
}

public void setFechaNacimiento(LocalDate fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
}

public String getPosicion() {
	return posicion;
}

public void setPosicion(String posicion) {
	this.posicion = posicion;
}

public boolean isEstadoFisico() {
	return estadoFisico;
}

public void setEstadoFisico(boolean estadoFisico) {
	this.estadoFisico = estadoFisico;
}

public Estadistica getEstadisticas() {
	return estadisticas;
}

public void setEstadisticas(Estadistica estadisticas) {
	this.estadisticas = estadisticas;
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
	Jugador other = (Jugador) obj;
	return id == other.id;
}


}

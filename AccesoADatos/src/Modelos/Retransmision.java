package Modelos;

import java.time.LocalDate;
import java.util.Objects;

public class Retransmision {
private int id;
private LocalDate fechaInicio;
private double duracion;
private String titulo;

public Retransmision(int id, LocalDate fechaInicio, double duracion, String titulo) {
	super();
	this.id = id;
	this.fechaInicio = fechaInicio;
	this.duracion = duracion;
	this.titulo = titulo;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public LocalDate getFechaInicio() {
	return fechaInicio;
}

public void setFechaInicio(LocalDate fechaInicio) {
	this.fechaInicio = fechaInicio;
}

public double getDuracion() {
	return duracion;
}

public void setDuracion(double duracion) {
	this.duracion = duracion;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

@Override
public int hashCode() {
	return Objects.hash(duracion, fechaInicio, id, titulo);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Retransmision other = (Retransmision) obj;
	return Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
			&& Objects.equals(fechaInicio, other.fechaInicio) && id == other.id && Objects.equals(titulo, other.titulo);
}

@Override
public String toString() {
	return "Retransmision [id=" + id + ", fechaInicio=" + fechaInicio + ", duracion=" + duracion + ", titulo=" + titulo
			+ "]";
}


}

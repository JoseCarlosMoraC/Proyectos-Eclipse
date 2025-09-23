package Modelos;

import java.time.LocalDate;
import java.util.Objects;

public class Canal {
private int id;
private String titulo;
private LocalDate fechaCreacion;

public Canal(int id, String titulo, LocalDate fechaCreacion) {
	super();
	this.id = id;
	this.titulo = titulo;
	this.fechaCreacion = fechaCreacion;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public LocalDate getFechaCreacion() {
	return fechaCreacion;
}

public void setFechaCreacion(LocalDate fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}

@Override
public int hashCode() {
	return Objects.hash(fechaCreacion, id, titulo);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Canal other = (Canal) obj;
	return Objects.equals(fechaCreacion, other.fechaCreacion) && id == other.id && Objects.equals(titulo, other.titulo);
}

@Override
public String toString() {
	return "Canal [id=" + id + ", titulo=" + titulo + ", fechaCreacion=" + fechaCreacion + "]";
}


}

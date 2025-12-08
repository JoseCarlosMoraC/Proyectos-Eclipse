package Models;

import java.time.LocalDate;
import java.util.Objects;

public class Partido {
private int id;
private int equipoId;
private String rival;
private LocalDate fecha;
private String lugar;

public Partido(int id, int equipoId, String rival, LocalDate fecha, String lugar) {
	super();
	this.id = id;
	this.equipoId = equipoId;
	this.rival = rival;
	this.fecha = fecha;
	this.lugar = lugar;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getEquipoId() {
	return equipoId;
}

public void setEquipoId(int equipoId) {
	this.equipoId = equipoId;
}

public String getRival() {
	return rival;
}

public void setRival(String rival) {
	this.rival = rival;
}

public LocalDate getFecha() {
	return fecha;
}

public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}

public String getLugar() {
	return lugar;
}

public void setLugar(String lugar) {
	this.lugar = lugar;
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
	Partido other = (Partido) obj;
	return id == other.id;
}



}

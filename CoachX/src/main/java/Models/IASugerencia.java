package Models;

import java.time.LocalDate;
import java.util.Objects;

public class IASugerencia {
private int id;
private int jugadorId;
private int entrenamientoId;
private String recomendacion;
private LocalDate fecha;

public IASugerencia(int id, int jugadorId, int entrenamientoId, String recomendacion, LocalDate fecha) {
	super();
	this.id = id;
	this.jugadorId = jugadorId;
	this.entrenamientoId = entrenamientoId;
	this.recomendacion = recomendacion;
	this.fecha = fecha;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getJugadorId() {
	return jugadorId;
}

public void setJugadorId(int jugadorId) {
	this.jugadorId = jugadorId;
}

public int getEntrenamientoId() {
	return entrenamientoId;
}

public void setEntrenamientoId(int entrenamientoId) {
	this.entrenamientoId = entrenamientoId;
}

public String getRecomendacion() {
	return recomendacion;
}

public void setRecomendacion(String recomendacion) {
	this.recomendacion = recomendacion;
}

public LocalDate getFecha() {
	return fecha;
}

public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
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
	IASugerencia other = (IASugerencia) obj;
	return id == other.id;
}


}

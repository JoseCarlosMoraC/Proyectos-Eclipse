package Models;

import java.util.Objects;

public class AsistenciaEntrenamiento {
private int id;
private int entrenamientoId;
private int jugadorId;
private String estado;

public AsistenciaEntrenamiento(int id, int entrenamientoId, int jugadorId, String estado) {
	super();
	this.id = id;
	this.entrenamientoId = entrenamientoId;
	this.jugadorId = jugadorId;
	this.estado = estado;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getEntrenamientoId() {
	return entrenamientoId;
}

public void setEntrenamientoId(int entrenamientoId) {
	this.entrenamientoId = entrenamientoId;
}

public int getJugadorId() {
	return jugadorId;
}

public void setJugadorId(int jugadorId) {
	this.jugadorId = jugadorId;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
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
	AsistenciaEntrenamiento other = (AsistenciaEntrenamiento) obj;
	return id == other.id;
}


}

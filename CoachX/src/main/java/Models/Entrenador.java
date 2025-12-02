package Models;

import java.util.Objects;

public class Entrenador {
private int id;
private int usuarioId;

public Entrenador(int id, int usuarioId) {
	super();
	this.id = id;
	this.usuarioId = usuarioId;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getUsuarioId() {
	return usuarioId;
}

public void setUsuarioId(int usuarioId) {
	this.usuarioId = usuarioId;
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
	Entrenador other = (Entrenador) obj;
	return id == other.id;
}


}

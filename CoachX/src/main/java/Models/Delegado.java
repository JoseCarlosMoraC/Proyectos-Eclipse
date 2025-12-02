package Models;

import java.util.Objects;

public class Delegado {
private int id;
private int UsuarioId;

public Delegado(int id, int usuarioId) {
	super();
	this.id = id;
	UsuarioId = usuarioId;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getUsuarioId() {
	return UsuarioId;
}

public void setUsuarioId(int usuarioId) {
	UsuarioId = usuarioId;
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
	Delegado other = (Delegado) obj;
	return id == other.id;
}


}

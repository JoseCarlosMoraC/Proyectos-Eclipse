package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Concierto {
private int id;
private String fecha;
private String descripcion;
private Escenario escenario;
private List<Grupo> grupo;


public Concierto() {
	super();
	this.grupo = new ArrayList<Grupo>();
}




public Concierto(int id, String fecha, String descripcion, Escenario escenario, List<Grupo> grupo) {
	super();
	this.id = id;
	this.fecha = fecha;
	this.descripcion = descripcion;
	this.escenario = escenario;
	this.grupo = grupo;
}




public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getFecha() {
	return fecha;
}


public void setFecha(String fecha) {
	this.fecha = fecha;
}


public String getDescripcion() {
	return descripcion;
}


public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}


public Escenario getEscenario() {
	return escenario;
}


public void setEscenario(Escenario escenario) {
	this.escenario = escenario;
}




public List<Grupo> getGrupo() {
	return grupo;
}




public void setGrupo(List<Grupo> grupo) {
	this.grupo = grupo;
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
	Concierto other = (Concierto) obj;
	return id == other.id;
}




@Override
public String toString() {
	return "Concierto [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion + ", escenario=" + escenario
			+ ", grupo=" + grupo + "]";
}




}

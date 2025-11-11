package Simulacro.BancoDeAlimentos.Models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CentroLogistico {
private String id;
private String nombre;
private String ciudad;
private int numComedores;
private Set<Trabajador> trabajadores;




public CentroLogistico(String id, String nombre, String ciudad, int numComedores, Set<Trabajador> trabajadores) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.ciudad = ciudad;
	this.numComedores = numComedores;
	this.trabajadores = new HashSet<Trabajador>();
}

public CentroLogistico() {
	this.trabajadores = new HashSet<Trabajador>();
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getCiudad() {
	return ciudad;
}

public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}

public int getNumComedores() {
	return numComedores;
}

public void setNumComedores(int numComedores) {
	this.numComedores = numComedores;
}

public Set<Trabajador> getTrabajadores() {
	return trabajadores;
}

public void setTrabajadores(Set<Trabajador> trabajadores) {
	this.trabajadores = trabajadores;
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
	CentroLogistico other = (CentroLogistico) obj;
	return Objects.equals(id, other.id);
}

@Override
public String toString() {
	return "CentroLogistico [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", numComedores=" + numComedores
			+ ", trabajadores=" + trabajadores + "]";
}



}

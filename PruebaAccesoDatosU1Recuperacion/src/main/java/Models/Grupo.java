package Models;

import java.util.Objects;

public class Grupo {
private String nombre;
private String codigo;
private int numIntegrantes;
private String email;


public Grupo() {
	super();
}


public Grupo(String nombre, String codigo, int numIntegrantes, String email) {
	super();
	this.nombre = nombre;
	this.codigo = codigo;
	this.numIntegrantes = numIntegrantes;
	this.email = email;
}


public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public String getCodigo() {
	return codigo;
}


public void setCodigo(String codigo) {
	this.codigo = codigo;
}


public int getNumIntegrantes() {
	return numIntegrantes;
}


public void setNumIntegrantes(int numIntegrantes) {
	this.numIntegrantes = numIntegrantes;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


@Override
public int hashCode() {
	return Objects.hash(codigo);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Grupo other = (Grupo) obj;
	return codigo == other.codigo;
}


@Override
public String toString() {
	return "Grupo [nombre=" + nombre + ", codigo=" + codigo + ", numIntegrantes=" + numIntegrantes + ", email=" + email
			+ "]";
}



}

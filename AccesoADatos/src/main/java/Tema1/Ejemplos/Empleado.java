package Tema1.Ejemplos;

import java.util.Objects;

public class Empleado {
private int id;
private String nombreApellido;
private int edad;
private String empresa;



public Empleado() {
	super();
}


public Empleado(int id, String nombreCompleto, int edad, String empresa) {
	super();
	this.id = id;
	this.nombreApellido = nombreApellido;
	this.edad = edad;
	this.empresa = empresa;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getNombreApellido() {
	return nombreApellido;
}


public void setNombreApellido(String nombreApellido) {
	this.nombreApellido = nombreApellido;
}


public int getEdad() {
	return edad;
}


public void setEdad(int edad) {
	this.edad = edad;
}


public String getEmpresa() {
	return empresa;
}


public void setEmpresa(String empresa) {
	this.empresa = empresa;
}


@Override
public int hashCode() {
	return Objects.hash(edad, empresa, id, nombreApellido);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Empleado other = (Empleado) obj;
	return edad == other.edad && Objects.equals(empresa, other.empresa) && id == other.id
			&& Objects.equals(nombreApellido, other.nombreApellido);
}


@Override
public String toString() {
	return "Empleado [id=" + id + ", nombreApellido=" + nombreApellido + ", edad=" + edad + ", empresa=" + empresa
			+ "]";
}



}

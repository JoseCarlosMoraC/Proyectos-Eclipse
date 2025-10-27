package Tema1.Ejemplos;

import java.util.Objects;

public class Empleado {
private int id;
private String nombreCompleto;
private int edad;
private String empresa;

public Empleado(int id, String nombreCompleto, int edad, String empresa) {
	super();
	this.id = id;
	this.nombreCompleto = nombreCompleto;
	this.edad = edad;
	this.empresa = empresa;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNombreCompleto() {
	return nombreCompleto;
}

public void setNombreCompleto(String nombreCompleto) {
	this.nombreCompleto = nombreCompleto;
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
	return Objects.hash(edad, empresa, id, nombreCompleto);
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
			&& Objects.equals(nombreCompleto, other.nombreCompleto);
}

@Override
public String toString() {
	return "Empleado [id=" + id + ", nombreCompleto=" + nombreCompleto + ", edad=" + edad + ", empresa=" + empresa
			+ "]";
}



}

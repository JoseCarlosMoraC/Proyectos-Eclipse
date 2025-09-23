package Modelos;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {
private int id;
private String nombre;
private String email;
private LocalDate fechaRegistro;




public Usuario(int id2, String nombre2, String email2, LocalDate now) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.email = email;
	this.fechaRegistro = fechaRegistro;
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public LocalDate getFechaRegistro() {
	return fechaRegistro;
}

public void setFechaRegistro(LocalDate fechaRegistro) {
	this.fechaRegistro = fechaRegistro;
}

@Override
public int hashCode() {
	return Objects.hash(id);
}



@Override
public String toString() {
	return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", fechaRegistro=" + fechaRegistro + "]";
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Usuario other = (Usuario) obj;
	return id == other.id;
}



}

package Repaso.Libreria.Models;

import java.util.Objects;

public class Libro {
private String ISBN;
private String titulo;
private String autor;
private int anioPublicacion;
private Genero genero;
private Editorial editorial;
private int numEjemplares;

public Libro(String iSBN, String titulo, String autor, int anioPublicacion, Genero genero, Editorial editorial,
		int numEjemplares) {
	super();
	this.ISBN = iSBN;
	this.titulo = titulo;
	this.autor = autor;
	this.anioPublicacion = anioPublicacion;
	this.genero = genero;
	this.editorial = editorial;
	this.numEjemplares = numEjemplares;
}

public String getISBN() {
	return ISBN;
}

public void setISBN(String iSBN) {
	ISBN = iSBN;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getAutor() {
	return autor;
}

public void setAutor(String autor) {
	this.autor = autor;
}

public int getAnioPublicacion() {
	return anioPublicacion;
}

public void setAnioPublicacion(int anioPublicacion) {
	this.anioPublicacion = anioPublicacion;
}

public Genero getGenero() {
	return genero;
}

public void setGenero(Genero genero) {
	this.genero = genero;
}

public Editorial getEditorial() {
	return editorial;
}

public void setEditorial(Editorial editorial) {
	this.editorial = editorial;
}

public int getNumEjemplares() {
	return numEjemplares;
}

public void setNumEjemplares(int numEjemplares) {
	this.numEjemplares = numEjemplares;
}

@Override
public int hashCode() {
	return Objects.hash(ISBN);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Libro other = (Libro) obj;
	return Objects.equals(ISBN, other.ISBN);
}

@Override
public String toString() {
	return "Libro [ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + ", anioPublicacion=" + anioPublicacion
			+ ", genero=" + genero + ", editorial=" + editorial + ", numEjemplares=" + numEjemplares + "]";
}



}
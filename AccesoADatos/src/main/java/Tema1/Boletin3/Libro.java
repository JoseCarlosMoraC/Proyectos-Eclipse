package Tema1.Boletin3;

import java.util.Objects;

public class Libro {
private String isbn;
private String titulo;
private String nombreEditorial;

public Libro(String isbn, String titulo, String nombreEditorial) {
	super();
	this.isbn = isbn;
	this.titulo = titulo;
	this.nombreEditorial = nombreEditorial;
}

public String getIsbn() {
	return isbn;
}

public void setIsbn(String isbn) {
	this.isbn = isbn;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getNombreEditorial() {
	return nombreEditorial;
}

public void setNombreEditorial(String nombreEditorial) {
	this.nombreEditorial = nombreEditorial;
}

@Override
public int hashCode() {
	return Objects.hash(isbn);
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
	return Objects.equals(isbn, other.isbn);
}

@Override
public String toString() {
	return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", nombreEditorial=" + nombreEditorial + "]";
}


}

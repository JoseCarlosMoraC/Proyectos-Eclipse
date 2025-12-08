package Tema1.BoletinFicherosXMLDom;

import java.util.List;
import java.util.Objects;

public class Pelicula {
private String titulo;
private int duracion;
private String genero;
private String sinopsis;
private int fecha;
private String director;
private List<String> actores;

public Pelicula() {
	super();
}

public Pelicula(String titulo, int duracion, String genero, String sinopsis, int fecha, String director,
		List<String> actores) {
	super();
	this.titulo = titulo;
	this.duracion = duracion;
	this.genero = genero;
	this.sinopsis = sinopsis;
	this.fecha = fecha;
	this.director = director;
	this.actores = actores;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public int getDuracion() {
	return duracion;
}

public void setDuracion(int duracion) {
	this.duracion = duracion;
}

public String getGenero() {
	return genero;
}

public void setGenero(String genero) {
	this.genero = genero;
}

public String getSinopsis() {
	return sinopsis;
}

public void setSinopsis(String sinopsis) {
	this.sinopsis = sinopsis;
}

public int getFecha() {
	return fecha;
}

public void setFecha(int fecha) {
	this.fecha = fecha;
}

public String getDirector() {
	return director;
}

public void setDirector(String director) {
	this.director = director;
}

public List<String> getActores() {
	return actores;
}

public void setActores(List<String> actores) {
	this.actores = actores;
}

@Override
public int hashCode() {
	return Objects.hash(actores, director, duracion, fecha, genero, sinopsis, titulo);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Pelicula other = (Pelicula) obj;
	return Objects.equals(actores, other.actores) && Objects.equals(director, other.director)
			&& duracion == other.duracion && fecha == other.fecha && Objects.equals(genero, other.genero)
			&& Objects.equals(sinopsis, other.sinopsis) && Objects.equals(titulo, other.titulo);
}

@Override
public String toString() {
	return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero + ", sinopsis=" + sinopsis
			+ ", fecha=" + fecha + ", director=" + director + ", actores=" + actores + "]";
}

}
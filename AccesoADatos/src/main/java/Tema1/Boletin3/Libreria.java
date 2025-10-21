package Tema1.Boletin3;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Libreria {
private Set<Libro> listaLibros;

public Libreria(Set<Libro> listaLibros) {
	super();
	listaLibros = new HashSet<Libro>();
}

public Set<Libro> getListaLibros() {
	return listaLibros;
}

public void setListaLibros(Set<Libro> listaLibros) {
	this.listaLibros = listaLibros;
}

@Override
public int hashCode() {
	return Objects.hash(listaLibros);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Libreria other = (Libreria) obj;
	return Objects.equals(listaLibros, other.listaLibros);
}


}

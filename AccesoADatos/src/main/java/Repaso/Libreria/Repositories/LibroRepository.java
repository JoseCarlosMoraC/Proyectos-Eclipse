package Repaso.Libreria.Repositories;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import Repaso.Libreria.Models.LibreriaException;
import Repaso.Libreria.Models.Libro;

public class LibroRepository {
private Set<Libro> libros;

public LibroRepository(Set<Libro> libros) {
	super();
	this.libros = new HashSet<Libro>();
}

public Set<Libro> getLibros() {
	return libros;
}

public void setLibros(Set<Libro> libros) {
	this.libros = libros;
}

@Override
public int hashCode() {
	return Objects.hash(libros);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	LibroRepository other = (LibroRepository) obj;
	return Objects.equals(libros, other.libros);
}

@Override
public String toString() {
	return "LibroRepository [libros=" + libros + "]";
}

public void añadirLibro(Libro l)throws LibreriaException {
	if(libros.contains(l)) {
		throw new LibreriaException("El libro ya existe");
	}else {
		libros.add(l);
	}
}

public Libro leeLibro(String l)
{
	
	  boolean encontrado = false;
      Libro libro = null;

    
      Iterator<Libro> it = libros.iterator();
      while (it.hasNext() && !encontrado) {
          Libro u = it.next();

          
          if (libro.getISBN().equalsIgnoreCase(l)){
              encontrado = true;
              libro = u;
          
      }}
      
      return libro;
}

public Libro actualizarLibro(Libro libroaActualizar) throws LibreriaException {
	  boolean encontrado = false;
      Libro libro = this.leeLibro(libroaActualizar.getISBN());
      if(libro != null)
      {
    	  this.libros.remove(libro);
    	  this.libros.add(libroaActualizar);
      }else {
    	  throw new LibreriaException("El libro no existe");
      }
    
     

      return libro;
  }

public void eliminarLibro(Libro l) throws LibreriaException {
	if(libros.contains(l)) {
		throw new LibreriaException("El libro ya existe");
	}else {
		libros.remove(l);
	}
}
public Libro buscarLibroPorGenero(Libro genero) {
	  boolean encontrado = false;
      Libro libro = null;

    
      Iterator<Libro> it = libros.iterator();
      while (it.hasNext() && !encontrado) {
          Libro u = it.next();

          
          if (libro.getGenero().equals(genero)){
              encontrado = true;
              libro = u;
          
      }
       
      }
	  return libro;
}
}



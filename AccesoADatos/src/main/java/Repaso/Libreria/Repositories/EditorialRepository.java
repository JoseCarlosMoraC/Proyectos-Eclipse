package Repaso.Libreria.Repositories;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import Repaso.Libreria.Models.Editorial;
import Repaso.Libreria.Models.LibreriaException;
import Repaso.Libreria.Models.Libro;

public class EditorialRepository {
private Set<Editorial> editoriales;

public EditorialRepository() {
	super();
	this.editoriales = new HashSet<Editorial>();
}

public Set<Editorial> getEditoriales() {
	return editoriales;
}

public void setEditoriales(Set<Editorial> editoriales) {
	this.editoriales = editoriales;
}

@Override
public int hashCode() {
	return Objects.hash(editoriales);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	EditorialRepository other = (EditorialRepository) obj;
	return Objects.equals(editoriales, other.editoriales);
}

@Override
public String toString() {
	return "EditorialRepository [editoriales=" + editoriales + "]";
}

public void añadirEditorial(Editorial l)throws LibreriaException {
	if(editoriales.contains(l)) {
		throw new LibreriaException("El libro ya existe");
	}else {
		editoriales.add(l);
	}
}

public Editorial getEditorial(String cif) {
	boolean encontrado = false;
    Editorial editorial = null;
    Iterator<Editorial> it = editoriales.iterator();
    while (it.hasNext() && !encontrado) {
        Editorial editorialdevuelto = it.next();
        if (editorial.getCif() != null && editorial.getCif().equalsIgnoreCase(cif)) {
            editorial = editorialdevuelto;
            encontrado = true;
        }
    }
    return editorial;
}


public Editorial actualizarEditorial(Editorial editorialAActualizar) throws LibreriaException {
	  boolean encontrado = false;
      Editorial editorial = this.getEditorial(editorialAActualizar.getCif());
      if(editorial != null)
      {
    	  this.editoriales.remove(editorial);
    	  this.editoriales.add(editorialAActualizar);
      }
      else {
    	  throw new LibreriaException("La editorial no existe");
          }
      
    
     

      return editorial;
  }


public void eliminarEditorial(Editorial l) throws LibreriaException {
    if (!editoriales.contains(l)) {
        throw new LibreriaException("La editorial no existe");
    } else {
        editoriales.remove(l);
    }
}
public Editorial buscarEditorialPorCif(String cif) {
	 boolean encontrado = false;
    Editorial editorial = null;

  
    Iterator<Editorial> it = editoriales.iterator();
    while (it.hasNext() && !encontrado) {
        Editorial e = it.next();

        
        if (e.getCif().equalsIgnoreCase(cif)){
            encontrado = true;
            editorial = e;
        
    }
     
    }
	  return editorial;
}
}





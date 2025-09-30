package Repaso.Libreria.Services;

import java.util.Iterator;

import Repaso.Libreria.Models.Editorial;
import Repaso.Libreria.Models.Genero;
import Repaso.Libreria.Models.LibreriaException;
import Repaso.Libreria.Models.Libro;
import Repaso.Libreria.Repositories.EditorialRepository;
import Repaso.Libreria.Repositories.LibroRepository;

public class LibreriaService {

	private EditorialRepository repoEditorial;
	private LibroRepository repoLibro;
	
	public LibreriaService() {
		super();
		this.repoEditorial = new EditorialRepository();
		this.repoLibro = new LibroRepository(null);
	}
	
	
	public EditorialRepository getRepoEditorial() {
		return repoEditorial;
	}


	public void setRepoEditorial(EditorialRepository repoEditorial) {
		this.repoEditorial = repoEditorial;
	}


	public LibroRepository getRepoLibro() {
		return repoLibro;
	}


	public void setRepoLibro(LibroRepository repoLibro) {
		this.repoLibro = repoLibro;
	}
	public Libro buscarLibroPorGenero(Genero genero) {
		  
		boolean encontrado = false;
		Libro libro = null;
	
		for (Libro l : this.repoLibro.getLibros()) {
			
		}
		/*while (it.hasNext() && !encontrado) {
		    Libro librodevuelto = it.next();
		    if (libro.getGenero() != null && libro.getGenero().equals(genero)) {
		        libro = librodevuelto;
		        encontrado = true;
		    }
		}*/
		return libro;
		}


	public void añadirEditorial (Editorial l) {
		try {
			repoEditorial.añadirEditorial(l);
		} catch (LibreriaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void getEditorial (String l) {
		repoEditorial.getEditorial(l);
	}
	public void actualizarEditorial (Editorial editorialAActualizar) {
		try {
			repoEditorial.actualizarEditorial(editorialAActualizar);
		} catch (LibreriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void eliminarEditorial (Editorial l) {
		try {
			repoEditorial.eliminarEditorial(l);
		} catch (LibreriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void buscarEditorialPorCif(String cif) {
		repoEditorial.buscarEditorialPorCif(cif);
	}
	public void añadirLibro (Libro l) {
		try {
			repoLibro.añadirLibro(l);;
		} catch (LibreriaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public Libro leeLibro(String isbn) {
	    return repoLibro.leeLibro(isbn);
	}

	public void actualizarLibro (Libro libroAActualizar) {
		try {
			repoLibro.actualizarLibro(libroAActualizar);
		} catch (LibreriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void eliminarLibro (Libro l) {
		try {
			repoLibro.eliminarLibro(l);
		} catch (LibreriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	}



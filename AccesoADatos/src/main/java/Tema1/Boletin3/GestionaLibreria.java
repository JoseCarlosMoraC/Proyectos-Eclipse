package Tema1.Boletin3;

import java.util.HashSet;
import java.util.Set;

import Utiles.ManejoTxtLibreria;

public class GestionaLibreria {
	 public static void main(String[] args) {
	    
	        Libro libro1 = new Libro("978-84-123456-0-0", "1984", "Secker & Warburg");
	        Libro libro2 = new Libro("978-84-987654-3-2", "El Hobbit", "Allen & Unwin");
	        Libro libro3 = new Libro("978-84-567890-1-1", "Cien años de soledad", "Sudamericana");
	        Libro libro4 = new Libro("978-84-112233-4-5", "Don Quijote de la Mancha", "Francisco de Robles");
	        Libro libro5 = new Libro("978-84-998877-6-5", "La sombra del viento", "Planeta");

	      
	        Set<Libro> listaLibros = new HashSet<>();
	        listaLibros.add(libro1);
	        listaLibros.add(libro2);
	        listaLibros.add(libro3);
	        listaLibros.add(libro4);
	        listaLibros.add(libro5);

	        
	        Libreria libreria = new Libreria(listaLibros);

	   
	        ManejoTxtLibreria manejo = new ManejoTxtLibreria();
	        String rutaFichero = "salida/libros.txt"; 
	        manejo.escribeLibros(libreria, rutaFichero);
	    }
	
}

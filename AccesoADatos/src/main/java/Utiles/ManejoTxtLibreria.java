package Utiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import Repaso.Libreria.Models.Libro;
import Tema1.Boletin3.Libreria;

public class ManejoTxtLibreria {

	   public void escribeLibros(Libreria libreria, String rutaFichero) {
	        if (libreria == null || rutaFichero == null) {
	            throw new IllegalArgumentException("Libreria y ruta no pueden ser null");
	        }

	        File fichero = new File(rutaFichero);


	        File carpetaPadre = fichero.getParentFile();
	        if (carpetaPadre != null && !carpetaPadre.exists()) {
	            carpetaPadre.mkdirs();
	        }

	        Set<Tema1.Boletin3.Libro> libros = libreria.getListaLibros();

	        if (libros == null || libros.isEmpty()) {
	            System.out.println("La librería no contiene libros.");
	            return;
	        }

	        try (PrintWriter out = new PrintWriter(new FileWriter(fichero))) {
	            for (Tema1.Boletin3.Libro l : libros) {
	                out.printf("%s, %s, %s%n", l.getIsbn(), l.getTitulo(), l.getNombreEditorial());
	            }
	            System.out.println("Fichero generado correctamente en: " + rutaFichero);
	        } catch (IOException e) {
	            System.err.println("Error al escribir en el fichero: " + e.getMessage());
	        }
	    }
	}
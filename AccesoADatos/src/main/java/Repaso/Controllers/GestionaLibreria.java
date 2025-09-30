package Repaso.Controllers;

import Repaso.Libreria.Models.Editorial;
import Repaso.Libreria.Models.Genero;
import Repaso.Libreria.Models.Libro;
import Repaso.Libreria.Services.LibreriaService;

public class GestionaLibreria {

    public static void main(String[] args) {

    	Editorial e = new Editorial("CIF123", "Planeta", "España", "contacto@planeta.com", "Barcelona");;
    	Libro l = new Libro("1234567890", "El Quijote", "Miguel de Cervantes", 1605, Genero.Infantil, e, 5);
        

        LibreriaService libreriaServicio = new LibreriaService();

       
        libreriaServicio.añadirLibro(l);
       libreriaServicio.leeLibro("1234567890"); 
        libreriaServicio.actualizarLibro(l);
        libreriaServicio.eliminarLibro(l);
        libreriaServicio.buscarLibroPorGenero(Genero.Historico);

      
       libreriaServicio.añadirEditorial(e); 
        libreriaServicio.actualizarEditorial(e);
        libreriaServicio.buscarEditorialPorCif("CIF123");
        libreriaServicio.eliminarEditorial(e);
        libreriaServicio.getEditorial("CIF123");

       
        System.out.println(libreriaServicio.getRepoEditorial().getEditoriales());
        System.out.println(libreriaServicio.getRepoLibro().getLibros());
    }
}


package Juegos.Repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Juegos.Modelo.EstadoJuego;
import Juegos.Modelo.EstudioDesarrollo;
import Juegos.Modelo.Juego;

// Esta clase es la plataforma que gestiona los juegos y estudios
public class PlataformaVideojuegos {

    // Uso un TreeMap para que los juegos estén ordenados según su número de descargas (compareTo en Juego)
    // Además, evita duplicados ya que la clave es única (basada en título y año)
    private Map<Juego, EstudioDesarrollo> plataforma;

    // Constructor, inicializo la plataforma con un TreeMap vacío
    public PlataformaVideojuegos() {
        this.plataforma = new TreeMap<>();
    }

    // Getter para acceder a la plataforma
    public Map<Juego, EstudioDesarrollo> getPlataforma() {
        return plataforma;
    }

    // Setter para modificar toda la plataforma si fuera necesario
    public void setPlataforma(Map<Juego, EstudioDesarrollo> plataforma) {
        this.plataforma = plataforma;
    }

    // Método para agregar un nuevo juego junto con su estudio
    public void agregarJuego(Juego juego, EstudioDesarrollo estudio) {
    	
        // Compruebo si ya existe el juego, para no añadir duplicados
        if (plataforma.containsKey(juego)) {
            System.out.println("Este juego ya ha sido agregado");
        } else {
            // Si no existe, agrego juego y estudio
            plataforma.put(juego, estudio);
        }
    }

    // Buscar un juego por título y año
    public Juego buscarJuego(String titulo, String añoPublicacion) {
        boolean encontrado = false;
        Juego juego = null;

        // Uso Iterator para recorrer juegos
        Iterator<Juego> it = plataforma.keySet().iterator();
        while (it.hasNext() && !encontrado) {
            Juego j = it.next();
            
            // Comparo ignorando mayúsculas y minúsculas
            if (j.getTitulo().equalsIgnoreCase(titulo) && j.getAñoPublicacion().equalsIgnoreCase(añoPublicacion)) {
                encontrado = true;
                juego = j;
            }
        }

        if (!encontrado)
            System.out.println("No se ha encontrado");

        return juego;
    }

    // Cambiar estado de un juego
    public boolean cambiarEstado(String titulo, String añoPublicacion, EstadoJuego nuevoEstado) {
        boolean modificado = false;

        Iterator<Juego> it = plataforma.keySet().iterator();
        while (it.hasNext() && !modificado) {
            Juego j = it.next();
            if (j.getTitulo().equalsIgnoreCase(titulo) && j.getAñoPublicacion().equalsIgnoreCase(añoPublicacion)) {
                j.setEstado(nuevoEstado);
                modificado = true;
            }
        }

        if (!modificado)
            System.out.println("No se ha encontrado");

        return modificado;
    }

    // Obtener los 3 juegos con más descargas
	public List<Juego> top3PorDescargas() {
		List<Juego> juegosMasDescargados = new ArrayList<>();
		
		// Recorro la plataforma y agrego todos los juegos a la lista
		 Iterator<Juego> it = this.plataforma.keySet().iterator();

		    while (it.hasNext()) {
		        Juego j = it.next();
		        juegosMasDescargados.add(j);
		    }
	        // Ordeno la lista según el número de descargas (descendente)
		    java.util.Collections.sort(juegosMasDescargados);

		    return juegosMasDescargados;
		}

    // Filtrar juegos por país del estudio de desarrollo
    public List<Juego> filtrarPorPais(String pais) {
        List<Juego> juegosFiltrados = new ArrayList<>();

        Iterator<Juego> it = plataforma.keySet().iterator();
        while (it.hasNext()) {
            Juego juego = it.next();
            EstudioDesarrollo estudio = plataforma.get(juego);
            
            // Comparo el país ignorando mayúsculas y minúsculas
            if (estudio.getPais().equalsIgnoreCase(pais)) {
                juegosFiltrados.add(juego);
            }
        }

        return juegosFiltrados;
    }
    
}

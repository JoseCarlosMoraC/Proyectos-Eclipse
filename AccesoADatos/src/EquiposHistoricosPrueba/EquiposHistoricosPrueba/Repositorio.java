package EquiposHistoricosPrueba.EquiposHistoricosPrueba;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Repositorio {
	private Set<Equipo> equipos;

	public Repositorio(Set<Equipo> equipos) {
		super();
		this.equipos = new HashSet<>();
	}

	public Set<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(equipos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repositorio other = (Repositorio) obj;
		return Objects.equals(equipos, other.equipos);
	}

	@Override
	public String toString() {
		return "Repositorio [equipos=" + equipos + "]";
	}

	public Equipo getEquipo(String nombre, int año) throws FutbolException {
		boolean encontrado = false;
		Equipo equipo = null;

		// Uso Iterator para recorrer juegos
		Iterator<Equipo> it = equipos.iterator();
		while (it.hasNext() && !encontrado) {
			Equipo e = it.next();

			// Comparo ignorando mayúsculas y minúsculas
			if (e.getNombre().equalsIgnoreCase(nombre) && e.getAñoInicio() == año) {
				encontrado = true;
				equipo = e;
			}
		}

		if (!encontrado)
			throw new FutbolException("No existe el equipo con nombre: " + nombre + " y año: " + año);

		return equipo;
	}

	public Equipo addJugador(String nombre, int año, Jugador jugador) throws FutbolException {
	    boolean encontrado = false;
	    Equipo equipo = null;

	    // Iteramos con iterator para buscar equipo por nombre y año
	    Iterator<Equipo> it = equipos.iterator();
	    while (it.hasNext() && !encontrado) {
	        Equipo e = it.next();

	        if (e.getNombre().equalsIgnoreCase(nombre) && e.getAñoInicio() == año) {
	            encontrado = true;
	            equipo = e;

	            // Comprobamos si hay presupuesto suficiente para el sueldo del jugador
	            if (e.getPresupuesto() < jugador.getSueldo()) {
	                throw new FutbolException("No hay presupuesto suficiente para añadir al jugador " + jugador.getNombre());
	            }

	            // Si jugador existe, actualizamos (eliminar y luego agregar)
	            if (e.getColeccionJugadores().contains(jugador)) {
	                e.getColeccionJugadores().remove(jugador);
	            }

	            e.getColeccionJugadores().add(jugador);

	            // Restamos sueldo del presupuesto del equipo
	            e.setPresupuesto(e.getPresupuesto() - jugador.getSueldo());
	        }
	    }

	    if (!encontrado) {
	        throw new FutbolException("No existe el equipo con nombre: " + nombre + " y año: " + año);
	    }

	    return equipo;
	}
	public Jugador getJugadorMasCaro() {
	    Iterator<Equipo> itEquipos = equipos.iterator();
	    Jugador masCaro = null;

	    while (itEquipos.hasNext()) {
	        Equipo e = itEquipos.next();
	        Iterator<Jugador> itJugadores = e.getColeccionJugadores().iterator();

	        while (itJugadores.hasNext()) {
	            Jugador actual = itJugadores.next();
	            if (masCaro == null || actual.getSueldo() > masCaro.getSueldo()) {
	                masCaro = actual;
	            }
	        }
	    }

	    return masCaro;
	}
	public List<Jugador> getHistoricoJugadores(String nombreEquipo) {
	    Set<Jugador> historico = new HashSet<>();
	    boolean encontrado = false;

	    Iterator<Equipo> itEquipos = equipos.iterator();
	    while (itEquipos.hasNext()) {
	        Equipo e = itEquipos.next();
	        if (e.getNombre().equalsIgnoreCase(nombreEquipo)) {
	            encontrado = true;
	            Iterator<Jugador> itJugadores = e.getColeccionJugadores().iterator();
	            while (itJugadores.hasNext()) {
	                Jugador j = itJugadores.next();
	                // Creo jugador simplificado solo con nombre y sueldo
	                Jugador jugadorSimple = new Jugador(0, j.getNombre(), "", 0, null, j.getSueldo());
	                historico.add(jugadorSimple);
	            }
	        }
	    }

	    if (!encontrado) {
	        System.out.println("No se encontró ningún equipo con nombre: " + nombreEquipo);


	   
	}
	    return new ArrayList<>(historico);

	}
}



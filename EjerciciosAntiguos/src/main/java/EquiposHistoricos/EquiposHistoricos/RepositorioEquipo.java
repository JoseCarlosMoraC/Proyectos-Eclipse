package EquiposHistoricos.EquiposHistoricos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/*
 * Clase que representa un repositorio de equipos históricos.
 * Permite almacenar múltiples equipos, buscar equipos,
 * añadir jugadores a equipos específicos y consultar información histórica.
 */
public class RepositorioEquipo {

	// Usamos un Set para almacenar equipos porque:
	// 1) No queremos equipos duplicados (mismo nombre y año).
	// 2) Permite búsquedas eficientes.
	private Set<Equipo> equipos;

	// Constructor: inicializamos el conjunto de equipos vacío.
	// Según el enunciado, partimos con un repositorio sin equipos.
	public RepositorioEquipo() {
		super();
		this.equipos = new HashSet<>();
	}

	// Getter para obtener el conjunto de equipos registrados.
	// Útil para mostrar, consultar o iterar sobre todos los equipos.
	public Set<Equipo> getEquipos() {
		return equipos;
	}

	// Setter para establecer el conjunto de equipos.
	// Permite cargar un conjunto ya existente o actualizar el repositorio.
	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	// hashCode y equals implementados para que dos repositorios sean
	// iguales si contienen los mismos equipos.
	// Esto facilita comparaciones o uso en colecciones que usan hashing.
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
		RepositorioEquipo other = (RepositorioEquipo) obj;
		return Objects.equals(equipos, other.equipos);
	}

	// Método toString para mostrar el contenido del repositorio de forma legible.
	@Override
	public String toString() {
		return "RepositorioEquipo [equipos=" + equipos + "]";
	}

	/*
	 * Método para buscar un equipo específico dado su nombre y año de inicio.
	 * Se usa un iterador para recorrer los equipos y detener la búsqueda
	 * cuando se encuentra el primero que coincida.
	 * Esto responde al requerimiento del enunciado de localizar equipos únicos
	 * por nombre y año.
	 */
	public Equipo getEquipo(String nombre, int año) {
		boolean encontrado = false;
		Equipo equipo = null;

		Iterator<Equipo> it = equipos.iterator();
		while (it.hasNext() && !encontrado) {
			Equipo e = it.next();

			// Comparamos ignorando mayúsculas porque el enunciado puede indicar
			// que la búsqueda no debe ser sensible a esto.
			if (e.getNombre().equalsIgnoreCase(nombre) && e.getAñoInicio() == año) {
				encontrado = true;
				equipo = e;
			}
		}

		// Devuelve el equipo encontrado o null si no existe.
		return equipo;
	}

	/*
	 * Método para añadir un jugador a un equipo específico,
	 * identificando el equipo por nombre y año.
	 * Según el enunciado, se deben validar dos condiciones antes de añadir:
	 * 1) Que el jugador no esté ya registrado en el equipo (evitar duplicados).
	 * 2) Que el equipo tenga presupuesto suficiente para pagar el sueldo del jugador.
	 * Si alguna validación falla, se lanza una excepción.
	 */
	public void addJugador(String nombre, int año, Jugador jugador) throws FutbolException {
		boolean encontrado = false;
		Equipo equipo = null;

		Iterator<Equipo> it = equipos.iterator();
		while (it.hasNext() && !encontrado) {
			Equipo e = it.next();

			if (e.getNombre().equalsIgnoreCase(nombre) && e.getAñoInicio() == año) {
			    encontrado = true;
			    equipo = e;

			    // Validación: jugador no puede estar ya en el equipo.
			    if (e.getJugadores().contains(jugador)) {
			        throw new FutbolException("El jugador ya está registrado");
			    }

			    // Validación: presupuesto suficiente para el sueldo del jugador.
			    if (e.getPresupuesto() < jugador.getSueldo()) {
			        throw new FutbolException("No hay presupuesto");
			    }

			    // Actualizamos el presupuesto restando el sueldo del jugador.
			    e.setPresupuesto(e.getPresupuesto() - jugador.getSueldo());

			    // Finalmente, añadimos el jugador al equipo.
			    e.getJugadores().add(jugador);
			}
		}
	}

	/*
	 * Método que devuelve el histórico de jugadores de un equipo dado su nombre.
	 * Según el enunciado, aunque el equipo pueda tener múltiples versiones
	 * con años distintos, queremos obtener todos los jugadores que hayan pasado
	 * por cualquiera de esas versiones (por eso recorremos todos los equipos
	 * con ese nombre).
	 * Se usa un conjunto para evitar duplicados en el histórico.
	 */
	public Set<Jugador> historicoJugadores(String nombre) {
	    Set<Jugador> historico = new HashSet<>();
	    Iterator<Equipo> it = equipos.iterator();
	    while (it.hasNext()) {
	        Equipo equipo = it.next();

	        // Si el nombre coincide (ignorando mayúsculas), añadimos sus jugadores.
	        if (equipo.getNombre().equalsIgnoreCase(nombre)) {
	            historico.addAll(equipo.getJugadores());
	        }
	    }

	    // Devolvemos el conjunto con todos los jugadores históricos del equipo.
	    return historico;
	}

	/*
	 * Método que obtiene el jugador con el sueldo más alto entre todos los equipos.
	 * Esto responde a la funcionalidad del enunciado que pide conocer cuál es
	 * el jugador "más caro" del repositorio.
	 * Se recorre cada equipo y dentro de cada uno cada jugador,
	 * actualizando el jugador más caro encontrado hasta el momento.
	 */
	public Jugador getJugadorMasCaro() {
	    Iterator<Equipo> itEquipos = equipos.iterator();
	    Jugador masCaro = null;

	    while (itEquipos.hasNext()) {
	        Equipo e = itEquipos.next();

	        Iterator<Jugador> itJugadores = e.getJugadores().iterator();

	        while (itJugadores.hasNext()) {
	            Jugador actual = itJugadores.next();

	            // Actualizamos masCaro si es el primero o si el sueldo es mayor.
	            if (masCaro == null || actual.getSueldo() > masCaro.getSueldo()) {
	                masCaro = actual;
	            }
	        }
	    }

	    // Retornamos el jugador más caro encontrado o null si no hay jugadores.
	    return masCaro;
	}
}

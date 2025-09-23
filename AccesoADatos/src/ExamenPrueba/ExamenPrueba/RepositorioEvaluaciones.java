package ExamenPrueba.ExamenPrueba;

import java.time.LocalDate;
import java.util.*;


public class RepositorioEvaluaciones {
	private Map<Evaluacion, HashSet<Observacion>> evaluaciones;

	public RepositorioEvaluaciones() {
		super();
		this.evaluaciones = new HashMap<Evaluacion, HashSet<Observacion>>();
	}

	// TODO: agregarObservacion
	public void agregarObservacion(LocalDate fecha, Alumno alumno, String asignatura, Observacion observacion)
			throws EvaluacionException {
		// TODO: agregar la observación a la evaluación correspondiente

		Evaluacion evaluacionExistente;
		evaluacionExistente = this.buscarEvaluacion(fecha, alumno, asignatura);

		if (evaluacionExistente == null) {
			evaluacionExistente = new Evaluacion(fecha, alumno, asignatura);
			evaluaciones.put(evaluacionExistente, new HashSet<>());
		}

		evaluaciones.get(evaluacionExistente).add(observacion);
	}

	// TODO: buscarEvaluacion
	public Evaluacion buscarEvaluacion(LocalDate fecha, Alumno alumno, String asignatura) throws EvaluacionException {
		// TODO: buscar una evaluación por alumno, fecha y asignatura
		boolean encontrado = false;
		Evaluacion evaluacion = null;

		Iterator<Evaluacion> it = evaluaciones.keySet().iterator();
		while (it.hasNext() && !encontrado) {
			Evaluacion e = it.next();

			if (e.getFecha().equals(fecha) && e.getAsignatura().equalsIgnoreCase(asignatura)
					&& e.getAlumno().equals(alumno)) {
				encontrado = true;
				evaluacion = e;
			}
		}

		return evaluacion;
	}

	// TODO: filtrarEvaluacionesPorAsignatura
	public List<Evaluacion> filtrarEvaluacionesPorAsignatura(String nombreAsignatura) {
		List<Evaluacion> evaluacionesFiltradas = new ArrayList<>();

		Iterator<Evaluacion> it = evaluaciones.keySet().iterator();
		while (it.hasNext()) {
			Evaluacion evaluacion = it.next();

			if (evaluacion.getAsignatura().equalsIgnoreCase(nombreAsignatura)) {
				evaluacionesFiltradas.add(evaluacion);
			}
		}

		return evaluacionesFiltradas;
	}

	// TODO: buscarObservacionDocente
	public boolean buscarObservacionDocente(LocalDate fecha, Alumno alumno, String asignatura, String docente) {
		// TODO: verificar si hay observaciones de ese docente
		Iterator<Evaluacion> it = evaluaciones.keySet().iterator();
		while (it.hasNext()) {
			Evaluacion evaluacion = it.next();
			if (evaluacion.getFecha().equals(fecha) && evaluacion.getAlumno().equals(alumno)
					&& evaluacion.getAsignatura().equalsIgnoreCase(asignatura)) {

				HashSet<Observacion> observaciones = evaluaciones.get(evaluacion);
				Iterator<Observacion> itObservacion = observaciones.iterator();
				while (itObservacion.hasNext()) {
					Observacion observacion = itObservacion.next();
					if (observacion.getDocente().equalsIgnoreCase(docente)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// TODO: estaCalificada
	public boolean estaCalificada(LocalDate fecha, Alumno alumno, String asignatura) {
		boolean esFinal = false;

		try {

			Evaluacion evaluacion = buscarEvaluacion(fecha, alumno, asignatura);

			if (evaluacion != null) {

				if (evaluacion.getAlumno().equals(alumno) && evaluacion.getFecha().equals(fecha)
						&& evaluacion.getAsignatura().equals(asignatura)) {

					esFinal = true;
					evaluacion.setEstado(EstadoEvaluacion.CALIFICADA);
				}

			}
		
			

		} catch (EvaluacionException e) {

			System.err.println("Error: " + e.getMessage());

		}

		return esFinal;

	}
}

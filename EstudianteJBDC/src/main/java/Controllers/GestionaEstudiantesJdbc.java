package Controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.Direccion;
import Models.Estudiante;
import Models.Score;
import Services.ServicioEstudiantesJdbc;

public class GestionaEstudiantesJdbc {

	private static final Logger logger = LogManager.getLogger(GestionaEstudiantesJdbc.class);

	public static void main(String[] args) {

		try {
			ServicioEstudiantesJdbc servicio = new ServicioEstudiantesJdbc();

			// CREAR ESTUDIANTES
			Estudiante e1 = new Estudiante(0, "Ana", 8.5);
			Estudiante e2 = new Estudiante(0, "Luis", 6.2);
			Estudiante e3 = new Estudiante(0, "Marta", 9.1);

			servicio.addEstudiante(e1);
			servicio.addEstudiante(e2);
			servicio.addEstudiante(e3);

			logger.info("Estudiantes creados correctamente");

			// CONSULTA: notaMedia < valor
			List<Estudiante> suspensos = servicio.estudiantesConNotaMenorQue(7);
			logger.info("Estudiantes con nota < 7: " + suspensos);

			// CONSULTA: estudiantes por ciudad
			List<Estudiante> sevilla = servicio.estudiantesPorCiudad("Sevilla");
			logger.info("Estudiantes que viven en Sevilla: " + sevilla);

			// CONSULTA: media de scores por estudiante
			logger.info("Media de scores por estudiante:");
			logger.info(servicio.mediaScoresPorEstudiante());

			// CONSULTA: número de scores por tipo
			logger.info("Número de puntuaciones por tipo:");
			logger.info(servicio.numeroScoresPorTipo());

		} catch (MiExcepcion e) {
			logger.error("ERROR CONTROLADO: " + e.getMessage());
		}
	}
}

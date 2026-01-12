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

            // ===== CREAR ESTUDIANTES =====
            Estudiante e1 = new Estudiante(0, "Ana", 8.5);
            Estudiante e2 = new Estudiante(0, "Luis", 6.2);
            Estudiante e3 = new Estudiante(0, "Marta", 9.1);

            servicio.addEstudiante(e1);
            servicio.addEstudiante(e2);
            servicio.addEstudiante(e3);

            logger.info("Estudiantes creados correctamente");

            // ===== CONSULTAS BÁSICAS =====
            List<Estudiante> suspensos = servicio.estudiantesConNotaMenorQue(7);
            logger.info("Estudiantes con nota < 7: " + suspensos);

            List<Estudiante> sevilla = servicio.estudiantesPorCiudad("Sevilla");
            logger.info("Estudiantes que viven en Sevilla: " + sevilla);

            logger.info("Media de scores por estudiante:");
            logger.info(servicio.mediaScoresPorEstudiante());

            logger.info("Número de puntuaciones por tipo:");
            logger.info(servicio.numeroScoresPorTipo());

            // ===== CONSULTAS AVANZADAS =====
            List<Estudiante> ordenNombreAsc = servicio.ordenarPorNombreAsc();
            logger.info("Estudiantes ordenados por nombre ascendente: " + ordenNombreAsc);

            List<Estudiante> ordenNotaDesc = servicio.ordenarPorNotaDesc();
            logger.info("Estudiantes ordenados por nota descendente: " + ordenNotaDesc);

            // Incrementar nota de un estudiante
            servicio.incrementarNotaEstudiante(e2.getId(), 1.5);
            logger.info("Nota incrementada para " + e2.getNombre());

            // Buscar estudiantes por nombre parcial
            List<Estudiante> busquedaNombre = servicio.buscarEstudiantesPorNombre("a");
            logger.info("Estudiantes con 'a' en el nombre: " + busquedaNombre);

            // ===== DIRECCIONES =====
            Direccion d1 = new Direccion(0, "Calle Falsa 123", "Sevilla", e1.getId());
            Direccion d2 = new Direccion(0, "Avenida Real 45", "Madrid", e2.getId());
            servicio.addDireccion(d1);
            servicio.addDireccion(d2);

            List<Direccion> direccionesE1 = servicio.listarDirecciones(e1.getId());
            logger.info("Direcciones de " + e1.getNombre() + ": " + direccionesE1);

            // ===== SCORES =====
            Score s1 = new Score(0, "Examen", 9.0, e1.getId());
            Score s2 = new Score(0, "Tarea", 7.5, e2.getId());
            servicio.addScore(s1);
            servicio.addScore(s2);

            List<Score> scoresE1 = servicio.listarScores(e1.getId());
            logger.info("Scores de " + e1.getNombre() + ": " + scoresE1);

        } catch (MiExcepcion e) {
            logger.error("ERROR CONTROLADO: " + e.getMessage());
        }
    }
}

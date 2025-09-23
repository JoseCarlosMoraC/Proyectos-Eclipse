package ExamenPrueba.ExamenPrueba;

import java.time.LocalDate;
import java.util.List;

public class GestorEvaluaciones {
    public static void main(String[] args) throws EvaluacionException {
        /////////////////////////////////////////////////////////////
        // ESTE BLOQUE NO SE DEBE DE MODIFICAR
        /////////////////////////////////////////////////////////////
        RepositorioEvaluaciones gestor = new RepositorioEvaluaciones();

        Alumno alumno1 = new Alumno("A001", "Lucía González");
        Alumno alumno2 = new Alumno("A002", "Carlos Pérez");

        Observacion obs1 = new Observacion("Buena comprensión del tema", "PROF_JUAN");
        Observacion obs2 = new Observacion("Necesita mejorar redacción", "PROF_LAURA");
        Observacion obs3 = new Observacion("Excelente participación", "PROF_JUAN");

        gestor.agregarObservacion(LocalDate.of(2025, 6, 10), alumno1, "Matemáticas", obs1);
        gestor.agregarObservacion(LocalDate.of(2025, 6, 10), alumno1, "Matemáticas", obs2);
        gestor.agregarObservacion(LocalDate.of(2025, 6, 11), alumno2, "Lengua", obs3);

        try {
            Evaluacion encontrada = gestor.buscarEvaluacion(LocalDate.of(2025, 6, 10), alumno1, "Matemáticas");
            System.out.println("Evaluación encontrada: " + encontrada);

            Evaluacion noEncontrada = gestor.buscarEvaluacion(LocalDate.of(2024, 6, 10), alumno1, "Matemáticas");
            System.out.println("Evaluación encontrada: " + noEncontrada);
        } catch (EvaluacionException e) {
            System.err.println("Error: " + e.getMessage());
        }

        boolean tieneObs = gestor.buscarObservacionDocente(LocalDate.of(2025, 6, 10), alumno1, "Matemáticas", "PROF_LAURA");
        System.out.println("¿Hay observación de PROF_LAURA?: " + tieneObs);

        boolean noTieneObs = gestor.buscarObservacionDocente(LocalDate.of(2025, 6, 10), alumno1, "Matemáticas", "PROF_MARIO");
        System.out.println("¿Hay observación de PROF_MARIO?: " + noTieneObs);

        List<Evaluacion> evaluacionesMatematicas = gestor.filtrarEvaluacionesPorAsignatura("Matemáticas");
        System.out.println("Evaluaciones de Matemáticas: " + evaluacionesMatematicas);

        List<Evaluacion> evaluacionesHistoria = gestor.filtrarEvaluacionesPorAsignatura("Historia");
        System.out.println("Evaluaciones de Historia: " + evaluacionesHistoria);

        boolean calificada = gestor.estaCalificada(LocalDate.of(2025, 6, 10), alumno1, "Matemáticas");
        System.out.println("¿Está calificada?: " + calificada);
        /////////////////////////////////////////////////////////////
        // FIN DEL BLOQUE NO MODIFICABLE
        /////////////////////////////////////////////////////////////

        /******************************************************************
         * A partir de este bloque puedes añadir tus posibles pruebas
         ***************************************************************** */
    }
}
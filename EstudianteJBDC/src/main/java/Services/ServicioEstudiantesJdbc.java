package Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.Estudiante;
import Repositories.RepositorioEstudiantesJdbc;

public class ServicioEstudiantesJdbc {

    private static final Logger logger = LogManager.getLogger(ServicioEstudiantesJdbc.class);

    private RepositorioEstudiantesJdbc repo;

    public ServicioEstudiantesJdbc() throws MiExcepcion {
        super();
        this.repo = new RepositorioEstudiantesJdbc();
    }

    public RepositorioEstudiantesJdbc getRepo() {
        return repo;
    }

    public void setRepo(RepositorioEstudiantesJdbc repo) {
        this.repo = repo;
    }

    // ===== CRUD =====
    public void addEstudiante(Estudiante e) {
        try {
            repo.agregarEstudiante(e);
        } catch (MiExcepcion ex) {
            logger.error("No se pudo añadir estudiante: " + ex.getMessage());
        }
    }

    // ===== CONSULTAS =====

    // notaMedia < valor
    public List<Estudiante> estudiantesConNotaMenorQue(double nota) {
        List<Estudiante> lista = new ArrayList<>();
        try {
            lista = repo.estudiantesNotaMenor(nota);
        } catch (MiExcepcion e) {
            logger.error("Error nota menor: " + e.getMessage());
        }
        return lista;
    }

    // estudiantes por ciudad
    public List<Estudiante> estudiantesPorCiudad(String ciudad) {
        List<Estudiante> lista = new ArrayList<>();
        try {
            lista = repo.estudiantesPorCiudad(ciudad);
        } catch (MiExcepcion e) {
            logger.error("Error ciudad: " + e.getMessage());
        }
        return lista;
    }

    // media de scores por estudiante
    public List<String> mediaScoresPorEstudiante() {
        List<String> lista = new ArrayList<>();
        try {
            lista = repo.mediaScoresPorEstudiante();
        } catch (MiExcepcion e) {
            logger.error("Error media scores: " + e.getMessage());
        }
        return lista;
    }

    // número de scores agrupados por tipo
    public List<String> numeroScoresPorTipo() {
        List<String> lista = new ArrayList<>();
        try {
            lista = repo.numeroScoresPorTipo();
        } catch (MiExcepcion e) {
            logger.error("Error numero scores: " + e.getMessage());
        }
        return lista;
    }
}

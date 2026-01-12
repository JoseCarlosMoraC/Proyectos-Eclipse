package Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.Direccion;
import Models.Estudiante;
import Models.Score;
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

    public void updateEstudiante(Estudiante e) {
        try {
            repo.actualizarEstudiante(e);
        } catch (MiExcepcion ex) {
            logger.error("No se pudo actualizar estudiante: " + ex.getMessage());
        }
    }

    public void deleteEstudiante(int id) {
        try {
            repo.eliminarEstudiante(id);
        } catch (MiExcepcion ex) {
            logger.error("No se pudo eliminar estudiante: " + ex.getMessage());
        }
    }

    public Estudiante getEstudiante(int id) {
        try {
            return repo.getEstudiantePorId(id);
        } catch (MiExcepcion ex) {
            logger.error("No se pudo obtener estudiante por ID: " + ex.getMessage());
        }
        return null;
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

    // ===== MÉTODOS EXTRA =====

    // Ordenar estudiantes por nombre ascendente
    public List<Estudiante> ordenarPorNombreAsc() {
        List<Estudiante> lista = new ArrayList<>();
        try {
            lista = repo.ordenarPorNombreAsc();
        } catch (MiExcepcion e) {
            logger.error("Error ordenando por nombre asc: " + e.getMessage());
        }
        return lista;
    }

    // Ordenar estudiantes por nota descendente
    public List<Estudiante> ordenarPorNotaDesc() {
        List<Estudiante> lista = new ArrayList<>();
        try {
            lista = repo.ordenarPorNotaDesc();
        } catch (MiExcepcion e) {
            logger.error("Error ordenando por nota desc: " + e.getMessage());
        }
        return lista;
    }

    // Incrementar nota de un estudiante
    public void incrementarNotaEstudiante(int id, double incremento) {
        try {
            repo.incrementarNota(id, incremento);
        } catch (MiExcepcion e) {
            logger.error("Error incrementando nota: " + e.getMessage());
        }
    }

    // Buscar estudiantes por nombre parcial
    public List<Estudiante> buscarEstudiantesPorNombre(String nombre) {
        List<Estudiante> lista = new ArrayList<>();
        try {
            lista = repo.buscarEstudiantesPorNombre(nombre);
        } catch (MiExcepcion e) {
            logger.error("Error buscando estudiantes por nombre: " + e.getMessage());
        }
        return lista;
    }

    // ===== MÉTODOS DIRECCION =====

    public void addDireccion(Direccion d) {
        try {
            repo.agregarDireccion(d);
        } catch (MiExcepcion e) {
            logger.error("Error añadiendo dirección: " + e.getMessage());
        }
    }

    public List<Direccion> listarDirecciones(int estudianteId) {
        List<Direccion> lista = new ArrayList<>();
        try {
            lista = repo.listarDirecciones(estudianteId);
        } catch (MiExcepcion e) {
            logger.error("Error listando direcciones: " + e.getMessage());
        }
        return lista;
    }

    // ===== MÉTODOS SCORE =====

    public void addScore(Score s) {
        try {
            repo.agregarScore(s);
        } catch (MiExcepcion e) {
            logger.error("Error añadiendo score: " + e.getMessage());
        }
    }

    public List<Score> listarScores(int estudianteId) {
        List<Score> lista = new ArrayList<>();
        try {
            lista = repo.listarScores(estudianteId);
        } catch (MiExcepcion e) {
            logger.error("Error listando scores: " + e.getMessage());
        }
        return lista;
    }
}

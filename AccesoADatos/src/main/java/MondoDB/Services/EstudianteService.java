package MondoDB.Services;

import java.util.List;

import com.mongodb.client.MongoDatabase;

import MondoDB.Models.Estudiante;
import MondoDB.Repositories.EstudianteRepositorio;

public class EstudianteService {
    private final EstudianteRepositorio repo;

    // El servicio recibe MongoDatabase y construye el repositorio
    public EstudianteService(MongoDatabase db) {
        this.repo = new EstudianteRepositorio(db);
    }

    // Guarda un estudiante en la base de datos
    public void save(Estudiante e) {
        // Aquí podrías añadir validaciones, reglas de negocio, etc.
        repo.save(e);
    }

    // Lista todos los estudiantes
    public List<Estudiante> read() {
        return repo.read();
    }

 
    public Estudiante buscarEstudiante(int id) {
        return repo.buscarEstudiante(id);
    }


    public void borrarEstudiante(int id) {
        repo.borrarEstudiante(id);
    }

  
    public List<Estudiante> getNotaInferiorA5() {
        return repo.getNotaInferiorA5();
    }


    public List<Estudiante> getCiudad(String city) {
        return repo.getCiudad(city);
    }


    public List<Estudiante> getScoreMedio(int id) {
        return repo.getScoreMedio(id);
    }
}
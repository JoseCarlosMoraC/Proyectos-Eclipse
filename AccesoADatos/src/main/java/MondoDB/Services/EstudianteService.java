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
	//TODO Agregar resto de operaciones del CRUD
	}

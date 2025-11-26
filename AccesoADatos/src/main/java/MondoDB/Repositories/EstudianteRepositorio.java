package MondoDB.Repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import MondoDB.Models.Direccion;
import MondoDB.Models.Estudiante;
import MondoDB.Models.Puntuacion;

public class EstudianteRepositorio {
	private static final String NOMBRE_COLECCION = "estudiantes";
	private final MongoCollection coleccion;
	private List<Estudiante> estudiantes;

	public EstudianteRepositorio(MongoDatabase db) {
		this.coleccion = db.getCollection(NOMBRE_COLECCION);
		this.estudiantes = this.read();
	}

	public void save(Estudiante e) {
		Document docAddress = new Document("city",e.getDireccion().getCity()).append("zip", e.getDireccion().getZip()).append("street", e.getDireccion().getStreet()).append("number", e.getDireccion().getNumber());
		List<Puntuacion> puntuaciones = new ArrayList<>();
		for (Puntuacion p : e.getPuntuacion()) {
			Document score = new Document("score", p.getScore());
			Document type = new Document("type", p.getType());
			puntuaciones.add(p);
		}
		
		Document doc = new Document("id", e.getId()).append("name", e.getName()).append("notaMedia", e.getNotaMedia())
				.append("aficiones", e.getAficiones()).append("address", docAddress).append("scores", e.getPuntuacion());
		coleccion.insertOne(doc);
	}

	public List<Estudiante> read() {
		List<Estudiante> estudiantes = new ArrayList<>();
		FindIterable<Document> documentos = coleccion.find();
		for (Document doc : documentos) {
			Estudiante e = new Estudiante();
			Document docAddress = (Document) doc.get("address");
			if(docAddress != null) {
				Direccion address = new Direccion(
					docAddress.getString("city"),
					docAddress.getInteger("zip", 0),
					docAddress.getString("street"),
					docAddress.getInteger("number", 0)
					
						);
						
				e.setDireccion(address);
			
				}
				
			
			e.setId(doc.getInteger("id", 0));
			e.setName(doc.getString("name"));
			e.setNotaMedia(doc.getDouble("notaMedia"));
			List<String> aficiones = doc.getList("aficiones", String.class);
			e.setAficiones(aficiones != null ? aficiones : new ArrayList<>());
			List<Puntuacion> scores = doc.getList("scores", Puntuacion.class);
			e.setPuntuacion(scores != null ? scores : new ArrayList<>());
			
			
			estudiantes.add(e);
		}
		return estudiantes;
	}
	public Estudiante buscarEstudiante(int id) {
		boolean encontrado = false;
		Estudiante estudiante = null;
		
		Iterator<Estudiante> it = estudiantes.iterator();
		while(it.hasNext()&& !encontrado) {
			Estudiante e = it.next();
			if(e.getId() != id) {
				encontrado = true;
				estudiante = e;
			}
		}
		
		return estudiante;
			
	}

	public void borrarEstudiante(int id) {
		boolean encontrado = false;
		Estudiante estudiante = null;
		
		Iterator<Estudiante> it = estudiantes.iterator();
		while(it.hasNext()&& !encontrado) {
			Estudiante e = it.next();
			if(e.getId() == id) {
				encontrado = true;
				estudiante = e;
				estudiantes.remove(id);
			}
		}
	
	}
	 public List<Estudiante> getNotaInferiorA5(){
	        List<Estudiante> notaInferior = new ArrayList<>();
	        boolean encontrado = false;
			Estudiante estudiante = null;
			
			Iterator<Estudiante> it = estudiantes.iterator();
			while(it.hasNext()&& !encontrado) {
				Estudiante e = it.next();
				if(e.getNotaMedia()<5) {
					encontrado = true;
					estudiante = e;
					notaInferior.add(e);
				}
			}
			return notaInferior;
	 }
	 public List<Estudiante> getCiudad(String city) {
		 List<Estudiante> estudiantesCiudad = new ArrayList<>();
	        boolean encontrado = false;
			Estudiante estudiante = null;
			
			Iterator<Estudiante> it = estudiantes.iterator();
			while(it.hasNext()&& !encontrado) {
				Estudiante e = it.next();
				if (e.getDireccion().getCity().equalsIgnoreCase(city)) {
					encontrado = true;
					estudiante = e;
					estudiantesCiudad.add(e);
				}
			}
			return estudiantesCiudad;
	 }
	 
	 public List<Estudiante> getScoreMedio(int id){
		 List<Estudiante> scoreMedio = new ArrayList<>();
	        boolean encontrado = false;
			Estudiante estudiante = null;
			
			Iterator<Estudiante> it = estudiantes.iterator();
			while(it.hasNext()&& !encontrado) {
				Estudiante e = it.next();
				if (e.getId() == id) {
					encontrado = true;
					estudiante = e;
					scoreMedio.add(e);
				}
				
			}
			return scoreMedio;
	 }
	 }
	 

		
		
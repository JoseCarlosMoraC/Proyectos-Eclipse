package Service;

import java.util.List;

import com.mongodb.client.MongoDatabase;

import Models.Evento;
import Models.Usuario;
import Repositories.AppRepository;


public class AppService {
	private final AppRepository repo;

	public AppService(MongoDatabase db) {
		super();
		this.repo = new AppRepository(db);
	}
	
	public void save(Usuario u) {
		repo.save(u);
	}

	public List<Usuario> read() {
		return repo.read();
	}
	public List<Usuario> ordenDescendenteYAscendente() {
		return repo.ordenDescendenteYAscendente();
	}
	public void crearUsuario(Usuario u) {
		repo.crearUsuario(u);
	}
	public Usuario getUsuarioPorId(String id) {
		return repo.getUsuarioPorId(id);
	}
	public void update(Usuario u) {
		repo.update(u);
	}
	public void actualizarAESP() {
		repo.actualizarAESP("ESP");;
	}
	/*public void eliminarPlanActivo() {
		repo.eliminarPlanActivo();
	}*/
	public void agregarUsuario(String id) {
		Evento nuevoEvento = new Evento();
		nuevoEvento.setId_evento("ev_121");
		nuevoEvento.setTag("GPS");
		nuevoEvento.setMensaje("Señal GPS ADQUIRIDA");
		nuevoEvento.setTimestamp("2024-02-12T08:21:00Z");;

		repo.agregarUsuario(id, nuevoEvento);
			}
	}


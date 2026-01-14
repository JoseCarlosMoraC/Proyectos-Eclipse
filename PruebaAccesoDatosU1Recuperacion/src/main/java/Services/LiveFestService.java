package Services;


import java.util.List;

import Exception.GrupoMusicaException;

import Models.Concierto;
import Models.Grupo;
import Repositories.RegistroFestival;

public class LiveFestService {
	private RegistroFestival registroFestival;

	public LiveFestService() {
		this.registroFestival = new RegistroFestival();
	}
	public LiveFestService(RegistroFestival registroFestival) {
		super();
		this.registroFestival = registroFestival;
		
	}

	

	public RegistroFestival getRegistroFestival() {
		return registroFestival;
	}

	public void setRegistroFestival(RegistroFestival registroFestival) {
		this.registroFestival = registroFestival;
	}
	 public void agregarGrupo(Grupo grupo) throws GrupoMusicaException {
	        registroFestival.agregarGrupo(grupo);
	    }
	 public List<Grupo> getGruposPorNombre(String nombre) {
	        return registroFestival.getGruposPorNombre(nombre);
	    }
	 public void agregarConcierto(Concierto concierto) throws GrupoMusicaException {
	        registroFestival.agregarConcierto(concierto);
	    }
	 public List<Grupo> getConciertoPorNombreGrupo(String nombre) throws GrupoMusicaException {
		return null;
		 
	 }
}

package Repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Exception.GrupoMusicaException;
import Models.Concierto;
import Models.Grupo;


public class RegistroFestival {
	private Set<Grupo> listaGrupos;
    private Set<Concierto> listaConciertos;
    
    
	public RegistroFestival() {
		super();
		this.listaGrupos = new HashSet<Grupo>();
		this.listaConciertos = new HashSet<Concierto>();
	}

	public RegistroFestival(Set<Grupo> listaGrupos, Set<Concierto> listaConciertos) {
	    super();
	    this.listaGrupos = new HashSet<>(listaGrupos);
	    this.listaConciertos = new HashSet<>(listaConciertos);
	}

	public Set<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(Set<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public Set<Concierto> getListaConciertos() {
		return listaConciertos;
	}

	public void setListaConciertos(Set<Concierto> listaConciertos) {
		this.listaConciertos = listaConciertos;
	}
    
	 public void agregarGrupo(Grupo grupo) throws GrupoMusicaException {
	        boolean existe = false;
	        
	        Iterator<Grupo> it = listaGrupos.iterator();
	        while (it.hasNext() && !existe) {
	            Grupo g = it.next();
	            if (g.getCodigo().equalsIgnoreCase(grupo.getCodigo())) {
	                existe = true;
	            }
	        }
	        
	        if (existe) {
	            throw new GrupoMusicaException("El grupo ya existe con codigo: " + grupo.getCodigo());
	        }
	        listaGrupos.add(grupo);
	    }
	
	 public List<Grupo> getGruposPorNombre(String nombre) {
	        List<Grupo> gruposFiltrados = new ArrayList<>();
	        
	        Iterator<Grupo> it = listaGrupos.iterator();
	        while (it.hasNext()) {
	            Grupo g = it.next();
	            if (g.getNombre().equalsIgnoreCase(nombre)) {
	            	gruposFiltrados.add(g);
	            }
	        }
	        
	        return gruposFiltrados;
	    }
	  public void agregarConcierto(Concierto concierto) throws GrupoMusicaException {
	        boolean existe = false;
	        
	        Iterator<Concierto> it = listaConciertos.iterator();
	        while (it.hasNext() && !existe) {
	            Concierto c = it.next();
	            if (c.getId() == concierto.getId()) {
	                existe = true;
	            }
	        }
	        
	        if (existe) {
	            throw new GrupoMusicaException("El concierto ya existe con ID: " + concierto.getId());
	        }
	        
	        listaConciertos.add(concierto);
	    }
	  public List<Grupo> getConciertoPorNombreGrupo(String nombre) throws GrupoMusicaException {
		    List<Grupo> conciertosPorNombre = new ArrayList<>();
		    for (Concierto concierto : listaConciertos) {
		        for (Grupo g : concierto.getGrupo()) {
		            if (g.getNombre().equalsIgnoreCase(nombre)) {
		                conciertosPorNombre.add(g);
		            }
		        }
		    }
		    if (conciertosPorNombre.isEmpty()) {
		        throw new GrupoMusicaException("No se encuentra ningún grupo asociado a ese nombre");
		    }
		    return conciertosPorNombre;
		}

}

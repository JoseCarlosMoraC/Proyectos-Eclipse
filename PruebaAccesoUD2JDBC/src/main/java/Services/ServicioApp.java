package Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.PlanActivo;

import Models.Usuario;
import Repositories.RepositorioPreferencias;
import Repositories.RepositorioUsuario;



public class ServicioApp {
	   private static final Logger logger = LogManager.getLogger(ServicioApp.class);
	    private RepositorioUsuario repoUsuario;
	    private RepositorioPreferencias repoPreferencias;
	    
	    
		public ServicioApp() throws MiExcepcion {
			super();
			this.repoUsuario = new RepositorioUsuario();
			this.repoPreferencias = new RepositorioPreferencias();
		}

	
		public RepositorioUsuario getRepoUsuario() {
			return repoUsuario;
		}

		public void setRepoUsuario(RepositorioUsuario repoUsuario) {
			this.repoUsuario = repoUsuario;
		}

		public RepositorioPreferencias getRepoPreferencias() {
			return repoPreferencias;
		}

		public void setRepoPreferencias(RepositorioPreferencias repoPreferencias) {
			this.repoPreferencias = repoPreferencias;
		}
	    
		public List<Usuario> buscarJugadorPorId(String id) throws MiExcepcion {
	        List<Usuario> lista = new ArrayList<>();
	        for(Usuario u : repoUsuario.buscarPorId(id)) {
	            if(u.getId() == id) {
	                lista.add(u);
	            }
	        }
	        return lista;
	    }
		public List<Usuario> buscarPorPlan(PlanActivo plan) {
	        List<Usuario> lista = new ArrayList<>();
	        for(Usuario u : repoUsuario.getListaUsuario()) {
	            if(u.getPlan_activo() == plan) {
	                lista.add(u);
	            }
	        }
	        return lista;
	    }
		
		}


package Repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Config.MySqlConector;
import Exceptions.MiExcepcion;
import Models.Preferencias;
import Models.Usuario;



public class RepositorioPreferencias {
    private static final Logger logger = LogManager.getLogger(RepositorioPreferencias.class);
    private MySqlConector conector;
    private List<Preferencias> listaPreferencias;
    
	public RepositorioPreferencias(MySqlConector conector, List<Preferencias> listaPreferencias) throws MiExcepcion {
		super();
		this.conector = new MySqlConector();
		this.listaPreferencias = cargar();
	}

	public RepositorioPreferencias() {
		// TODO Auto-generated constructor stub
	}

	public MySqlConector getConector() {
		return conector;
	}

	public void setConector(MySqlConector conector) {
		this.conector = conector;
	}

	public List<Preferencias> getListaPreferencias() {
		return listaPreferencias;
	}

	public void setListaPreferencias(List<Preferencias> listaPreferencias) {
		this.listaPreferencias = listaPreferencias;
	}
	
	private List<Preferencias> cargar()throws MiExcepcion {
		List<Preferencias> lista = new ArrayList<>();
		 try {
	            Connection conexion = conector.getConnect(); 
	            Statement sentencia = conexion.createStatement(); 
	            String sql = "SELECT * FROM JCMoraBD.preferencias"; 
	            ResultSet resultado = sentencia.executeQuery(sql); 
	            while(resultado.next()) {
	            	Preferencias preferencias = new Preferencias();
	            	String usuarioId = resultado.getString("usuario_id");
	            	Usuario buscarid = new Usuario();
	            	buscarid.setId(usuarioId);
	                preferencias.setUsuarioId(buscarid);
	                preferencias.setTema_oscuro(resultado.getBoolean("tema_oscuro"));
	                preferencias.setIdioma(resultado.getString("idioma"));
	                preferencias.setNotificaciones_push(resultado.getBoolean("notificaciones_push"));
	                preferencias.setLimite_datos_moviles(resultado.getBoolean("limite_datos_moviles"));
	            }
	        } catch (SQLException e) {
	            logger.error("Error al cargar preferencias: " + e.getMessage());
	        }
	        return lista;
	    }
}

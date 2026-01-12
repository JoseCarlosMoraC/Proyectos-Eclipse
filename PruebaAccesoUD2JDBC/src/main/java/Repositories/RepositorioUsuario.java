package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Config.MySqlConector;
import Exceptions.AppException;
import Exceptions.MiExcepcion;
import Models.PlanActivo;

import Models.Usuario;

public class RepositorioUsuario {
	private static final Logger logger = LogManager.getLogger(RepositorioUsuario.class);
	private MySqlConector conector;
	private List<Usuario> listaUsuario;
	
	public RepositorioUsuario() throws MiExcepcion {
		super();
		this.conector = new MySqlConector();
		this.listaUsuario = cargar();
	}


	public MySqlConector getConector() {
		return conector;
	}

	public void setConector(MySqlConector conector) {
		this.conector = conector;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	private List<Usuario> cargar() throws MiExcepcion {
		List<Usuario> lista = new ArrayList<>();
		try {
			Connection conexion = conector.getConnect();
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM JCMoraBD.usuarios";
			ResultSet resultado = sentencia.executeQuery(sql);
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getString("id"));
				usuario.setUsername(resultado.getString("username"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setPlan_activo(PlanActivo.valueOf(resultado.getString("plan_activo")));
				usuario.setDispositivo(resultado.getString("dispositivo"));
				lista.add(usuario);
			}
		} catch (SQLException e) {
			logger.error("Error al cargar usuarios: " + e.getMessage());
		}
		return lista;
	}

	 public List<Usuario> buscarPorId(String id) throws MiExcepcion {
	        List<Usuario> lista = new ArrayList<>();
	        String sql = "SELECT * FROM JCMoraBD.usuarios WHERE id LIKE ?";
	        try {
	            Connection conexion = conector.getConnect();
	            PreparedStatement ps = conexion.prepareStatement(sql);
	            ps.setString(1, id);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()) {
	            	Usuario u = new Usuario();
	                u.setId(rs.getString("id"));
	                u.setUsername(rs.getString("username"));
	                u.setEmail(rs.getString("email"));
	                u.setPlan_activo(PlanActivo.valueOf(rs.getString("plan_activo")));
	                u.setDispositivo(rs.getString("dispositivo"));
	                lista.add(u);
	            }
	        } catch(SQLException e) {
	            throw new MiExcepcion("Error al buscar jugador por id: " + e.getMessage());
	        }
	        return lista;
	    }
	 public List<Usuario> buscarPorPlan(PlanActivo planActivo) throws MiExcepcion {
	        List<Usuario> lista = new ArrayList<>();
	        String sql = "SELECT * FROM JCMoraBD.usuarios WHERE plan_activo = ? order by email asc";
	        try {
	            Connection conexion = conector.getConnect();
	            PreparedStatement ps = conexion.prepareStatement(sql);
	            ps.setString(1, planActivo.name());
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()) {
	                Usuario u = new Usuario();
	                u.setEmail(rs.getString("email"));;
	                u.setDispositivo(rs.getString("dispositivo"));
	                u.setPlan_activo(PlanActivo.valueOf(rs.getString("plan_activo")));
	                lista.add(u);
	            }
	        } catch(SQLException e) {
	            throw new MiExcepcion("Error al buscar usuarios por plan activo: " + e.getMessage());
	        }
	        return lista;
	 }

	 public void agregarUsuario(Usuario usuario) throws MiExcepcion{
	        String agregar = "INSERT INTO JCMoraBD.usuarios (username, email, preferencias) VALUES (?,?,?)";
	        try {
	            Connection conexion = conector.getConnect(); 
	            PreparedStatement ps = conexion.prepareStatement(agregar, Statement.RETURN_GENERATED_KEYS);  


	            ps.setString(1, usuario.getUsername());
	            ps.setString(2, usuario.getEmail());
	            ps.setString(3, usuario.getDispositivo());

	            ps.executeUpdate();

	            ResultSet rs = ps.getGeneratedKeys();
	            if(rs.next()) {
	                usuario.setId(rs.getString(1)); 

	            logger.info("Usuario añadido: "+ usuario.getUsername()); 
	            this.listaUsuario.add(usuario);
	        } 
	        }catch (SQLException e) {
	            throw new MiExcepcion("Error al añadir usuario: " + e.getMessage()); 
	        }
	        }
	 public int contarUsuarios() throws MiExcepcion {
	        int total = 0;
	        String sql = "SELECT COUNT(*) FROM JCMoraBD.usuarios WHERE plan_activo= ?";
	        try {
	            Connection conexion = conector.getConnect();
	            PreparedStatement ps = conexion.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            if(rs.next()) total = rs.getInt(1);
	        } catch(SQLException e) {
	            throw new MiExcepcion("Error al contar usuarios: " + e.getMessage());
	        }
	        return total;
	    }
	  public void eliminarUsuario(String preferencias) throws MiExcepcion {
	        String sql = "DELETE FROM JCMoraBD.usuarios WHERE id=? ";
	        try {
	            Connection conexion = conector.getConnect();
	            PreparedStatement ps = conexion.prepareStatement(sql);
	            ps.setString(1, preferencias);
	            ps.executeUpdate();
	            logger.info("Usuario eliminado con ID: " + preferencias);
	        } catch (SQLException ex) {
	            throw new MiExcepcion("Error al eliminar usuario: " + ex.getMessage());
	        }
	    }
	 }




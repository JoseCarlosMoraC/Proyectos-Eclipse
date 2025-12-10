package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

	private String id;
	private String username;
	private String email;
	private PlanActivo plan_activo; // Enum
	private String dispositivo;
	private Preferencias preferencias;
	private List<Evento> logs_eventos;

	public Usuario() {
		super();
		this.logs_eventos = new ArrayList<Evento>();
	}

	public Usuario(String id, String username, String email, PlanActivo plan_activo, String dispositivo,
			Preferencias preferencias, List<Evento> logs_eventos) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.plan_activo = plan_activo;
		this.dispositivo = dispositivo;
		this.preferencias = preferencias;
		this.logs_eventos = logs_eventos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	// Getters y Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PlanActivo getPlan_activo() {
		return plan_activo;
	}

	public void setPlan_activo(PlanActivo plan_activo) {
		this.plan_activo = plan_activo;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Preferencias getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(Preferencias preferencias) {
		this.preferencias = preferencias;
	}

	public List<Evento> getLogs_eventos() {
		return logs_eventos;
	}

	public void setLogs_eventos(List<Evento> logs_eventos) {
		this.logs_eventos = logs_eventos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", plan_activo=" + plan_activo
				+ ", dispositivo=" + dispositivo + ", preferencias=" + preferencias + ", logs_eventos=" + logs_eventos
				+ "]";
	}
	
}

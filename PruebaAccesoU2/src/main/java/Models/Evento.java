package Models;

import java.util.Objects;

public class Evento {

	private String id_evento;
	private String tag;
	private String mensaje;
	private String timestamp;

	public Evento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Evento(String id_evento, String tag, String mensaje, String timestamp) {
		super();
		this.id_evento = id_evento;
		this.tag = tag;
		this.mensaje = mensaje;
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_evento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(id_evento, other.id_evento);
	}

	// Getters y Setters
	public String getId_evento() {
		return id_evento;
	}

	public void setId_evento(String id_evento) {
		this.id_evento = id_evento;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}

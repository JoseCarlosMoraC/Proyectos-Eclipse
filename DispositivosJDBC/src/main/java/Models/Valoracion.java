package Models;

public class Valoracion {

    private int id;
    private String usuario;
    private String comentario;
    private int puntuacion;
    private int dispositivoId;

    public Valoracion() {
    	
    }

	public Valoracion(int id, String usuario, String comentario, int puntuacion, int dispositivoId) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.comentario = comentario;
		this.puntuacion = puntuacion;
		this.dispositivoId = dispositivoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getDispositivoId() {
		return dispositivoId;
	}

	public void setDispositivoId(int dispositivoId) {
		this.dispositivoId = dispositivoId;
	}

	@Override
	public String toString() {
		return "Valoracion [id=" + id + ", usuario=" + usuario + ", comentario=" + comentario + ", puntuacion="
				+ puntuacion + ", dispositivoId=" + dispositivoId + "]";
	}

}

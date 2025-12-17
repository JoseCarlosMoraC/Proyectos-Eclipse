package Models;

public class Score {

	private int id;
	private String tipo;
	private double puntuacion;
	private int estudianteId;

	public Score() {
	}

	public Score(int id, String tipo, double puntuacion, int estudianteId) {
		this.id = id;
		this.tipo = tipo;
		this.puntuacion = puntuacion;
		this.estudianteId = estudianteId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(int estudianteId) {
		this.estudianteId = estudianteId;
	}
}

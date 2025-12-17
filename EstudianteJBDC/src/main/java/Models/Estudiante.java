package Models;

public class Estudiante {

	private int id;
	private String nombre;
	private String email;
	private double notaMedia;

	public Estudiante() {
	}

	public Estudiante(int id, String nombre, String email, double notaMedia) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.notaMedia = notaMedia;
	}

	public Estudiante(int i, String string, double d) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(double notaMedia) {
		this.notaMedia = notaMedia;
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", email=" + email + ", notaMedia=" + notaMedia + "]";
	}
}

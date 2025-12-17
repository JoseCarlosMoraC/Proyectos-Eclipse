package Models;

public class Direccion {

	private int id;
	private String calle;
	private String ciudad;
	private int estudianteId;

	public Direccion() {
	}

	public Direccion(int id, String calle, String ciudad, int estudianteId) {
		this.id = id;
		this.calle = calle;
		this.ciudad = ciudad;
		this.estudianteId = estudianteId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(int estudianteId) {
		this.estudianteId = estudianteId;
	}
}

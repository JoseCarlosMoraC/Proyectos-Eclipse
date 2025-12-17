package Models;

public class Especificacion {

    private int id;
    private String descripcion;
    private int dispositivoId;

    public Especificacion() {
    	
    }

	public Especificacion(int id, String descripcion, int dispositivoId) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.dispositivoId = dispositivoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDispositivoId() {
		return dispositivoId;
	}

	public void setDispositivoId(int dispositivoId) {
		this.dispositivoId = dispositivoId;
	}

	@Override
	public String toString() {
		return "Especificacion [id=" + id + ", descripcion=" + descripcion + ", dispositivoId=" + dispositivoId + "]";
	}

   
}

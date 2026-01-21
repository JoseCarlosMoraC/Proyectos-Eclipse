package ModelsEmpleado;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "acta")
public class Acta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idActa;
	private String contenido;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idReunion")
	private Reunion reunion;
	
	public Acta(int idActa, String contenido, Reunion reunion) {
		super();
		this.idActa = idActa;
		this.contenido = contenido;
		this.reunion = reunion;
	}

	public int getIdActa() {
		return idActa;
	}

	public void setIdActa(int idActa) {
		this.idActa = idActa;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	public Acta() {
		super();
	}

	@Override
	public String toString() {
		return "Acta [idActa=" + idActa + ", contenido=" + contenido + ", reunion=" + reunion + "]";
	}
	
	
}

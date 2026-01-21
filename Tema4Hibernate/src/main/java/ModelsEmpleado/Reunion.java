package ModelsEmpleado;

import ModelsEmpleado.Sala;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reunion")
public class Reunion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Primary key

	private LocalDateTime fecha;
	private String asunto;

	@ManyToOne // No cascade para evitar errores si Sala ya existe
	@JoinColumn(name = "idSala")
	private Sala sala;

	@ManyToMany
	@JoinTable(name = "reunion_empleado", joinColumns = @JoinColumn(name = "reunion_id"), inverseJoinColumns = @JoinColumn(name = "empleado_id"))
	private List<Empleado> asistentes = new ArrayList<>();

	@OneToOne(mappedBy = "reunion", cascade = CascadeType.ALL)
	private Acta acta;

	// Constructores
	public Reunion() {
	}

	



	public Reunion(Long id, LocalDateTime fecha, String asunto, Sala sala, List<Empleado> asistentes, Acta acta) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.asunto = asunto;
		this.sala = sala;
		this.asistentes = asistentes;
		this.acta = acta;
	}





	public Reunion(LocalDateTime fecha, String asunto, Sala sala) {
		super();
		this.fecha = fecha;
		this.asunto = asunto;
		this.sala = sala;
	}





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Empleado> getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(List<Empleado> asistentes) {
		this.asistentes = asistentes;
	}

	public void addAsistente(Empleado empleado) {
		this.asistentes.add(empleado);
		if (!empleado.getReuniones().contains(this)) {
			empleado.getReuniones().add(this);
		}
	}

	public Acta getActa() {
		return acta;
	}

	public void setActa(Acta acta) {
		this.acta = acta;
		if (acta.getReunion() != this) {
			acta.setReunion(this);
		}
	}

	@Override
	public String toString() {
	    return "Reunion [id=" + id + ", asunto=" + asunto + ", fecha=" + fecha + ", salaId=" + (sala != null ? sala.getIdSala() : null) + "]";
	}
}

package Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "jugadores")
public class Jugador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(nullable = false, length = 100)
	private String apellidos;

	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;

	@Column
	private Integer dorsal;

	@Column(length = 30)
	private String posicion;

	@Column(name = "estado_fisico", length = 50)
	private String estadoFisico;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipo_id")
	private Equipo equipo;

	
	@ManyToMany
	@JoinTable(name = "asistencia_entrenamiento", joinColumns = @JoinColumn(name = "jugador_id"), inverseJoinColumns = @JoinColumn(name = "entrenamiento_id"))
	private Set<Entrenamiento> entrenamientos = new HashSet<>();


	public Jugador() {
	}

	public Jugador(String nombre, String apellidos, LocalDate fechaNacimiento, Integer dorsal, String posicion) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.dorsal = dorsal;
		this.posicion = posicion;
		this.estadoFisico = "Disponible";
	}

	public void addEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamientos.add(entrenamiento);
		entrenamiento.getJugadores().add(this);
	}

	public void removeEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamientos.remove(entrenamiento);
		entrenamiento.getJugadores().remove(this);
	}

	public int calcularEdad() {
		if (fechaNacimiento != null) {
			return LocalDate.now().getYear() - fechaNacimiento.getYear();
		}
		return 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getDorsal() {
		return dorsal;
	}

	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getEstadoFisico() {
		return estadoFisico;
	}

	public void setEstadoFisico(String estadoFisico) {
		this.estadoFisico = estadoFisico;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Set<Entrenamiento> getEntrenamientos() {
		return entrenamientos;
	}

	public void setEntrenamientos(Set<Entrenamiento> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}

	@Override
	public String toString() {
		return "Jugador{" + "id=" + id + ", nombre='" + nombre + '\'' + ", apellidos='" + apellidos + '\'' + ", dorsal="
				+ dorsal + ", posicion='" + posicion + '\'' + ", edad=" + calcularEdad() + '}';
	}
}
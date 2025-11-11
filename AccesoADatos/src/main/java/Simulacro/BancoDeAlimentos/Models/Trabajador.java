	package Simulacro.BancoDeAlimentos.Models;
	
	import java.time.LocalDate;
	import java.util.Objects;
	
	public class Trabajador {
	private String nombre;
	private String dni;
	private LocalDate fecha;
	private Tipo tipo;
	private String idCentro;
	private CentroLogistico centrologistico;
	
	public Trabajador(String nombre, String dni, LocalDate fecha, Tipo tipo) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.fecha = fecha;
		this.tipo = tipo;
		this.idCentro = centrologistico.getId();
	}
	
	public Trabajador() {
		// TODO Auto-generated constructor stub
	}
	

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}

	public CentroLogistico getCentrologistico() {
		return centrologistico;
	}

	public void setCentrologistico(CentroLogistico centrologistico) {
		this.centrologistico = centrologistico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trabajador other = (Trabajador) obj;
		return Objects.equals(dni, other.dni);
	}
	
	@Override
	public String toString() {
		return "Trabajador [nombre=" + nombre + ", dni=" + dni + ", fecha=" + fecha + ", tipo=" + tipo + ", idCentro="
				+ idCentro + "]";
	}
	
	
	}

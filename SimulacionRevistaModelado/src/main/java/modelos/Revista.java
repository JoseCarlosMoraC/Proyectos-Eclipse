package modelos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "revista")
public class Revista {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRevista;
	private String nombreRevista;
	private int numeroRevista;
	private LocalDate fecha;
	private int unidadesImpresas;
	
	@OneToMany(mappedBy= "revista", cascade = CascadeType.ALL)
	private Set<Articulo> articulos;
	
	
	public Revista() {
		super();
		this.articulos = new HashSet<>();
	}

	public Revista(String nombreRevista, int numeroRevista, LocalDate fecha) {
		super();
		this.nombreRevista = nombreRevista;
		this.numeroRevista = numeroRevista;
		this.fecha = fecha;
	}

	public Revista(String nombreRevista, int numeroRevista, LocalDate fecha, int unidadesImpresas) {
		super();
		this.nombreRevista = nombreRevista;
		this.numeroRevista = numeroRevista;
		this.fecha = fecha;
		this.unidadesImpresas = unidadesImpresas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, idRevista, nombreRevista, numeroRevista, unidadesImpresas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Revista other = (Revista) obj;
		return Objects.equals(fecha, other.fecha) && idRevista == other.idRevista
				&& Objects.equals(nombreRevista, other.nombreRevista) && numeroRevista == other.numeroRevista
				&& unidadesImpresas == other.unidadesImpresas;
	}

	public int getIdRevista() {
		return idRevista;
	}

	public void setIdRevista(int idRevista) {
		this.idRevista = idRevista;
	}

	public String getNombreRevista() {
		return nombreRevista;
	}

	public void setNombreRevista(String nombreRevista) {
		this.nombreRevista = nombreRevista;
	}

	public int getNumeroRevista() {
		return numeroRevista;
	}

	public void setNumeroRevista(int numeroRevista) {
		this.numeroRevista = numeroRevista;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getUnidadesImpresas() {
		return unidadesImpresas;
	}

	public void setUnidadesImpresas(int unidadesImpresas) {
		this.unidadesImpresas = unidadesImpresas;
	}
	

	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	public void addArticulo(Articulo a) {

		this.articulos.add(a);

		a.setRevista(this);

	}


	
}

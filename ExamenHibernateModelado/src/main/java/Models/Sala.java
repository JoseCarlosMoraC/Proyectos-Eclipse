package Models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "sala")
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSala;

	private String nombre;
	private int capacidad;

	@ManyToMany(cascade = CascadeType.MERGE)
	private Set<Pelicula> peliculas;
	
	@ManyToOne(cascade  = CascadeType.MERGE)
	@JoinColumn(name = "id_cine")
	private Cine cine;
	
	
	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}

	public Sala() {
		super();
		this.peliculas = new HashSet<>();
	}

	public Sala(String nombre, int capacidad) {
		super();
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.peliculas = new HashSet<>();
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "Sala [idSala=" + idSala + ", nombre=" + nombre + ", capacidad=" + capacidad + "]";
	}
	public void addPelicula(Pelicula a) { 
	    this.peliculas.add(a);
	    
	    if (!a.getSalas().contains(this)) {
	        a.getSalas().add(this);
	    }
	}
	public void removeAutor(Pelicula a) {

		this.peliculas.remove(a);

		if (a.getSalas().contains(this)) {
			a.getSalas().remove(this);

		}

	}

	
}

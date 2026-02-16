package Models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "cine")
public class Cine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCine;
	private String nombre;
	private String ubicacion;
	
	@OneToMany(mappedBy= "cine", cascade = CascadeType.ALL)
	private Set<Sala> salas;
	

	public Cine() {
		super();
		this.salas = new HashSet<>();
	}

	public Cine(String nombre, String ubicacion) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.salas = new HashSet<>();
	}

	public int getIdCine() {
		return idCine;
	}

	public void setIdCine(int idCine) {
		this.idCine = idCine;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Cine [idCine=" + idCine + ", nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
	}
	public void addSala(Sala a) {

		this.salas.add(a);

		a.setCine(this);

	}

}

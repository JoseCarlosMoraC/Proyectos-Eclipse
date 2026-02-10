package modelos;

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
@Table(name = "articulo")
public class Articulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idArticulo;
	private String titulo;
	private int numPaginaInicio;
	private int numPaginaFin;
	
	@ManyToOne(cascade  = CascadeType.MERGE)
	@JoinColumn(name = "id_revista")
	private Revista revista;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	private Set<Autor> autores;
	

	public Articulo() {
		super();
		this.autores = new HashSet<>();;
		
	}

	public Articulo(String titulo) {
		super();
		this.titulo = titulo;
	}



	public Articulo(String titulo, int numPaginaInicio, int numPaginaFin) {
		super();
		this.titulo = titulo;
		this.numPaginaInicio = numPaginaInicio;
		this.numPaginaFin = numPaginaFin;
		this.autores = new HashSet<>();
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumPaginaInicio() {
		return numPaginaInicio;
	}

	public void setNumPaginaInicio(int numPaginaInicio) {
		this.numPaginaInicio = numPaginaInicio;
	}

	public int getNumPaginaFin() {
		return numPaginaFin;
	}

	public void setNumPaginaFin(int numPaginaFin) {
		this.numPaginaFin = numPaginaFin;
	}

	public Revista getRevista() {
		return revista;
	}

	public void setRevista(Revista revista) {
		this.revista = revista;
	}

	@Override
	public String toString() {
		return "Articulo [idArticulo=" + idArticulo + ", titulo=" + titulo + ", numPaginaInicio=" + numPaginaInicio
				+ ", numPaginaFin=" + numPaginaFin + ", revista=" + revista + "]";
	}
	
	
	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}


	public void addAutor(Autor a) { 
	    this.autores.add(a);
	    
	    if (!a.getArticulos().contains(this)) {
	        a.getArticulos().add(this);
	    }
	}
	public void removeAutor(Autor a) {

		this.autores.remove(a);

		if (a.getArticulos().contains(this)) {
			a.getArticulos().remove(this);

		}

	}


}

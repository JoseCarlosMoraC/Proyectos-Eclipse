package Repaso.Concert.Models;

import java.time.LocalDate;
import java.util.Objects;

import Repaso.Concert.Exceptions.CraftersException;


public abstract class Evento {
	private static int id;
	private String nombre;
	private final LocalDate fecha;
	private int entradasVendidas;
	private int capacidadAsistentes;
	private Estado estado;
	
	public Evento(int id,String nombre, LocalDate fecha, int entradasVendidas, int capacidadAsistentes) {
		super();
		this.id = id++;
		this.nombre = nombre;
		this.fecha = fecha;
		this.entradasVendidas = entradasVendidas;
		this.capacidadAsistentes = capacidadAsistentes;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Evento.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEntradasVendidas() {
		return entradasVendidas;
	}

	public void setEntradasVendidas(int entradasVendidas) throws CraftersException {
		if(entradasVendidas <= 0) {
			throw new CraftersException("No es posible construir un evento con esos datos");
		}
		this.entradasVendidas = entradasVendidas;
	}

	public int getCapacidadAsistentes() {
		return capacidadAsistentes;
	}

	public void setCapacidadAsistentes(int capacidadAsistentes) throws CraftersException {
		if(capacidadAsistentes <= 0) {
			throw new CraftersException("No es posible construir un evento con esos datos");
		}
		this.capacidadAsistentes = capacidadAsistentes;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Evento [id ="+id +"nombre=" + nombre + ", fecha=" + fecha + ", estado=" + estado + "]";
	}

public abstract double calcularCosteBase();


public void modificaEstado() {
	
}


}



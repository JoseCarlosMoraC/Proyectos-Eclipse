package Modelos;

import java.time.LocalDate;

public class Evento {
	private static int id;
	private String nombre;
	private final LocalDate fecha;
	int entradasVendidas;
	int capacidadAsistentes;
	public Evento(int id,String nombre, LocalDate fecha, int entradasVendidas, int capacidadAsistentes) {
		super();
		this.id = id++;
		this.nombre = nombre;
		this.fecha = fecha;
		this.entradasVendidas = entradasVendidas;
		this.capacidadAsistentes = capacidadAsistentes;
	}
	
	
	
	
}

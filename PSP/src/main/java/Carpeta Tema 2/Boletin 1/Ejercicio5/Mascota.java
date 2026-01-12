package Tema2.Boletin1.Ejercicio5;

import java.util.Objects;

public class Mascota implements Runnable{
	private String nombre;
	private int numCome;
	public Mascota(String nombre, int numCome) {
		super();
		this.nombre = nombre;
		this.numCome = numCome;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumCome() {
		return numCome;
	}
	public void setNumCome(int numCome) {
		this.numCome = numCome;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre, numCome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mascota other = (Mascota) obj;
		return Objects.equals(nombre, other.nombre) && numCome == other.numCome;
	}
	@Override
	public String toString() {
		return "Mascota [nombre=" + nombre + ", numCome=" + numCome + "]";
	}

		
	public void run() {
		numCome ++;
		System.out.println(nombre + " numero veces :" + numCome);
		try {
			Thread.sleep(1*numCome);
			System.out.println(nombre + " numero veces :" + numCome);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
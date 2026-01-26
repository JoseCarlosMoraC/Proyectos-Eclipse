package Boletin2.Ejercicio3;

public class Contador {
	private int numConexiones;

	public Contador() {
		super();
		this.numConexiones = 1;
	}

	public int getNumConexiones() {
		return numConexiones;
	}

	public void setNumConexiones(int numConexiones) {
		this.numConexiones = numConexiones;
	}

	@Override
	public String toString() {
		return "Contador [numConexiones=" + numConexiones + "]";
	}

}

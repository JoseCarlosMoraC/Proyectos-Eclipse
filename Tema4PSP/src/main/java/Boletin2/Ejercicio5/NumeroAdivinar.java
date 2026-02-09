package Boletin2.Ejercicio5;

public class NumeroAdivinar {
	
	private int numero;
	private boolean acertado;
	
	public NumeroAdivinar() {
		super();
		this.numero = numero;
		this.acertado = false;
	}
	
	// en el caso de acertar el numero
	public synchronized boolean esAcertado() {
		return acertado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isAcertado() {
		return acertado;
	}

	public void setAcertado(boolean acertado) {
		this.acertado = acertado;
	}
	
	

}
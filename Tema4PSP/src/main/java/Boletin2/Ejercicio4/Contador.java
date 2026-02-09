package Boletin2.Ejercicio4;

public class Contador {
private int numeroconexiones;

public Contador() {
	super();
	this.numeroconexiones = numeroconexiones;
}

public int getNumeroconexiones() {
	return numeroconexiones;
}

public void setNumeroconexiones(int numeroconexiones) {
	this.numeroconexiones = numeroconexiones;
}

@Override
public String toString() {
	return "Contador [numeroconexiones=" + numeroconexiones + "]";
}

public synchronized void incrementar() {
    numeroconexiones++;
}

}
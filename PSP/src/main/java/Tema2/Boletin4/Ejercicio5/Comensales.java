package Tema2.Boletin4.Ejercicio5;

public class Comensales implements Runnable{
private Cocinero cocinero;

public Comensales(Cocinero cocinero) {
	super();
	this.cocinero = cocinero;
}

@Override
public void run() {
	// TODO Auto-generated method stub
	cocinero.cocina();
	
}


}

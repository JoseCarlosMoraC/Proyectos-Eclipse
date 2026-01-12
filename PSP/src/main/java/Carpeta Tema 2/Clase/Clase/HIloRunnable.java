package Tema2.Clase;

public class HIloRunnable implements Runnable{
private String nombreHilo;

public HIloRunnable(String nombreHilo) {
	super();
	this.nombreHilo = nombreHilo;
}

@Override
public void run() {
	// TODO Auto-generated method stub
	Thread.currentThread().setName(nombreHilo);
	System.out.println("Ejecutando Hilo: " + Thread.currentThread().getName());
	
}


}

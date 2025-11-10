package Tema2.Clase;

public class MiHilo extends Thread{
private String nombreHilo;

public MiHilo(String nombreHilo) {
	super();
	this.nombreHilo = nombreHilo;
}

@Override
public void run() {
	// TODO Auto-generated method stub
	System.out.println(this.nombreHilo + " estado " + this.getState());
	try {
		sleep(10000);
		
	}catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println("Termina hilo: " + this.nombreHilo);
}



}

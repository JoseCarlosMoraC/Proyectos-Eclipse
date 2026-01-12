package Tema2.Boletin4.gasolinera.gasolinera;

public class Coche implements Runnable {
	   private final Surtidor surtidor;

	   public Coche(Surtidor surtidor, String nombre) {
	       super();
	       this.surtidor = surtidor;
	   }

	   @Override
	   public void run() {
		   surtidor.pagocontarjeta();
		   surtidor.reposta();
		   
	   }
	}

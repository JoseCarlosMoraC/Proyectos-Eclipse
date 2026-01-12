package Tema2.Boletin4.gasolinera.gasolinera;

import java.util.concurrent.Semaphore;

public class Surtidor {
	private Semaphore semaforo;

	public Surtidor(int numeroConexiones) {
		semaforo = new Semaphore(numeroConexiones);
	}
	public void reposta() {
		try {
			semaforo.acquire();
			System.out.println(Thread.currentThread().getName() + " está echando gasolina");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			System.out.println(Thread.currentThread().getName() + " libera surtidor");
		}}
	public void pagocontarjeta() {
		System.out.println( Thread.currentThread().getName()+ "Pago con tarjeta");
		
	}
	
}
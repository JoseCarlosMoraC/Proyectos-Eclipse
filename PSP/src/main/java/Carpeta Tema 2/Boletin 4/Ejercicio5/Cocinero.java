package Tema2.Boletin4.Ejercicio5;

import java.util.concurrent.Semaphore;

public class Cocinero implements Runnable{
private Semaphore comida;

public Cocinero(int numComensales) {
	super();
	comida = new Semaphore(numComensales);
}

public void cocina() {
	try {
		comida.acquire();
		System.out.println(Thread.currentThread().getName()+ "le da comida");
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		comida.release(3);
		System.out.println(Thread.currentThread().getName()+ "espera a que haya un putisimo plato");
	}
}

@Override
public void run() {
	// TODO Auto-generated method stub
	
}

}

package Tema2.Boletin1.Ejercicio1;

public class Hilo2 extends Thread{
	private String nombre;
	
	public Hilo2(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		try {
			while(true) {
				System.out.println("Procesos");
				sleep(500);
			}
			
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

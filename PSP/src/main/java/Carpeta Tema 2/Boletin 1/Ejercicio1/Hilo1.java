package Tema2.Boletin1.Ejercicio1;

public class Hilo1 implements Runnable{
	private String nombre;
	
	public Hilo1(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		try {
			while(true) {
				System.out.println("Servicios");
				Thread.sleep(500);
			}
			
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

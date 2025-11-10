package Tema2.Clase;

public class Main {
	 public static void main(String[] args) {
		 MiHilo hilo1 = new MiHilo("Hilo 1");
		 MiHilo hilo2 = new MiHilo("Hilo 2");
		 System.out.println(hilo1.getState());
		 System.out.println(hilo2.getState());
		 hilo1.start();
		 hilo2.start();
	 }
	
	
	
}

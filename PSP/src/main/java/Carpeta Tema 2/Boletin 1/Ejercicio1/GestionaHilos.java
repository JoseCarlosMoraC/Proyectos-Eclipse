package Tema2.Boletin1.Ejercicio1;



public class GestionaHilos {
	 public static void main(String[] args) {
		 Hilo1 runnable1 = new Hilo1("hilo1");
		 Hilo2 hilo2 = new Hilo2("hilo2");
		 Thread hilo1 = new Thread(runnable1);
		 hilo1.start();
		 hilo2.start();
	 }
	
	
	
}
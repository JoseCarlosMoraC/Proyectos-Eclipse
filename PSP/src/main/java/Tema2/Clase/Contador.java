package Tema2.Clase;

public class Contador {
	private int valor = 0;
	   public  synchronized void incrementar() { 
	       valor++;
	   }
	   public int getValor() {
	       return valor;
	   }
	}
	class TareaIncremento implements Runnable {
	   private Contador contador;
	   public TareaIncremento(Contador contador) {
	       this.contador = contador;
	   }
	   @Override
	   public void run() {
	       for (int i = 0; i < 1000; i++) {
	           contador.incrementar();
	       }
	       try {
               Thread.sleep(1000); // duerme 1 segundo
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
       }
	   }
	



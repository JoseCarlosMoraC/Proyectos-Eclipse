package Tema2.Boletin3.Ejercicio4;

public class MultiploCoopHilo3 extends Thread {
	 private MultiplosCooperativos multiplo;
	 
	 

	 public MultiploCoopHilo3(MultiplosCooperativos multiplo) {
		super();
		this.multiplo = multiplo;
	}



	 public void run() {
		// TODO Auto-generated method stub
		multiplo.imprimeMultiplos(3);
	 }
}
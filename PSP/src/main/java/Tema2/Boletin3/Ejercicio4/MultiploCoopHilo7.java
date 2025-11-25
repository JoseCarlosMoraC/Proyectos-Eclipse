package Tema2.Boletin3.Ejercicio4;

public class MultiploCoopHilo7 extends Thread{
	 private MultiplosCooperativos multiplo;
	 
	 

	 public MultiploCoopHilo7(MultiplosCooperativos multiplo) {
		super();
		this.multiplo = multiplo;
	}



	 public void run() {
		// TODO Auto-generated method stub
		multiplo.imprimeMultiplos(7);
	 }
}
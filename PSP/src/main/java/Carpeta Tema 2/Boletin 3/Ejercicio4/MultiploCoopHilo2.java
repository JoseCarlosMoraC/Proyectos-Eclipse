package Tema2.Boletin3.Ejercicio4;

public class MultiploCoopHilo2 extends Thread{
 private MultiplosCooperativos multiplo;
 
 

 public MultiploCoopHilo2(MultiplosCooperativos multiplo) {
	super();
	this.multiplo = multiplo;
}



 @Override
 public void run() {
	// TODO Auto-generated method stub
	multiplo.imprimeMultiplos(2);
 }
 
}

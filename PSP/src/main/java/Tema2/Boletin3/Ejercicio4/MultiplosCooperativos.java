package Tema2.Boletin3.Ejercicio4;

import java.util.Objects;

public class MultiplosCooperativos{

	public void imprimeMultiplos(int num) {
		for (int i = 0; i <= 10; i++) {
			System.out.println(num*i);
			}
		 try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	



}

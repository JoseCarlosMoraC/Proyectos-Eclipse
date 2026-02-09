package Boletin2.Ejercicio1;

public class LanzadorClientes {
public static void main(String[] args) {
		
		for (int i = 0; i < 10; i++) {
			Thread clientehilo1= new Thread(new ClienteHilo());
			clientehilo1.start();
			
		}
	
		
	}
	
	
	}
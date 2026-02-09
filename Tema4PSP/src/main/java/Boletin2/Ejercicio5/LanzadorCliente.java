package Boletin2.Ejercicio5;

public class LanzadorCliente {

	public static void main(String[] args) {
		
		ClienteImpar clienteImpar = new ClienteImpar();
        ClientePar clientePar = new ClientePar();
        
        // inicio los dos hilos
        clienteImpar.start();
        clientePar.start();

	}

}

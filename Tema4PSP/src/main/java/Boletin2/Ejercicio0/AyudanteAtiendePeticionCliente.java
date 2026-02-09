package Boletin2.Ejercicio0;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AyudanteAtiendePeticionCliente extends Thread {
	private Socket socket;
	

public AyudanteAtiendePeticionCliente(Socket socket) {
		super();
		this.socket = socket;
	}


@Override
public void run() {

	
	try {
		PrintWriter salida = null;
		BufferedReader entrada = null;
		
				
		System.out.println("Cliente conectado" + socket.getLocalPort());
			Thread.sleep(5000);
			System.out.println("Conexion cerrada");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	


}
}
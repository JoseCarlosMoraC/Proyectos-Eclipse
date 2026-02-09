package Clase;

import java.io.IOException;
import java.net.Socket;

public class ClienteHilo extends Thread {

	@Override
	public void run() {
		String Host = "localhost"; 
		int Puerto = 5555;
		Socket cliente = null;
		
		try {
			cliente = new Socket(Host, Puerto);
			System.out.println("Cliente: conexión establecida");
			/*
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String mensaje = entrada.readLine();
			System.out.println("Cliente dice: " + mensaje);
			*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

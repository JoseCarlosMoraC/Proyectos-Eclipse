package Clase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {

	private Socket socket = null;

	public HiloServidor(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			//BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
			
			System.out.println("Conexión inicializada con: " + this.socket.getPort());
			Thread.sleep(5000);
			System.out.println("Conexión finalizada");
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}

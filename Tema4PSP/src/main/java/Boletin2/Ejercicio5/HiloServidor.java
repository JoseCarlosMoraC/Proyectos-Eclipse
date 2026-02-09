package Boletin2.Ejercicio5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class HiloServidor extends Thread {

	private Socket socket = null;
	private NumeroAdivinar numero;

	public HiloServidor(Socket socket, NumeroAdivinar numero) {
		super();
		this.socket = socket;
		this.numero = numero;
	}

	@Override
	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
			String mensaje = " ";

			System.out.println("Cliente dice: " + mensaje);
			salida.println("Servidor responde: ");

			System.out.println("Conexión inicializada en fecha y hora: " + LocalDateTime.now());
			// Thread.sleep(5000);
			System.out.println("Conexión finalizada");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String mensaje = "";
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public NumeroAdivinar getNumero() {
		return numero;
	}

	public void setNumero(NumeroAdivinar numero) {
		this.numero = numero;
	}

}
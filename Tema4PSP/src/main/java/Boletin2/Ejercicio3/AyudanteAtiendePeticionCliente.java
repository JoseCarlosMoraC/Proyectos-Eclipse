package Boletin2.Ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AyudanteAtiendePeticionCliente extends Thread {
	private Socket socket;
	private Contador contador;

	public AyudanteAtiendePeticionCliente(Socket socket, Contador contador) {
		super();
		this.socket = socket;
		this.contador = contador;
	}

	@Override
	public void run() {
		BufferedReader entrada = null;
		PrintWriter salida = null;

		try {

			entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			salida = new PrintWriter(socket.getOutputStream(), true);

			salida.println("Eres el cliente número " + contador.getNumConexiones());

			contador.setNumConexiones(contador.getNumConexiones()+1);

		} catch (IOException e) {
			System.err.println("Error en ManejadorHilosCliente: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

package Boletin2.Ejercicio3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultiHilos {

	public static void main(String[] args) {
		Contador contador = new Contador();
		int puerto = 44444;

		try (ServerSocket servidor = new ServerSocket(puerto)) {
			System.out.println("Servidor multihilo iniciado en el puerto " + puerto);

			while (true) {

				Socket socketCliente = servidor.accept();
				System.out.println("Nuevo cliente conectado: " + socketCliente.getInetAddress());

				new AyudanteAtiendePeticionCliente(socketCliente, contador).start();

			}
		} catch (IOException e) {
			System.err.println("Error en el servidor: " + e.getMessage());
		}
	}
}

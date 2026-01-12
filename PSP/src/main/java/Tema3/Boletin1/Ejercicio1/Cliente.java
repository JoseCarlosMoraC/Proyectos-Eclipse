package Tema3.Boletin1.Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Host = "localhost"; // host servidor con el que el cliente quiere conectarse
		int Puerto = 4000;// puerto remoto en el servidor que el cliente conoce
		Socket cliente = null;
		try {
			cliente = new Socket(Host, Puerto);
			System.out.println("Cliente: conexión establecida");

			PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

			Scanner sc = new Scanner(System.in);

			boolean acaba = false;
			
			while (!acaba) {
				System.out.println("Escribe algo bb");
				String mensajeParaServidor = sc.next();
				if (!mensajeParaServidor.equals("fin")) {
					acaba = true;
				}
				else {
					salida.println(mensajeParaServidor);
				}
			}



			// Conexión
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // conecta

	}

}
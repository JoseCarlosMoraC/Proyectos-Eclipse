package Tema3.Boletin1.Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteEjercicio1 {

	public static void main(String[] args) {

		String Host = "localhost"; // host servidor con el que el cliente quiere conectarse

		int Puerto = 6000;// puerto remoto en el servidor que el cliente conoce

		Socket cliente = null;

		try {

			cliente = new Socket(Host, Puerto);

			System.out.println("Cliente: conexion establecida");

			// Conexion

			PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

			Scanner sc = new Scanner(System.in);



			boolean acabado = false;

			String mensajeparaservidor = "";



			while (!acabado) {

				System.out.println("Escribe un mensaje para el servidor: ");

				mensajeparaservidor = sc.nextLine();

				if (mensajeparaservidor != null && mensajeparaservidor.equals("fin")) {

					acabado = true;

				} else {

					salida.println(mensajeparaservidor);

				}

			}



		} catch (UnknownHostException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} // conecta



	}



}
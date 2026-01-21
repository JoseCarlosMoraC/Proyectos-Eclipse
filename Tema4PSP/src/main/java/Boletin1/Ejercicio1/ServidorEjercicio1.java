package Boletin1.Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEjercicio1 {

public static void main(String[] args) {

	int puerto=6000;

	ServerSocket servidor= null;

	Socket clienteconectado=null;

	

	try {

		servidor= new ServerSocket(puerto);

		System.out.println("Servidor escuchando en el puerto "+ puerto);



		Socket cliente= servidor.accept();

		BufferedReader entrada= new BufferedReader( new InputStreamReader(cliente.getInputStream()));

		System.out.println("Nuevo cliente conectado" + cliente.getInetAddress());

		String mensaje="";

		while(mensaje!=null) {

		 mensaje= entrada.readLine();



		System.out.println("Cliente dice:" + mensaje);
		System.out.print("Servidor recibió: " + mensaje.toLowerCase());

		}

	} catch (IOException e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

	}



	

	

}

}
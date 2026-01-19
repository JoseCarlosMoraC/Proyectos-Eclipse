package Tema3.Boletin1.Ejercicio3;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.InetAddress;

import java.net.Socket;

import java.net.UnknownHostException;

import java.util.Scanner;



public class PruebaCliente {

	public static void main(String[] args) {

		String host = "localhost"; // host servidor con el que el cliente quiere conectarse

		int puerto = 6000;// puerto remoto en el servidor que el cliente conoce

		Socket cliente = null;

		try {
			System.out.println("Conectando al servidor en " + host + ":" + puerto + "...");

			cliente = new Socket(host, puerto);

			System.out.println("Cliente: conexion establecida");

			// Conexion

			BufferedReader entrada= new BufferedReader( new InputStreamReader(cliente.getInputStream()));

			String mensaje= entrada.readLine();

			if(mensaje != null && !mensaje.equals(""))



				System.out.println("Mensaje del servidor: " + mensaje);
        

				

			



			

		} catch (UnknownHostException e) {

			 System.err.println("No se puede encontrar el host: " + host);
	            e.printStackTrace();

		} catch (IOException e) {

			  System.err.println("Error de entrada/salida: " + e.getMessage());
	            e.printStackTrace();

		} // conecta

		

		try {

			cliente.close();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}



	}



}
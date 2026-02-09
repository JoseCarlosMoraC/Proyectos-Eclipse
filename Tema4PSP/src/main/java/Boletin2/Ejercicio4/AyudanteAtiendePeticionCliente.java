package Boletin2.Ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Boletin2.Ejercicio4.Contador;

public class AyudanteAtiendePeticionCliente extends Thread {
	private Socket socket;
	private Contador contador;
	private Tablero tablero;
	

public AyudanteAtiendePeticionCliente(Socket socket, Contador contador,Tablero tablero) {
		super();
		this.contador=contador;
		this.socket = socket;
		this.tablero= tablero;
	}

public boolean nohaypremios() {
	int contadornulo=0;
boolean esnulo=false;
	String[][]tableropremios= tablero.getTablero();
try {
	for (String[] filas : tableropremios) {
		for (String elementoitero : filas) {
			
			if(elementoitero==null) {
				contadornulo++;
			}
			
		}
	}
	}

catch(NullPointerException e ) {
	System.out.println("Es nulo" +contadornulo);
}
	if(contadornulo==12) {
		esnulo=true;
	}
	return esnulo;
	
}

public synchronized String aciertapremio(String apuestacliente) {
	String mensajeresultadoapuesta="";
	
	  int pos1 = apuestacliente.charAt(0) - '0';
	  int pos2 = apuestacliente.charAt(2) - '0';

	String apuestaeneltablero= tablero.getTablero()[pos1][pos2];
	if(apuestaeneltablero!=null) {
		mensajeresultadoapuesta="Felicidades, has ganado" + apuestaeneltablero;
		tablero.getTablero()[pos1][pos2]=null;
	}
	else {
		mensajeresultadoapuesta="No ganaste nada";
	}
	return mensajeresultadoapuesta;
	}


@Override
public void run() {
	
	
	PrintWriter salida=null;
	BufferedReader entrada=null;
	String mensaje;
	boolean nohaypremios= nohaypremios();
    contador.incrementar();

	try {
		entrada= new BufferedReader( new InputStreamReader(socket.getInputStream()));

		salida = new PrintWriter(socket.getOutputStream(), true);

		mensaje= "Cliente conectado => " + contador.getNumeroconexiones();
		salida.println(mensaje);
		while(!nohaypremios) {
			String mensajeparacliente="Dime la posicion que quieres jugar. FORMATO: X,X o escribe salir para terminar";
			salida.println(mensajeparacliente);
			String mensajedecliente= entrada.readLine();
			if(!mensajedecliente.equals("salir")) {
			String premioparacliente= aciertapremio(mensajedecliente);
			salida.println(premioparacliente);
			}
			
		}
	
		
		
	} 
	catch (java.net.SocketException e) {
        // Capturamos el "Connection reset" para que no ensucie la consola
        System.out.println("El cliente (Hilo " + this.getId() + ") cerró la conexión .");
        }
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
}
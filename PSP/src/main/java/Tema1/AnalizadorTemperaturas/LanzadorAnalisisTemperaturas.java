package Tema1.AnalizadorTemperaturas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Tema1.Clase.Padre;

public class LanzadorAnalisisTemperaturas {
	private static final String directorioGenerarClases = "target\\classes" ;
	private static final String rutaFicheroJava = "src\\main\\java\\Tema1\\Clase\\Hijo.java";
	
	public static void main(String[] args) {
		
		Padre lanzador = new Padre();
		
		lanzador.ejecutaProcesoJava();
		
	}
	
	public void compilaProceso() {

		String[] comando = { "javac", "-d", rutaFicheroJava, 
				"AnalizadorTemperaturas.java"};
		ProcessBuilder pb = new ProcessBuilder(comando);
		
		try {
			// para la comunicacion entre proceso padre e hijo
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process p1 = pb.start();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ejecutaProcesoJava() {
	
		String[] comando2 = {"java", "-cp", directorioGenerarClases,rutaFicheroJava};
		ProcessBuilder pb = new ProcessBuilder(comando2);
		
		try {
			//pb.redirectErrorStream(true);
			//pb.inheritIO();
			Process p1 = pb.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(p1.getErrorStream()));

			int exit = p1.waitFor();
			System.out.println(exit);

			if (exit != 0) {
				String errorLinea = errorReader.readLine();
				while (errorLinea != null) {
					System.err.println("Error Padre: " + errorLinea);
					errorLinea = errorReader.readLine();
				}
			} else {
				String linea = reader.readLine();

				while (linea != null) {
					System.out.println("Padre: " + linea);
					linea = reader.readLine();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
package Tema1.AnalizadorTemperaturas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Tema1.Clase.Padre;

import java.io.IOException;

//papa
public class LanzadorAnalisisTemperaturas {
	
	private static final String rutaFicheroJava = "src\\main\\java\\tema1\\AnalizadorTemperaturas\\AnalizadorTemperaturas.java" ;
	private static final String directorioGenerarClases = "target\\classes";
	

	public static void main(String[] args) {
		
		LanzadorAnalisisTemperaturas lanzador = new LanzadorAnalisisTemperaturas();
		
		int[] array = {10, 20, 25, 30, 35};
		String fichero = "src\\main\\java\\resources\\datos.txt";
		
		lanzador.compilaProceso();
		
		for (int temp : array) {
			
			lanzador.ejecutaProceso(fichero, String.valueOf(temp));
		}
		
		
	}
	
	public void compilaProceso() {

		String[] comando = { "javac", "-d",directorioGenerarClases ,rutaFicheroJava};
		ProcessBuilder pb = new ProcessBuilder(comando);
		
		try {
		
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process p1 = pb.start();
			int exit = p1.waitFor();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ejecutaProceso(String ruta, String palabra) {
	
		String[] comando1 = {"java", "-cp", directorioGenerarClases,rutaFicheroJava,ruta, palabra};

		ProcessBuilder pb = new ProcessBuilder(comando1);

		try {
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process p1 = pb.start();
			
	
		} catch (IOException e) {
			e.printStackTrace();

	}

	}
}
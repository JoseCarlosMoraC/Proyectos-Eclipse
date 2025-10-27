package Tema1.Boletin2.Ejercicio13;

import java.io.IOException;

public class LanzadorCalculaSuma {

	private static final String directorioGenerarClases = "target\\classes" ;
	private static final String rutaFicheroJava = "src\\main\\java\\tema1\\Boletin2\\Ejercicio13\\CalculaSuma.java";
	
	public static void main(String[] args) {
		
		LanzadorCalculaSuma lanzador = new LanzadorCalculaSuma();
		
		lanzador.ejecutaProceso();
		
	}
	
	public void compilaProceso() {

		String[] comando = { "javac", "-d", rutaFicheroJava, 
				"CalculaSuma.java"};
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

	public void ejecutaProceso() {
	
		String[] comando2 = {"java", "-cp", directorioGenerarClases,rutaFicheroJava,"par" ,"10"};
		ProcessBuilder pb = new ProcessBuilder(comando2);
		
		try {
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process p1 = pb.start();
			/*int exit = p1.waitFor();
			System.out.println(exit);*/
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
package Tema1.Boletin2.Ejercicio15;

import java.io.IOException;

public class LanzaMultiplicador {

	private static final String directorioGenerarClases = "target\\classes" ;
	private static final String rutaFicheroJava = "src\\main\\java\\tema1\\Boletin2\\Ejercicio15\\CalculaMultiplicacion.java";
	
	public static void main(String[] args) {
		
		LanzaMultiplicador lanzador = new LanzaMultiplicador();
		
		lanzador.ejecutaProceso();

	}
	
	public void compilaProceso() {

		String[] comando = { "javac", "-d", rutaFicheroJava, "CalculaMultiplicacion.java"};
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
	
		String[] comando1 = {"java", "-cp", directorioGenerarClases,rutaFicheroJava,"1" ,"4"};
		String[] comando2 = {"java", "-cp", directorioGenerarClases,rutaFicheroJava,"5" ,"7"};
		//cambiar para hacerlo llamando al jar
		ProcessBuilder pb = new ProcessBuilder(comando1);
		ProcessBuilder pb2 = new ProcessBuilder(comando2);
		
		try {
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process p1 = pb.start();
			p1 = pb.start();
			Process p2 = pb2.start();
			p2 = pb2.start();
			
			/*int exit = p1.waitFor();
			System.out.println(exit);*/
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
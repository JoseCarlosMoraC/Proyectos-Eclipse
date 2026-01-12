package Tema1.Boletin2.Ejercicio10;

import java.io.IOException;

public class LanzadorPython {

	private static final String rutaFicheroJava = "src\\main\\resources\\fichero1.py";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LanzadorPython lanzador = new LanzadorPython();
		lanzador.ejecutaProceso();

	}

	public void ejecutaProceso() {

		String[] comando = {"python", rutaFicheroJava};
		ProcessBuilder pb = new ProcessBuilder(comando);
		
		try {
			//con esto rediirige el error del hijo al padre para saber que pasa
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
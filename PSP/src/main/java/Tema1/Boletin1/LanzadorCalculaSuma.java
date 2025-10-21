package Tema1.Boletin1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanzadorCalculaSuma {
	private static final Logger logger = LogManager.getLogger(LanzadorCalculaSuma.class);
	private final static String directorioGenerarClasses = "target\\classes";
	private final static String rutaSourceJava = "src\\main\\java\\";

	public void ejecutaProcesoJava() {
		String[] comando = { "javac", "-d", rutaSourceJava + "\\Tema1\\Boletin1\\CalculaSuma.java", directorioGenerarClasses };
		ProcessBuilder pb = new ProcessBuilder(comando);
		try {

			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process p1 = pb.start();
			

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void lanzaCompilador() {
		ProcessBuilder pb;
		
		String rutaFicheroJava = rutaSourceJava+"\\Tema1\\Boletin1\\CalculaSuma.java" ;
		System.out.println(rutaFicheroJava);
		System.out.println(directorioGenerarClasses);
		try {
			String[] comando = { "javac", "-d", directorioGenerarClasses, rutaFicheroJava };
			pb = new ProcessBuilder(comando);
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process proceso = pb.start();
			int exit = proceso.waitFor();
			System.out.println(exit);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		LanzadorCalculaSuma l = new LanzadorCalculaSuma();
		System.out.println("Proceso padre");
		l.lanzaCompilador();

	}
}

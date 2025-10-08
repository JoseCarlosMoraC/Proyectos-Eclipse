package Tema1.Boletin1;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LanzaComandosJava {
	private static final Logger logger = LogManager.getLogger(LanzaComandosJava.class);
	private final static String directorioGenerarClasses = "C:\\Users\\alumno\\Desktop\\DAM\\Proyectos Eclipse\\PSP\\target";
	private final static String rutaSourceJava = "C:\\Users\\alumno\\Desktop\\DAM\\Proyectos Eclipse\\PSP\\src\\main\\java";
	public void ejecutaProcesoCompila() {
		String[] comando = { "javac", "-d", directorioGenerarClasses, rutaSourceJava + "Gestiona.java"};
		ProcessBuilder pb = new ProcessBuilder(comando);
		try {

			
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process p1 = pb.start();
			
			
		} catch (Exception e) { 			logger.error(e.getMessage()); 		}
	}
	
	public void lanzaCompilador(String rutaFicheroFuente, String rutaParaGenerarClass) {
		ProcessBuilder pb;
		try {
			String[] comando = { "javac", "-d", directorioGenerarClasses, rutaSourceJava};
			pb = new ProcessBuilder(comando);
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process proceso = pb.start();
			int exit = proceso.waitFor();
			logger.debug(exit);
			
		} catch (Exception e) { 			logger.error(e.getMessage()); 		}
	}
	public static void main(String[] args) {
		LanzaComandosJava l = new LanzaComandosJava();
		l.lanzaCompilador(rutaSourceJava, directorioGenerarClasses);
	
	} }



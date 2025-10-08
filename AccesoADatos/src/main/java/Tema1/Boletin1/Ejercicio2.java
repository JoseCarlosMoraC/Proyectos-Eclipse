package Tema1.Boletin1;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio2 {
	private static final Logger logger = LogManager.getLogger(Ejercicio2.class);
	public static void main(String[] args) {
	String rutaDirectorio = "C:\\Users\\alumno\\Desktop\\DAM";
	
	File directorio = new File(rutaDirectorio);

		
		logger.info(directorio.getName());
		logger.info(directorio.getAbsolutePath());
		logger.info(directorio.canRead());
		logger.info(directorio.canWrite());
		logger.info(directorio.getParentFile());
		
		
}
	}


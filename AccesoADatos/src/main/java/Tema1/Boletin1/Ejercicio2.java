package Tema1.Boletin1;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio2 {
	private static final Logger logger = LogManager.getLogger(Ejercicio2.class);
	public static void main(String[] args) {
	String rutaDirectorio = "C:\\Users\\alumno\\Desktop\\DAM";
	
	File f = new File(rutaDirectorio);
	File directorio = new File(rutaDirectorio);
	String[] archivos = f.list();
	for(String ficheros: archivos) {
		logger.info(ficheros);
}
	}
}

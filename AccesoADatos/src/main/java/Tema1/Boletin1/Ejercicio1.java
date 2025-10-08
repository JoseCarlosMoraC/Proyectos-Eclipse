package Tema1.Boletin1;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tema1.Ejemplos.Ejemplo;

public class Ejercicio1 {
	private static final Logger logger = LogManager.getLogger(Ejercicio1.class);


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String rutaDirectorio = "C:\\Users\\alumno\\Desktop\\DAM";
		
		File f = new File(rutaDirectorio);
		File directorio = new File(rutaDirectorio);

		File fichero = new File(directorio, "fichero.txt");

		String[] archivos = f.list();
		File[] archivos2 = f.listFiles();
		
		if(directorio.exists()) {
			logger.info("Existe");
			for(String ficheros: archivos) {
				logger.info(ficheros);
			}
		}else {
			logger.error("No existe");
		}
		
		
	}

	

}


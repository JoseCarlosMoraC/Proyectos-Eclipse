package Tema1.Boletin1;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio4 {
	private static final Logger logger = LogManager.getLogger(Ejercicio1.class);
	
	
	public static void main(String[] args) {
		Ejercicio4 a = new Ejercicio4();
		File directorio = new File("C:\\Users\\alumno\\Desktop\\DAM");
		a.getPropRecursiva(directorio);
		
	}public void getPropRecursiva(File padre) {
		boolean existe = padre.exists();
		if(existe) {
			File[] listaficheros = padre.listFiles();
			for(File fichero: listaficheros) {
				if(fichero.isFile()) {
					logger.info(fichero.getName());
				}else {
					this.getPropRecursiva(fichero);
				}
			}
		}else {
			logger.error("No se encuentra");
		}
		

	}
}



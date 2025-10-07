package Tema1.Ejemplos;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;





public class Ejemplo {
	private static final Logger logger = LogManager.getLogger(Ejemplo.class);
	public String toString() {
		return "Ejemplo []";
	}

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
		
/*		try {
			boolean creado = fichero.createNewFile(); // Aquí Sí creo fichero
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error al crear fichero:" + e.getMessage());
		}*/

	}

	

}

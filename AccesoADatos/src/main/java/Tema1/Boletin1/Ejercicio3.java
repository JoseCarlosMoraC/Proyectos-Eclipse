package Tema1.Boletin1;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio3 {
	private static final Logger logger = LogManager.getLogger(Ejercicio3.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String rutaDirectorio = "C:\\Users\\alumno\\Desktop\\DAM\\NuevaCarpeta";
		File directorio = new File(rutaDirectorio);
		
		if(directorio.exists()) {
			logger.error("Ya existe");
			
			
		}else {
			directorio.mkdir();
			
			logger.info(directorio.getAbsolutePath());
			try {
				logger.info(directorio.createNewFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	}


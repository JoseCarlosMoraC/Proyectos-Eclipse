package Utiles;

import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import Tema1.Pokemon.Pokemon;

public class ManejaPokemons {

private static final Logger logger = LogManager.getLogger(ManejaPokemons.class);
	
	public Pokemon leePokemon(String rutaFichero) {
		
		Pokemon pokemon = null;
		
		try {
		  Gson gson = new Gson();
		  FileReader fichero = new FileReader(rutaFichero);
		  // Leer el archivo JSON y convertirlo a un objeto 
		  pokemon = gson.fromJson(fichero,Pokemon.class);
		  
			logger.info(pokemon);
		} catch (Exception e) {
			logger.debug("Error al leer pokemon"+e.getMessage());
		}	
		return pokemon;
	}
	
}
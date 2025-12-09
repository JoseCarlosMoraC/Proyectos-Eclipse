package Utiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import Tema1.Pokemon.Pokemon;



public class EscribirPokemonCsv {

	//esta clase con el metodo sirve para escribir un csv de un fichero json
	
	public void escribePokemon(Pokemon pokemon, String ruta)
	{// Convertir el objeto a JSON
		 PrintWriter out = null;
		 FileWriter fichero = null;
		 try {
			fichero = new FileWriter(ruta);
			out = new PrintWriter(fichero);
			//fichero.write(json);
			
			//asi se escribe un fichero:
			out.printf(Locale.US,"Id, Nombre,Tipo, Altura, Peso, habilidadPrincipal,evoluciona_a\n"+"%d,%s,%s,%f,%f,%s,%s",pokemon.getId(), 
					pokemon.getNombre(),pokemon.getTipo(),pokemon.getAltura_m()
					,pokemon.getPeso_kg(), pokemon.getHabilidadPrincipal(), pokemon.getEvoluciona_a());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
					out.close();
				} catch (IOException e) {
					System.out.println("Error al escribir pokemon");
				}			
			}		
		}	  
	}

	
}
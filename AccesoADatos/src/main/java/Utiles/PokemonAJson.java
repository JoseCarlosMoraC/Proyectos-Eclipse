	package Utiles;
	
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;
	
	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	
	import com.google.gson.Gson;
	import com.google.gson.GsonBuilder;
	
	import Tema1.Pokemon.Pokemon;
	
	public class PokemonAJson {
		
		//lee el csv como le indicamos y luego lo usa para escrbrir el json
		
	private static final Logger logger = LogManager.getLogger(PokemonAJson.class);
		
		public List<Pokemon> leePokemoCsv(String ruta) throws FileNotFoundException {
			List<Pokemon> listaPokemon = new ArrayList<>();
	
			Scanner in = null;
			try {
				// abre el fichero
				FileReader fichero = new FileReader(ruta);
				// Se crea el flujo
				in = new Scanner(fichero);
				in.nextLine();
				// lee el fichero
				while (in.hasNextLine()) { // Lectura palabra a palabra
					// Aquí se hará la lectura in.next()
					// creo en cada posicion donde va a ir cada parte del fichero para que el json lea el csv y sepa donde va
					String linea = in.nextLine();
					String[] sinEspacios = linea.split(",");
					int id = Integer.parseInt(sinEspacios[0]);
					String nombre = sinEspacios[1];
					String tipo = sinEspacios[2];
					float altura_m = Float.parseFloat(sinEspacios[3]);
					float peso_kg = Float.parseFloat(sinEspacios[4]);
					String[] habilidadPrincipal = sinEspacios[5].split(";");
					String evoluciona_a = sinEspacios[6];
						
					Pokemon p = new Pokemon(id,nombre, tipo, altura_m, peso_kg, habilidadPrincipal, evoluciona_a);
					
					listaPokemon.add(p);
					
					}
				
			} finally {
				if (in != null) {
					in.close();
				}
			}
			logger.info(listaPokemon);
			return listaPokemon;
		}
		
		
		public void escribePokemonJson(List<Pokemon> p, String ruta)
		{// Convertir el objeto a JSON
			 Gson gson = new GsonBuilder().setPrettyPrinting().create();
			 String json = gson.toJson(p);
			 FileWriter fichero = null;
			 try {
				fichero = new FileWriter(ruta);
				fichero.write(json);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fichero != null) {
					try {
						fichero.close();
					} catch (IOException e) {
						System.out.println("Error al escribir pokemon");
					}			
					
				}		
				
			}	   
			 
		}
	
	
		
		/*public void escribeFichero(String ruta, int numero) {
			
			PrintWriter escribir = null;
			try {
				FileWriter ficheroResultado = new FileWriter(ruta);
				escribir = new PrintWriter(ficheroResultado);
				
				escribir.printf("Numero de veces: %d", numero);
				
				
			} catch (IOException e) {
					System.out.println("Error, no se ha creado fichero");	
			}
			finally {
				if(escribir!=null) {
					escribir.close();
				}
			}
			
		}*/
		
	
	}
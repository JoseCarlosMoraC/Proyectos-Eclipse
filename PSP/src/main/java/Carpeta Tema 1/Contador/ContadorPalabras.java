package Tema1.Contador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Clase ContadorPalabras
 * Esta clase representa el PROCESO HIJO.
 * Su función es:
 *   - Recibir un fichero de texto.
 *   - Contar cuántas veces aparece una palabra concreta.
 *   - Mostrar o guardar ese resultado.
 *
 * Recibe los argumentos:
 *   args[0] → nombre del fichero (por ejemplo: "texto.txt")
 *   args[1] → palabra a buscar (por ejemplo: "servicio")
 *
 * En versiones posteriores del ejercicio puede:
 *   - Escribir el resultado en un fichero.
 *   - O mostrarlo por consola (System.out.println).
 */
public class ContadorPalabras {
	
	// Carpeta base donde están los ficheros de entrada
	private static final String fichero = "src/main/resources/";
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// args[0] = nombre del fichero (sin ruta completa)
		String ruta = fichero + args[0];
		
		// args[1] = palabra a buscar dentro del fichero
		String palabra = args[1];
		
		// Creamos un objeto de la propia clase para usar sus métodos no estáticos
		ContadorPalabras contador = new ContadorPalabras();
		
		// Llamamos al método contarPalabras y mostramos el resultado por consola
		// (esto facilita que el proceso padre pueda leerlo después del InputStream)
		System.out.println(contador.contarPalabras(ruta, palabra));
	
	}
	
	/**
	 * Método que recorre el fichero línea a línea
	 * y cuenta cuántas veces aparece una palabra concreta.
	 */
	public int contarPalabras(String ruta, String palabra) throws FileNotFoundException {
		int contador = 0; // Contador de ocurrencias
		Scanner in = null; // Scanner para leer el fichero
		
		try {
			// Abrimos el fichero con FileReader y lo asociamos al Scanner
			FileReader fichero = new FileReader(ruta);
			in = new Scanner(fichero);
			
			// Leemos el fichero línea a línea
			while (in.hasNextLine()) {
				String linea = in.nextLine();
				
				// Dividimos la línea por espacios (obtenemos las palabras sueltas)
				String[] sinEspacios = linea.split(" ");
				
				// Recorremos las palabras de esa línea
				for (String letra : sinEspacios) {
					
					// Comparamos palabra ignorando mayúsculas/minúsculas
					if (letra.equalsIgnoreCase(palabra)) {
						contador += 1; // Si coincide, incrementamos el contador
					}
				}
			}
		} finally {
			// Cerramos el Scanner si se ha abierto correctamente
			if (in != null) {
				in.close();
			}
		}
		
		// Devolvemos el número total de coincidencias
		return contador;
	}
	
	/**
	 * Método alternativo (usado en el Apartado 1 del enunciado)
	 * para guardar el resultado en un fichero de texto.
	 * Más adelante puede no usarse si se muestra el resultado por consola.
	 */
	public void escribeFichero(String ruta, int numero) {
		
		PrintWriter escribir = null;
		try {
			// Creamos el fichero de salida y un PrintWriter para escribir en él
			FileWriter ficheroResultado = new FileWriter(ruta);
			escribir = new PrintWriter(ficheroResultado);
			
			// Escribimos el resultado
			escribir.printf("Numero de veces: %d", numero);
			
		} catch (IOException e) {
			System.out.println("Error, no se ha creado fichero");	
		}
		finally {
			// Cerramos el recurso si no es nulo
			if(escribir != null) {
				escribir.close();
			}
		}
	}
}

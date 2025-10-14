package Tema1.Ejemplos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejemplo3 {
	private static final Logger logger = LogManager.getLogger(Ejemplo3.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Notas> listaNotas = new ArrayList<Notas>();
		Ejemplo3 p = new Ejemplo3();
		try {
			p.cargarLista(
					"C:\\Users\\alumno\\Desktop\\segundoDAM\\JAVA\\accesoADatos\\src\\main\\java\\tema1\\fichero1.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cargarLista(String rutaYNombre) throws FileNotFoundException {
		Scanner in = null;
		try {
			// abre el fichero
			FileReader fichero = new FileReader(rutaYNombre);
			// Se crea el flujo
			in = new Scanner(fichero);
			// lee el fichero
			while (in.hasNextLine()) { // Lectura palabra a palabra
				// Aquí se hará la lectura in.next()
				String linea = in.nextLine();
				Persona p = cargaPersona(linea);
				logger.info(p.toString());
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	//public Persona cargaPersona(String linea) {
	//	String[] cadenas = linea.split(" ");
		//List<Notas> lista = new ArrayList<Notas>();
		//for (int i = 1; i < cadenas.length; i++) {
			//Notas n = new Notas(Double.parseDouble(cadenas[i]));
			//lista.add(n);
	//	}
		//Persona p = new Persona(cadenas[0], lista);
		//return p;

	}

//}
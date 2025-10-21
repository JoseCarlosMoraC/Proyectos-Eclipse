package Utiles;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import Tema1.Ejemplos.Empleado;

public class ManejaJson {
	public void leeEmpleados(String rutaFichero) {
		try {
			Gson gson = new Gson();
			FileReader fichero = new FileReader(rutaFichero);
			// Leer el archivo JSON y convertirlo a un objeto Empleado
			Empleado[] empleadosArray = gson.fromJson(fichero, Empleado[].class);
			List<Empleado> empleados = Arrays.asList(empleadosArray);
			System.out.println(empleados);
		} catch (Exception e) {
			System.out.println("Error al leer empleados" + e.getMessage());
		}
	}

}

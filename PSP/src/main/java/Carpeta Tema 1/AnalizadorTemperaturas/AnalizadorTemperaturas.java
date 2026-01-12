package Tema1.AnalizadorTemperaturas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Clase Hijo
 * Esta clase se encarga de analizar un fichero de temperaturas diarias.
 * Recibe dos argumentos:
 *  - El nombre del fichero con las temperaturas.
 *  - Un valor umbral (temperatura mínima a comprobar).
 *
 * Cuenta cuántos días tienen una temperatura >= umbral
 * y guarda el resultado en un fichero llamado {umbral}.txt.
 */
public class AnalizadorTemperaturas {

    // Ruta base donde se encuentran los ficheros con las temperaturas
    private static final String fichero = "src/main/resources/";

    public static void main(String[] args) throws FileNotFoundException {
        // Comprobamos que el usuario haya pasado al menos dos argumentos:
        // 1. nombre del fichero con temperaturas
        // 2. umbral
        if (args.length < 2) {
            System.out.println("Uso: java AnalizadorTemperaturas <nombreFichero> <umbral>");
            return;
        }

        // args[0] = nombre del fichero de temperaturas
        // args[1] = valor del umbral (convertido a int)
        String ruta = fichero + args[0];
        int temp = Integer.parseInt(args[1]);

        // Creamos un objeto de la clase AnalizadorTemperaturas para llamar a los métodos no estáticos
        AnalizadorTemperaturas contador = new AnalizadorTemperaturas();

        // Llamamos al método que cuenta cuántos días superan el umbral
        int resul = contador.contarTemperatura(ruta, temp);

        // Creamos el nombre del fichero resultado (por ejemplo, 30.txt)
        String ficheroResultado = fichero + temp + ".txt";

        // Guardamos el resultado en el fichero de salida
        contador.escribeFichero(ficheroResultado, resul);
    }

    /**
     * Método que cuenta cuántas líneas del fichero (una temperatura por línea)
     * son mayores o iguales al umbral recibido.
     */
    public int contarTemperatura(String ruta, int temp) throws FileNotFoundException {
        int contador = 0; // Contador de días
        Scanner in = null; // Scanner para leer el fichero

        try {
            // Abrimos el fichero con FileReader y lo asociamos al Scanner
            FileReader fichero = new FileReader(ruta);
            in = new Scanner(fichero);

            // Leemos línea a línea todas las temperaturas
            while (in.hasNextLine()) {
                String linea = in.nextLine(); // Leemos una línea
                int c = Integer.parseInt(linea); // Convertimos la línea en número entero

                // Si la temperatura es mayor o igual al umbral, la contamos
                if (c >= temp) {
                    contador += 1;
                }
            }

        } finally {
            // Cerramos el Scanner al terminar (buena práctica)
            if (in != null) {
                in.close();
            }
        }

        // Devolvemos el número de días que cumplen la condición
        return contador;
    }

    /**
     * Método que escribe el resultado (número de días) en un fichero de texto.
     */
    public void escribeFichero(String ruta, int temperatura) {
        PrintWriter wr = null;

        try {
            // Creamos un FileWriter asociado al nombre de salida (por ejemplo "30.txt")
            FileWriter ficheroResultado = new FileWriter(ruta);
            wr = new PrintWriter(ficheroResultado);

            // Escribimos el número de días encontrados
            wr.printf("Número de días con temperatura ≥ umbral: %d", temperatura);

        } catch (IOException e) {
            System.out.println("Error, no se ha creado el fichero");

        } finally {
            // Cerramos el escritor
            if (wr != null) {
                wr.close();
            }
        }
    }
}

package Simulacro.Simulacro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanzadorAmazon {

    // Ruta del fichero fuente del proceso hijo (Amazon.java)
    // Esto se usa para compilar dinámicamente el programa antes de lanzarlo.
    private static final String rutaFicheroJava = "src\\main\\java\\Simulacro\\Amazon.java";

    // Carpeta donde se almacenarán las clases compiladas (.class)
    private static final String directorioGenerarClases = "target\\classes";

    public static void main(String[] args) {

        LanzadorAmazon lanzador = new LanzadorAmazon();

        // Array con todas las provincias de Andalucía
        String[] provincias = {"Sevilla", "Málaga", "Granada", "Huelva", "Almería", "Jaén", "Córdoba", "Cádiz"};

        // Ruta del fichero general de pedidos
        String fichero = "src/main/resources/pedidos.txt";

        // Compilo el proceso hijo antes de lanzarlo
        lanzador.compilaProceso();

        int totalPedidos = 0; // Variable para sumar los pedidos totales de todas las provincias

        // Recorro cada provincia del array y lanzo un proceso hijo para cada una
        for (String temp : provincias) {

            // Cada hijo devuelve el número de pedidos encontrados en esa provincia
            int pedidos = lanzador.ejecutaProceso(fichero, temp);

            // Acumulo el total de pedidos
            totalPedidos += pedidos;
        }

        // Cuando todos los hijos han terminado, muestro el total de pedidos procesados
        System.out.println("Nº total de Pedidos : " + totalPedidos);
    }

    // Método que compila el proceso hijo (Amazon.java)
    public void compilaProceso() {

        // Comando que ejecuta el compilador javac
        String[] comando = {"javac", "-d", directorioGenerarClases, rutaFicheroJava};
        ProcessBuilder pb = new ProcessBuilder(comando);

        try {
            pb.redirectErrorStream(true); // Redirige los errores a la salida estándar
            pb.inheritIO(); // Hace que los mensajes de compilación se vean en consola
            Process p1 = pb.start(); // Inicia el proceso de compilación
            int exit = p1.waitFor(); // Espera a que termine antes de continuar

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método que ejecuta el proceso hijo para una provincia
    public int ejecutaProceso(String ruta, String provincia) {

        // Construyo el comando que ejecuta la clase Amazon con el nombre de la provincia como argumento
        String[] comando = {"java", "-cp", directorioGenerarClases, "Simulacro.Amazon", provincia};
        ProcessBuilder pb = new ProcessBuilder(comando);

        int numPedidos = 0; // Guardará cuántos pedidos tiene esa provincia

        try {
            // Lanzo el proceso hijo
            Process p = pb.start();

            // Leo la salida del hijo (lo que imprime con System.out.println)
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;

            // Recorro la salida línea por línea
            while ((linea = reader.readLine()) != null) {

                // Si la línea contiene un ":", significa que el hijo imprimió "Provincia : número"
                if (linea.contains(":")) {
                    // Separo el texto antes y después de los dos puntos
                    String[] partes = linea.split(":");

                    // La segunda parte (partes[1]) es el número de pedidos
                    numPedidos = Integer.parseInt(partes[1].trim());
                }

                // Imprimo la salida del hijo directamente (por ejemplo, "Sevilla : 9")
                System.out.println(linea);
            }

            // Espero a que el proceso hijo termine antes de continuar con el siguiente
            p.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // Devuelvo el número de pedidos para esa provincia
        return numPedidos;
    }
}

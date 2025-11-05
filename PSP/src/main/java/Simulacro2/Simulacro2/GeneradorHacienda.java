package Simulacro2.Simulacro2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneradorHacienda {

    // Rutas de los ficheros .java de los procesos hijos
    // Hecho así para poder compilarlos dinámicamente desde el programa padre
    private static final String rutaNSSJava = "src\\main\\java\\Simulacro2\\GeneraNSSFichero.java";
    private static final String rutaDNIJava = "src\\main\\java\\Simulacro2\\GeneraDNIFichero.java";

    // Carpeta donde se guardarán las clases compiladas (.class)
    private static final String directorioGenerarClases = "target\\classes";

    public static void main(String[] args) {

        // Creo una instancia del generador
        GeneradorHacienda generador = new GeneradorHacienda();

        // Fichero que se leerá (en este caso datos.txt en resources)
        String fichero = "src/main/resources/datos.txt";

        // Primero, compilo los procesos hijos antes de ejecutarlos
        generador.compilaProcesos();

        // Ejecuto los dos procesos hijos pasándoles el fichero como argumento
        // Cada proceso devolverá el número de registros procesados
        int totalNSS = generador.ejecutaProceso("Simulacro2.GeneraNSSFichero", fichero);
        int totalDNIs = generador.ejecutaProceso("Simulacro2.GeneraDNIFichero", fichero);

        // Muestro los resultados totales
        System.out.println("NSS tratados: " + totalNSS);
        System.out.println("DNIs tratados: " + totalDNIs);
    }

    // Método que compila los dos procesos .java con ProcessBuilder
    public void compilaProcesos() {

        // El comando javac compila ambos ficheros y los deja en target/classes
        String[] comando = {"javac", "-d", directorioGenerarClases, rutaNSSJava, rutaDNIJava};
        ProcessBuilder pb = new ProcessBuilder(comando);

        try {
            pb.redirectErrorStream(true); // Redirige errores a la salida estándar
            pb.inheritIO(); // Hereda la consola del padre (para ver los mensajes)
            Process p1 = pb.start(); // Lanza el proceso de compilación
            int exit = p1.waitFor(); // Espera a que termine

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método que ejecuta cada proceso hijo (java GeneraNSSFichero o GeneraDNIFichero)
    public int ejecutaProceso(String clase, String fichero) {

        // Construyo el comando de ejecución con el classpath correcto
        String[] comando = {"java", "-cp", directorioGenerarClases, clase, fichero};
        ProcessBuilder pb = new ProcessBuilder(comando);

        int resultado = 0;

        try {
            // Lanzo el proceso hijo
            Process p = pb.start();

            // Leo la salida estándar del proceso (lo que imprime con System.out.println)
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;

            // El proceso hijo imprime el número de líneas procesadas, que aquí se captura
            while ((linea = reader.readLine()) != null) {
                if (!linea.isEmpty()) {
                    resultado = Integer.parseInt(linea.trim()); // Guardo ese número
                }
                System.out.println(linea); // También muestro lo que diga el hijo
            }

            p.waitFor(); // Espero a que el proceso hijo termine

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // Devuelvo el número de registros procesados (NSS o DNI)
        return resultado;
    }
}

package Tema1.AnalizadorTemperaturas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Clase principal (Padre)
public class LanzadorAnalisisTemperaturas {
    
    // Ruta donde se encuentra el código fuente de AnalizadorTemperaturas.java
    private static final String rutaFicheroJava = "src\\main\\java\\tema1\\AnalizadorTemperaturas\\AnalizadorTemperaturas.java" ;
    
    // Directorio donde se generarán los archivos .class compilados
    private static final String directorioGenerarClases = "target\\classes";
    

    public static void main(String[] args) {
        
        // Creamos un objeto de la clase lanzadora
        LanzadorAnalisisTemperaturas lanzador = new LanzadorAnalisisTemperaturas();
        
        // Array con los valores de umbral que vamos a analizar
        int[] array = {10, 20, 25, 30, 35};
        
        // Ruta del fichero con los datos de temperatura
        String fichero = "src\\main\\java\\resources\\datos.txt";
        
        // 1️⃣ Primero compilamos la clase AnalizadorTemperaturas
        lanzador.compilaProceso();
        
        // 2️⃣ Después, lanzamos un proceso hijo por cada umbral
        for (int temp : array) {
            // Convertimos el umbral a String para pasarlo como argumento
            lanzador.ejecutaProceso(fichero, String.valueOf(temp));
        }
    }
    
    /**
     * Método que compila el fichero AnalizadorTemperaturas.java
     * usando el comando javac a través de un ProcessBuilder.
     */
    public void compilaProceso() {

        // Comando que se ejecutará en la terminal: javac -d target\classes src\main\java\tema1\AnalizadorTemperaturas\AnalizadorTemperaturas.java
        String[] comando = { "javac", "-d", directorioGenerarClases, rutaFicheroJava };
        ProcessBuilder pb = new ProcessBuilder(comando);
        
        try {
            // Redirigimos la salida de error al flujo normal
            pb.redirectErrorStream(true);
            // Hacemos que la salida del proceso se muestre en la consola actual
            pb.inheritIO();
            
            // Iniciamos el proceso de compilación
            Process p1 = pb.start();
            
            // Esperamos a que la compilación termine antes de continuar
            int exit = p1.waitFor();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que ejecuta el proceso hijo (AnalizadorTemperaturas)
     * con los argumentos:
     *  - nombre del fichero con temperaturas
     *  - valor umbral
     */
    public void ejecutaProceso(String ruta, String palabra) {
    
        // Comando que ejecutará la clase AnalizadorTemperaturas compilada:
        // java -cp target\classes Tema1.AnalizadorTemperaturas.AnalizadorTemperaturas <ruta> <umbral>
        String[] comando1 = {"java", "-cp", directorioGenerarClases, "Tema1.AnalizadorTemperaturas.AnalizadorTemperaturas", ruta, palabra};

        ProcessBuilder pb = new ProcessBuilder(comando1);

        try {
            // Redirigimos errores y salida estándar al mismo flujo
            pb.redirectErrorStream(true);
            // Hacemos que se vea la salida del proceso hijo en la misma consola
            pb.inheritIO();
            
            // Iniciamos el proceso hijo
            Process p1 = pb.start();
            
            // En este primer apartado NO esperamos a que terminen (no usamos waitFor())

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

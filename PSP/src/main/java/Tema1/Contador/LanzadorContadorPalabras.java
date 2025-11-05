package Tema1.Contador;

import java.io.IOException;

/**
 * Clase LanzadorContadorPalabras
 * Esta clase representa el PROCESO PADRE.
 * 
 * Su función es:
 *   - Compilar la clase hija (ContadorPalabras.java)
 *   - Lanzar varios procesos hijos, uno por cada palabra del array.
 *
 * En el apartado 1, solo se lanzan los procesos hijos (sin esperar ni leer resultados).
 * En el apartado 2, el padre esperará a que terminen y leerá los resultados.
 */
public class LanzadorContadorPalabras {

    // Ruta al archivo .java del hijo
    private static final String rutaFicheroJava = "src/main/java/Tema1/Contador/ContadorPalabras.java";
    
    // Directorio donde se generarán las clases compiladas
    private static final String directorioGenerarClases = "target";

    public static void main(String[] args) {
        
        // Creamos un objeto de esta misma clase para usar sus métodos
        LanzadorContadorPalabras lanzarPalabras = new LanzadorContadorPalabras();
        
        // 1️⃣ Compilamos la clase hija (ContadorPalabras.java)
        lanzarPalabras.compilaProceso();
        
        // 2️⃣ Ejecutamos los procesos hijos (uno por palabra)
        lanzarPalabras.ejecutaProceso();
    }

    /**
     * Método que se encarga de COMPILAR la clase hija
     * usando el comando: javac -d target ContadorPalabras.java
     */
    public void compilaProceso() {
        String[] comando = {"javac", "-d", directorioGenerarClases, rutaFicheroJava};
        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.redirectErrorStream(true); // Redirige los errores al flujo estándar
            pb.inheritIO(); // Muestra la salida en la consola actual
            Process p1 = pb.start(); // Inicia el proceso de compilación
            
            p1.waitFor(); // Espera a que termine antes de continuar
            
            System.out.println("Compilación completada correctamente");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que lanza los procesos hijos para analizar varias palabras.
     */
    public void ejecutaProceso() {
        
        // Nombre completo de la clase hija (incluyendo el paquete)
        String clase = "Tema1.Contador.ContadorPalabras";
        
        // En este ejemplo, se podrían leer de un array:
        // String[] palabras = {"servicio", "hilo", "proceso", "multihilo", "concurrencia"};
        // (Aquí se lanzan tres de ejemplo)
        String[] comando1 = {"java", "-cp", directorioGenerarClases, clase, "fichero.txt", "es"};
        String[] comando2 = {"java", "-cp", directorioGenerarClases, clase, "fichero.txt", "Java"};
        String[] comando3 = {"java", "-cp", directorioGenerarClases, clase, "fichero.txt", "y"};

        try {
            // Creamos y configuramos los tres ProcessBuilder (uno por palabra)
            ProcessBuilder pb1 = new ProcessBuilder(comando1);
            pb1.redirectErrorStream(true);
            pb1.inheritIO(); // Hereda la consola del proceso padre
            Process p1 = pb1.start(); // Inicia el proceso 1

            ProcessBuilder pb2 = new ProcessBuilder(comando2);
            pb2.redirectErrorStream(true);
            pb2.inheritIO();
            Process p2 = pb2.start(); // Inicia el proceso 2

            ProcessBuilder pb3 = new ProcessBuilder(comando3);
            pb3.redirectErrorStream(true);
            pb3.inheritIO();
            Process p3 = pb3.start(); // Inicia el proceso 3

            // Esperamos a que todos terminen (esto sería parte del Apartado 2)
            p1.waitFor();
            p2.waitFor();
            p3.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

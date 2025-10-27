package Tema1.Contador;

import java.io.IOException;

public class LanzadorContadorPalabras {

    private static final String rutaFicheroJava = "src/main/java/Tema1/Contador/ContadorPalabras.java";
    private static final String directorioGenerarClases = "target";

    public static void main(String[] args) {
        LanzadorContadorPalabras lanzarPalabras = new LanzadorContadorPalabras();
        lanzarPalabras.compilaProceso();
        lanzarPalabras.ejecutaProceso();
    }

    public void compilaProceso() {
        String[] comando = {"javac", "-d", directorioGenerarClases, rutaFicheroJava};
        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.redirectErrorStream(true);
            pb.inheritIO();
            Process p1 = pb.start();
            p1.waitFor();
            System.out.println("Compilación completada correctamente");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ejecutaProceso() {
        String clase = "Tema1.Contador.ContadorPalabras";

        String[] comando1 = {"java", "-cp", directorioGenerarClases, clase, "fichero.txt", "es"};
        String[] comando2 = {"java", "-cp", directorioGenerarClases, clase, "fichero.txt", "Java"};
        String[] comando3 = {"java", "-cp", directorioGenerarClases, clase, "fichero.txt", "y"};

        try {
            ProcessBuilder pb1 = new ProcessBuilder(comando1);
            pb1.redirectErrorStream(true);
            pb1.inheritIO();
            Process p1 = pb1.start();

            ProcessBuilder pb2 = new ProcessBuilder(comando2);
            pb2.redirectErrorStream(true);
            pb2.inheritIO();
            Process p2 = pb2.start();

            ProcessBuilder pb3 = new ProcessBuilder(comando3);
            pb3.redirectErrorStream(true);
            pb3.inheritIO();
            Process p3 = pb3.start();

            p1.waitFor();
            p2.waitFor();
            p3.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

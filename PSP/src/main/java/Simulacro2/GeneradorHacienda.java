package Simulacro2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneradorHacienda {

    private static final String rutaNSSJava = "src\\main\\java\\Simulacro2\\GeneraNSSFichero.java";
    private static final String rutaDNIJava = "src\\main\\java\\Simulacro2\\GeneraDNIFichero.java";

    private static final String directorioGenerarClases = "target\\classes";

    public static void main(String[] args) {

        GeneradorHacienda generador = new GeneradorHacienda();

        String fichero = "src/main/resources/datos.txt";

        generador.compilaProcesos();

        int totalNSS = generador.ejecutaProceso("Simulacro2.GeneraNSSFichero", fichero);
        int totalDNIs = generador.ejecutaProceso("Simulacro2.GeneraDNIFichero", fichero);

        System.out.println("NSS tratados: " + totalNSS);
        System.out.println("DNIs tratados: " + totalDNIs);
    }

    public void compilaProcesos() {

        String[] comando = {"javac", "-d", directorioGenerarClases, rutaNSSJava, rutaDNIJava};
        ProcessBuilder pb = new ProcessBuilder(comando);

        try {
            pb.redirectErrorStream(true);
            pb.inheritIO();
            Process p1 = pb.start();
            int exit = p1.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int ejecutaProceso(String clase, String fichero) {

        String[] comando = {"java", "-cp", directorioGenerarClases, clase, fichero};
        ProcessBuilder pb = new ProcessBuilder(comando);

        int resultado = 0;

        try {
            Process p = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;

            while ((linea = reader.readLine()) != null) {
            
                if (!linea.isEmpty()) {
                    resultado = Integer.parseInt(linea.trim());
                }
                System.out.println(linea);
            }

            p.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return resultado;
    }
}

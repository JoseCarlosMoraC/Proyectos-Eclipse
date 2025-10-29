package Simulacro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanzadorAmazon {

    private static final String rutaFicheroJava = "src\\main\\java\\Simulacro\\Amazon.java";
    private static final String directorioGenerarClases = "target\\classes";

    public static void main(String[] args) {

        LanzadorAmazon lanzador = new LanzadorAmazon();

        String[] provincias = {"Sevilla", "Málaga", "Granada", "Huelva", "Almería", "Jaén", "Córdoba", "Cádiz"};
        String fichero = "src/main/resources/pedidos.txt";

        lanzador.compilaProceso();

        int totalPedidos = 0;

        for (String temp : provincias) {
            int pedidos = lanzador.ejecutaProceso(fichero, temp);
            totalPedidos += pedidos;
        }

        System.out.println("Nº total de Pedidos : " + totalPedidos);
    }

    public void compilaProceso() {

        String[] comando = {"javac", "-d", directorioGenerarClases, rutaFicheroJava};
        ProcessBuilder pb = new ProcessBuilder(comando);

        try {
            pb.redirectErrorStream(true);
            pb.inheritIO();
            Process p1 = pb.start();
            int exit = p1.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int ejecutaProceso(String ruta, String provincia) {
        String[] comando = {"java", "-cp", directorioGenerarClases, "Simulacro.Amazon", provincia};
        ProcessBuilder pb = new ProcessBuilder(comando);

        int numPedidos = 0;

        try {
            Process p = pb.start();

       
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {

                if (linea.contains(":")) {
                    String[] partes = linea.split(":");
                    numPedidos = Integer.parseInt(partes[1].trim());
                }
                System.out.println(linea);
            }

            p.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return numPedidos;
    }
}

package pruebaEVUD1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanzadorTemperatura {
    private static final String rutaGeneraTemperatura = "src\\main\\java\\pruebaEVUD1\\GeneraTemperatura.java";
    private static final String rutaGeneraHumedad = "src\\main\\java\\pruebaEVUD1\\GeneraHumedad.java";
    private static final String rutaGeneraPresion = "src\\main\\java\\pruebaEVUD1\\GeneraPresion.java";
    
    private static final String directorioGenerarClases = "target\\classes";
    public static void main(String[] args) {

    	
    	LanzadorTemperatura generador = new LanzadorTemperatura();

        
        String fichero = "src/main/resources/lecturas.txt";

        generador.compilaProcesos();

        int totalTemperatura = generador.ejecutaProceso("pruebaEVUD1.GeneraTemperatura", fichero);
        int totalHumedad = generador.ejecutaProceso("pruebaEVUD1.GeneraHumedad", fichero);
        int totalPresion = generador.ejecutaProceso("pruebaEVUD1.GeneraPresion", fichero);
        

        System.out.println("---Prcoeso Padre: Resumen Resultados---");
        System.out.println("TEMPERATURA: "+ "NºRegistros:"+ totalTemperatura);
        System.out.println("HUMEDAD: "+ "NºRegistros:"+ totalHumedad);
        System.out.println("PRESION: "+ "NºRegistros:"+ totalPresion);
        
    }

    

    public void compilaProcesos() {


        String[] comando = {"javac", "-d", directorioGenerarClases, rutaGeneraTemperatura,rutaGeneraHumedad, rutaGeneraPresion};
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

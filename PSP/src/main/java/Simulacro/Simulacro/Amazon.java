package Simulacro.Simulacro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Amazon {

    // Ruta del fichero de pedidos. Aquí se encuentra toda la información a procesar.
    private static final String fichero = "src/main/resources/pedidos.txt";

    public static void main(String[] args) throws FileNotFoundException {
        // Compruebo que se pase una provincia como argumento
        // (de lo contrario, el proceso no sabría qué provincia filtrar)
        if (args.length < 1) {
            System.out.println("Se debe pasar la provincia como argumento");
            return;
        }

        String provincia = args[0]; // Guardo la provincia pasada como argumento
        Amazon contador = new Amazon();

        // Llamo al método que obtiene las líneas correspondientes a esa provincia
        List<String> lineas = contador.contadorDatos(provincia, fichero);

        // Creo el nombre del fichero de salida con la nomenclatura "pedidos.txt_Provincia.txt"
        String ficheroResultado = fichero + "_" + provincia + ".txt";

        // Escribo esas líneas en el nuevo fichero de la provincia
        contador.escribeFichero(ficheroResultado, lineas);

        // Cuento el número de líneas encontradas (pedidos)
        int numPedidos = lineas.size();

        // Imprimo el resultado en consola. 
        // El padre leerá esta salida para sumar los pedidos totales.
        System.out.println(provincia + ": " + numPedidos);
    }

    // Método que lee el fichero general de pedidos y extrae las líneas que contienen la provincia
    public List<String> contadorDatos(String pro, String ruta) throws FileNotFoundException {
        List<String> provincias = new ArrayList<>();
        Scanner in = null;

        try {
            FileReader f = new FileReader(ruta);
            in = new Scanner(f);

            // Leo línea por línea el fichero de pedidos
            while (in.hasNextLine()) {
                String linea = in.nextLine();

                // Si la línea contiene el nombre de la provincia, la añado a la lista
                if (linea.contains(pro)) {
                    provincias.add(linea);
                }
            }
        } finally {
            // Cierro el Scanner para liberar recursos
            if (in != null) {
                in.close();
            }
        }

        // Devuelvo la lista con los pedidos de esa provincia
        return provincias;
    }

    // Método para escribir en un fichero las líneas de la provincia
    public void escribeFichero(String ruta, List<String> resul) {
        PrintWriter wr = null;
        try {
            FileWriter ficheroResultado = new FileWriter(ruta);
            wr = new PrintWriter(ficheroResultado);

            // Recorro la lista e imprimo cada pedido en el fichero
            for (String linea : resul) {
                wr.println(linea);
            }

        } catch (IOException e) {
            System.out.println("Error, no se ha creado fichero");

        } finally {
            // Cierro el PrintWriter
            if (wr != null) {
                wr.close();
            }
        }
    }
}

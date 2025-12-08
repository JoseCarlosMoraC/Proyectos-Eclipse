package Simulacro2.Simulacro2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeneraNSSFichero {

    public static void main(String[] args) throws FileNotFoundException {

        // Compruebo que se haya pasado un argumento (el nombre del fichero de entrada)
        // Esto garantiza que el proceso no se ejecute sin saber qué fichero analizar.
        if (args.length < 1) {
            System.out.println("Se debe pasar el fichero como argumento");
            return;
        }

        String fichero = args[0]; // Guardo el nombre del fichero que recibo por argumento

        GeneraNSSFichero g = new GeneraNSSFichero();

        // Llamo al método que separa las líneas con NSS (empiezan por AN)
        List<String> lineas = g.separaNSS("AN", fichero);

        // Escribo todas las líneas encontradas en un nuevo fichero NSSs.txt
        g.escribeFichero("NSSs.txt", lineas);

        // Imprimo por pantalla el número de líneas encontradas (esto lo lee el proceso padre)
        System.out.println(lineas.size());
    }

    // Método que busca líneas que comiencen por "AN" (indicando un NSS)
    public List<String> separaNSS(String pro, String ruta) throws FileNotFoundException {
        List<String> listaNSS = new ArrayList<>();
        Scanner in = null;

        try {
            FileReader f = new FileReader(ruta);
            in = new Scanner(f);

            // Leo línea por línea el fichero de entrada
            while (in.hasNextLine()) {
                String linea = in.nextLine();

                // Compruebo si la línea comienza por "AN" y tiene al menos 12 caracteres (AN + 10 dígitos)
                if (linea.startsWith("TEMPERATURA") && linea.length() >= 12) {
                    // Si cumple, la añado a la lista de NSS encontrados
                    listaNSS.add(linea);
                }
            }

        } finally {
            // Cierro el Scanner para liberar el recurso
            if (in != null) in.close();
        }

        // Devuelvo la lista con los NSS encontrados
        return listaNSS;
    }

    // Método que escribe en un fichero las líneas con NSS
    public void escribeFichero(String ruta, List<String> resul) {
        PrintWriter wr = null;

        try {
            FileWriter ficheroResultado = new FileWriter(ruta);
            wr = new PrintWriter(ficheroResultado);

            // Escribo cada línea de la lista en el fichero de salida
            for (String linea : resul) wr.println(linea);

        } catch (IOException e) {
            System.out.println("Error, no se ha creado fichero");

        } finally {
            if (wr != null) wr.close();
        }
    }
}

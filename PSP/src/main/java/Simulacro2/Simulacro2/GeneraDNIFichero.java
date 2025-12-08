package Simulacro2.Simulacro2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeneraDNIFichero {

    public static void main(String[] args) throws FileNotFoundException {

        // Igual que en el proceso de NSS, comprobamos que se pase el fichero
        if (args.length < 1) {
            System.out.println("Se debe pasar el fichero como argumento");
            return;
        }

        String fichero = args[0];
        GeneraDNIFichero g = new GeneraDNIFichero();

        // Llamo al método que separa los DNIs del fichero
        List<String> lineas = g.separaDNIs(fichero);

        // Escribo las líneas encontradas en el fichero DNIs.txt
        g.escribeFichero("DNIs.txt", lineas);

        // Devuelvo al padre el número de DNIs procesados
        System.out.println(lineas.size());
    }

    // Método que separa las líneas que no son NSS (por lo tanto son DNIs)
    public List<String> separaDNIs(String ruta) throws FileNotFoundException {
        List<String> listaDNIs = new ArrayList<>();
        Scanner in = null;

        try {
            FileReader f = new FileReader(ruta);
            in = new Scanner(f);

            // Leo línea por línea
            while (in.hasNextLine()) {
                String linea = in.nextLine();

                // Si la línea no empieza por "AN", la considero un DNI
                if (!linea.startsWith("AN")) {
                    listaDNIs.add(linea);
                }
                // También incluyo líneas que empiecen por AN pero tengan menos de 12 caracteres
                // (por si no cumplen el formato completo de NSS)
                else {
                    if (linea.length() < 12) {
                        listaDNIs.add(linea);
                    }
                }
            }

        } finally {
            if (in != null) in.close();
        }

        return listaDNIs;
    }

    // Método que escribe los DNIs encontrados en su fichero correspondiente
    public void escribeFichero(String ruta, List<String> resul) {
        PrintWriter wr = null;

        try {
            FileWriter ficheroResultado = new FileWriter(ruta);
            wr = new PrintWriter(ficheroResultado);

            for (String linea : resul)
                wr.println(linea);

        } catch (IOException e) {
            System.out.println("Error, no se ha creado fichero");

        } finally {
            if (wr != null)
                wr.close();
        }
    }
}

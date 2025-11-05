package Simulacro2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeneraNSSFichero {

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 1) {
            System.out.println("Se debe pasar el fichero como argumento");
            return;
        }

        String fichero = args[0];
        GeneraNSSFichero g = new GeneraNSSFichero();

        List<String> lineas = g.separaNSS("AN", fichero);


        g.escribeFichero("NSSs.txt", lineas);

        System.out.println(lineas.size()); 
    }

    public List<String> separaNSS(String pro, String ruta) throws FileNotFoundException {
        List<String> listaNSS = new ArrayList<>();
        Scanner in = null;

        try {
            FileReader f = new FileReader(ruta);
            in = new Scanner(f);

            while (in.hasNextLine()) {
            	String linea = in.nextLine();


                if (linea.startsWith(pro) && linea.length() >= 12) {
                    listaNSS.add(linea);
                }
            }

        } finally {
            if (in != null) in.close();
        }

        return listaNSS;
    }

    public void escribeFichero(String ruta, List<String> resul) {
        PrintWriter wr = null;

        try {
            FileWriter ficheroResultado = new FileWriter(ruta);
            wr = new PrintWriter(ficheroResultado);

            for (String linea : resul) wr.println(linea);

        } catch (IOException e) {
            System.out.println("Error, no se ha creado fichero");

        } finally {
            if (wr != null) wr.close();
        }
    }
}

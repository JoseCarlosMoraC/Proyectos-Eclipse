package Simulacro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Amazon {
    private static final String fichero = "src/main/resources/pedidos.txt";

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Se debe pasar la provincia como argumento");
            return;
        }

        String provincia = args[0];
        Amazon contador = new Amazon();

       
        List<String> lineas = contador.contadorDatos(provincia, fichero);
        String ficheroResultado = fichero + "_" + provincia + ".txt";
        contador.escribeFichero(ficheroResultado, lineas);

        int numPedidos = lineas.size();
        System.out.println(provincia + ": " + numPedidos);
    }

    public List<String> contadorDatos(String pro, String ruta) throws FileNotFoundException {
        List<String> provincias = new ArrayList<>();
        Scanner in = null;

        try {
            FileReader f = new FileReader(ruta);
            in = new Scanner(f);

            while (in.hasNextLine()) {
                String linea = in.nextLine();
                if (linea.contains(pro)) {
                    provincias.add(linea);
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return provincias;
    }

    public void escribeFichero(String ruta, List<String> resul) {
        PrintWriter wr = null;
        try {
            FileWriter ficheroResultado = new FileWriter(ruta);
            wr = new PrintWriter(ficheroResultado);

            for (String linea : resul) {
                wr.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error, no se ha creado fichero");
        } finally {
            if (wr != null) {
                wr.close();
            }
        }
    }
}

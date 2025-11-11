package Simulacro.BancoDeAlimentos.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import Simulacro.BancoDeAlimentos.Models.CentroLogistico;
import Simulacro.BancoDeAlimentos.Models.Trabajador;

public class CreacionCSV {

    public void escribeCSV(List<CentroLogistico> centros, String ruta) {
        PrintWriter out = null;
        FileWriter fichero = null;

        try {
            fichero = new FileWriter(ruta);
            out = new PrintWriter(fichero);

            // Encabezado CSV
            out.printf("ID Centro,Nombre Centro,Ciudad,Comedores Abastecidos,Nombre Trabajador,DNI,Fecha Nacimiento,Tipo\n");

            // Recorremos cada centro y sus trabajadores
            for (CentroLogistico c : centros) {
                Set<Trabajador> trabajadores = c.getTrabajadores();
                if (trabajadores == null) {
                    trabajadores = new HashSet<>();
                }

                for (Trabajador t : trabajadores) {
                    out.printf(
                        Locale.US,
                        "%s,%s,%s,%d,%s,%s,%s,%s\n",
                        c.getId(),
                        c.getNombre(),
                        c.getCiudad(),
                        c.getNumComedores(),
                        t.getNombre(),
                        t.getDni(),
                        t.getFecha(),
                        t.getTipo()
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                    if (out != null) out.close();
                } catch (IOException e) {
                    System.out.println("Error al escribir el CSV del banco de alimentos");
                }
            }
        }
    }
}

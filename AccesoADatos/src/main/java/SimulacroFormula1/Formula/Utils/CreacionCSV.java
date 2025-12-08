package SimulacroFormula1.Formula.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import SimulacroFormula1.Formula.Models.Equipo;
import SimulacroFormula1.Formula.Models.Piloto;




public class CreacionCSV {

    public void escribeCSV(List<Equipo> equipos, String ruta) {
        PrintWriter out = null;
        FileWriter fichero = null;

        try {
            fichero = new FileWriter(ruta);
            out = new PrintWriter(fichero);

            // Encabezado CSV
            out.printf("ID Equipo,Nombre Equipo,Puntos Equipo,ID Piloto,Nombre Piloto,Puntos Piloto,Pais\n");

            // Recorremos cada equipo y sus pilotos
            for (Equipo e : equipos) {
                Set<Piloto> pilotos = e.getPilotos();
                if (pilotos == null || pilotos.isEmpty()) {
                    // escribir linea con campos de piloto vacíos
                    out.printf(Locale.US, "%s,%s,%d,%s,%s,%s,%s\n",
                            e.getIdentificadorEquipo(),
                            e.getNombreEquipo(),
                            e.getPuntos(),
                            "", "", "", "");
                } else {
                    for (Piloto p : pilotos) {
                        out.printf(Locale.US, "%s,%s,%d,%s,%s,%d,%s\n",
                                e.getIdentificadorEquipo(),
                                e.getNombreEquipo(),
                                e.getPuntos(),
                                p.getIdentificadorPiloto(),
                                p.getNombrePiloto(),
                                p.getPuntos(),
                                p.getPais()
                                );
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                    if (out != null) out.close();
                } catch (IOException e) {
                    System.out.println("Error al escribir el CSV de formula1");
                }
            }
        }
    }
}

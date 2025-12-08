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

/**
 * Clase encargada de generar archivos CSV.
 * Relacionado directamente con el APARTADO 4 del enunciado,
 * donde se pide generar un fichero que muestre la información
 * de los voluntarios registrados en el Banco de Alimentos.
 */
public class CreacionCSV {

    /**
     * Método encargado de escribir un archivo CSV con los centros logísticos
     * y los trabajadores que pertenecen a cada uno.
     *
     * @param centros Lista de centros logísticos cargados desde el XML.
     * @param ruta    Ruta donde se creará el fichero CSV.
     *
     * Este método recorre cada centro, y dentro de ellos,
     * recorre su lista de trabajadores para escribir sus datos en el CSV.
     * Se usa PrintWriter para escribir en formato texto.
     */
    public void escribeCSV(List<CentroLogistico> centros, String ruta) {
        PrintWriter out = null;
        FileWriter fichero = null;

        try {
            // Abrimos el fichero en modo escritura
            fichero = new FileWriter(ruta);
            out = new PrintWriter(fichero);

            // ESCRIBIMOS LA CABECERA DEL CSV (como pide el enunciado)
            out.printf("ID Centro,Nombre Centro,Ciudad,Comedores Abastecidos,Nombre Trabajador,DNI,Fecha Nacimiento,Tipo\n");

            // Recorremos cada centro
            for (CentroLogistico c : centros) {

                // Obtenemos el listado de trabajadores del centro
                Set<Trabajador> trabajadores = c.getTrabajadores();
                if (trabajadores == null) {
                    trabajadores = new HashSet<>();
                }

                // Por cada trabajador escribimos una línea en el CSV
                for (Trabajador t : trabajadores) {
                    out.printf(
                        Locale.US, // Para evitar problemas de formato numérico en diferentes sistemas
                        "%s,%s,%s,%d,%s,%s,%s,%s\n",
                        c.getId(),          // Identificador del centro
                        c.getNombre(),      // Nombre del centro
                        c.getCiudad(),      // Ciudad del centro
                        c.getNumComedores(),// Número de comedores abastecidos
                        t.getNombre(),      // Nombre del trabajador
                        t.getDni(),         // DNI (identificador único)
                        t.getFecha(),       // Fecha de nacimiento
                        t.getTipo()         // Tipo (ASALARIADO o VOLUNTARIO)
                    );
                }
            }

        } catch (IOException e) {
            // Si ocurre un error al escribir, lo mostramos
            e.printStackTrace();
        } finally {
            // Cerramos escritor y archivo correctamente
            if (fichero != null) {
                try {
                    fichero.close();
                    if (out != null) out.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el fichero CSV");
                }
            }
        }
    }
}

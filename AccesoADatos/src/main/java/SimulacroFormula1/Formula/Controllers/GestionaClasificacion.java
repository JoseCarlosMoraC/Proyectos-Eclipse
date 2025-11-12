package SimulacroFormula1.Formula.Controllers;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SimulacroFormula1.Formula.Exception.Formula1Exception;
import SimulacroFormula1.Formula.Models.Equipo;
import SimulacroFormula1.Formula.Models.Piloto;
import SimulacroFormula1.Formula.Repositories.RepositorioEquipos;
import SimulacroFormula1.Formula.Utils.CreacionCSV;
import SimulacroFormula1.Formula.Utils.XMLDomFormula1;


/*
 * Clase principal del programa.
 * El enunciado indica que debemos:
 *  1) Leer la información desde un fichero XML.
 *  2) Cargarla en estructuras en memoria (Set<Equipo> y Pilotos asociados).
 *  3) Generar un CSV con los equipos y sus pilotos.
 *  4) Consultar pilotos con más puntos que un valor dado.
 *  5) Generar un fichero JSON con el resultado del filtrado.
 */
public class GestionaClasificacion {

    private static final Logger logger = LogManager.getLogger(GestionaClasificacion.class);

    public static void main(String[] args) {

        XMLDomFormula1 xml = new XMLDomFormula1(); // Clase que gestiona la lectura DOM del XML
        RepositorioEquipos repo = new RepositorioEquipos(); // Repositorio que gestiona la lógica de negocio

        try {
            // 1) Leer equipos desde el XML y guardarlos en un Set para evitar duplicados
            List<Equipo> equipos = xml.leerEquiposDesdeXML("formula1.xml");
            Set<Equipo> setEquipos = new HashSet<Equipo>(equipos);
            repo.setListaEquipos(setEquipos);

            // 2) Leer pilotos desde el mismo XML
            List<Piloto> pilotos = xml.leerPilotosDesdeXML("formula1.xml");

            // 3) Asignar cada piloto a su equipo mediante el repositorio
            //    Aquí solo se pasa el piloto, y el repositorio busca el equipo comparando los ID.
            for (Piloto p : pilotos) {
                try {
                    repo.agregarPilotoAEquipo(p);
                } catch (Formula1Exception fe) {
                    logger.error("Error añadiendo piloto " + p.getIdentificadorPiloto() + ": " + fe.getMessage());
                }
            }

            // 4) Crear el archivo CSV con toda la información equipo-piloto
            CreacionCSV creador = new CreacionCSV();
            creador.escribeCSV(equipos, "src/main/resources/formula1_equipos_pilotos.csv");

            // 5) Obtener los pilotos que tienen más de 4 puntos (valor fijado según enunciado)
            List<Piloto> listaFiltrados = repo.getPilotosConPuntosMayor(4);

            // 6) Generar JSON con la lista de pilotos filtrados usando GSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(listaFiltrados);

            try (PrintWriter pw = new PrintWriter("src/main/resources/pilotosPuntosMayor4.json")) {
                pw.println(json);
                logger.info("JSON generado correctamente en src/main/resources/pilotosPuntosMayor4.json");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package Simulacro.BancoDeAlimentos.Controllers;

import java.util.HashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Simulacro.BancoDeAlimentos.Exception.BancoException;
import Simulacro.BancoDeAlimentos.Models.CentroLogistico;
import Simulacro.BancoDeAlimentos.Models.Trabajador;
import Simulacro.BancoDeAlimentos.Services.ServicioBancoAlimentos;
import Simulacro.BancoDeAlimentos.Utils.CreacionCSV;
import Simulacro.BancoDeAlimentos.Utils.XMLBancoAlimentos;     // Para escribir XML
import Simulacro.BancoDeAlimentos.Utils.XMLDomBancoAlimentos; // Para leer XML
import Simulacro.BancoDeAlimentos.Utils.BancoAlimentosAJson; // Para JSON

public class GestionaBancoAlimentos {

    private static final Logger logger = LogManager.getLogger(GestionaBancoAlimentos.class);

    public static void main(String[] args) {

        // 1️⃣ Instanciamos el lector DOM para leer el XML original
        XMLDomBancoAlimentos lectorXML = new XMLDomBancoAlimentos();

        // 2️⃣ Instanciamos el servicio que gestiona la lógica de negocio
        ServicioBancoAlimentos servicio = new ServicioBancoAlimentos();

        // 3️⃣ Instanciamos el escritor de XML para exportar datos
        XMLBancoAlimentos escritorXML = new XMLBancoAlimentos();

        // 4️⃣ Instanciamos el escritor JSON
        BancoAlimentosAJson escritorJSON = new BancoAlimentosAJson();

        try {
            // 5️⃣ Leer los centros desde el XML usando DOM
            List<CentroLogistico> centros = lectorXML.leerCentroLogisticoDesdeXML("bancoAlimentos.xml");

            // 6️⃣ Pasamos los centros al servicio (actualiza internamente el repositorio)
            servicio.setCentros(new HashSet<>(centros));

            // 7️⃣ Mostrar centros cargados
            logger.info("Centros cargados: " + servicio.getCentros());

            // 8️⃣ Mostrar asalariados
            logger.info("=== ASALARIADOS ===");
            for (Trabajador t : servicio.getColaboradoresPorTipo("asalariado")) {
                logger.info(t);
            }

            // 9️⃣ Mostrar voluntarios
            logger.info("=== VOLUNTARIOS ===");
            for (Trabajador t : servicio.getColaboradoresPorTipo("voluntario")) {
                logger.info(t);
            }

            // 🔟 Generar CSV con la lista de centros y trabajadores
            CreacionCSV creadorCSV = new CreacionCSV();
            creadorCSV.escribeCSV(centros, "src/main/resources/bancoAlimentos.csv");
            logger.info("CSV generado correctamente");

            // 1️⃣1️⃣ Generar XML de salida (misma estructura que el original)
            escritorXML.escribeCentrosEnXML("bancoAlimentosSalida.xml", centros);
            logger.info("XML generado correctamente: bancoAlimentosSalida.xml");

            // 1️⃣2️⃣ Generar JSON de salida
            escritorJSON.escribeCentrosJson(centros, "bancoAlimentosSalida.json");
            logger.info("JSON generado correctamente: bancoAlimentosSalida.json");

        } catch (BancoException e) {
            logger.error("Error del banco: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

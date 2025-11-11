package Simulacro.BancoDeAlimentos.Controllers;

import java.util.HashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Simulacro.BancoDeAlimentos.Exception.BancoException;
import Simulacro.BancoDeAlimentos.Models.CentroLogistico;
import Simulacro.BancoDeAlimentos.Models.Trabajador;
import Simulacro.BancoDeAlimentos.Repositories.BancoAlimentos;
import Simulacro.BancoDeAlimentos.Utils.CreacionCSV;
import Simulacro.BancoDeAlimentos.Utils.XMLDomBancoAlimentos;

public class GestionaBancoAlimentos {

    private static final Logger logger = LogManager.getLogger(GestionaBancoAlimentos.class);

    public static void main(String[] args) {

        XMLDomBancoAlimentos xmlbanco = new XMLDomBancoAlimentos();
        BancoAlimentos banco = new BancoAlimentos();

        try {
            // Leemos todos los centros desde el XML
            List<CentroLogistico> centros = xmlbanco.leerCentroLogisticoDesdeXML("bancoAlimentos.xml");

            // Guardamos los centros dentro del banco 👇
            banco.setListaCentros(new HashSet<>(centros));

            // Mostramos la info completa del banco
            logger.info(centros);

            //Aquí mostramos colaboradores por tipo
            logger.info("Lista de Asalariados");
            List<Trabajador> asalariados = banco.getColaboradoresPorTipo("asalariado");
            for (Trabajador t : asalariados) {
                logger.info(t);
            }

            logger.info("Lista de Voluntarios");
            List<Trabajador> voluntarios = banco.getColaboradoresPorTipo("voluntario");
            for (Trabajador t : voluntarios) {
                logger.info(t);
            }

            // 🧾 Ahora sí: generamos el CSV con todos los datos cargados
            CreacionCSV creador = new CreacionCSV();
            creador.escribeCSV(centros, "src/main/resources/bancoAlimentos.csv");
            logger.info("CSV generado correctamente en bancoAlimentos.csv");

        } catch (BancoException e) {
            logger.error("Error del banco: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

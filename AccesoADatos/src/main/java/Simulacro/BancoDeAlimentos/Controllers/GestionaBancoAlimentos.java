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

    // Logger para mostrar mensajes según el enunciado (uso de log4j en lugar de System.out)
    private static final Logger logger = LogManager.getLogger(GestionaBancoAlimentos.class);

    public static void main(String[] args) {

        // Creamos un objeto para leer los datos desde el XML mediante DOM
        XMLDomBancoAlimentos xmlbanco = new XMLDomBancoAlimentos();

        // Creamos el repositorio principal que gestionará los centros y trabajadores
        BancoAlimentos banco = new BancoAlimentos();

        try {
            // Apartado 2: Carga de datos desde el archivo XML
            // Se leen todos los centros logísticos junto con sus trabajadores ya asociados
            List<CentroLogistico> centros = xmlbanco.leerCentroLogisticoDesdeXML("bancoAlimentos.xml");

            // Guardamos los centros en el objeto BancoAlimentos usando un HashSet para evitar duplicados
            banco.setListaCentros(new HashSet<>(centros));

            // Mostramos la información completa cargada
            logger.info(centros);

            // Apartado 3: Obtener colaboradores según su tipo
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

            // Apartado 4: Generar archivo CSV con la información de los centros y trabajadores
            CreacionCSV creador = new CreacionCSV();
            creador.escribeCSV(centros, "src/main/resources/bancoAlimentos.csv");
            logger.info("CSV generado correctamente en bancoAlimentos.csv");

        } catch (BancoException e) {
            // Control de errores definidos según las reglas del sistema
            logger.error("Error del banco: " + e.getMessage());

        } catch (Exception e) {
            // Manejo de excepciones generales (fallos inesperados)
            e.printStackTrace();
        }
    }
}
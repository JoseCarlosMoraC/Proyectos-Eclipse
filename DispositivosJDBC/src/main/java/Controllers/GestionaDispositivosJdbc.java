package Controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.Dispositivo;
import Models.Especificacion;
import Models.Valoracion;
import Services.ServicioDispositivosJdbc;

public class GestionaDispositivosJdbc {

    private static final Logger logger = LogManager.getLogger(GestionaDispositivosJdbc.class);

    public static void main(String[] args) {

        try {
            ServicioDispositivosJdbc servicio = new ServicioDispositivosJdbc();

            // CREAR DISPOSITIVOS
            Dispositivo d1 = new Dispositivo(0, "Router TP-Link", "Redes", 59.99, 10);
            Dispositivo d2 = new Dispositivo(0, "Smartphone Samsung", "Movil", 399.99, 5);
            Dispositivo d3 = new Dispositivo(0, "Portátil Lenovo", "Informatica", 799.99, 3);

            servicio.addDispositivo(d1);
            servicio.addDispositivo(d2);
            servicio.addDispositivo(d3);

            logger.info("Dispositivos creados correctamente");

            // CONSULTA: dispositivos por categoría
            List<Dispositivo> moviles = servicio.dispositivosPorCategoria("Movil");
            logger.info("Dispositivos categoría Movil: " + moviles);

            // CONSULTA: puntuación media por dispositivo
            logger.info("Puntuación media por dispositivo:");
            logger.info(servicio.puntuacionMediaPorDispositivo());

            // CONSULTA: stock total
            logger.info("Stock total disponible: " + servicio.stockTotal());

            // CONSULTA: dispositivos ordenados por precio desc
            logger.info("Dispositivos ordenados por precio (desc):");
            logger.info(servicio.dispositivosOrdenadosPorPrecio());

        } catch (MiExcepcion e) {
            logger.error("ERROR CONTROLADO: " + e.getMessage());
        }
    }
}

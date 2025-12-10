package Controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.client.MongoDatabase;

import Config.MongoDBConexion;
import Models.Habitacion;
import Models.Hotel;
import Services.HotelService;

public class GestionaPocoAPoco {

    private static final Logger logger = LogManager.getLogger(GestionaPocoAPoco.class);

    public static void main(String[] args) {

        // Conexión a MongoDB
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();

        // Crear servicio
        HotelService hotelService = new HotelService(db);

        logger.info("=== INICIO DE LAS PRUEBAS ===");

        // ------------------------------
        // EJERCICIO 1: Listar todos los hoteles
        logger.info("--- EJERCICIO 1: Listado de todos los hoteles ---");
        List<Hotel> todosHoteles = hotelService.read();
        if (todosHoteles.isEmpty()) {
            logger.warn("No se encontraron hoteles.");
        } else {
            logger.info("Total de hoteles encontrados: " + todosHoteles.size());
            for (Hotel h : todosHoteles) {
                logger.info(h.getIdHotel() + " - " + h.getNombre() +
                            " (" + h.getEstrellas() + " estrellas)");
            }
        }

        // ------------------------------
        // EJERCICIO 2: Buscar hotel por ID
        logger.info("--- EJERCICIO 2: Buscar hotel por ID (h101) ---");
        Hotel hotel101 = hotelService.buscarHotel("h101");
        if (hotel101 != null) {
            logger.info("Hotel encontrado: " + hotel101.getNombre() + " - " + hotel101.getUbicacion().getCalle());
        } else {
            logger.warn("Hotel con ID 'h101' no encontrado.");
        }

        // ------------------------------
        // EJERCICIO 3: Hoteles en Madrid (5 estrellas o permiten mascotas)
        logger.info("--- EJERCICIO 3: Hoteles en Madrid ---");
        List<Hotel> hotelesMadrid = hotelService.buscarHotelesMadrid();
        logger.info("Hoteles encontrados en Madrid: " + hotelesMadrid.size());
        if (hotelesMadrid.isEmpty()) {
            logger.warn("No se encontraron hoteles en Madrid.");
        } else {
            for (Hotel h : hotelesMadrid) {
                logger.info("- " + h.getNombre() + " (CP: " +
                            h.getUbicacion().getCodigoPostal() + ", Estrellas: " +
                            h.getEstrellas() + ", Mascotas: " + h.isAdmiteMascotas() + ")");
            }
        }

        // ------------------------------
        // EJERCICIO 4: Contar hoteles con Suite Junior
        logger.info("--- EJERCICIO 4: Hoteles con Suite Junior ---");
        int numHotelesSuite = hotelService.contarHotelesConSuiteJunior();
        logger.info("Número de hoteles con Suite Junior: " + numHotelesSuite);
        if (numHotelesSuite == 0) {
            logger.warn("No se encontraron hoteles con Suite Junior.");
        }

        // ------------------------------
        // EJERCICIO 5: Añadir Penthouse al hotel h101
        logger.info("--- EJERCICIO 5: Añadir Penthouse al hotel h101 ---");
        hotelService.agregarHabitacionPenthouse("h101");
        logger.info("Penthouse añadida correctamente al hotel h101.");

        // Verificar habitaciones después de añadir la Penthouse
        hotel101 = hotelService.buscarHotel("h101");
        if (hotel101 != null) {
            logger.info("Habitaciones actuales en h101:");
            for (Habitacion hab : hotel101.getHabitaciones()) {
                logger.info("  - " + hab.getTipo() + ": " + hab.getPrecio() + "€");
            }
        } else {
            logger.error("No se pudo encontrar el hotel h101 después de agregar la Penthouse.");
        }

        // ------------------------------
        // EJERCICIO 6: Actualizar código postal de Gran Vía a 28013
        logger.info("--- EJERCICIO 6: Actualizar código postal de Gran Vía a 28013 ---");
        hotelService.actualizarCodigoPostalGranVia();
        logger.info("Códigos postales de los hoteles en Gran Vía actualizados a 28013.");

        // Verificar actualización del código postal
        hotel101 = hotelService.buscarHotel("h101");
        if (hotel101 != null) {
            logger.info("Nuevo código postal de " + hotel101.getNombre() + ": " +
                        hotel101.getUbicacion().getCodigoPostal());
        } else {
            logger.error("No se pudo actualizar el código postal para el hotel h101.");
        }

        // ------------------------------
        // EJERCICIO 7: Actualizar precio habitación Individual en h101
        logger.info("--- EJERCICIO 7: Actualizar precio de habitación Individual en h101 ---");
        hotelService.actualizarPrecioIndividualH101();
        logger.info("Precio actualizado de la habitación Individual a 90.00€.");

        // Verificar precio actualizado de la habitación
        hotel101 = hotelService.buscarHotel("h101");
        if (hotel101 != null) {
            for (Habitacion hab : hotel101.getHabitaciones()) {
                if ("Individual".equals(hab.getTipo())) {
                    logger.info("Nuevo precio Individual: " + hab.getPrecio() + "€");
                }
            }
        } else {
            logger.error("No se pudo encontrar el hotel h101 después de actualizar el precio.");
        }

        // ------------------------------
        // EJERCICIO 8: Eliminar habitaciones > 300€ en Grand Hotel Central
        logger.info("--- EJERCICIO 8: Eliminar habitaciones > 300€ en Grand Hotel Central ---");
        hotelService.eliminarHabitacionesCarasGrandHotel();
        logger.info("Habitaciones con precio superior a 300€ eliminadas en Grand Hotel Central.");

        // Verificar habitaciones restantes después de la eliminación
        hotel101 = hotelService.buscarHotel("Grand Hotel Central");
        if (hotel101 != null) {
            logger.info("Habitaciones restantes en Grand Hotel Central:");
            for (Habitacion hab : hotel101.getHabitaciones()) {
                logger.info("  - " + hab.getTipo() + ": " + hab.getPrecio() + "€");
            }
        } else {
            logger.error("No se pudo encontrar el hotel 'Grand Hotel Central'.");
        }

        // ------------------------------
        // EJERCICIO 9: Calcular media de estrellas en Barcelona
        logger.info("--- EJERCICIO 9: Calcular media de estrellas en Barcelona ---");
        double mediaBarcelona = hotelService.calcularMediaEstrellasBarcelona();
        logger.info(String.format("Media de estrellas en Barcelona: %.2f", mediaBarcelona));
        if (mediaBarcelona == 0.0) {
            logger.warn("No se encontraron hoteles en Barcelona para calcular la media de estrellas.");
        }

        logger.info("=== FIN DE LAS PRUEBAS ===");
    }
}

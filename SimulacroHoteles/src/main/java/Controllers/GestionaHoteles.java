package Controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.client.MongoDatabase;

import Config.MongoDBConexion;
import Models.Habitacion;
import Models.Hotel;
import Services.HotelService;

public class GestionaHoteles {

    private static final Logger logger = LogManager.getLogger(GestionaHoteles.class);

    public static void main(String[] args) {

        // Conexión a MongoDB
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();

        // Crear servicio
        HotelService hotelService = new HotelService(db);

        logger.info("=== EJERCICIO 4: CRUD Completo ===");

        // Listar todos los hoteles
        logger.info("--- Listado de todos los hoteles ---");
        List<Hotel> todosHoteles = hotelService.read();
        for (Hotel h : todosHoteles) {
            logger.info(h.getIdHotel() + " - " + h.getNombre() +
                        " (" + h.getEstrellas() + " estrellas)");
        }

        // Buscar hotel por ID
        logger.info("--- Buscar hotel h101 ---");
        Hotel hotel101 = hotelService.buscarHotel("h101");
        if (hotel101 != null) {
            logger.info("Encontrado: " + hotel101.getNombre() +
                        " - " + hotel101.getUbicacion().getCalle());
        }

        // Hoteles en Madrid (5 estrellas o mascotas)
        logger.info("=== EJERCICIO 5: Hoteles en Madrid ===");
        List<Hotel> hotelesMadrid = hotelService.buscarHotelesMadrid();
        logger.info("Hoteles encontrados en Madrid: " + hotelesMadrid.size());
        for (Hotel h : hotelesMadrid) {
            logger.info("- " + h.getNombre() + " (CP: " +
                        h.getUbicacion().getCodigoPostal() + ", Estrellas: " +
                        h.getEstrellas() + ", Mascotas: " + h.isAdmiteMascotas() + ")");
        }

        // Contar hoteles con Suite Junior
        logger.info("=== EJERCICIO 6: Hoteles con Suite Junior ===");
        int numHotelesSuite = hotelService.contarHotelesConSuiteJunior();
        logger.info("Número de hoteles con Suite Junior: " + numHotelesSuite);

        // Añadir Penthouse al hotel h101
        logger.info("=== EJERCICIO 7: Añadir Penthouse al hotel h101 ===");
        hotelService.agregarHabitacionPenthouse("h101");
        logger.info("Penthouse añadida correctamente");

        // Verificar habitaciones
        hotel101 = hotelService.buscarHotel("h101");
        if (hotel101 != null) {
            logger.info("Habitaciones actuales en h101:");
            for (Habitacion hab : hotel101.getHabitaciones()) {
                logger.info("  - " + hab.getTipo() + ": " + hab.getPrecio() + "€");
            }
        }

        // Actualizar código postal de Gran Vía a 28013
        logger.info("=== EJERCICIO 8: Actualizar CP de Gran Vía a 28013 ===");
        hotelService.actualizarCodigoPostalGranVia();
        logger.info("Códigos postales actualizados");

        // Actualizar precio habitación Individual en h101
        logger.info("=== EJERCICIO 9: Actualizar precio Individual en h101 ===");
        hotelService.actualizarPrecioIndividualH101();
        logger.info("Precio actualizado a 90.00€");

        // Verificar precio
        hotel101 = hotelService.buscarHotel("h101");
        if (hotel101 != null) {
            for (Habitacion hab : hotel101.getHabitaciones()) {
                if ("Individual".equals(hab.getTipo())) {
                    logger.info("Nuevo precio Individual: " + hab.getPrecio() + "€");
                }
            }
        }

        // Eliminar habitaciones > 300€ en Grand Hotel Central
        logger.info("=== EJERCICIO 10: Eliminar habitaciones > 300€ ===");
        hotelService.eliminarHabitacionesCarasGrandHotel();
        logger.info("Habitaciones eliminadas");

        // Verificar habitaciones restantes
        hotel101 = hotelService.buscarHotel("h101");
        if (hotel101 != null) {
            logger.info("Habitaciones restantes en h101:");
            for (Habitacion hab : hotel101.getHabitaciones()) {
                logger.info("  - " + hab.getTipo() + ": " + hab.getPrecio() + "€");
            }
        }

        // Calcular media de estrellas en Barcelona
        logger.info("=== EJERCICIO 11: Media de estrellas en Barcelona ===");
        double mediaBarcelona = hotelService.calcularMediaEstrellasBarcelona();
        logger.info(String.format("Media de estrellas en Barcelona: %.2f", mediaBarcelona));

        logger.info("=== FIN DE LAS PRUEBAS ===");
    }
}

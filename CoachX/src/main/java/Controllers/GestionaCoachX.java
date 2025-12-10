package Controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.client.MongoDatabase;

import Config.MongoDBConexion;


public class GestionaCoachX {

    private static final Logger logger = LogManager.getLogger(GestionaHoteles.class);

    public static void main(String[] args) {

        // Conexión a MongoDB
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();  // Obtenemos la conexión a la base de datos

        // Crear el servicio para interactuar con la base de datos
        HotelService service = new HotelService(db);

        // Iniciar los ejercicios
        logger.info("=== INICIO DE LA GESTIÓN DE HOTELES ===");

        // -----------------------------
        // EJERCICIO 1: Listar todos los hoteles
        logger.info("--- Listado de todos los hoteles ---");
        List<Hotel> todosHoteles = service.read();  // Llamamos al método read() del servicio
        for (Hotel h : todosHoteles) {
            logger.info(h.getIdHotel() + " - " + h.getNombre() +
                        " (" + h.getEstrellas() + " estrellas)");
        }

        // -----------------------------
        // EJERCICIO 2: Buscar hotel por ID
        logger.info("--- Buscar hotel con ID h101 ---");
        Hotel hotel101 = service.buscarHotel("h101");
        if (hotel101 != null) {
            logger.info("Encontrado: " + hotel101.getNombre() +
                        " - " + hotel101.getUbicacion().getCalle());
        } else {
            logger.warn("Hotel h101 no encontrado.");
        }

        // -----------------------------
        // EJERCICIO 3: Hoteles en Madrid (5 estrellas o mascotas)
        logger.info("=== EJERCICIO 3: Hoteles en Madrid ===");
        List<Hotel> hotelesMadrid = service.buscarHotelesMadrid();  // Llamamos al método buscarHotelesMadrid
        logger.info("Hoteles encontrados en Madrid: " + hotelesMadrid.size());
        for (Hotel h : hotelesMadrid) {
            logger.info("- " + h.getNombre() + " (CP: " +
                        h.getUbicacion().getCodigoPostal() + ", Estrellas: " +
                        h.getEstrellas() + ", Mascotas: " + h.isAdmiteMascotas() + ")");
        }

        // -----------------------------
        // EJERCICIO 4: Número de hoteles con Suite Junior
        logger.info("=== EJERCICIO 4: Número de hoteles con Suite Junior ===");
        int numHotelesSuite = service.getHotelesSuiteJuniorDisponibles();  // Llamamos al método getHotelesSuiteJuniorDisponibles
        logger.info("Número de hoteles con Suite Junior: " + numHotelesSuite);

        // -----------------------------
        // EJERCICIO 5: Añadir Penthouse al hotel h101
        logger.info("=== EJERCICIO 5: Añadir Penthouse al hotel h101 ===");
        service.agregarHabitacionPenthouse("h101");  // Llamamos al método agregarHabitacionPenthouse
        logger.info("Penthouse añadida correctamente al hotel h101.");

        // Verificar habitaciones después de añadir la Penthouse
        hotel101 = service.buscarHotel("h101");
        if (hotel101 != null) {
            logger.info("Habitaciones actuales en h101:");
            for (Habitacion hab : hotel101.getHabitaciones()) {
                logger.info("  - " + hab.getTipo() + ": " + hab.getPrecio() + "€");
            }
        }

        // -----------------------------
        // EJERCICIO 6: Actualizar código postal de Gran Vía a 28013
        logger.info("=== EJERCICIO 6: Actualizar código postal de Gran Vía a 28013 ===");
        service.actualizarCodigoPostalGranVia("28013");  // Llamamos al método actualizarCodigoPostalGranVia
        logger.info("Códigos postales de hoteles en Gran Vía actualizados a 28013.");

        // Verificar actualización de código postal
        hotel101 = service.buscarHotel("h101");
        if (hotel101 != null) {
            logger.info("Nuevo código postal de " + hotel101.getNombre() + ": " + 
                        hotel101.getUbicacion().getCodigoPostal());
        }

        // -----------------------------
        // EJERCICIO 7: Actualizar precio habitación Individual en h101
        logger.info("=== EJERCICIO 7: Actualizar precio de habitación Individual en h101 ===");
        service.actualizarPrecioIndividualH101();  // Llamamos al método actualizarPrecioIndividualH101
        logger.info("Precio de habitación Individual en h101 actualizado a 90.00€.");

        // Verificar precio actualizado de la habitación Individual
        hotel101 = service.buscarHotel("h101");
        if (hotel101 != null) {
            for (Habitacion hab : hotel101.getHabitaciones()) {
                if ("Individual".equals(hab.getTipo())) {
                    logger.info("Nuevo precio Individual: " + hab.getPrecio() + "€");
                }
            }
        }

        // -----------------------------
        // EJERCICIO 8: Eliminar habitaciones > 300€ en Grand Hotel Central
        logger.info("=== EJERCICIO 8: Eliminar habitaciones > 300€ en Grand Hotel Central ===");
        service.eliminarHabitacionesCarasGrandHotel();  // Llamamos al método eliminarHabitacionesCarasGrandHotel
        logger.info("Habitaciones con precio superior a 300€ eliminadas en Grand Hotel Central.");

        // Verificar habitaciones restantes después de la eliminación
        hotel101 = service.buscarHotel("Grand Hotel Central");
        if (hotel101 != null) {
            logger.info("Habitaciones restantes en Grand Hotel Central:");
            for (Habitacion hab : hotel101.getHabitaciones()) {
                logger.info("  - " + hab.getTipo() + ": " + hab.getPrecio() + "€");
            }
        }

        // -----------------------------
        // EJERCICIO 9: Calcular media de estrellas en Barcelona
        logger.info("=== EJERCICIO 9: Calcular media de estrellas en Barcelona ===");
        double mediaBarcelona = service.calcularMediaEstrellasBarcelona();  // Llamamos al método calcularMediaEstrellasBarcelona
        logger.info(String.format("Media de estrellas en Barcelona: %.2f", mediaBarcelona));

        // -----------------------------
        // EJERCICIO 10: Eliminar habitaciones con precio > 300 del Grand Hotel Central
        logger.info("=== EJERCICIO 10: Eliminar habitaciones > 300€ del Grand Hotel Central ===");
        service.eliminarHabitacionesCaras("Grand Hotel Central", 300.00);  // Llamamos al método eliminarHabitacionesCaras con precio 300€
        logger.info("Habitaciones con precio superior a 300€ eliminadas en Grand Hotel Central.");

        // Verificar habitaciones restantes después de la eliminación
        hotel101 = service.buscarHotel("Grand Hotel Central");
        if (hotel101 != null) {
            logger.info("Habitaciones restantes en Grand Hotel Central:");
            for (Habitacion hab : hotel101.getHabitaciones()) {
                logger.info("  - " + hab.getTipo() + ": " + hab.getPrecio() + "€");
            }
        }

        // -----------------------------
        // EJERCICIO 11: Calcular media de estrellas en Barcelona
        logger.info("=== EJERCICIO 11: Calcular media de estrellas en Barcelona ===");
        double mediaEstrellasBarcelona = service.calcularMediaEstrellasBarcelona();  // Llamamos al método calcularMediaEstrellasBarcelona
        logger.info(String.format("Media de estrellas en Barcelona: %.2f", mediaEstrellasBarcelona));

        logger.info("=== FIN DE LAS PRUEBAS ===");
    }
}

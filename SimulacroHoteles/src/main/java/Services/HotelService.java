package Services;

import java.util.List;

import com.mongodb.client.MongoDatabase;

import Models.Habitacion;
import Models.Hotel;
import Models.Tipo;
import Repositories.HotelRepository;

public class HotelService {
	private final HotelRepository repo;

	public HotelService(MongoDatabase db) {
		super();
		this.repo = new HotelRepository(db);
	}

	// ------------------------
	// CRUD BÁSICO
	// ------------------------
	public void save(Hotel h) {
		repo.save(h);
	}

	public List<Hotel> read() {
		return repo.read();
	}

	public void crearHotel(Hotel h) {
		repo.crearHotel(h);
	}

	public Hotel buscarHotel(String id) {
		return repo.getHotelPorId(id);
	}

	public void delete(String id) {
		repo.delete(id);
	}

	public void update(Hotel hotel) {
		repo.update(hotel);
	}

	// ------------------------
	// EJERCICIO 5: Buscar hoteles en Madrid
	// ------------------------
	public List<Hotel> buscarHotelesMadrid() {
		return repo.buscarHotelesMadrid();
	}

	// ------------------------
	// EJERCICIO 6: Contar hoteles con Suite Junior
	// ------------------------
	public int contarHotelesConSuiteJunior() {
		return repo.numHotelesConSuiteJunior();
	}

	// ------------------------
	// EJERCICIO 7: Añadir habitación Penthouse
	// ------------------------
	public void agregarHabitacionPenthouse(String idHotel) {
		Habitacion penthouse = new Habitacion();
		// Acceder directamente a la constante del enum Tipo
		penthouse.setTipo(Tipo.Penthouse);
		penthouse.setPrecio(500.00);
		penthouse.setCapacidad(4);
		penthouse.setDisponible(true);

		repo.agregarHabitacion(idHotel, penthouse);
	}

	// Método genérico para agregar cualquier habitación
	public void agregarHabitacion(String idHotel, Habitacion habitacion) {
		repo.agregarHabitacion(idHotel, habitacion);
	}

	// ------------------------
	// EJERCICIO 8: Actualizar código postal en Gran Vía
	// ------------------------
	public void actualizarCodigoPostalGranVia() {
		repo.actualizarCodigoPostalGranVia("28013");
	}

	// ------------------------
	// EJERCICIO 9: Actualizar precio habitación Individual
	// ------------------------
	public void actualizarPrecioIndividualH101() {
		repo.actualizarPrecioHabitacionIndividual("h101", 90.00);
	}

	// Método genérico
	public void actualizarPrecioHabitacionIndividual(String idHotel, double nuevoPrecio) {
		repo.actualizarPrecioHabitacionIndividual(idHotel, nuevoPrecio);
	}

	// ------------------------
	// EJERCICIO 10: Eliminar habitaciones caras
	// ------------------------
	public void eliminarHabitacionesCarasGrandHotel() {
		repo.eliminarHabitacionesCaras("Grand Hotel Central", 300.00);
	}

	// Método genérico
	public void eliminarHabitacionesCaras(String nombreHotel, double precioMaximo) {
		repo.eliminarHabitacionesCaras(nombreHotel, precioMaximo);
	}

	// ------------------------
	// EJERCICIO 11: Media de estrellas en Barcelona
	// ------------------------
	public double calcularMediaEstrellasBarcelona() {
		return repo.calcularMediaEstrellasBarcelona();
	}
}
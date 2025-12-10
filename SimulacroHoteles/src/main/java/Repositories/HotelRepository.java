package Repositories;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.AggregateIterable;
import Models.Coordenadas;
import Models.Habitacion;
import Models.Hotel;
import Models.Tipo;
import Models.Ubicacion;

public class HotelRepository {
	private static final String NOMBRE_COLECCION = "hoteles"; // Nombre de la colección de MongoDB
	private final MongoCollection<Document> coleccion;

	// Constructor del repositorio. Recibe una instancia de la base de datos de
	// MongoDB
	public HotelRepository(MongoDatabase db) {
		this.coleccion = db.getCollection(NOMBRE_COLECCION); // Obtiene la colección "hoteles" de la base de datos
	}

	// Método para convertir un documento de MongoDB a un objeto Hotel en Java

	private Hotel fromHotelDocument2Java(Document doc) {

		Hotel h = new Hotel();

		h.setIdHotel(doc.getString("idHotel"));

		h.setNombre(doc.getString("nombre"));

		h.setEstrellas(doc.getInteger("estrellas", 0));

		h.setAdmiteMascotas(doc.getBoolean("admiteMascotas", false));

		h.setFechaApertura(doc.getString("fechaApertura"));

		Document docUbicacion = doc.get("ubicacion", Document.class);

		Ubicacion u = new Ubicacion();

		u.setCalle(docUbicacion.getString("calle"));

		u.setNumero(docUbicacion.getInteger("numero", 0));

		u.setCodigoPostal(docUbicacion.getString("codigoPostal"));

		Document docCoordenadas = doc.get("coordenadas", Document.class);

		Coordenadas c = new Coordenadas();

		c.setLat(docUbicacion.getInteger("lat", 0));

		c.setLon(docUbicacion.getInteger("lon", 0));

		u.setCoordenadas(c);

		// Crear lista de documentos habitacion

		List<Document> habitacionesDocs = doc.getList("habitaciones", Document.class);

		// Crear lista de anuncios que sea añade al hotel

		List<Habitacion> habitacioneslista = new ArrayList<>();

		// Iteramos sobre cada Documento dentro de la lista

		for (Document habitacionDoc : habitacionesDocs) {

			Habitacion a = new Habitacion();

			a.setTipo(Tipo.valueOf(habitacionDoc.getString("tipo")));

			a.setCapacidad(habitacionDoc.getInteger("capacidad", 0));

			a.setDisponible(habitacionDoc.getBoolean("disponible"));

			// Usamos Double para el precio decimal

			Number precio = habitacionDoc.get("precio", Number.class);

			a.setPrecio(precio.doubleValue());

			habitacioneslista.add(a);

		}

		h.setHabitaciones(habitacioneslista);

		h.setUbicacion(u);

		return h;

	}

	public Document hoteladoc(Hotel h) {

		Document coordenadasDoc = new Document();

		if (h.getUbicacion().getCoordenadas() != null) {

			coordenadasDoc

					.append("lat", h.getUbicacion().getCoordenadas().getLat())

					.append("lon", h.getUbicacion().getCoordenadas().getLon());

		}

		Document ubicacionDoc = new Document();

		if (h.getUbicacion() != null) {

			ubicacionDoc

					.append("calle", h.getUbicacion().getCalle())

					.append("numero", h.getUbicacion().getNumero())

					.append("experiencia", h.getUbicacion().getCodigoPostal())

					.append("coordenadas", coordenadasDoc);

		}

		List<Document> habitacionesDocs = new ArrayList<>();

		if (h.getHabitaciones() != null) {

			for (Habitacion a : h.getHabitaciones()) {

				Document anuncioDoc = new Document()

						.append("tipo", a.getTipo().name()) // Usar .name() para ENUM

						.append("precio", a.getPrecio())

						.append("capacidad", a.getCapacidad())

						.append("disponible", a.isDisponible())

						.append("precio", a.getPrecio());

				habitacionesDocs.add(anuncioDoc);

			}

		}

		Document hotelDoc = new Document("idHotel", h.getIdHotel())

				.append("nombre", h.getNombre())

				.append("estrellas", h.getEstrellas())

				.append("admiteMascotas", h.isAdmiteMascotas())

				.append("fechaApertura", h.getFechaApertura())

				.append("ubicacion", ubicacionDoc)

				.append("habitaciones", habitacionesDocs);

		return hotelDoc;

	}

	// ------------------------
	// CRUD Básico
	// ------------------------

	// Insertar un nuevo hotel
	public void save(Hotel h) {
		coleccion.insertOne(hoteladoc(h)); // Inserta el documento del hotel en la colección
	}

	// Leer todos los hoteles de la base de datos
	public List<Hotel> read() {
		List<Hotel> lista = new ArrayList<>();
		FindIterable<Document> docs = coleccion.find();
		for (Document d : docs) {
			lista.add(fromHotelDocument2Java(d)); // Convierte los documentos a objetos Hotel
		}
		return lista;
	}

	// Buscar un hotel por su ID
	public Hotel getHotelPorId(String idHotel) {
		Hotel hotelId = null;
		Document encuentraHotel = new Document("idHotel", idHotel);
		Document documento = coleccion.find(encuentraHotel).first(); // Encuentra el hotel por ID
		if (documento != null) {
			hotelId = fromHotelDocument2Java(documento); // Convierte el documento a un objeto Hotel
		}
		return hotelId;
	}

	// Eliminar un hotel por su ID
	public void delete(String idHotel) {
		Document filtro = new Document("idHotel", idHotel);
		coleccion.findOneAndDelete(filtro); // Elimina el documento que coincide con el ID
	}

	// Actualizar un hotel
	public void update(Hotel h) {
		Document filtro = new Document("idHotel", h.getIdHotel());
		Document actualizacion = new Document("$set", hoteladoc(h));
		coleccion.findOneAndUpdate(filtro, actualizacion); // Actualiza el hotel con el nuevo documento
	}

	// Verificar si un hotel con el mismo ID ya existe antes de insertar
	public void crearHotel(Hotel h) {
		Document filtro = new Document("idHotel", h.getIdHotel());
		Document existente = coleccion.find(filtro).first(); // Verifica si el hotel ya existe
		if (existente == null) {
			coleccion.insertOne(hoteladoc(h)); // Si no existe, lo inserta
		}
	}

	// ------------------------
	// Ejercicios
	// ------------------------

	// Buscar hoteles en Madrid con 5 estrellas o que admitan mascotas
	public List<Hotel> buscarHotelesMadrid() {
		List<Hotel> hotelesFiltrados = new ArrayList<>();

		// Filtra por código postal de Madrid (empieza con "28") y cumple las
		// condiciones adicionales
		Document filtro = new Document("$and", List.of(
				new Document("$or",
						List.of(new Document("ubicacion.codigoPostal", new Document("$regex", "^28")),
								new Document("ubicacion.codigoPostal",
										new Document("$gte", 28000).append("$lte", 28999)))),
				new Document("$or", List.of(new Document("estrellas", 5), new Document("admiteMascotas", true)))));

		FindIterable<Document> documentos = coleccion.find(filtro);
		for (Document doc : documentos) {
			hotelesFiltrados.add(fromHotelDocument2Java(doc)); // Convierte cada documento a un hotel
		}
		return hotelesFiltrados;
	}

	// Contar el número de hoteles con al menos una habitación tipo "Suite Junior"
	public int numHotelesConSuiteJunior() {
		int contador = 0;
		FindIterable<Document> documentos = coleccion.find();
		for (Document doc : documentos) {
			List<Document> habitacionesDocs = doc.getList("habitaciones", Document.class);
			boolean tieneSuite = false;

			// Recorre las habitaciones buscando una "Suite Junior"
			for (Document habDoc : habitacionesDocs) {
				String tipo = habDoc.getString("tipo");
				if ("Suite Junior".equalsIgnoreCase(tipo) || "SUITE_JUNIOR".equalsIgnoreCase(tipo)) {
					tieneSuite = true;
					break;
				}
			}

			if (tieneSuite) {
				contador++; // Si tiene una Suite Junior, incrementa el contador
			}
		}
		return contador;
	}

	// Añadir una nueva habitación (Penthouse) a un hotel
	public void agregarHabitacion(String idHotel, Habitacion nuevaHabitacion) {
		Document filtro = new Document("idHotel", idHotel);
		Document habitacionDoc = new Document().append("tipo", nuevaHabitacion.getTipo().toString())
				.append("precio", nuevaHabitacion.getPrecio()).append("capacidad", nuevaHabitacion.getCapacidad())
				.append("disponible", nuevaHabitacion.isDisponible());

		Document actualizacion = new Document("$push", new Document("habitaciones", habitacionDoc));
		coleccion.updateOne(filtro, actualizacion); // Añade la nueva habitación
	}

	// Actualizar código postal de hoteles en Gran Vía
	public void actualizarCodigoPostalGranVia(String nuevoCodigoPostal) {
		Document filtro = new Document("ubicacion.calle", "Gran Vía");
		Document actualizacion = new Document("$set", new Document("ubicacion.codigoPostal", nuevoCodigoPostal));
		coleccion.updateMany(filtro, actualizacion); // Actualiza todos los hoteles en Gran Vía
	}

	// Actualizar el precio de la habitación "Individual" en el hotel con ID "h101"
	public void actualizarPrecioHabitacionIndividual(String idHotel, double nuevoPrecio) {
		Document filtro = new Document("idHotel", idHotel).append("$or", List
				.of(new Document("habitaciones.tipo", "Individual"), new Document("habitaciones.tipo", "INDIVIDUAL")));

		Document actualizacion = new Document("$set", new Document("habitaciones.$.precio", nuevoPrecio));
		coleccion.updateOne(filtro, actualizacion); // Actualiza el precio de la habitación
	}

	// Eliminar habitaciones con un precio superior a 300 del hotel "Grand Hotel
	// Central"
	public void eliminarHabitacionesCaras(String nombreHotel, double precioMaximo) {
		Document filtro = new Document("nombre", nombreHotel);
		Document actualizacion = new Document("$pull",
				new Document("habitaciones", new Document("precio", new Document("$gt", precioMaximo))));
		coleccion.updateOne(filtro, actualizacion); // Elimina las habitaciones caras
	}

	// Calcular la media de estrellas de los hoteles en Barcelona
	public double calcularMediaEstrellasBarcelona() {
		List<Document> pipeline = List.of(
				new Document("$match", new Document("ubicacion.codigoPostal", new Document("$regex", "^08"))),
				new Document("$group",
						new Document("_id", null).append("mediaEstrellas", new Document("$avg", "$estrellas"))));

		AggregateIterable<Document> resultado = coleccion.aggregate(pipeline);
		Document doc = resultado.first();

		if (doc != null && doc.containsKey("mediaEstrellas")) {
			Object media = doc.get("mediaEstrellas");
			if (media instanceof Number) {
				return ((Number) media).doubleValue();
			}
		}
		return 0.0;
	}

	/*
	 * - **$eq**: Compara si los valores son iguales. Ejemplo: {"edad": {$eq: 25}}
	 * busca documentos donde "edad" sea igual a 25. - **$gt**: Compara si un valor
	 * es mayor que el valor especificado. Ejemplo: {"edad": {$gt: 18}} busca
	 * documentos donde "edad" sea mayor que 18. - **$gte**: Compara si un valor es
	 * mayor o igual al especificado. Ejemplo: {"edad": {$gte: 18}} busca documentos
	 * donde "edad" sea mayor o igual a 18. - **$in**: Compara si el valor está
	 * dentro de un conjunto de valores especificado. Ejemplo: {"edad": {$in: [18,
	 * 20, 25]}} busca documentos donde "edad" sea uno de los valores listados (18,
	 * 20, 25). - **$lt**: Compara si un valor es menor que el valor especificado.
	 * Ejemplo: {"edad": {$lt: 30}} busca documentos donde "edad" sea menor que 30.
	 * - **$lte**: Compara si un valor es menor o igual al especificado. Ejemplo:
	 * {"edad": {$lte: 30}} busca documentos donde "edad" sea menor o igual a 30. -
	 * **$ne**: Compara si el valor no es igual al valor especificado. Ejemplo:
	 * {"edad": {$ne: 30}} busca documentos donde "edad" no sea igual a 30. -
	 * **$nin**: Compara si el valor no está dentro de un conjunto de valores
	 * especificado. Ejemplo: {"edad": {$nin: [18, 20, 25]}} busca documentos donde
	 * "edad" no sea ninguno de los valores listados.
	 */

}

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

	private static final String NOMBRE_COLECCION = "hoteles";
	private final MongoCollection<Document> coleccion;

	public HotelRepository(MongoDatabase db) {
		this.coleccion = db.getCollection(NOMBRE_COLECCION);
	}

	// ------------------------
	// CONVERSIÓN DOCUMENT -> JAVA
	// ------------------------
	private Hotel fromDocumentToHotel(Document doc) {
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
	    
	    // Manejar codigoPostal que puede venir como String o Integer
	    try {
	        u.setCodigoPostal(docUbicacion.getInteger("codigoPostal"));
	    } catch (ClassCastException e) {
	        // Si es String, convertir a Integer
	        u.setCodigoPostal(Integer.parseInt(docUbicacion.getString("codigoPostal")));
	    }

	    Document docCoord = docUbicacion.get("coordenadas", Document.class);
	    Coordenadas c = new Coordenadas();
	    
	    // Manejar coordenadas que pueden venir como Integer o Double
	    Number lat = docCoord.get("lat", Number.class);
	    Number lon = docCoord.get("lon", Number.class);
	    
	    c.setLat(lat != null ? lat.doubleValue() : 0.0);
	    c.setLon(lon != null ? lon.doubleValue() : 0.0);

	    u.setCoordenadas(c);
	    h.setUbicacion(u);

	    // ---- Habitaciones ----
	    List<Document> habsDocs = doc.getList("habitaciones", Document.class);
	    List<Habitacion> habitaciones = new ArrayList<>();

	    if (habsDocs != null) {
	        for (Document habDoc : habsDocs) {
	            Habitacion hab = new Habitacion();
	            
	            // Manejar el tipo de habitación
	            String tipoStr = habDoc.getString("tipo");
	            if (tipoStr != null) {
	                // Convertir diferentes formatos a enum
	                tipoStr = tipoStr.toUpperCase()
	                    .replace(" ", "_")
	                    .replace("Á", "A")
	                    .replace("É", "E")
	                    .replace("ESTÁNDAR", "ESTANDAR");
	                
	                try {
	                    hab.setTipo(Tipo.valueOf(tipoStr));
	                } catch (IllegalArgumentException e) {
	                    // Si no existe el enum, asignar un valor por defecto
	                    System.err.println("Tipo de habitación desconocido: " + tipoStr);
	                    hab.setTipo(Tipo.INDIVIDUAL);
	                }
	            }
	            
	            Number precio = habDoc.get("precio", Number.class);
	            hab.setPrecio(precio != null ? precio.doubleValue() : 0.0);
	            hab.setCapacidad(habDoc.getInteger("capacidad", 0));
	            hab.setDisponible(habDoc.getBoolean("disponible", false));
	            habitaciones.add(hab);
	        }
	    }

	    h.setHabitaciones(habitaciones);

	    return h;
	}

	// ------------------------
	// CONVERSIÓN JAVA -> DOCUMENT
	// ------------------------
	private Document hotelToDocument(Hotel h) {
		Document coord = new Document()
			.append("lat", h.getUbicacion().getCoordenadas().getLat())
			.append("lon", h.getUbicacion().getCoordenadas().getLon());

		Document ubicacion = new Document()
			.append("calle", h.getUbicacion().getCalle())
			.append("numero", h.getUbicacion().getNumero())
			.append("codigoPostal", h.getUbicacion().getCodigoPostal())
			.append("coordenadas", coord);

		List<Document> habitacionesDocs = new ArrayList<>();

		if (h.getHabitaciones() != null) {
			for (Habitacion hab : h.getHabitaciones()) {
				Document habDoc = new Document()
					.append("tipo", hab.getTipo())
					.append("precio", hab.getPrecio())
					.append("capacidad", hab.getCapacidad())
					.append("disponible", hab.isDisponible());
				habitacionesDocs.add(habDoc);
			}
		}

		Document hotel = new Document("idHotel", h.getIdHotel())
			.append("nombre", h.getNombre())
			.append("estrellas", h.getEstrellas())
			.append("admiteMascotas", h.isAdmiteMascotas())
			.append("fechaApertura", h.getFechaApertura())
			.append("ubicacion", ubicacion)
			.append("habitaciones", habitacionesDocs);

		return hotel;
	}

	// ------------------------
	// CRUD BÁSICO
	// ------------------------
	public void save(Hotel h) {
		coleccion.insertOne(hotelToDocument(h));
	}

	public List<Hotel> read() {
		List<Hotel> lista = new ArrayList<>();
		FindIterable<Document> docs = coleccion.find();

		for (Document d : docs) {
			lista.add(fromDocumentToHotel(d));
		}
		return lista;
	}

	public Hotel getHotelPorId(String idHotel) {
		Hotel hotelId = null;
		Document encuentraHotel = new Document("idHotel", idHotel);
		Document documento = coleccion.find(encuentraHotel).first();

		if (documento != null) {
			hotelId = fromDocumentToHotel(documento);
		}

		return hotelId;
	}

	public void delete(String idHotel) {
		Document filtro = new Document("idHotel", idHotel);
		coleccion.findOneAndDelete(filtro);
	}

	public void update(Hotel h) {
		Document filtro = new Document("idHotel", h.getIdHotel());
		Document actualizacion = new Document("$set", hotelToDocument(h));
		coleccion.findOneAndUpdate(filtro, actualizacion);
	}

	public void crearHotel(Hotel h) {
		Document filtro = new Document("idHotel", h.getIdHotel());
		Document existente = coleccion.find(filtro).first();

		if (existente == null) {
			coleccion.insertOne(hotelToDocument(h));
		}
	}

	// ------------------------
	// EJERCICIO 5: Hoteles en Madrid con 5 estrellas O que admitan mascotas
	// ------------------------
	public List<Hotel> buscarHotelesMadrid() {
		List<Hotel> hotelesFiltrados = new ArrayList<>();

		// Madrid: códigos postales que empiezan por "28"
		Document filtro = new Document("$and", List.of(
			new Document("$or", List.of(
				// Si codigoPostal es String
				new Document("ubicacion.codigoPostal", new Document("$regex", "^28")),
				// Si codigoPostal es Integer
				new Document("ubicacion.codigoPostal", 
					new Document("$gte", 28000).append("$lte", 28999))
			)),
			new Document("$or", List.of(
				new Document("estrellas", 5),
				new Document("admiteMascotas", true)
			))
		));

		FindIterable<Document> documentos = coleccion.find(filtro);

		for (Document doc : documentos) {
			hotelesFiltrados.add(fromDocumentToHotel(doc));
		}

		return hotelesFiltrados;
	}

	// ------------------------
	// EJERCICIO 6: Número de hoteles con al menos una Suite Junior
	// ------------------------
	public int numHotelesConSuiteJunior() {
		int contador = 0;
		FindIterable<Document> documentos = coleccion.find();

		for (Document doc : documentos) {
			List<Document> habitacionesDocs = doc.getList("habitaciones", Document.class);
			boolean tieneSuite = false;

			if (habitacionesDocs != null) {
				for (Document habDoc : habitacionesDocs) {
					String tipo = habDoc.getString("tipo");
					// Buscar tanto "Suite Junior" como "SUITEJUNIOR"
					if ("Suite Junior".equals(tipo) || 
					    "SUITEJUNIOR".equalsIgnoreCase(tipo) ||
					    "SUITE_JUNIOR".equalsIgnoreCase(tipo)) {
						tieneSuite = true;
						break;
					}
				}
			}

			if (tieneSuite) {
				contador++;
			}
		}

		return contador;
	}

	// ------------------------
	// EJERCICIO 7: Añadir nueva habitación Penthouse a un hotel
	// ------------------------
	public void agregarHabitacion(String idHotel, Habitacion nuevaHabitacion) {
		Document filtro = new Document("idHotel", idHotel);
		
		Document habitacionDoc = new Document()
			.append("tipo", nuevaHabitacion.getTipo().toString())
			.append("precio", nuevaHabitacion.getPrecio())
			.append("capacidad", nuevaHabitacion.getCapacidad())
			.append("disponible", nuevaHabitacion.isDisponible());

		Document actualizacion = new Document("$push", 
			new Document("habitaciones", habitacionDoc));

		coleccion.updateOne(filtro, actualizacion);
	}

	// ------------------------
	// EJERCICIO 8: Actualizar código postal de hoteles en Gran Vía
	// ------------------------
	public void actualizarCodigoPostalGranVia(String nuevoCodigoPostal) {
		Document filtro = new Document("ubicacion.calle", "Gran Vía");
		Document actualizacion = new Document("$set", 
			new Document("ubicacion.codigoPostal", nuevoCodigoPostal));

		coleccion.updateMany(filtro, actualizacion);
	}

	// ------------------------
	// EJERCICIO 9: Actualizar precio de habitación Individual en hotel h101
	// ------------------------
	public void actualizarPrecioHabitacionIndividual(String idHotel, double nuevoPrecio) {
		// Buscar por diferentes formatos de "Individual"
		Document filtro = new Document("idHotel", idHotel)
			.append("$or", List.of(
				new Document("habitaciones.tipo", "Individual"),
				new Document("habitaciones.tipo", "INDIVIDUAL")
			));

		Document actualizacion = new Document("$set", 
			new Document("habitaciones.$.precio", nuevoPrecio));

		coleccion.updateOne(filtro, actualizacion);
	}

	// ------------------------
	// EJERCICIO 10: Eliminar habitaciones con precio > 300 del Grand Hotel Central
	// ------------------------
	public void eliminarHabitacionesCaras(String nombreHotel, double precioMaximo) {
		Document filtro = new Document("nombre", nombreHotel);
		
		Document actualizacion = new Document("$pull", 
			new Document("habitaciones", 
				new Document("precio", new Document("$gt", precioMaximo))));

		coleccion.updateOne(filtro, actualizacion);
	}

	// ------------------------
	// EJERCICIO 11: Calcular media de estrellas en Barcelona
	// ------------------------
	public double calcularMediaEstrellasBarcelona() {
		List<Document> pipeline = List.of(
			new Document("$match", 
				new Document("$or", List.of(
					// Si codigoPostal es String
					new Document("ubicacion.codigoPostal", new Document("$regex", "^08")),
					// Si codigoPostal es Integer
					new Document("ubicacion.codigoPostal", 
						new Document("$gte", 8000).append("$lte", 8999))
				))
			),
			new Document("$group", 
				new Document("_id", null)
					.append("mediaEstrellas", new Document("$avg", "$estrellas")))
		);

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
}
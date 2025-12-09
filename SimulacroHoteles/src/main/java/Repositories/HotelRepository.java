package Repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Models.Club;
import Models.Coordenadas;
import Models.Habitacion;
import Models.Hotel;
import Models.Ubicacion;

public class HotelRepository {
private static final String NOMBRE_COLECCION = "hoteles";
private final MongoCollection<Document> coleccion;
private List<Hotel> hoteles;


public HotelRepository(MongoDatabase db) {
	super();
	this.coleccion = db.getCollection(NOMBRE_COLECCION, Document.class);
	this.hoteles = hoteles;
}

public List<Hotel> getHoteles() {
	return hoteles;
}

public void setHoteles(List<Hotel> hoteles) {
	this.hoteles = hoteles;
}

private Hotel fromHotelDocument2Java(Document doc) {
	Hotel h = new Hotel();
	h.setIdHotel(doc.getString("idHotel"));
	h.setNombre(doc.getString("nombre"));
	h.setEstrellas(doc.getInteger("estrellas", 0));
	h.setAdmiteMascotas(doc.getBoolean("admiteMascotas", false));
	h.setFechaApertura(doc.getString("fechaApertura"));
	List<Habitacion> habitaciones = doc.getList("habitaciones", Habitacion.class);
	h.setHabitaciones(habitaciones != null ? habitaciones: new ArrayList<>());
	

	Ubicacion u = new Ubicacion();
	u.setCalle(doc.getString("calle"));
	u.setNumero(doc.getInteger("numero", 0));
	u.setCodigoPostal(doc.getInteger("codigo postal", 0));
	
	Coordenadas c = new Coordenadas();
	c.setLat(doc.getInteger("lat",0));
	c.setLon(doc.getInteger("lon", 0));
	h.setUbicacion(u);
	return h;
}
private Document HotelADoc(Hotel h) {
	List<Document> habitacionDocs = new ArrayList<>();
	for(Habitacion n: h.getHabitaciones()) {
		Document habDocs = new Document("tipo", n.getTipo()).append("precio", n.getPrecio()).append("capacidad", n.getCapacidad()).append("disponible", n.isDisponible());
		habitacionDocs.add(habDocs);
	
	}

	Document coordenadasDoc = new Document("lat",h.getUbicacion().getCoordenadas().getLat()).append("lon", h.getUbicacion().getCoordenadas().getLon());
	Document ubicacionDoc = new Document("calle", h.getUbicacion().getCalle()).append("numero", h.getUbicacion().getNumero()).append("coordenadas", coordenadasDoc);
	Document hotelDocs = new Document("idHotel", h.getIdHotel()).append("nombre", h.getNombre()).append("estrellas", h.getEstrellas()).append("admiteMascotas", h.isAdmiteMascotas()).append("fechaApertura", h.getFechaApertura()).append("ubicacion", ubicacionDoc).append("habitaciones", habitacionDocs);
	
	return hotelDocs;

}
private void save(Hotel h) {
	coleccion.insertOne(HotelADoc(h));

}
private List<Hotel> read(){
	List<Hotel> listaHotel = new ArrayList();
	FindIterable<Document> docs = coleccion.find();
	for (Document document : docs) {
		Hotel h = fromHotelDocument2Java(document);
		listaHotel.add(h);
	}
	return listaHotel;
}
public Hotel crearHotel(Hotel nuevoHotel) {
	boolean encontrado = false;
	Iterator<Hotel> it = hoteles.iterator();
	while (it.hasNext() && !encontrado) {
		Hotel h = it.next();
		if (h.getIdHotel()== nuevoHotel.getIdHotel()) {
			encontrado = true;
		}
	}

	if (!encontrado) {
		save(nuevoHotel);
	}

	return nuevoHotel;
}
public void borrarHotel(String id) {
	Document encuentraHotel = new Document("idHotel", id);
	coleccion.findOneAndDelete(encuentraHotel);
}

}

